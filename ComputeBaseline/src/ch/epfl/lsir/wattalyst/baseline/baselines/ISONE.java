package ch.epfl.lsir.wattalyst.baseline.baselines;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;
import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.baseline.util.Util;

/**
 * Implementation of baseline ISONE
 * The basic of this baseline is exp
 * @author Tri Kurniawan Wijaya
 * @date   2013.03.10
 *
 */
public class ISONE implements Baseline{

	private Calendar startCal;
	private Calendar endCal;
	private Calendar minCalAvailable;
	
	private Calendar[] lastComputedCal;

	private SensorReadings data;
	private SensorReadings baseline;
	private HashMap<Long,Byte> exclDays; // list of days to be excluded from historical computation 
	
	// set baseline run for weekday/weekend
	double[][] baselineRun;
	
	protected int WEEKDAY_Y;
	protected int WEEKEND_Y;

	private boolean inputHistory;

	public ISONE() {
		startCal = Calendar.getInstance();
		startCal.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
		endCal = Calendar.getInstance();
		endCal.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
		minCalAvailable = Calendar.getInstance();
		minCalAvailable.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
		lastComputedCal = new Calendar[2];
		
		data = new SensorReadings();
		baseline = new SensorReadings();
		exclDays = null;

		baselineRun = null;
		
		// set minimal initial date required for exponential moving average
		WEEKDAY_Y = 5;
		WEEKEND_Y = 5;

		inputHistory = false;
	}
	
	@Override
	public void compute(String fileInput, String startDate, String endDate) {
		
		/*
		 * 1. get 5 data historical days. compute the average
		 * 2. move for the next days by  
		 * 3. 
		 */
		
		try {
			SimpleDateFormat formatDate= new SimpleDateFormat(Constants.DATE_FORMAT);
			formatDate.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));

			// set start and end time for calculating baseline
			startCal.setTime(formatDate.parse(startDate));
			Util.setToTheBeginningOfTheDay(startCal);
			endCal.setTime(formatDate.parse(endDate));
			Util.setToTheEndOfTheDay(endCal);
			
			// read the file input
			Util.hourlyCSVToSensorReadings(fileInput, this.data);

			// copy the original data for the baseline calculation
			long dataMinDate = data.getMinDate();
			long dataMaxDate = data.getMaxDate();
			if (data.copyHourly(dataMinDate, dataMaxDate, baseline) == true) {
				// nothing to do
			} else {
				System.err.println("[ERROR] [ISONE] Failed in copying " + fileInput);
				System.exit(1);
			}
			
			// get the first initial day >= min Date
			minCalAvailable.setTimeInMillis(dataMinDate);
			if ( !Util.isInitialDay(minCalAvailable) ) {
				minCalAvailable.add(Calendar.DAY_OF_MONTH, 1);
				Util.setToTheBeginningOfTheDay(minCalAvailable);
			}
			
			SimpleDateFormat formatDateTime = new SimpleDateFormat(Constants.DATETIME_FORMAT);
			formatDateTime.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));

			if ( Constants.VERBOSE == 1 ) {
				System.out.println("dataMinDate: " + formatDateTime.format(new Date(dataMinDate)));
				System.out.println("dataMaxDate: " + formatDateTime.format(new Date(dataMaxDate)));
				System.out.println("minCal now  : " + formatDateTime.format(minCalAvailable.getTime()));
			}
			
			// start computing the baseline
			// prevStartDate = startDate - 1, hour 23 (end of day) 
			Calendar lastNeeded = Calendar.getInstance();
			lastNeeded.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
			lastNeeded.setTime(startCal.getTime());
			lastNeeded.add(Calendar.HOUR_OF_DAY, -1);

			// computeCal is the minimum date that should be computed 
			Calendar computeCal = Calendar.getInstance();
			computeCal.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
			if ( data.getMaxDate() >= lastNeeded.getTimeInMillis() ) {
				// if prevStartDate is exist in database, then fine.
				computeCal.setTimeInMillis(startCal.getTimeInMillis());				
			} else {
				// otherwise, compute endHourOfDatabasewhen until prevStartDate
				// compute from startDate, hour 0 until endDate hour 23
				computeCal.setTimeInMillis(data.getMaxDate());
				SimpleDateFormat formatOutput = new SimpleDateFormat(Constants.DATETIME_FORMAT);
				formatOutput.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));

				
				System.err.println("[WARNING] Data from " + formatOutput.format(new Date(data.getMaxDate())) 
				+ " to " + formatOutput.format(startCal.getTime()) + " in " + fileInput
				+ " is not available. "  
				+ "Estimation will be done for that period using the specified baseline method.");
			}

			
			// start to compute the baseline
			Util.setToTheBeginningOfTheDay(computeCal);

			// first initial days 
			firstRunHourly(Util.WEEKDAY);
			firstRunHourly(Util.WEEKEND);
			
			// run up to computeCal-1
			Calendar yesterday = Calendar.getInstance();
			yesterday.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
			yesterday.setTimeInMillis(computeCal.getTimeInMillis());
			yesterday.add(Calendar.DAY_OF_MONTH, -1);
			baselineRunHourlyUpTo(Util.WEEKDAY, yesterday);
			baselineRunHourlyUpTo(Util.WEEKEND, yesterday);
			// try to print 
			if (Constants.VERBOSE==1) {
				for (int i=0; i<24; i++) 
					System.out.println("baseline["+Util.WEEKDAY+"]["+i+"]="+baselineRun[Util.WEEKDAY][i]);
				
				for (int i=0; i<24; i++) 
					System.out.println("baseline["+Util.WEEKEND+"]["+i+"]="+baselineRun[Util.WEEKEND][i]);
				
			}
			
			if (Constants.VERBOSE == 1)
				System.err.println("ComputeCal: "+computeCal.getTime());
			// loop to get the baseline
			Calendar earlyEndCal = Calendar.getInstance();
			earlyEndCal.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
			earlyEndCal.setTimeInMillis(endCal.getTimeInMillis());
			Util.setToTheBeginningOfTheDay(earlyEndCal);
			while (!computeCal.after(endCal)){
				ComputeBaselineOneDayHourly(computeCal);
				
				// if inputhistory option is set
				// after we compute baseline for today
				// we renew the baselineRun until today using the input data
				if (inputHistory == true && computeCal.before(earlyEndCal) ) {
					baselineRunHourlyUpTo(Util.WEEKDAY, computeCal);
					baselineRunHourlyUpTo(Util.WEEKEND, computeCal);
				}
				computeCal.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			// done computing baseline
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
	}
	
	/**
	 * Run baseline, start from the initial first 5 (WEEKDAY_Y or WEEKEND_Y) days
	 * up to right before the requested baseline's days
	 * @param dowType
	 * @param yesterday
	 */
	private void baselineRunHourlyUpTo(int dowType, Calendar yesterday) {
		
		while ( lastComputedCal[dowType].before(yesterday) ) {
			lastComputedCal[dowType].add(Calendar.DAY_OF_MONTH, 1);
			Util.setToTheBeginningOfTheDay(lastComputedCal[dowType]);
			
			if (Util.getDOWType(lastComputedCal[dowType]) == dowType) {
				if ((exclDays==null) || !exclDays.containsKey(lastComputedCal[dowType].getTimeInMillis())) {
					for (int i=0; i<24; i++) {
						lastComputedCal[dowType].set(Calendar.HOUR_OF_DAY, i);
						double newValue = 0.9 *baselineRun[dowType][i] + 0.1*data.get(lastComputedCal[dowType].getTimeInMillis());
						newValue =  Math.round(newValue * 100000) / 100000.0;
						baselineRun[dowType][i] = newValue;
						// round to 5 digit decimal
						
					}				
				}
			}
		}
		
		Util.setToTheBeginningOfTheDay(lastComputedCal[dowType]);
		
		if ( Constants.VERBOSE == 1 )
			System.err.println("[baselineRunHourlyUpTo] lastComputedCal, dowType: "+ dowType + ", value: " + lastComputedCal[dowType].getTime());
	}

	/**
	 * First run for 5 (or WEEKDAY_Y or WEEKEND_Y) days
	 * to get the initial averages 
	 * @param dowType
	 */
	
	private void firstRunHourly(int dowType) {
		
		// there are 24 hours in one day
		int numHours = 24;
		
		// initialize baseline Run according to dowType;
		if (baselineRun == null) baselineRun = new double[2][24];
		for (int i=0; i<numHours; i++){
			baselineRun[dowType][i] = 0.0;
		}
			
		// loop until num of historical data is fulfilled
		int numHist = getYDOWType(dowType);
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
		tempCal.setTimeInMillis(minCalAvailable.getTimeInMillis());
		int counter = 0;
		while (counter < numHist && tempCal.before(startCal)) {
			if (Util.getDOWType(tempCal) == dowType) {
				
				if ((exclDays==null) || !exclDays.containsKey(tempCal.getTimeInMillis())) {
					
					// loop and get data for 24 hour
					for (int i=0; i<numHours; i++){
						tempCal.set(Calendar.HOUR_OF_DAY, i);
						baselineRun[dowType][i] += data.get(tempCal.getTimeInMillis());
					}
					
					counter ++;
				}
					
			}
			tempCal.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		// error message if the number of historical data is not enough
		if (!tempCal.before(startCal)) {
			String dowTypeStr = "weekday";
			if ( dowType == Util.WEEKEND ) dowTypeStr = "weekend";
			System.err.println("[ERROR] [ISONE] more than "+ numHist + " "+ dowTypeStr + 
					" days of historical data needed before: "+startCal.getTime());
			System.exit(1);
		} else {
			// if the historical data is enough
			// set last compute cal
			lastComputedCal[dowType] = Calendar.getInstance();
			lastComputedCal[dowType].setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
			tempCal.add(Calendar.DAY_OF_MONTH, -1);
			lastComputedCal[dowType].setTimeInMillis(tempCal.getTimeInMillis());
			Util.setToTheBeginningOfTheDay(lastComputedCal[dowType]);
			if ( Constants.VERBOSE == 1 )
				System.err.println("[firstRunHourly] lastComputedCal, dowType: "+ dowType + ", value: " + lastComputedCal[dowType].getTime());
			
			// set the initial baseline run to avg of it
			for (int i=0; i<numHours; i++){
				
				baselineRun[dowType][i] = baselineRun[dowType][i] / (numHist + 0.0);
				
				// round to 5 digit decimal
				baselineRun[dowType][i] =  Math.round(baselineRun[dowType][i] * 100000) / 100000.0;
				
				if ( Constants.VERBOSE == 1 )
				System.err.println("[firstRunHourly] baselineRun, dowType: " + dowType + ", hour: "+ i +", value: " + baselineRun[dowType][i]);
			}
			
		}
		
	}

	private void ComputeBaselineOneDayHourly(Calendar computeCal) {

		int dowType = Util.getDOWType(computeCal);
		for (int i=0; i<24; i++) {
			computeCal.set(Calendar.HOUR_OF_DAY, i);			
			baseline.insert(computeCal.getTimeInMillis(), baselineRun[dowType][i]);
		}

		// set computeCal back to initial of the day, just in case...
		Util.setToTheBeginningOfTheDay(computeCal);
		
	}

	private int getYDOWType(int dowType) {
		if (dowType == Util.WEEKDAY ) {
			return WEEKDAY_Y;
		} else {
			return WEEKEND_Y;
		}
	}


	@Override
	public ArrayList<String> getResultString() {
		return baseline.toArrStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis());

	}

	@Override
	public void writeResult(PrintStream out) {
	
		out.print(baseline.toStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis()));
	}

	/**
	 * Write the baseline result to a file
	 */
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
	public void compute(String fileInput, String startDate, String endDate, HashMap<Long, Byte> exclDays) {
		this.exclDays = exclDays;
		compute(fileInput, startDate, endDate);						
		
	}

	@Override
	public void setInputHistoryOption(boolean flag) {
		
		inputHistory = flag;
		
	}

}
