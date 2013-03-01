package ch.epfl.lsir.wattalyst.weather;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.baseline.util.SensorReadings;
import com.baseline.util.Util;

import de.mbenning.weather.wunderground.api.domain.WeatherStation;

public class WundergroundYahoo implements Temperature {

	private SensorReadings temperature;
	private Calendar startCal;
	private Calendar endCal;
	
	/**
	 * 
	 */
	public WundergroundYahoo(){
		startCal = Calendar.getInstance();
		endCal = Calendar.getInstance();
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.weather.Temperature#compute(java.util.Date, java.util.Date, java.lang.String, java.lang.String)
	 */
	@Override
	public void compute(Date startDate, Date endDate, String country, String place) {
		
		temperature = new SensorReadings();
		
		// Save the original start and end date into the calendars
		startCal.setTime(startDate);
		Util.setCalToStartOfTheDay(startCal);
		endCal.setTime(endDate);
		Util.setCalToEndOfTheDay(endCal);
		
		// Set the end date for Wunderground to yesterday if it is today or in the future
		Calendar calendar = Calendar.getInstance();
		Util.setCalToStartOfTheDay(calendar);	
		boolean invokeYahoo = false;
		
		Date weatherStartDate = startDate;
		Date weatherEndDate = endDate;
		if(weatherEndDate.equals(calendar.getTime()) || weatherEndDate.after(calendar.getTime())){
			calendar.roll(Calendar.DAY_OF_YEAR, -1);
			weatherEndDate = calendar.getTime();
			invokeYahoo = true;
		}
		
		// Invoke Wunderground service
		WundergroundDataReader wundergroundDataReader = new WundergroundDataReader();
		WeatherStation weatherStation = wundergroundDataReader.getWeatherStation(country, place);
		if(weatherStation != null){
			SensorReadings wundergroundData = wundergroundDataReader.getHourlyTemperatures(weatherStartDate, weatherEndDate, weatherStation);
			if(wundergroundData != null){
				wundergroundData.copyHourly(wundergroundData.getMinDate(), wundergroundData.getMaxDate(), temperature);
			}
		}
		
		// If end date is today or in the future, invoke also Yahoo service
		if(invokeYahoo){
			YahooWeatherDataReader yahooWeatherDataReader = new YahooWeatherDataReader();
			SensorReadings yahooData = yahooWeatherDataReader.getTemperatureData(place);
			if(yahooData != null){
				yahooData.copyHourly(yahooData.getMinDate(), yahooData.getMaxDate(), temperature);
			}
		}
		
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
