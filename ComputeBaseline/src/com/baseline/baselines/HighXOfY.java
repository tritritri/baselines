package com.baseline.baselines;

import java.io.BufferedReader;
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

import com.baseline.constants.Constants;
import com.baseline.util.EnergyHourly;
import com.baseline.util.Util;

/**
 * Class to compute PJM Economic baseline
 * from startDate
 * to endDate
 * 
 * created 2013.02.23
 * @author tritritri
 *
 */
public class HighXOfY implements Baseline{
	
	private Calendar startCal;
	private Calendar endCal;
	private EnergyHourly data;
	protected EnergyHourly baseline;
	protected int WEEKDAY_X;
	protected int WEEKDAY_Y;
	protected int WEEKEND_X;
	protected int WEEKEND_Y;
	
	/**
	 * Constructor, initialize main variable/data structure needed.
	 */
	public HighXOfY(){
		
		startCal = Calendar.getInstance();
		endCal = Calendar.getInstance();
		data = new EnergyHourly();
		baseline = new EnergyHourly();
		
		
	}
	
	
	/**
	 * 
	 * @param input file name as an input
	 * @param startDate baseline will be calculated from startDate hour 00:00:00-00:59:59 
	 * @param endDate  baseline will be calculated until endDate hour 23:00:00-23:59:59
	 */
	@Override
	public void compute(String input, String startDate, String endDate){
		
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		
		try {
			// set start and end time for calculating baseline
			startCal.setTime(formatter.parse(startDate));
			Util.setCalToStartOfTheDay(startCal);
			endCal.setTime(formatter.parse(endDate));
			Util.setCalToEndOfTheDay(endCal);
			
			// read the file input
			readInput(input);

			// copy the original data for the baseline calculation
			data.copy(data.getMinDate(), data.getMaxDate(), baseline);
			
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
				+ " to " + formatOutput.format(startCal.getTime()) + " is not available. " 
				+ "Estimation will be done for that period using the specified baseline method.");
			}

			// start to compute the baseline
			Util.setCalToStartOfTheDay(computeCal);

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
		Util.setCalToStartOfTheDay(tempCal);
		for (int i=0;i<24; i++) {
			tempCal.set(Calendar.HOUR_OF_DAY, i);
			
			// compute the average
			int count=0;
			double total=0;
			double avg = 0.0;
			for (Calendar s: trainingCals){
				s.set(Calendar.HOUR_OF_DAY, i);
				Double energy = baseline.get(s.getTimeInMillis());
				if (energy!=null) {
					count ++;
					total += energy;
				}
			}
			if (count > 0) {
				avg = total / count;
			}
			// store
			baseline.insert(tempCal.getTimeInMillis(), avg);
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
				// store the calendar
				Calendar source = Calendar.getInstance();
				source.setTimeInMillis(tempCal.getTimeInMillis());	
				result.add(source);	
			
				// get the average of that day
				avgs.add(baseline.getAvgDay(tempCal));
				count ++;
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


	private void readInput(String fileInput) {
		try {
			
			// read file
			BufferedReader in = new BufferedReader(new FileReader(fileInput));
			String line = null;  
			// read all lines
			while ( (line=in.readLine()) != null ){
				String[] lineArray = line.split(",");
				
				// first element is date, second element is hour
				Calendar dateCal = Util.dateStrHourToCal(lineArray[0], Integer.parseInt(lineArray[1]));
				
				// third element is reading
				double kWh = Double.parseDouble(lineArray[2]);
				
				// put the data 
				data.insert(dateCal.getTimeInMillis(), kWh);
			}
			
			in.close();
			//.. System.out.println(data);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}


	@Override
	public void writeResult(PrintStream out) {

		out.println(baseline.toStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis()));

	}


	@Override
	public void writeResultToFile(String fileName) {
		PrintWriter fileOut;
		try {
			fileOut = new PrintWriter(fileName);
			fileOut.println(baseline.toStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis()));
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
