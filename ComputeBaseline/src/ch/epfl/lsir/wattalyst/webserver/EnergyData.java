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
	
	public SensorReadings getData(){
		return energy;
	}
	
	/**
	 * 
	 * @param sensorName
	 * @param startDate
	 * @param endDate
	 * @param useDifferenceMethod
	 * @throws RemoteException
	 */
	public void compute(String sensorName, Date startDate, Date endDate, boolean useDifferenceMethod) throws RemoteException{
		
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
		energy = webserverDataReader.getValuesForSensorByRange(sensorName, startDate, endDate, useDifferenceMethod);
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

	/**
	 * @param sensor 
	 * 
	 */
	public void removeOutliers(String sensor) {
		double maxCap = getEnergyMaxCap(sensor);
		double minCap = 0;
		for(long timestamp = energy.getMinDate(); timestamp <= energy.getMaxDate(); timestamp = timestamp + 3600000){
			if(energy.get(timestamp) > maxCap){
				energy.insert(timestamp, 0);
			}
			else if(energy.get(timestamp) < minCap){
				energy.insert(timestamp, 0);
			}
		}
	}

	/**
	 * 
	 * @param sensor
	 * @return
	 */
	private double getEnergyMaxCap(String sensor) {
		
		// Cap based on Arne's power sensor values from Dec 01 2012 up to May 29 2013
		// max(sensor)/1000. The power sensor value is one minute consumption
		// so the assumption is that the maximum energy consumption in
		// one hour is equal to the max 1 minute power consumption
		// for the whole hour
		
		// Energy total DH
		if("wattalyst.lulea.location_43.sensor_344".equals(sensor) ||
				"wattalyst.lulea.location_76.sensor_820".equals(sensor) || 
				"wattalyst.lulea.location_156.sensor_1942".equals(sensor) || 
				"wattalyst.lulea.location_75.sensor_1460".equals(sensor) || 
				"wattalyst.lulea.location_120.sensor_1460".equals(sensor) || 
				"wattalyst.lulea.location_159.sensor_1978".equals(sensor) || 
				"wattalyst.lulea.location_115.sensor_1390".equals(sensor) || 
				"wattalyst.lulea.location_123.sensor_1502".equals(sensor) || 
				"wattalyst.lulea.location_46.sensor_545".equals(sensor) ||
				"wattalyst.lulea.location_160.sensor_1992".equals(sensor)){
			return 59.2;
		}
		// Energy heating DH
		else if("wattalyst.lulea.location_43.sensor_346".equals(sensor) ||
				"wattalyst.lulea.location_76.sensor_822".equals(sensor) || 
				"wattalyst.lulea.location_156.sensor_1944".equals(sensor) || 
				"wattalyst.lulea.location_75.sensor_1462".equals(sensor) || 
				"wattalyst.lulea.location_120.sensor_1462".equals(sensor) || 
				"wattalyst.lulea.location_159.sensor_1980".equals(sensor) || 
				"wattalyst.lulea.location_115.sensor_1392".equals(sensor) || 
				"wattalyst.lulea.location_123.sensor_1504".equals(sensor) || 
				"wattalyst.lulea.location_46.sensor_560".equals(sensor) ||
				"wattalyst.lulea.location_160.sensor_1994".equals(sensor)){
			return 9.4;
		}
		// Energy water DH
		else if("wattalyst.lulea.location_43.sensor_348".equals(sensor) ||
				"wattalyst.lulea.location_76.sensor_824".equals(sensor) || 
				"wattalyst.lulea.location_156.sensor_1946".equals(sensor) || 
				"wattalyst.lulea.location_75.sensor_1464".equals(sensor) || 
				"wattalyst.lulea.location_120.sensor_1464".equals(sensor) || 
				"wattalyst.lulea.location_159.sensor_1982".equals(sensor) || 
				"wattalyst.lulea.location_115.sensor_1394".equals(sensor) || 
				"wattalyst.lulea.location_123.sensor_1506".equals(sensor) || 
				"wattalyst.lulea.location_46.sensor_562".equals(sensor) ||
				"wattalyst.lulea.location_160.sensor_1996".equals(sensor)){
			return 56.2;
		}
		// Energy EL
		else if("wattalyst.lulea.location_43.sensor_590".equals(sensor) ||
				"wattalyst.lulea.location_76.sensor_826".equals(sensor) || 
				"wattalyst.lulea.location_156.sensor_1948".equals(sensor) || 
				"wattalyst.lulea.location_75.sensor_1466".equals(sensor) || 
				"wattalyst.lulea.location_120.sensor_1466".equals(sensor) || 
				"wattalyst.lulea.location_159.sensor_1984".equals(sensor) || 
				"wattalyst.lulea.location_115.sensor_1396".equals(sensor) || 
				"wattalyst.lulea.location_123.sensor_1508".equals(sensor) ||
				"wattalyst.lulea.location_46.sensor_514".equals(sensor) ||
				"wattalyst.lulea.location_160.sensor_1998".equals(sensor)){
			return 12.3;
		}		
		// SAMPOL
		else if("wattalyst.mallorca.sampol.Building.sensor_117".equals(sensor)){
			return 80;
		}
		else if("wattalyst.mallorca.sampol.Building.sensor_111".equals(sensor)){
			return 30;
		}
		else if("wattalyst.mallorca.sampol.Building.sensor_99".equals(sensor)){
			return 48;
		}
		else if("wattalyst.mallorca.sampol.Building.sensor_105".equals(sensor)){
			return 140;
		}
		else if("wattalyst.mallorca.sampol.TechnologicalIntegration.sensor_19".equals(sensor)){
			return 20;
		}
		else if("wattalyst.mallorca.sampol.TechnologicalIntegration.sensor_17".equals(sensor)){
			return 10;
		}
		else if("wattalyst.mallorca.sampol.TechnologicalIntegration.sensor_1069".equals(sensor)){
			return 30;
		}
		else if("wattalyst.mallorca.sampol.AdministrativeAccounting.sensor_1078".equals(sensor)){
			return 20;
		}
		else if("wattalyst.mallorca.sampol.AdministrativeAccounting.sensor_26".equals(sensor)){
			return 10;
		}
		else if("wattalyst.mallorca.sampol.AdministrativeAccounting.sensor_1030".equals(sensor)){
			return 30;
		}
		else if("wattalyst.mallorca.sampol.ITDepartment.sensor_1097".equals(sensor)){
			return 10;
		}
		else if("wattalyst.mallorca.sampol.ITDepartment.sensor_1088".equals(sensor)){
			return 20;
		}
		else if("wattalyst.mallorca.sampol.ITDepartment.sensor_1095".equals(sensor)){
			return 30;
		}
		else if("wattalyst.mallorca.sampol.InstallationsDepartment.sensor_56".equals(sensor)){
			return 15;
		}
		else if("wattalyst.mallorca.sampol.InstallationsDepartment.sensor_1103".equals(sensor)){
			return 25;
		}
		else if("wattalyst.mallorca.sampol.InstallationsDepartment.sensor_50".equals(sensor)){
			return 10;
		}
		else if("wattalyst.mallorca.sampol.EnergyDepartment.sensor_68".equals(sensor)){
			return 25;
		}
		else if("wattalyst.mallorca.sampol.EnergyDepartment.sensor_62".equals(sensor)){
			return 15;
		}
		else if("wattalyst.mallorca.sampol.EnergyDepartment.sensor_1110".equals(sensor)){
			return 40;
		}
		else if("wattalyst.mallorca.sampol.Storehouse.sensor_74".equals(sensor)){
			return 60;
		}
		else if("wattalyst.mallorca.sampol.Storehouse.sensor_1116".equals(sensor)){
			return 30;
		}
		else if("wattalyst.mallorca.sampol.Storehouse.sensor_1119".equals(sensor)){
			return 90;
		}
		return Double.POSITIVE_INFINITY;
	}
	
}
