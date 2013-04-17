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

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;
import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.baseline.util.Util;

/**
 * Basic class for Highest X out of Y days type of baseline.
 * Such as: PJM Economic, CAISO, and NYISO.
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.23
 *
 */
public class HighXOfY implements Baseline{
	
	private Calendar startCal;
	private Calendar endCal;
	// data[0] = data, data[1] = baseline
	private SensorReadings[] data;
	//private SensorReadings baseline;
	private HashMap<Long,Byte> exclDays; // list of days to be excluded from historical computation 
	// choose X out of Y days
	
	protected int WEEKDAY_X;
	protected int WEEKDAY_Y;
	protected int WEEKEND_X;
	protected int WEEKEND_Y;
	
	private boolean inputHistory;
	private int historyAccess;
	
	/**
	 * Constructor, initialize main variable/data structure needed.
	 */
	public HighXOfY(){
		
		startCal = Calendar.getInstance();
		endCal = Calendar.getInstance();
		data = new SensorReadings[2];
		data[0] = new SensorReadings();
		data[1] = new SensorReadings();
		exclDays = null;

		// by default, access history from baseline
		// if inputHistory==true, then we set historyAccess=0 (access history from input data) 
		inputHistory = false;
		historyAccess=1;
	}
	
	/**
	 * 
	 * @param input file name as an input
	 * @param startDate baseline will be calculated from startDate hour 00:00:00-00:59:59 
	 * @param endDate  baseline will be calculated until endDate hour 23:00:00-23:59:59
	 */
	@Override
	public void compute(String input, String startDate, String endDate){
		
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
			// set start and end time for calculating baseline
			startCal.setTime(formatter.parse(startDate));
			Util.setToTheBeginningOfTheDay(startCal);
			endCal.setTime(formatter.parse(endDate));
			Util.setToTheEndOfTheDay(endCal);
			
			// read the file input
			Util.hourlyCSVToSensorReadings(input, this.data[0]);

			// copy the original data for the baseline calculation
			if (data[0].copyHourly(data[0].getMinDate(), data[0].getMaxDate(), data[1]) == true) {
				// nothing to do
			} else {
				System.err.println("[ERROR] [HighXOfY] Failed in copying " + input);
				System.exit(1);
			}
			
			// process the baseline
			// prevStartDate = startDate - 1, hour 23 (end of day) 
			Calendar lastNeeded = Calendar.getInstance();
			lastNeeded.setTime(startCal.getTime());
			lastNeeded.add(Calendar.HOUR_OF_DAY, -1);

			Calendar computeCal = Calendar.getInstance();
			
			if ( data[0].getMaxDate() >= lastNeeded.getTimeInMillis() ) {
				// if prevStartDate is exist in database, then fine.
				computeCal.setTimeInMillis(startCal.getTimeInMillis());				
			} else {
				// otherwise, compute endHourOfDatabasewhen until prevStartDate
				// compute from startDate, hour 0 until endDate hour 23
				computeCal.setTimeInMillis(data[0].getMaxDate());
				SimpleDateFormat formatOutput = new SimpleDateFormat(Constants.DATETIME_FORMAT);
				System.err.println("[WARNING] Data from " + formatOutput.format(new Date(data[0].getMaxDate())) 
				+ " to " + formatOutput.format(startCal.getTime()) + " in " + input
				+ " is not available. "  
				+ "Estimation will be done for that period using the specified baseline method.");
			}

			// start to compute the baseline
			Util.setToTheBeginningOfTheDay(computeCal);

			// loop to get the baseline
			while (!computeCal.after(endCal)){
				ComputeBaselineOneDay(computeCal);
				computeCal.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			// done computing baseline
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}


	/**
	 * Compute baseline for one day.
	 * Get the historical data from the recent 5 days, calculate average kWh per day, choose the highest 4.
	 * 
	 * Assume that the necessary data has been put in baseline
	 * The result will be put in baseline.
	 * 
	 * @param currCal
	 */
	protected void ComputeBaselineOneDay(Calendar targetCal) {
		
		ArrayList<Calendar> trainingCals = getTrainingDays(targetCal);
		
		// compute the baseline for the day
		// loop for hour 00 to hour 23
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTimeInMillis(targetCal.getTimeInMillis());
		Util.setToTheBeginningOfTheDay(tempCal);
		for (int i=0;i<24; i++) {
			tempCal.set(Calendar.HOUR_OF_DAY, i);
			
			// compute the average
			int count=0;
			double total=0;
			double avg = 0.0;
			for (Calendar s: trainingCals){
				s.set(Calendar.HOUR_OF_DAY, i);
				Double energy = data[historyAccess].get(s.getTimeInMillis());
				if (energy!=null) {
					count ++;
					total += energy;
				}
			}
			if (count > 0) {
				avg = total / count;
			}
			// round to 5 digit decimal
			//.. avg =  Math.round(avg * 100000) / 100000.0;

			// store
			data[1].insert(tempCal.getTimeInMillis(), avg);
		}
		
	}


	protected ArrayList<Calendar> getTrainingDays(Calendar targetCal) {

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
				
				// test if tempCal is not in exclDays
				if (exclDays != null ){
					if ( exclDays.containsKey(tempCal.getTimeInMillis()) ){
						continue;
					}
				}
					
				// store the calendar
				Calendar source = Calendar.getInstance();
				source.setTimeInMillis(tempCal.getTimeInMillis());	
				result.add(source);	
			
				// get the average of that day
				avgs.add(data[historyAccess].getAvgOneDayHourly(tempCal));
				count ++;
			}
		}

		// try to print
		if (Constants.VERBOSE == 1){
			for (int i=0; i<result.size(); i++){
				System.out.println("days selected "+i+": "+result.get(i).getTime());
			}
		}
		// retain highest X of Y
		daysRemoval(targetCal, avgs, result);		
		return result;
	}


	protected int getYDOWType(int dowType) {
		if (dowType == Util.WEEKDAY ) {
			return WEEKDAY_Y;
		} else {
			return WEEKEND_Y;
		}
	}

	protected int getXDOWType(int dowType) {
		if (dowType == Util.WEEKDAY ) {
			return WEEKDAY_X;
		} else {
			return WEEKEND_X;
		}
	}

	protected void daysRemoval(Calendar targetCal, ArrayList<Double> avgs, ArrayList<Calendar> result) {

		int X = getXDOWType(Util.getDOWType(targetCal));
		int Y = getYDOWType(Util.getDOWType(targetCal));

		// get highest X out of Y days
		for (int i=0; i<(Y-X); i++) {
			// get the minimum avg
			int minIdx = Util.getMinIdx(avgs);
			avgs.remove(minIdx);
			// remove it from potential baseline's source
			result.remove(minIdx);
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
	public ArrayList<String> getResultString() {
		return data[1].toArrStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis());
		
	}


	@Override
	public void compute(String fileInput, String startDate, String endDate,HashMap<Long, Byte> exclDays) {
		this.exclDays = exclDays;
		compute(fileInput, startDate, endDate);						
	}

	@Override
	public void setInputHistoryOption(boolean flag) {
		inputHistory = flag;	
		if (inputHistory==true){
			// if inputHistory option is activated,
			// we always use input data as historical look up 
			historyAccess = 0;
		} else {
			// (normally, for baseline calculation more than one day, we use
			// previous baseline calculation for history lookup)
			historyAccess = 1;
		}
	}
}
