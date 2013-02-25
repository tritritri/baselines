package com.baseline.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import com.baseline.constants.Constants;

/**
 * A hash map data structure, stores date (as Long) as its key, and energy as its value (as Double)
 * Semantics: The key is date up to hours granularity. 
 * The value is energy consumed during that hour.
 * If minDate is set (see setMinDate), method insert will accept only the date after minDate
 *   
 * @author tritritri
 * created: 2013.02.23
 */
public class EnergyHourly {
	
	HashMap<Long,Double> data;
	long maxDate;
	long minDate;
	
	
	public EnergyHourly(){
		data = new HashMap<Long,Double>();
		maxDate = 0;
		minDate = 0;
	}
	
	/*
	public void setMinDate(Date minDate){
		this.minDate = minDate.getTime();
	}
	*/
	
	/**
	 * Inserting date and energy.
	 * If minDate is set (see setMinDate), accept only date no earlier than minDate
	 * @param date
	 * @param energy
	 */
	public void insert(Long date, double energy){
		
		/*
		// check if the minDate is set
		if (this.minDate != null){
			// if yes, then compare the date inserted
			// if earlier than minDate, reject
			if (date.getTime() < minDate){
				return;
			}
		}
		
		// if minDate=null or date inserted >= minDate		
		 */
		
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
		
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		// loop from minDate to maxDate
		Calendar currCal = Calendar.getInstance();
		currCal.setTimeInMillis(from);		
		while (currCal.getTimeInMillis() <= to) {
			Double energy = data.get(currCal.getTimeInMillis());
			// check if the reading for current time is exist
			if (energy!=null){
				result = result + formatter.format(currCal.getTime()) + "," + currCal.get(Calendar.HOUR_OF_DAY) + "," + energy +"\n";
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
	
	public void copy(long from, long to, EnergyHourly destination) {
		Calendar currCal = Calendar.getInstance();
		currCal.setTimeInMillis(from);
		while (currCal.getTimeInMillis() <= to) {
			destination.insert(currCal.getTimeInMillis(), data.get(currCal.getTimeInMillis()));
			currCal.add(Calendar.HOUR_OF_DAY, 1);
		}		
	}

	/**
	 * Get average energy reading for a day 
	 * @param targetCal compute average for this day
	 * @return
	 */
	public double getAvgDay(Calendar targetCal) {
		// initialize the counter 
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTimeInMillis(targetCal.getTimeInMillis());
		Util.setCalToStartOfTheDay(tempCal);
		
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
