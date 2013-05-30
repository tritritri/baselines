package ch.epfl.lsir.wattalyst.webserver;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.wattalyst.services.secured.AValueDto;
import org.wattalyst.services.secured.BaselineDto;
import org.wattalyst.services.secured.BaselineListResultContainer;
import org.wattalyst.services.secured.LocationDto;
import org.wattalyst.services.secured.LocationListResultContainer;
import org.wattalyst.services.secured.NumericValueDto;
import org.wattalyst.services.secured.SecuredDRSignalManagement;
import org.wattalyst.services.secured.SecuredDRSignalManagementService;
import org.wattalyst.services.secured.SecuredDataAccess;
import org.wattalyst.services.secured.SecuredDataAccessService;
import org.wattalyst.services.secured.SensorDto;
import org.wattalyst.services.secured.SensorListResultContainer;
import org.wattalyst.services.secured.ValueListResultContainer;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;
import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;

public class WebserverDataReader {
		
	/**
	 * 
	 * @param sensorName
	 * @param authenticationToken
	 * @param startDate these dates are in executing machine time zone
	 * @param endDate these dates are in executing machine time zone
	 * @param useDifferenceMethod
	 * @return
	 * @throws RemoteException 
	 */
	public SensorReadings getValuesForSensorByRange(String authenticationToken, String sensorName, Date startDate, Date endDate, 
			boolean useDifferenceMethod) {
		
		// Invoke the web service and retrieve the result
		SecuredDataAccessService service = new SecuredDataAccessService();
		SecuredDataAccess port = service.getSecuredDataAccessPort();
		
		// Create a sensor readings data structure 
		SensorReadings readings = new SensorReadings();
					
		try{
			ValueListResultContainer result = port.getValuesForSensorByRange(authenticationToken, sensorName, startDate.getTime(), endDate.getTime());
			
			// Put the result in a sorted set
			if("OK".equals(result.getStatus().value())){
				TreeSet<AValueDto> sortedSet = new TreeSet<AValueDto>();
				for(AValueDto r : result.getValues()){
					sortedSet.add(r);
				}
	// TODO comment this part	
//					if(r instanceof NumericValueDto){
//						Calendar c = Calendar.getInstance();
//						c.setTimeInMillis(r.getTimestamp());
//						System.out.println(r.getTimestamp() + "," + ((NumericValueDto) r).getValue()); 
//						System.out.println(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) +
//							"," + c.get(Calendar.HOUR_OF_DAY) + "," + c.get(Calendar.MINUTE) + "," +
//							((NumericValueDto)r).getValue());
//					}
//				}
//				System.out.println();
	// TODO end comment	
				
				// Generate a sensor readings data set using the difference method (e.g. for energy)
				if(useDifferenceMethod){
					Calendar current = Calendar.getInstance();
					current.setTime(startDate);
					while(!current.getTime().after(endDate)){
						Double lower = findLastValueBeforeOrEqual(sortedSet, current.getTime().getTime());
						Double upper = findLastValueBeforeOrEqual(sortedSet, current.getTime().getTime() + 3600000 - 1); //3600000 = 1*60*60*1000: 1 hour shift
						
						if(lower.isNaN() || upper.isNaN()){
							readings.insert(current.getTimeInMillis(), Double.NaN);
						}
						else {
							readings.insert(current.getTimeInMillis(), (upper - lower));
						}
						
						current.add(Calendar.HOUR_OF_DAY, 1);
						//current.add(Calendar.MINUTE, 10);
					}
				}
				// Generate a sensor readings data set aggregating different readings in the same hour (e.g. for power)
				else{
					throw new RuntimeException("Not implemented yet!");
				}
			}
		}
		catch(Exception e){
			
		}
		
		return readings;
	}
	
	/*
	 * 
	 */
	private double findLastValueBeforeOrEqual(TreeSet<AValueDto> sortedSet, long time) {
		Double lastValue = Double.NaN;
		for(AValueDto v : sortedSet){
			if(v.getTimestamp() <= time){
				if(v instanceof NumericValueDto){
					lastValue = ((NumericValueDto)v).getValue();
				}
			}
			else{
				break;
			}
		}
		return lastValue;
	}
	
	/**
	 * 
	 * @param authenticationToken
	 * @param sensor
	 * @return
	 */
	public List<String> getBaselines(String authenticationToken, String sensor){
		// Invoke the web service and retrieve the result
		SecuredDRSignalManagementService service = new SecuredDRSignalManagementService();
		SecuredDRSignalManagement port = service.getSecuredDRSignalManagementPort();
		
		List<String> baselines = new ArrayList<String>();
		BaselineListResultContainer result = port.getBaselines(authenticationToken, sensor);
		if("OK".equals(result.getStatus().value())){
			for(BaselineDto r : result.getBaselines()){
				baselines.add(r.getFullQualifiedName());
			}
		}
		return baselines;
	}
	
	/**
	 * 
	 * @param authenticationToken
	 * @return
	 */
	public List<String> getSensors(String authenticationToken){
		// Invoke the web service and retrieve the result
		SecuredDataAccessService service = new SecuredDataAccessService();
		SecuredDataAccess port = service.getSecuredDataAccessPort();
		
		List<String> sensorList = new ArrayList<String>();
		LocationListResultContainer locations = port.getAllLocations(authenticationToken);
		if("OK".equals(locations.getStatus().value())){
			for(LocationDto loc : locations.getLocations()){
				SensorListResultContainer sensors = port.getLocationSensors(authenticationToken, loc.getFullQualifiedName());
				if("OK".equals(sensors.getStatus().value())){
					for(SensorDto s : sensors.getSensors()){
						sensorList.add(s.getFullQualifiedName());
					}
				}
			}
		}
		return sensorList;
	}
	
	/**
	 * 
	 * @param authenticationToken
	 * @param baselineID
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public SensorReadings getBaselineData(String authenticationToken, String baselineID, Date startDate, Date endDate){
		// Invoke the web service and retrieve the result
		SecuredDRSignalManagementService service = new SecuredDRSignalManagementService();
		SecuredDRSignalManagement port = service.getSecuredDRSignalManagementPort();
		
		ValueListResultContainer result = port.getBaselineData(authenticationToken, baselineID, startDate.getTime(),
				endDate.getTime());
		
		SensorReadings readings = new SensorReadings();
		// Put the result in sensor readingas
		if("OK".equals(result.getStatus().value())){
			for(AValueDto r : result.getValues()){
				if(r instanceof NumericValueDto){
					readings.insert(r.getTimestamp(), ((NumericValueDto)r).getValue());
				}
			}
		}
		
		return readings;
	}
	

	/**
	 * 
	 * @param args
	 * @throws RemoteException
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws RemoteException, ParseException{
		WebserverDataReader reader = new WebserverDataReader();
		Date startDate = new SimpleDateFormat(Constants.DATETIME_FORMAT).parse("2012-12-01 00:00:00");
		Date endDate = new SimpleDateFormat(Constants.DATETIME_FORMAT).parse("2013-03-31 23:59:00");
		SensorReadings readings = reader.getValuesForSensorByRange("", "wattalyst.lulea.location_43.sensor_348", startDate, endDate, true);
		System.out.println(readings.toStringAsc());
//		try{
//			java.io.BufferedWriter r = new java.io.BufferedWriter(new java.io.FileWriter("/tmp/out.txt"));
//			for(long date = readings.getMinDate(); date <= readings.getMaxDate(); date = date + 10*60*1000){
//				r.write("insert into daily_profile values(" + date + 
//						", \'wattalyst.lulea.location_43.sensor_348\', " + (date/1000) + 
//						", " + readings.get(date) + ");");
//				r.newLine();
//			}
//			r.flush();
//			r.close();
//		}
//		catch(Exception e){}
	}
}
