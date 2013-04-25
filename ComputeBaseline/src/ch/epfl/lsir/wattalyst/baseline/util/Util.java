package ch.epfl.lsir.wattalyst.baseline.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;

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

		String dateStr = "";
		if (hour < 10) dateStr = date + " 0"+hour+":00:00";
			else dateStr = date + " " + hour+":00:00";
		
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATETIME_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));

		//formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
		//System.out.println("formatter date: "+formatter.parse(dateStr) + ": "+formatter.parse(date).getTime());
		cal.setTimeInMillis(formatter.parse(dateStr).getTime());
		//System.out.println(cal.getTimeInMillis());
		
		//System.out.println("resulting date: "+cal.getTime());
		//System.out.println(cal.getTimeInMillis());
		//System.out.println("resulting timezone: "+cal.getTimeZone());
		//System.exit(1);
		return cal;
		
	}

	/**
	 * Set hour to 0, minute to 0, second to 0, millisecond to 0
	 * @param calendar
	 */
	public static void setToTheBeginningOfTheDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
	}

	/**
	 * Set hour to 23, minute to 59, second to 59, millisecond to 999
	 * @param calendar
	 */
	public static void setToTheEndOfTheDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		
	}

	/**
	 * Set minute to 59, second to 59, millisecond to 999
	 * @param calendar
	 */
	public static void setToTheBeginningOfTheHour(Calendar calendar) {
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
	 * Test if the day of week of cal is weekday or weekend. 
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
	
	/**
	 * Read csv file, put it in SensorReadings data structure
	 * @param fileInput Inpu file name. The file is of form DATE,HOUR,READINGS. 
	 * Where DATE=yyyy-MM-dd, HOUR=HH, READINGS=double
	 * @param data the resulting data structure
	 */
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
				if (Constants.VERBOSE==1) {
					System.out.println("[Util.hourlyCSVToSensorReadings] "+lineArray[0]+", "+lineArray[1] + 
							" ["+dateCal.getTimeInMillis()+"]. ");
				}
				// third element is reading
				double reading = Double.parseDouble(lineArray[2]);
				
				// put the data 
				data.insert(dateCal.getTimeInMillis(), reading);
			}
			
			in.close();
			//.. System.out.println(data);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}

	
	/**
	 * Compare file text line by line against an array list of string element by element.
	 * "Equal" if line i in the file is equal to element i in the array if their, for i=all lines in the file.
	 *  
	 * @param fileName
	 * @param arrString
	 * @return file size + 1 (the number of lines + 1, hence the size of the array + 1) if the file and the array is equal.
	 * 1 up to file size denoted the mismatched line
	 * -1 if the file and the array does not have the same size  
	 */
	public static int isEqual(String fileName, ArrayList<String> arrString){
		
		int i=0;
		try {
			// open the file
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			// compare the result with the reference
			String line = null;
			while ((line = in.readLine()) != null ){
				if (i>=arrString.size()) {
					in.close();
					return -1;
				}
				if (!line.equals(arrString.get(i))){
					in.close();
					return i+1;
				}
				i++;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return i+1;

	}
	
	public static boolean isInitialDay(Calendar cal){
		if ( cal.get(Calendar.HOUR_OF_DAY) == 0 && cal.get(Calendar.MINUTE) == 0 && 
				cal.get(Calendar.SECOND) == 0 && cal.get(Calendar.MILLISECOND) == 0 ) {
			return true;
		}
		return false;
	}
	
	public static boolean isEqualDate(Calendar cal1, Calendar cal2){
		if (cal1.get(Calendar.YEAR)==cal2.get(Calendar.YEAR) &&
				cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR))
			return true;
		else
			return false;
	}
}
