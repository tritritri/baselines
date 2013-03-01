package ch.epfl.lsir.wattalyst.baseline.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;

/**
 * A hash map data structure, stores timestamp (as Long) as its key, and sensor reading as its value (as Double)
 * The value is energy consumed during that hour.
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
	 * Inserting date and energy.
	 * If minDate is set (see setMinDate), accept only date no earlier than minDate
	 * @param date
	 * @param energy
	 */
	public void insert(Long date, double energy){
		
		data.put(date, energy);
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
	
	public String toString(){
		String result = "";
		Iterator<Long> iter = (Iterator<Long>) data.keySet().iterator();
		while (iter.hasNext()){
			Long key = iter.next();
			double energy = data.get(key);			
			result = result + "["+ new Date(key) + "," + energy +"] \n";
		}
		result = result + "maxDate: " + new Date(maxDate) + "\n";
		return result;
	}

	public String toStringAsc(){
		return toStringAsc(minDate, maxDate);
	}
	
	/**
	 * Get the element sorted ascending between from and maxDate
	 * @param from start time
	 * @return element from the start time (from) to the end time (maxDate) 
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
	 * Convert each element having key between from and to (inclusive) into string.
	 * @param from starting key
	 * @param to ending key
	 * @return an array (list) of string representation the element
	 */
	public ArrayList<String> toArrStringAsc(long from, long to){
		
		// initialize the result structure
		ArrayList<String> result = new ArrayList<String>();
		
		// loop from minDate to maxDate
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		Calendar currCal = Calendar.getInstance();
		currCal.setTimeInMillis(from);		
		while (currCal.getTimeInMillis() <= to) {
			
			// get the element
			Double energy = data.get(currCal.getTimeInMillis());
			
			// check if the reading for current time is exist
			if (energy!=null){
				result.add(formatter.format(currCal.getTime()) + "," + currCal.get(Calendar.HOUR_OF_DAY) + "," + energy);
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
	public void copyHourly(long from, long to, SensorReadings destination) {
		Calendar currCal = Calendar.getInstance();
		currCal.setTimeInMillis(from);
		while (currCal.getTimeInMillis() <= to) {
			destination.insert(currCal.getTimeInMillis(), data.get(currCal.getTimeInMillis()));
			currCal.add(Calendar.HOUR_OF_DAY, 1);
		}		
	}

	
	/**
	 * Get average readings for a day from each one hour reading
	 * @param targetCal compute average for this day
	 * @return
	 */
	public double getAvgOneDayHourly(Calendar targetCal) {
		// initialize the counter 
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTimeInMillis(targetCal.getTimeInMillis());
		Util.setToTheBeginningOfTheDay(tempCal);
		
		// start the counter
		int count = 0;
		double total = 0.0;
		for (int i=0; i<24; i++){
			tempCal.set(Calendar.HOUR_OF_DAY, i);
			Double energy = data.get(tempCal.getTimeInMillis());
			if ( energy!= null ){
				count ++;
				total = total + energy;
			}
		}
		
		if (count == 0 ) return 0;
		
		return total/count;
	}

	/**
	 * Get an energy reading for a given time.
	 * @param timeInMillis 
	 * @return the energy reading on timeInMillis.
	 */
	public Double get(long timeInMillis) {
		
		return data.get(timeInMillis);
	}
	
}
