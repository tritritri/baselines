package ch.epfl.lsir.wattalyst.webserver;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.baseline.util.Util;

public class EnergyData {

	private SensorReadings energy;
	private Calendar startCal;
	private Calendar endCal;
	
	/**
	 * 
	 */
	public EnergyData(){
		startCal = Calendar.getInstance();
		endCal = Calendar.getInstance();
	}
	
	/**
	 * 
	 * @param sensor
	 * @param startDate
	 * @param endDate
	 * @throws RemoteException
	 */
	public void compute(String sensorName, Date startDate, Date endDate) throws RemoteException{
		
		// Check dates
		if(startDate.after(endDate)){
			throw new RuntimeException("Start date must be before or equal to end date");
		}
				
		energy = new SensorReadings();
		
		// Save the original start and end date into the calendars
		startCal.setTime(startDate);
		Util.setToTheBeginningOfTheDay(startCal);
		endCal.setTime(endDate);
		Util.setToTheEndOfTheDay(endCal);
		
		// Invoke Aachen webservice
		WebserverDataReader webserverDataReader = new WebserverDataReader();
		SensorReadings e = webserverDataReader.getValuesForSensorByRange(sensorName, startDate, endDate, true);
		e.copyHourly(e.getMinDate(), e.getMaxDate(), energy);
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getResultString() {
		return energy.toArrStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis());
	}

	/**
	 * 
	 * @param out
	 */
	public void writeResult(PrintStream out) {
		out.print(energy.toStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis()));
	}

	/**
	 * 
	 * @param fileName
	 */
	public void writeResultToFile(String fileName) {
		PrintWriter fileOut;
		try {
			fileOut = new PrintWriter(fileName);
			fileOut.print(energy.toStringAsc(startCal.getTimeInMillis(), endCal.getTimeInMillis()));
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
}
