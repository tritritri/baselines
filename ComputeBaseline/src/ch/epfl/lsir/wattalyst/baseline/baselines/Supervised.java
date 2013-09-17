package ch.epfl.lsir.wattalyst.baseline.baselines;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.TimeZone;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;
import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.baseline.util.Util;
import ch.epfl.lsir.wattalyst.webserver.WebserverDataWriter;

/**
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.28
 *
 */
public class Supervised implements Baseline{

	private Calendar startCal;
	private Calendar endCal;
	
	// choose Y days for training
	private int WEEKDAY_Y;
	private int WEEKEND_Y;

	// for features contruction: the number of historical days 
	private int WEEKDAY_LAG;
	private int WEEKEND_LAG;

	// data structure for target variables / baseline to compute
	private SensorReadings[] data; // original data
	
	// data structure for features/context
	private SensorReadings context;
	
	private String contextFileName;
	private String algFileName;
	private String dataFileName;
	
	// list of days to be excluded from historical computation
	private HashMap<Long,Byte> exclDays;  

	private double minValue;
	
	private boolean inputHistory;
	private int historyAccess;
	
	public Supervised(){
		
		startCal = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
		endCal = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
		exclDays = null;
		
		context = new SensorReadings();
		data = new SensorReadings[2];
		data[0] = new SensorReadings();
		data[1] = new SensorReadings();
	
		WEEKDAY_Y = 20;
		WEEKEND_Y = 10;
		
		// number of days we should look back
		WEEKDAY_LAG = 5;
		WEEKEND_LAG = 2;
				
		inputHistory = false;
		historyAccess = 1;
	}
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public void compute(String input, String startDate, String endDate){
		
		try {

			// get all configuration for regression
			Properties prop = new Properties();
			prop.load(new FileInputStream(input));
			
			// get files for energy, context (temperature), and algorithm 
			this.dataFileName = prop.getProperty("energy-file");
			this.contextFileName = prop.getProperty("temperature-file");
			this.algFileName = prop.getProperty("algorithm-file");
			
			// get features parameter (for learning)
			this.WEEKDAY_Y = Integer.parseInt(prop.getProperty("history-weekday"));
			this.WEEKEND_Y = Integer.parseInt(prop.getProperty("history-weekend"));
			this.WEEKDAY_LAG = Integer.parseInt(prop.getProperty("lag-weekday"));
			this.WEEKEND_LAG = Integer.parseInt(prop.getProperty("lag-weekend"));
			
			// set the minimum reading value required
			this.minValue = Integer.parseInt(prop.getProperty("min-value-allowed"));
			
			// set start and end target date
			SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
			formatter.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));

			startCal.setTime(formatter.parse(startDate));
			Util.setToTheBeginningOfTheDay(startCal);
			endCal.setTime(formatter.parse(endDate));
			Util.setToTheEndOfTheDay(endCal);
			
			// read the target data, in this case: the energy sensors
			Util.hourlyCSVToSensorReadings(dataFileName, data[0]);

			// copy the original data for the baseline calculation
			data[0].copyHourly(data[0].getMinDate(), data[0].getMaxDate(), data[1]);

			
			// read the context (feature) data, in this case: temperature
			if (this.contextFileName!=null)
				Util.hourlyCSVToSensorReadings(contextFileName, context);
			
			// process the baseline
			// prevStartDate = startDate - 1, hour 23 (end of day) 
			Calendar lastNeeded = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
			lastNeeded.setTime(startCal.getTime());
			lastNeeded.add(Calendar.HOUR_OF_DAY, -1);

			Calendar computeCal = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
			
			if ( data[0].getMaxDate() >= lastNeeded.getTimeInMillis() ) {
				// if prevStartDate is exist in database, then fine.
				computeCal.setTimeInMillis(startCal.getTimeInMillis());				
			} else {
				// otherwise, compute endHourOfDatabasewhen until prevStartDate
				// compute from startDate, hour 0 until endDate hour 23
				computeCal.setTimeInMillis(data[0].getMaxDate());
				SimpleDateFormat formatOutput = new SimpleDateFormat(Constants.DATETIME_FORMAT);
				formatOutput.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));

				System.err.println("[WARNING] Data from " + formatOutput.format(new Date(data[0].getMaxDate())) 
				+ " to " + formatOutput.format(startCal.getTime()) + " in " + dataFileName 
				+" is not available. " +
				"Estimation will be done for that period using the specified baseline method.");
			}
			
			// start to compute the baseline
			Util.setToTheBeginningOfTheDay(computeCal);

			// loop to get the baseline
			while (!computeCal.after(endCal)){
				ComputeBaselineOneDayHourly(computeCal);
				computeCal.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			// done computing baseline


		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}

	}

	private void ComputeBaselineOneDayHourly(Calendar targetDay) {
		
		if (Constants.VERBOSE==1)
		System.out.println("[ComputeBaselineOneDayHourly] " + targetDay.getTime());
		// get historical data (except for the target day (of course))
		ArrayList<Calendar> trainingCals = getTrainingDays(targetDay);
		
		// hourly 
		for (int h=0; h<24; h++){			
			ComputeBaselineOneHour(trainingCals, targetDay, h);
		}
		
	}

	
	private void ComputeBaselineOneHour(ArrayList<Calendar> trainingCals, Calendar targetDay, int targetH) {
		
		if (Constants.VERBOSE==1){
			System.out.println("\n==========================");
			System.out.println(" Target days: "+targetDay.getTime());
			System.out.println(" target hour: "+ targetH);
			for (int i=0; i<trainingCals.size();i++){
				System.out.println(" historical date: "+trainingCals.get(i).getTime());
			}			
		}

		// create the attributes (lag attributes and target attribute)		
		int numLag = getLagDOWType(Util.getDOWType(targetDay));
		FastVector fvWekaAttrs = createWekaAttrs(numLag);
		
		// create the training set
		Instances trainingSet = createWekaTrainingSet(trainingCals, targetDay, targetH, fvWekaAttrs);
		if (Constants.VERBOSE==1) System.out.println(trainingSet.toString());
		
		try {
			// read the algorithm
			BufferedReader fileAlgorithm = new BufferedReader(new FileReader(this.algFileName));
			String line = fileAlgorithm.readLine();
			String[] params = Utils.splitOptions(line);
			String algorithmName = params[0];
			params[0] = "";
			fileAlgorithm.close();
			
			// set the classifier
			Classifier c = (Classifier) Utils.forName(Classifier.class, algorithmName, params);
		    c.buildClassifier(trainingSet);
		    if (Constants.VERBOSE==1) System.out.println(c.toString());

			// create a new instance to classify
		    int numCols = numLag*2+2;
		    if (this.contextFileName==null) numCols = numLag+1;
			Instance newInst = new Instance(numCols);
			for (int j=0; j<numLag; j++) {
				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIMEZONE_REF)); 
				cal.setTimeInMillis(trainingCals.get(numLag-j-1).getTimeInMillis());
				cal.set(Calendar.HOUR_OF_DAY, targetH);

				// put load historical data
				newInst.setValue((Attribute)fvWekaAttrs.elementAt(j), data[historyAccess].get(cal.getTimeInMillis()));
				
				// put temp historical data
				if (this.contextFileName!=null) 
					newInst.setValue((Attribute)fvWekaAttrs.elementAt(j+numLag), context.get(cal.getTimeInMillis()));
			}

			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIMEZONE_REF)); 
			cal.setTimeInMillis(targetDay.getTimeInMillis());
			cal.set(Calendar.HOUR_OF_DAY, targetH);

			// put current temp
			if (this.contextFileName!=null) {
				if (context.get(cal.getTimeInMillis()) == null){
					SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATETIME_FORMAT);
					formatter.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));

					System.err.println("[ERROR] [Regression] There is no context data in "+this.contextFileName+ " for "+formatter.format(cal.getTime())+".");
					System.exit(1);
				}
				newInst.setValue((Attribute)fvWekaAttrs.elementAt(numLag*2), context.get(cal.getTimeInMillis()));
			}
			
			// put dummy value on the target value			
			newInst.setValue((Attribute)fvWekaAttrs.elementAt(numCols-1), 0);
			if (Constants.VERBOSE==1) System.out.println("new instance: " + newInst.toString());
			
			// get prediction value
			double cPredict = c.classifyInstance(newInst);
			
			// fix to min value
			if (cPredict < this.minValue) cPredict = this.minValue;				

			if (Constants.VERBOSE==1) System.out.println("Predicted value: "+cPredict);
			
			// store the result (predicted value) 
			Util.setToTheBeginningOfTheHour(cal);			
			data[1].insert(cal.getTimeInMillis(), cPredict);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Creating all training set needed
	 * The dataset is taken from trainingCals
	 * @param trainingCals
	 * @param targetCal
	 * @param targetH
	 * @param fvWekaAttrs
	 * @return
	 */
	private Instances createWekaTrainingSet(ArrayList<Calendar> trainingCals, Calendar targetCal, int targetH, FastVector fvWekaAttrs) {
		
		//int currY = getYDOWType(Util.getDOWType(targetCal));
		int currY = trainingCals.size();
		//System.out.println("TrainingCal size: "+currY);
		int numLag = getLagDOWType(Util.getDOWType(targetCal));		

		int numTrain = currY - numLag;
		Instances trainingSet = new Instances("Training", fvWekaAttrs, numTrain);
		trainingSet.setClassIndex(fvWekaAttrs.size()-1);
		
		// create #(trainingCals.size()-currZ) training instances
		for (int i=trainingCals.size()-numLag-1; i>=0; i--){
			Instance inst = createWekaInstanceForTargetIdx(trainingCals, i, targetH, numLag, fvWekaAttrs);
			trainingSet.add(inst);
		}
		
		return trainingSet;

	}

	/**
	 * Create an instance of the training set
	 * @param trainingCals an array of date included in the whole training set. The date involved in this instance
	 * is a subset of all dates in trainingCals
	 * @param targetIdx is a index of element in the trainingCals (so, it is the date) for a target attribute
	 * @param targetH and this is the target hour
	 * @param numLag how many lags (historical day) taken into account for an instance
	 * @param fvWekaAttrs the instance are made w.r.t to this weka attributes
	 * @return
	 */
	private Instance createWekaInstanceForTargetIdx(ArrayList<Calendar> trainingCals, int targetIdx, int targetH, int numLag, FastVector fvWekaAttrs) {
		// currZ historical load, currZ historical temp, 1 curr temp, 1 target attr (curr load) 			
		int numCols = numLag*2+2;
		if ( this.contextFileName==null ) numCols = numLag+1;
		Instance inst = new Instance(numCols);
			
		// put historical load and temperature
		for (int j=0; j<numLag; j++) {
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIMEZONE_REF)); 
			cal.setTimeInMillis(trainingCals.get(targetIdx+numLag-j).getTimeInMillis());
			cal.set(Calendar.HOUR_OF_DAY, targetH);
			
			// check if we miss some data
			if (data[historyAccess].get(cal.getTimeInMillis())==null) {
				System.err.println("[Error] Entry for "+cal.getTime()+" is not found in "+this.dataFileName);
				System.exit(0);
			}
			inst.setValue((Attribute)fvWekaAttrs.elementAt(j), data[historyAccess].get(cal.getTimeInMillis()));
			
			if (this.contextFileName!=null) {
				if (context.get(cal.getTimeInMillis())==null) {
					System.err.println("[Error] Entry for "+cal.getTime()+" is not found in "+this.contextFileName);
					System.exit(0);
				}			
				inst.setValue((Attribute)fvWekaAttrs.elementAt(j+numLag), context.get(cal.getTimeInMillis()));
			}
		}

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIMEZONE_REF)); 
		cal.setTimeInMillis(trainingCals.get(targetIdx).getTimeInMillis());
		cal.set(Calendar.HOUR_OF_DAY, targetH);
		
		// put current temperature
		if (this.contextFileName!=null) {
			if (context.get(cal.getTimeInMillis())==null) {
				System.err.println("[Error] Entry for "+cal.getTime()+" is not found in "+this.contextFileName);
				System.exit(0);
			}
			inst.setValue((Attribute)fvWekaAttrs.elementAt(numLag*2), context.get(cal.getTimeInMillis()));
		}
		
		// put current load
		if (data[historyAccess].get(cal.getTimeInMillis())==null) {
			System.err.println("[Error] Entry for "+cal.getTime()+" is not found in "+this.dataFileName);
			System.exit(0);
		}
		
		inst.setValue((Attribute)fvWekaAttrs.elementAt(numCols-1), data[historyAccess].get(cal.getTimeInMillis()));

		return inst;
	}

	
	/**
	 * Create a set of weka attributes (columns)
	 * @param lagLen
	 * @return
	 */
	private FastVector createWekaAttrs(int lagLen){
		// # historical load = lagLen
		// # historical temperature = lagLen
		// # current temperature = 1
		// # target attr = 1
		// Declare 'lagLen*2 + 2' numeric attributes
		FastVector fvWekaAttrs = new FastVector(lagLen*2 + 2);
		
		for (int i=0; i<lagLen; i++){
			Attribute attr = new Attribute("load_d-"+(lagLen-i));
			fvWekaAttrs.addElement(attr);
		}
		
		if (this.contextFileName!=null) {
			for (int i=0; i<lagLen; i++){
				Attribute attr = new Attribute("temp_d-"+(lagLen-i));
				fvWekaAttrs.addElement(attr);
			}

			Attribute attrT = new Attribute("temp_d+0");
			fvWekaAttrs.addElement(attrT);
		}
		
		Attribute attrL = new Attribute("load_target");
		fvWekaAttrs.addElement(attrL);
		
		return fvWekaAttrs;
	}
	
	
	private ArrayList<Calendar> getTrainingDays(Calendar targetCal) {

		// array to store calendars to be used for computing the baselines
		ArrayList<Calendar> result = new ArrayList<Calendar>();

		long Y = getYDOWType(Util.getDOWType(targetCal));
		
		// 0 means we use all history available
		if (Y==0){
			
			Calendar minCal = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
			
			minCal.setTimeInMillis(data[0].getMinDate());
			long diff = targetCal.getTimeInMillis() - minCal.getTimeInMillis();			
			
			Y = diff/1000/3600/24; 
			// System.out.println("Target: "+targetCal.getTime()+", MinCal: "+minCal.getTime()+", diff: "+Y);
		}
		// calendar for loop 
		Calendar tempCal = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
		tempCal.setTimeInMillis(targetCal.getTimeInMillis());
		long count = 0;
		while (count < Y ){			
			// backward one day
			tempCal.add(Calendar.DAY_OF_MONTH, -1);
			
			if (Util.getDOWType(tempCal) == Util.getDOWType(targetCal)) {

				// test if tempCal is not in exclDays
				if (exclDays != null ){
					if ( exclDays.containsKey(tempCal.getTimeInMillis()) ){
						continue;
					}
				}

				// store the calendar
				Calendar source = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
				source.setTimeInMillis(tempCal.getTimeInMillis());	
				result.add(source);	
			
			}
			count ++;
		}
		if (Constants.VERBOSE==1) {
			for (int i=0; i<result.size(); i++){
				System.out.println("days selected "+i+": "+result.get(i).getTime());
			}
		}
		
		return result;
	}

	private int getYDOWType(int dowType) {		
		if (dowType == Util.WEEKDAY ) {
			return WEEKDAY_Y;
		} else {
			return WEEKEND_Y;
		}
	}

	private int getLagDOWType(int dowType) {
		if (dowType == Util.WEEKDAY ) {
			return WEEKDAY_LAG;
		} else {
			return WEEKEND_LAG;
		}
	}

	
	@Override
	public void writeResult(PrintStream out) {

		out.print(data[1].toStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis()));

	}


	@Override
	public void writeResultToFile(String fileName) {
		PrintWriter fileOut;
		try {
			fileOut = new PrintWriter(fileName);
			fileOut.print(data[1].toStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis()));
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void writeResultToWattalystDB(String baselineID) {
		WebserverDataWriter writer = new WebserverDataWriter();
		writer.updateBaselineData(baselineID, data[1]);
	}

	@Override
	public ArrayList<String> getResultString() {
		return data[1].toArrStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis());
		
	}

	@Override
	public void compute(String fileInput, String startDate, String endDate,	HashMap<Long, Byte> exclDays) {
		
		this.exclDays = exclDays;
		compute(fileInput, startDate, endDate);						

	}

	@Override
	public void setInputHistoryOption(boolean flag) {
		inputHistory = flag;
		if (inputHistory == true) {
			// if inputHistory is activated, 
			// we access historical data directly from the input 
			historyAccess = 0;
		} else {
			// otherwise, for baseline calculation more than one day,
			// we access historical data from previous baseline calculation 
			historyAccess = 1;
		}
	}

}
