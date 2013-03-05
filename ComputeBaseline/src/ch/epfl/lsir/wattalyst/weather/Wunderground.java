package ch.epfl.lsir.wattalyst.weather;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.baseline.util.Util;

public class Wunderground implements Temperature {

	private SensorReadings temperature;
	private Calendar startCal;
	private Calendar endCal;
	
	/**
	 * 
	 */
	public Wunderground(){
		startCal = Calendar.getInstance();
		endCal = Calendar.getInstance();
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.weather.Temperature#compute(java.util.Date, java.util.Date, java.lang.String, java.lang.String)
	 */
	@Override
	public void compute(Date startDate, Date endDate, String country, String place) {
		
		// Check dates
		if(startDate.after(endDate)){
			throw new RuntimeException("Start date must be before or equal to end date");
		}
				
		temperature = new SensorReadings();
		
		// Save the original start and end date into the calendars
		startCal.setTime(startDate);
		Util.setToTheBeginningOfTheDay(startCal);
		endCal.setTime(endDate);
		Util.setToTheEndOfTheDay(endCal);
		
		// Invoke Wunderground service
		WundergroundDataReader wundergroundDataReader = new WundergroundDataReader();
		SensorReadings temp = wundergroundDataReader.getHourlyTemperatures(startDate, endDate, country, place);
		temp.copyHourly(temp.getMinDate(), temp.getMaxDate(), temperature);
	}

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.weather.Temperature#getResultString()
	 */
	@Override
	public ArrayList<String> getResultString() {
		return temperature.toArrStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis());
	}

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.weather.Temperature#writeResult(java.io.PrintStream)
	 */
	@Override
	public void writeResult(PrintStream out) {
		out.print(temperature.toStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis()));
	}

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.weather.Temperature#writeResultToFile(java.lang.String)
	 */
	@Override
	public void writeResultToFile(String fileName) {
		PrintWriter fileOut;
		try {
			fileOut = new PrintWriter(fileName);
			fileOut.print(temperature.toStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis()));
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}

}
