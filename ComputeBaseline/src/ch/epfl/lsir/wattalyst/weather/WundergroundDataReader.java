package ch.epfl.lsir.wattalyst.weather;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.baseline.util.SensorReadings;
import com.baseline.util.Util;

import de.mbenning.weather.wunderground.api.domain.DataSet;
import de.mbenning.weather.wunderground.api.domain.WeatherStation;
import de.mbenning.weather.wunderground.impl.services.HttpDataReaderService;
import de.mbenning.weather.wunderground.impl.services.WeatherStationService;

/**
 * 
 * @author vasirani
 *
 */
public class WundergroundDataReader {

	private HttpDataReaderService dataReader;
	private WeatherStationService weatherStationService;
	
	/**
	 * 
	 */
	public WundergroundDataReader(){
		dataReader = new HttpDataReaderService();
		weatherStationService = new WeatherStationService();		
	}
	
	/**
	 * 
	 * @param weatherDate
	 * @param weatherStation
	 * @return
	 */
	public double getMinTemperature(Date weatherDate, WeatherStation weatherStation){
		dataReader.setWeatherDate(weatherDate);
		dataReader.setWeatherStation(weatherStation);     
		DataSet minTemp = dataReader.minTemperature();
		if(minTemp != null){
			return minTemp.getTemperature();
		}
		return Double.NaN;
	}
	
	/**
	 * 
	 * @param weatherDate
	 * @param weatherStation
	 * @return
	 */
	public double getMaxTemperature(Date weatherDate, WeatherStation weatherStation){
		dataReader.setWeatherDate(weatherDate);
		dataReader.setWeatherStation(weatherStation);     
		DataSet maxTemp = dataReader.maxTemperature();
		if(maxTemp != null){
			return maxTemp.getTemperature();
		}
		return Double.NaN;
	}
	
	/**
	 * 
	 * @param weatherDate
	 * @param weatherStation
	 * @return
	 */
	public double getAvgTemperature(Date weatherDate, WeatherStation weatherStation) {
		dataReader.setWeatherDate(weatherDate);
		dataReader.setWeatherStation(weatherStation);     
		List<DataSet> datasets;
		try {
			datasets = dataReader.readDataSets();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return Double.NaN;
		} catch (IOException e) {
			e.printStackTrace();
			return Double.NaN;
		} catch (ParseException e) {
			e.printStackTrace();
			return Double.NaN;
		}
		if(!datasets.isEmpty()){
			Double avgTemp = 0.0;
			int count = 0;
			for(DataSet data : datasets){
				avgTemp = avgTemp + (data.getTemperature() - avgTemp)/(count + 1);
				count++;
			}
			return avgTemp;
		}
		return Double.NaN;
	}
	
	/**
	 * 
	 * @param weatherDate
	 * @param weatherStation
	 * @return
	 */
	public double[] getHourlyTemperatures(Date weatherDate, WeatherStation weatherStation) {
		
		Calendar calendar = Calendar.getInstance();
		Util.setCalToStartOfTheDay(calendar);		
		if(weatherDate.equals(calendar.getTime()) || weatherDate.after(calendar.getTime())){
			throw new RuntimeException("Weather date must be before the present day");
		}
		
		dataReader.setWeatherDate(weatherDate);
		dataReader.setWeatherStation(weatherStation);     
		List<DataSet> datasets;
		try {
			datasets = dataReader.readDataSets();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		if(!datasets.isEmpty()){
			double[] hourlyTemperatures = new double[24];
			int[] counters = new int[24];
			for(DataSet data : datasets){
				calendar.setTime(data.getDateTime());
				hourlyTemperatures[calendar.get(Calendar.HOUR_OF_DAY)] = hourlyTemperatures[calendar.get(Calendar.HOUR_OF_DAY)] + 
						(data.getTemperature() - hourlyTemperatures[calendar.get(Calendar.HOUR_OF_DAY)])/(counters[calendar.get(Calendar.HOUR_OF_DAY)] + 1);
				counters[calendar.get(Calendar.HOUR_OF_DAY)]++;
			}
			return hourlyTemperatures;
		}
		return null;
	}	
	
	/**
	 * 
	 * @param weatherStartDate
	 * @param weatherEndDate
	 * @param weatherStation
	 * @return
	 */
	public SensorReadings getHourlyTemperatures(Date weatherStartDate, Date weatherEndDate, WeatherStation weatherStation) {
		
		Calendar calendar = Calendar.getInstance();
		Util.setCalToStartOfTheDay(calendar);		
		if(weatherStartDate.equals(calendar.getTime()) || weatherStartDate.after(calendar.getTime())){
			throw new RuntimeException("Weather start date must be before the present day");
		}
		
		if(weatherEndDate.equals(calendar.getTime()) || weatherEndDate.after(calendar.getTime())){
			throw new RuntimeException("Weather end date must be before the present day");
		}
		
		if(weatherStartDate.after(weatherEndDate)){
			throw new RuntimeException("Start date must be before or equal to end date");
		}
		
		SensorReadings hourlyTemperatures = new SensorReadings();
				
		calendar.setTime(weatherEndDate);
		Util.setCalToEndOfTheDay(calendar);
		weatherEndDate = calendar.getTime();
		
		Date weatherDate = weatherStartDate;
		calendar.setTime(weatherDate);
		Util.setCalToStartOfTheDay(calendar);
		weatherDate = calendar.getTime();
				
		while(weatherDate.before(weatherEndDate) || weatherDate.equals(weatherEndDate)){
			double[] hourlyTemperaturesForDay = getHourlyTemperatures(weatherDate, weatherStation);
			if(hourlyTemperaturesForDay != null){
				for(int h = 0; h < hourlyTemperaturesForDay.length; h++){
					calendar.set(Calendar.HOUR_OF_DAY, h);
					hourlyTemperatures.insert(calendar.getTimeInMillis(), hourlyTemperaturesForDay[h]);
				}
			}
			calendar.setTime(weatherDate);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			weatherDate = calendar.getTime();
		}
		return hourlyTemperatures;
	}
	
	/**
	 * 
	 * @param weatherStartDate
	 * @param weatherEndDate
	 * @param weatherStation
	 * @param filename
	 * @return
	 * @throws IOException 
	 */
	public void writeHourlyTemperatures(Date weatherStartDate, Date weatherEndDate, WeatherStation weatherStation, String filename) throws IOException {
		
		SensorReadings hourlyTemperatures = getHourlyTemperatures(weatherStartDate, weatherEndDate, weatherStation);
		
		if(weatherStartDate.after(weatherEndDate)){
			throw new RuntimeException("Start date must be before or equal to end date");
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)));
		bw.write(hourlyTemperatures.toStringAsc());
		bw.close();
	}
	
	/**
	 * 
	 * @param country
	 * @param city
	 * @return
	 */
	public WeatherStation getWeatherStation(String country, String city){
		// find all weather stations
		List<WeatherStation> stations = weatherStationService.findAllWeatherStationsByCountry(country);
		for(WeatherStation station : stations){
			if(station.getCity().equals(city)){
				return station;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
				
		WundergroundDataReader wundergroundDataReader = new WundergroundDataReader();
		WeatherStation weatherStation = wundergroundDataReader.getWeatherStation("Sweden", "Lulea");
		Date weatherDate = new DateTime(2013, 2, 25, 0, 0, 0).toDate();
		double min = wundergroundDataReader.getMinTemperature(weatherDate, weatherStation);
		double max = wundergroundDataReader.getMaxTemperature(weatherDate, weatherStation);
		double avg = wundergroundDataReader.getAvgTemperature(weatherDate, weatherStation);
		double[] hourlyTemperatures = wundergroundDataReader.getHourlyTemperatures(weatherDate, weatherStation);
		
		System.out.println("min:max temperature at " + weatherStation.getCity() + " ID " + weatherStation.getStationId() +
						" on " + weatherDate + " is [" + min + ":" + max + "]");	
		System.out.println("avg temperature at " + weatherStation.getCity() + " ID " + weatherStation.getStationId() +
				" on " + weatherDate + " is " + avg);
		System.out.print("hourly temperatures at " + weatherStation.getCity() + " ID " + weatherStation.getStationId() +
				" on " + weatherDate + " is [ ");
		for(double t : hourlyTemperatures){
			System.out.print(t + " ");
		}
		System.out.println("]");
		
		Date weatherStartDate = new DateTime(2012, 1, 1, 0, 0, 0).toDate();
		Date weatherEndDate = new DateTime(2012, 12, 31, 0, 0, 0).toDate();
		System.out.println(wundergroundDataReader.getHourlyTemperatures(weatherStartDate, weatherEndDate, weatherStation).toStringAsc());
	}
}
