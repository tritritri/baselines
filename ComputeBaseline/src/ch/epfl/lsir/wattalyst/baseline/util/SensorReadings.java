package ch.epfl.lsir.wattalyst.baseline.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;

/**
 * A hash map data structure, stores timestamp (as Long) as its key, and sensor reading as its value (as Double)
 * The value is reading consumed during that hour.
 * If minDate is set (see setMinDate), method insert will accept only the date after minDate
 *   
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.23
 */
public class SensorReadings {
	
	HashMap<Long,Double> data;
	long maxDate;
	long minDate;
	
	
	public SensorReadings(){
		data = new HashMap<Long,Double>();
		maxDate = 0;
		minDate = 0;
	}
	
	
	/**
	 * Inserting date and reading.
	 * If minDate is set (see setMinDate), accept only date no earlier than minDate
	 * @param date
	 * @param reading
	 */
	public void insert(Long date, double reading){

		data.put(date, reading);
		if (data.size() == 1) {
			maxDate = date;
			minDate = date;
		} else 
		if ( date > maxDate ) {
			maxDate = date;
		} else 
		if ( date < minDate ) {
			minDate = date;
		}					
	}
	
	/**
	 * 
	 * @return the latest date in the collection
	 */
	public long getMaxDate(){
		return maxDate;
	}
	
	/**
	 * 
	 * @return the earliest date in the collection
	 */
	public long getMinDate(){
		return minDate;
	}
	
	/**
	 * Print all elements (timing and readings) using iterator
	 * Reading value printed is rounded, up to 5 decimal point 
	 */
	public String toString(){
		String result = "";
		Iterator<Long> iter = (Iterator<Long>) data.keySet().iterator();
		while (iter.hasNext()){
			Long key = iter.next();
			double reading = data.get(key);	

			// round to 5 digit decimal
			reading =  Math.round(reading * 100000) / 100000.0;

			result = result + "["+ new Date(key) + "," + reading +"] \n";
		}
		result = result + "maxDate: " + new Date(maxDate) + "\n";
		return result;
	}

	/**
	 * @return String of elements, ordered by timing
	 */
	public String toStringAsc(){
		return toStringAsc(minDate, maxDate);
	}
	
	/**
	 * @return String of elements, ordered by timing, ready to store in Wattalyst DB
	 */
	public String toStringAscDBFormat(){
		return toStringAscDBFormat(minDate, maxDate);
	}
	
	/**
	 * Get the element sorted ascending between from and to
	 * @param from start time
	 * @param to end time
	 * @return element from the start time (from) to the end time (to) 
	 */
	public String toStringAsc(long from, long to){
	
		
		String result = "";
		
		ArrayList<String> arrString = toArrStringAsc(from, to);
		for (int i=0; i<arrString.size(); i++){
			result = result + arrString.get(i) + "\n";
		}
		
		return result;
	}
	
	/**
	 * Get the element sorted ascending between from and to
	 * @param from start time
	 * @param to end time
	 * @return element from the start time (from) to the end time (to) 
	 */
	public String toStringAscDBFormat(long from, long to){
	
		
		String result = "";
		
		ArrayList<String> arrString = toArrStringAscDBFormat(from, to);
		for (int i=0; i<arrString.size(); i++){
			result = result + arrString.get(i) + "\n";
		}
		
		return result;
	}
	

	/**
	 * Convert each element having key between from and to (inclusive) into string.
	 * Value printed is rounded, up to 5 decimal point 
	 * @param from starting key
	 * @param to ending key
	 * @return an array (list) of string representation the element
	 */
	public ArrayList<String> toArrStringAsc(long from, long to){
		
		// initialize the result structure
		ArrayList<String> result = new ArrayList<String>();
		
		// loop from minDate to maxDate
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));

		Calendar currCal = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
		currCal.setTimeInMillis(from);		
		while (currCal.getTimeInMillis() <= to) {
			
			// get the element
			Double reading = data.get(currCal.getTimeInMillis());

			// check if the reading for current time is exist
			if (reading!=null){
				// round to 5 digit decimal
				reading =  Math.round(reading * 100000) / 100000.0;
				result.add(formatter.format(currCal.getTime()) + "," + currCal.get(Calendar.HOUR_OF_DAY) + "," + reading + "," + currCal.getTimeInMillis());
			}
			
			// advance one hour
			currCal.add(Calendar.HOUR_OF_DAY, 1);
		}		
		return result;
	}
	
	/**
	 * Convert each element having key between from and to (inclusive) into string.
	 * Value printed is rounded, up to 5 decimal point 
	 * @param from starting key
	 * @param to ending key
	 * @return an array (list) of string representation the element
	 */
	public ArrayList<String> toArrStringAscDBFormat(long from, long to){
		
		// initialize the result structure
		ArrayList<String> result = new ArrayList<String>();
		
		// loop from minDate to maxDate
		
		Calendar currCal = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
		currCal.setTimeInMillis(from);		
		while (currCal.getTimeInMillis() <= to) {
			
			// get the element
			Double reading = data.get(currCal.getTimeInMillis());

			// check if the reading for current time is exist
			if (reading!=null){
				// round to 5 digit decimal
				reading =  Math.round(reading * 100000) / 100000.0;
				result.add(currCal.getTimeInMillis() + ";" + reading);
			}
			
			// advance one hour
			currCal.add(Calendar.HOUR_OF_DAY, 1);
		}		
		return result;
	}

	/**
	 * Copy data to destination. Between from and to (inclusive), hourly advances.
	 * @param from starting point of the copy
	 * @param to ending point of the copy
	 * @param destination target collection
	 */	
	public boolean copyHourly(long from, long to, SensorReadings destination) {
		//... Calendar currCal = Calendar.getInstance();	
		//.. currCal.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
		//... currCal.setTimeInMillis(from);
		//... while (currCal.getTimeInMillis() <= to) {
		while (from <= to) {
					// check if the data is exist
			//... if (!data.containsKey(currCal.getTimeInMillis())) {
			if (!data.containsKey(from)) {
				
				//.... SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATETIME_FORMAT);
				//.... System.err.println("[ERROR] [SensorReadings.copyHourly] There is no data for "+
						//... currCal.getTimeInMillis() + " ["+formatter.format(currCal.getTime())+"].");
						//.... from + " ["+formatter.format(new Date(from))+"].");
				//.... return false;
				
			} else {
				//... destination.insert(currCal.getTimeInMillis(), data.get(currCal.getTimeInMillis()));
				destination.insert(from, data.get(from));
			}
			//... currCal.add(Calendar.HOUR_OF_DAY, 1);
			from += 3600000;  // advance one hour
		}		
		return true;
	}

	
	/**
	 * Get average readings for a day from each one hour reading
	 * @param targetCal compute average for this day
	 * @return
	 */
	public double getAvgOneDayHourly(Calendar targetCal) {
		// initialize the counter 
		Calendar tempCal = Calendar.getInstance();
		//.. tempCal.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));
		tempCal.setTimeInMillis(targetCal.getTimeInMillis());
		Util.setToTheBeginningOfTheDay(tempCal);
		
		// start the counter
		int count = 0;
		double total = 0.0;
		for (int i=0; i<24; i++){
			tempCal.set(Calendar.HOUR_OF_DAY, i);
			Double reading = data.get(tempCal.getTimeInMillis());
			if ( reading!= null ){
				count ++;
				total = total + reading;
			}
		}
		
		if (count == 0 ) return 0;
		
		return total/count;
	}

	/**
	 * Get a reading for a given time.
	 * @param timeInMillis 
	 * @return the reading on timeInMillis.
	 */
	public Double get(long timeInMillis) {
		
		return data.get(timeInMillis);
	}
	
	
	/**
	 * Calculate the difference between this.data.value and reference.data.value
	 * for all element contained in this.data
	 * @param reference other SensorReading to be compared to
	 * @param result append all timing in this.data and its value subtracted by its value in other
	 * @return true if all comparison has been computed, false if there is a missing value in the reference 
	 */
	public boolean comparedTo(SensorReadings reference, SensorReadings result){
		Iterator<Long> iter = this.data.keySet().iterator();
		while ( iter.hasNext() ) {
			long timing = iter.next();
			Double ref = reference.get(timing);
			if (ref == null) {
				System.err.println("[ERROR] [SensorReadings.comparedTo] no value for "+new Date(timing) + 
						" in the reference");
				return false;
			}
			double error = this.data.get(timing) - ref;			
			result.insert(timing, error);
		}
		return true;
	}
	
}
