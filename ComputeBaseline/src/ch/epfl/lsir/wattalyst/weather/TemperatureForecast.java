package ch.epfl.lsir.wattalyst.weather;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.baseline.constants.Constants;

public class TemperatureForecast {

	private Date date;
	private String strDate;
	private double minTemp;
	private double maxTemp;
	
	/**
	 * 
	 * @param date
	 * @param minTemp
	 * @param maxTemp
	 */
	public TemperatureForecast(Date date, double minTemp, double maxTemp) {
		this.date = date;
		strDate = new SimpleDateFormat(Constants.DATE_FORMAT).format(date);
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * 
	 * @return
	 */
	public double getMinTemp() {
		return minTemp;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getMaxTemp() {
		return maxTemp;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return strDate + " min: " + minTemp + " max: " + maxTemp;
	}
	
	
}
