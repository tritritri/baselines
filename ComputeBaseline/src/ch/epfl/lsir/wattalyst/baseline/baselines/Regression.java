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
import java.util.Properties;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;
import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.baseline.util.Util;

/**
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.28
 *
 */
public class Regression implements Baseline{

	private Calendar startCal;
	private Calendar endCal;
	
	// choose Y days for training
	private int WEEKDAY_Y;
	private int WEEKEND_Y;

	// for features contruction: the number of historical days 
	private int WEEKDAY_LAG;
	private int WEEKEND_LAG;

	// data structure for target variables / baseline to compute
	private SensorReadings data; // original data
	private SensorReadings baseline; // will be developed as the baseline
	
	// data structure for features/context
	private SensorReadings temp;
	
	private String contextFileName;
	private String algFileName;
	
	private int VERBOSE = 0;
	
	public Regression(){
		
		startCal = Calendar.getInstance();
		endCal = Calendar.getInstance();
		
		temp = new SensorReadings();
		data = new SensorReadings();
		baseline = new SensorReadings();
	
		WEEKDAY_Y = 20;
		WEEKEND_Y = 10;
		
		// number of days we should look back
		WEEKDAY_LAG = 5;
		WEEKEND_LAG = 2;
				
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
			String dataFileName = prop.getProperty("energy-file");
			this.contextFileName = prop.getProperty("temperature-file");
			this.algFileName = prop.getProperty("algorithm-file");
			
			// get features parameter (for learning)
			this.WEEKDAY_Y = Integer.parseInt(prop.getProperty("history-weekday"));
			this.WEEKEND_Y = Integer.parseInt(prop.getProperty("history-weekend"));
			this.WEEKDAY_LAG = Integer.parseInt(prop.getProperty("lag-weekday"));
			this.WEEKEND_LAG = Integer.parseInt(prop.getProperty("lag-weekday"));
			
			// set start and end target date
			SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
			startCal.setTime(formatter.parse(startDate));
			Util.setToTheBeginningOfTheDay(startCal);
			endCal.setTime(formatter.parse(endDate));
			Util.setToTheEndOfTheDay(endCal);
			
			// read the target data, in this case: the energy sensors
			Util.hourlyCSVToSensorReadings(dataFileName, data);

			// copy the original data for the baseline calculation
			data.copyHourly(data.getMinDate(), data.getMaxDate(), baseline);

			
			// read the context (feature) data, in this case: temperature
			temp = new SensorReadings();
			Util.hourlyCSVToSensorReadings(contextFileName, temp);
			
			// process the baseline
			// prevStartDate = startDate - 1, hour 23 (end of day) 
			Calendar lastNeeded = Calendar.getInstance();
			lastNeeded.setTime(startCal.getTime());
			lastNeeded.add(Calendar.HOUR_OF_DAY, -1);

			Calendar computeCal = Calendar.getInstance();
			
			if ( data.getMaxDate() >= lastNeeded.getTimeInMillis() ) {
				// if prevStartDate is exist in database, then fine.
				computeCal.setTimeInMillis(startCal.getTimeInMillis());				
			} else {
				// otherwise, compute endHourOfDatabasewhen until prevStartDate
				// compute from startDate, hour 0 until endDate hour 23
				computeCal.setTimeInMillis(data.getMaxDate());
				SimpleDateFormat formatOutput = new SimpleDateFormat(Constants.DATETIME_FORMAT);
				
				System.err.println("[WARNING] Data from " + formatOutput.format(new Date(data.getMaxDate())) 
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
		
		if (VERBOSE==1)
		System.out.println("[ComputeBaselineOneDayHourly] " + targetDay.getTime());
		// get historical data (except for the target day (of course))
		ArrayList<Calendar> trainingCals = getTrainingDays(targetDay);
		
		// hourly 
		for (int h=0; h<24; h++){			
			ComputeBaselineOneHour(trainingCals, targetDay, h);
		}
		
	}

	
	private void ComputeBaselineOneHour(ArrayList<Calendar> trainingCals, Calendar targetDay, int targetH) {
		
		if (VERBOSE==1){
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
		if (VERBOSE==1) System.out.println(trainingSet.toString());
		
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
		    if (VERBOSE==1) System.out.println(c.toString());

		    // take the last instance of the training instance
			Instance lastInst = trainingSet.lastInstance();		
			if (VERBOSE==1) System.out.println(lastInst.toString());

			// create a new instance
			Instance newInst = new Instance(numLag*2+2);
			for (int j=0; j<numLag; j++) {
				Calendar cal = Calendar.getInstance(); 
				cal.setTimeInMillis(trainingCals.get(numLag-j-1).getTimeInMillis());
				cal.set(Calendar.HOUR_OF_DAY, targetH);

				// put load historical data
				newInst.setValue((Attribute)fvWekaAttrs.elementAt(j), baseline.get(cal.getTimeInMillis()));
				// put temp historical data
				newInst.setValue((Attribute)fvWekaAttrs.elementAt(j+numLag), temp.get(cal.getTimeInMillis()));
			}

			Calendar cal = Calendar.getInstance(); 
			cal.setTimeInMillis(targetDay.getTimeInMillis());
			cal.set(Calendar.HOUR_OF_DAY, targetH);

			// put current temp
			if (temp.get(cal.getTimeInMillis()) == null){
				SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATETIME_FORMAT);
				System.err.println("[ERROR] [Regression] There is no context data in "+this.contextFileName+ " for "+formatter.format(cal.getTime())+".");
				System.exit(0);
			}
			newInst.setValue((Attribute)fvWekaAttrs.elementAt(numLag*2), temp.get(cal.getTimeInMillis()));
			
			// put dummy value on the target value			
			newInst.setValue((Attribute)fvWekaAttrs.elementAt(numLag*2+1), 0);
			if (VERBOSE==1) System.out.println("new instance: " + newInst.toString());
			
			double cPredict = c.classifyInstance(newInst);
			cPredict =  Math.round(cPredict * 100000) / 100000;
			if (VERBOSE==1) System.out.println(cPredict);

			Util.setToTheBeginningOfTheHour(cal);			
			baseline.insert(cal.getTimeInMillis(), cPredict);
						

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		

	}
	
	private Instances createWekaTrainingSet(ArrayList<Calendar> trainingCals, Calendar targetCal, int targetH, FastVector fvWekaAttrs) {
		
		int currY = getYDOWType(Util.getDOWType(targetCal));
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

	private Instance createWekaInstanceForTargetIdx(ArrayList<Calendar> trainingCals, int targetIdx, int targetH, int numLag, FastVector fvWekaAttrs) {
		// currZ historical load, currZ historical temp, 1 curr temp, 1 target attr (curr load) 			
		Instance inst = new Instance(numLag*2+2);

		// put historical load and temperature
		for (int j=0; j<numLag; j++) {
			Calendar cal = Calendar.getInstance(); 
			cal.setTimeInMillis(trainingCals.get(targetIdx+numLag-j).getTimeInMillis());
			cal.set(Calendar.HOUR_OF_DAY, targetH);
			inst.setValue((Attribute)fvWekaAttrs.elementAt(j), baseline.get(cal.getTimeInMillis()));
			if (temp.get(cal.getTimeInMillis())==null) {
				System.err.println("Entry for "+cal.getTime()+" is not found in "+this.contextFileName);
				System.exit(0);
			}			
			inst.setValue((Attribute)fvWekaAttrs.elementAt(j+numLag), temp.get(cal.getTimeInMillis()));
		}

		// put current temperature
		Calendar cal = Calendar.getInstance(); 
		cal.setTimeInMillis(trainingCals.get(targetIdx).getTimeInMillis());
		cal.set(Calendar.HOUR_OF_DAY, targetH);
		if (temp.get(cal.getTimeInMillis())==null) {
			System.err.println("Entry for "+cal.getTime()+" is not found in "+this.contextFileName);
			System.exit(0);
		}
		inst.setValue((Attribute)fvWekaAttrs.elementAt(numLag*2), temp.get(cal.getTimeInMillis()));

		// put current load
		inst.setValue((Attribute)fvWekaAttrs.elementAt(numLag*2+1), baseline.get(cal.getTimeInMillis()));

		return inst;
	}

	
	/**
	 * Create a set of weka attributes (columns)
	 * @param lagLen
	 * @return
	 */
	private static FastVector createWekaAttrs(int lagLen){
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
		
		for (int i=0; i<lagLen; i++){
			Attribute attr = new Attribute("temp_d-"+(lagLen-i));
			fvWekaAttrs.addElement(attr);
		}

		Attribute attrT = new Attribute("temp_d+0");
		fvWekaAttrs.addElement(attrT);
	
		Attribute attrL = new Attribute("load_target");
		fvWekaAttrs.addElement(attrL);
		
		return fvWekaAttrs;
	}
	
	
	private ArrayList<Calendar> getTrainingDays(Calendar targetCal) {

		// array to store calendars to be used for computing the baselines
		ArrayList<Calendar> result = new ArrayList<Calendar>();

		int Y = getYDOWType(Util.getDOWType(targetCal));
				
		// data structure to store average kWh of a day
		ArrayList<Double> avgs = new ArrayList<Double>(Y);
		
		// calendar for loop 
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTimeInMillis(targetCal.getTimeInMillis());
		int count = 0;
		while (count < Y ){		
			
			// backward one day
			tempCal.add(Calendar.DAY_OF_MONTH, -1);
			
			if (Util.getDOWType(tempCal) == Util.getDOWType(targetCal)) {
				// store the calendar
				Calendar source = Calendar.getInstance();
				source.setTimeInMillis(tempCal.getTimeInMillis());	
				result.add(source);	
			
				// get the average of that day
				avgs.add(baseline.getAvgOneDayHourly(tempCal));
				count ++;
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

		out.print(baseline.toStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis()));

	}


	@Override
	public void writeResultToFile(String fileName) {
		PrintWriter fileOut;
		try {
			fileOut = new PrintWriter(fileName);
			fileOut.print(baseline.toStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis()));
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public ArrayList<String> getResultString() {
		return baseline.toArrStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis());
		
	}

}
