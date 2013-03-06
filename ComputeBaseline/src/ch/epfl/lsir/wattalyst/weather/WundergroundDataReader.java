package ch.epfl.lsir.wattalyst.weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.web.client.RestTemplate;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.baseline.util.Util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WundergroundDataReader {

	private static final String API_ID = "d8a2881e5945445d";
	private static final String WUNDERGROUND_DATE_FORMAT = "yyyyMMdd";
	private RestTemplate restTemplate;
	
	/**
	 * 
	 */
	public WundergroundDataReader(){
		restTemplate = new RestTemplate();
	}
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param country
	 * @param place
	 * @return
	 */
	SensorReadings getHourlyTemperatures(Date startDate, Date endDate, String country, String place) {
	
		Calendar calendar = Calendar.getInstance();
		
		// Set today date
		Util.setToTheBeginningOfTheDay(calendar);
		Date today = calendar.getTime();
		
		// Set start date
		calendar.setTime(startDate);
		Util.setToTheBeginningOfTheDay(calendar);
		startDate = calendar.getTime();
		
		// Set end date
		calendar.setTime(endDate);
		Util.setToTheEndOfTheDay(calendar);
		endDate = calendar.getTime();
		
		// Initialize current day
		Date currentDate = startDate;
		calendar.setTime(currentDate);
		SimpleDateFormat formatter = new SimpleDateFormat(WUNDERGROUND_DATE_FORMAT);
		SensorReadings hourlyTemperatures = new SensorReadings();
		
		// Cycle over all the days using "history_YYYYMMDD"
		JsonParser parser = new JsonParser();
		while(currentDate.before(endDate)){
			String JSONresult = restTemplate.getForObject("http://api.wunderground.com/api/{API_ID}/history_{date}/q/{country}/{place}.json",
					String.class, API_ID, formatter.format(currentDate), country, place);
			JsonObject obj = parser.parse(JSONresult).getAsJsonObject();
			if(obj.has("history")){
				JsonObject history = obj.get("history").getAsJsonObject();
				if(history.has("observations")){
					JsonArray observations = history.get("observations").getAsJsonArray();
					for(JsonElement obs : observations){
						JsonObject date = obs.getAsJsonObject().get("date").getAsJsonObject();
						int year = date.get("year").getAsInt();
						int month = date.get("mon").getAsInt();
						int day = date.get("mday").getAsInt();
						int hour = date.get("hour").getAsInt();
						double temp = obs.getAsJsonObject().get("tempm").getAsDouble();		
						temp = (temp == -9999 ? Double.NaN : temp);
						
						calendar.set(Calendar.YEAR, year);
						calendar.set(Calendar.MONTH, month - 1);
						calendar.set(Calendar.DAY_OF_MONTH, day);
						calendar.set(Calendar.HOUR_OF_DAY, hour);
						calendar.set(Calendar.MINUTE, 0);
						calendar.set(Calendar.SECOND, 0);
						calendar.set(Calendar.MILLISECOND, 0);
						
						hourlyTemperatures.insert(calendar.getTimeInMillis(), temp);
					}
				}
			}
			calendar.setTime(currentDate);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			currentDate = calendar.getTime();
		}
		
		// If end date is today or in the future, invoke also "hourly10day"
		if(endDate.equals(today) || endDate.after(today)){
		
			String JSONresult = restTemplate.getForObject("http://api.wunderground.com/api/{API_ID}/hourly10day/q/{country}/{place}.json",
					String.class, API_ID, country, place);
			JsonObject obj = parser.parse(JSONresult).getAsJsonObject();
			
			// Get the forecast
			if(obj.has("hourly_forecast")){
				JsonArray hourly_forecast = obj.get("hourly_forecast").getAsJsonArray();
				
				for(JsonElement forecast : hourly_forecast){
					JsonObject FCTTIME = forecast.getAsJsonObject().get("FCTTIME").getAsJsonObject();
					int year = FCTTIME.get("year").getAsInt();
					int month = FCTTIME.get("mon").getAsInt();
					int day = FCTTIME.get("mday").getAsInt();
					int hour = FCTTIME.get("hour").getAsInt();
					
					calendar.set(Calendar.YEAR, year);
					calendar.set(Calendar.MONTH, month - 1);
					calendar.set(Calendar.DAY_OF_MONTH, day);
					calendar.set(Calendar.HOUR_OF_DAY, hour);
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);
					calendar.set(Calendar.MILLISECOND, 0);
					
					if(calendar.getTime().before(startDate)){
						continue;
					}
					else if(calendar.getTime().after(endDate)){
						break;
					}
					JsonObject temperature = forecast.getAsJsonObject().get("temp").getAsJsonObject();
					double temp = temperature.get("metric").getAsDouble();
					temp = (temp == -9999 ? Double.NaN : temp);
					
					hourlyTemperatures.insert(calendar.getTimeInMillis(), temp);
				}
			}
		}
		
		return hourlyTemperatures;
	}
	
	/**
	 * 
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException{
		WundergroundDataReader r = new WundergroundDataReader();
		Date startDate = new SimpleDateFormat(WUNDERGROUND_DATE_FORMAT).parse("20130307");
		Date endDate = new SimpleDateFormat(WUNDERGROUND_DATE_FORMAT).parse("20130308");
		System.out.println(r.getHourlyTemperatures(startDate, endDate, "Sweden", "Lulea").toStringAsc());
	}
}
