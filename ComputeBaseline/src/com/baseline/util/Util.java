package com.baseline.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.baseline.constants.Constants;

public class Util {
	
	public static final int WEEKDAY = 0;
	public static final int WEEKEND = 1;
	
	
	/**
	 * Convert date,hour into calendar object (date hour:00:00)
	 * @param date
	 * @param hour
	 * @return
	 * @throws ParseException
	 */
	public static Calendar dateStrHourToCal(String date, int hour) throws ParseException{
		
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		Calendar cal = Calendar.getInstance();
		cal.setTime(formatter.parse(date));
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
		
	}

	/**
	 * Set hour to 0, minute to 0, second to 0, millisecond to 0
	 * @param calendar
	 */
	public static void setCalToStartOfTheDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
	}

	/**
	 * Set hour to 23, minute to 59, second to 59, millisecond to 999
	 * @param calendar
	 */
	public static void setCalToEndOfTheDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		
	}

	/**
	 * Set minute to 59, second to 59, millisecond to 999
	 * @param calendar
	 */
	public static void setToBeginningOfTheHour(Calendar calendar) {
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
	}

	/**
	 * Return the index of the minimum vale
	 * 
	 * @param avgs
	 * @return
	 */
	public static int getMinIdx(ArrayList<Double> arr) {
		int idx = -1;
		double min = 0;
		if (arr.size()>=1) {
			idx = 0;
			min = arr.get(0);			
		}
		for (int i=1; i<arr.size(); i++){
			if (arr.get(i) < min) {
				idx = i;
				min = arr.get(i);
			}
		}
		return idx;
	}


	/**
	 * Tested if the day of week of cal is weekday or weekend. 
	 * @param cal calendar to be tested
	 * @return Util.WEEKDAY if cal is weekday, Util.WEEKEND if cal is weekend
	 */
	public static int getDOWType(Calendar cal) {
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			return Util.WEEKEND;
		else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			return Util.WEEKEND;
		else return Util.WEEKDAY;
		
	}

	public static int getMaxIdx(ArrayList<Double> arr) {
		int idx = -1;
		double max = 0;
		if (arr.size()>=1) {
			idx = 0;
			max = arr.get(0);			
		}
		for (int i=1; i<arr.size(); i++){
			if (arr.get(i) > max) {
				idx = i;
				max = arr.get(i);
			}
		}
		return idx;

	}
	
	public static void hourlyCSVToSensorReadings(String fileInput, SensorReadings data) {
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

}
