package ch.epfl.lsir.wattalyst.webserver;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import javax.xml.ws.BindingProvider;

import org.wattalyst.services.secured.AValueDto;
import org.wattalyst.services.secured.BaselineDto;
import org.wattalyst.services.secured.BaselineListResultContainer;
import org.wattalyst.services.secured.DrSignalDto;
import org.wattalyst.services.secured.DrSignalListResultContainer;
import org.wattalyst.services.secured.DrStatusDto;
import org.wattalyst.services.secured.LocationDto;
import org.wattalyst.services.secured.LocationListResultContainer;
import org.wattalyst.services.secured.NumericValueDto;
import org.wattalyst.services.secured.SecuredDRSignalManagement;
import org.wattalyst.services.secured.SecuredDRSignalManagementService;
import org.wattalyst.services.secured.SecuredDataAccess;
import org.wattalyst.services.secured.SecuredDataAccessService;
import org.wattalyst.services.secured.SensorDto;
import org.wattalyst.services.secured.SensorListResultContainer;
import org.wattalyst.services.secured.SignalStatus;
import org.wattalyst.services.secured.ValueListResultContainer;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;

public class WebserverDataReader {
	
//	/**
//	 * 
//	 * @param sensor
//	 * @param startDate these dates are in executing machine time zone
//	 * @param endDate these dates are in executing machine time zone
//	 * @param useDifferenceMethod
//	 * @return
//	 */
//	public SensorReadings getValuesForSensorByRange(String sensor, Date startDate, Date endDate, boolean useDifferenceMethod) {
//					
//		try {
//			
//			// Invoke the web service and retrieve the result
//			SecuredDataAccessService service = new SecuredDataAccessService();
//			SecuredDataAccess port = service.getSecuredDataAccessPort();
//			
//			String endpointURL = "https://wattalyst-ci.se.rwth-aachen.de/SecuredDataAccessService/SecuredDataAccess";
//			BindingProvider bp = (BindingProvider) port;
//			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
//						
//			// Create a sensor readings data structure 
//			SensorReadings readings = new SensorReadings();
//			
//			String username = "wattalyst";
//			byte[] pwd = "bqdyoq35".getBytes("UTF-8");
//			byte[] digestedPwd = MessageDigest.getInstance("MD5").digest(pwd);
//			byte[] base64DigestedPwd = Base64.encodeBase64(digestedPwd);
//			String password = new String(base64DigestedPwd);
//					
//			String authenticationToken = port.login(username, password);
//			
//			ValueListResultContainer result = port.getValuesForSensorByRange(authenticationToken, sensor, startDate.getTime(), endDate.getTime());
//			
//			// Put the result in a sorted set
//			if("OK".equals(result.getStatus().value())){
//				TreeSet<AValueDto> sortedSet = new TreeSet<AValueDto>();
//				for(AValueDto r : result.getValues()){
//					sortedSet.add(r);
//				}
//				
//				// Generate a sensor readings data set using the difference method (e.g. for energy)
//				if(useDifferenceMethod){
//					Calendar current = Calendar.getInstance();
//					current.setTime(startDate);
//					while(!current.getTime().after(endDate)){
//						Double lower = findLastValueBeforeOrEqual(sortedSet, current.getTime().getTime());
//						Double upper = findLastValueBeforeOrEqual(sortedSet, current.getTime().getTime() + 3600000 - 1); //3600000 = 1*60*60*1000: 1 hour shift
//						
//						if(lower.isNaN() || upper.isNaN()){
//							readings.insert(current.getTimeInMillis(), Double.NaN);
//						}
//						else {
//							readings.insert(current.getTimeInMillis(), (upper - lower));
//						}
//						
//						current.add(Calendar.HOUR_OF_DAY, 1);
//						//current.add(Calendar.MINUTE, 10);
//					}
//				}
//				// Generate a sensor readings data set aggregating different readings in the same hour (e.g. for power)
//				else{
//					throw new RuntimeException("Not implemented yet!");
//				}
//			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		return readings;
//	}	

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

		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, Constants.DATA_ENDPOINT_URL);

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
			e.printStackTrace();
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
		
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, Constants.DRSIGNAL_ENDPOINT_URL);
		
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
		
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, Constants.DATA_ENDPOINT_URL);
		
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
	 * @param location
	 * @param annotationKey
	 * @param annotationValue
	 * @return
	 */
	public List<String> getLocationSensorsByAnnotation(String authenticationToken,
			String location, String annotationKey, String annotationValue) {
		
		// Invoke the web service and retrieve the result
		SecuredDataAccessService service = new SecuredDataAccessService();
		SecuredDataAccess port = service.getSecuredDataAccessPort();
		
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, Constants.DATA_ENDPOINT_URL);
		
		List<String> sensorList = new ArrayList<String>();
		SensorListResultContainer sensors = port.getLocationSensorsByAnnotation(authenticationToken, location,
				annotationKey, annotationValue);
		if("OK".equals(sensors.getStatus().value())){
			for(SensorDto s : sensors.getSensors()){
				sensorList.add(s.getFullQualifiedName());
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
		
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, Constants.DRSIGNAL_ENDPOINT_URL);
				
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
	 * @param authenticationToken
	 * @return
	 */
	public HashMap<LocationDto, List<DrSignalDto>> getNotEvaluatedDRSignals(String authenticationToken) {
		
		// Invoke the web service and retrieve the result
		SecuredDRSignalManagementService drService = new SecuredDRSignalManagementService();
		SecuredDRSignalManagement drPort = drService.getSecuredDRSignalManagementPort();
				
		BindingProvider drBp = (BindingProvider) drPort;
		drBp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, Constants.DRSIGNAL_ENDPOINT_URL);
		
		SecuredDataAccessService dataService = new SecuredDataAccessService();
		SecuredDataAccess dataPort = dataService.getSecuredDataAccessPort();
		
		BindingProvider dataBp = (BindingProvider) dataPort;
		dataBp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, Constants.DRSIGNAL_ENDPOINT_URL);
				
		List<String> signalStatuses = new ArrayList<String>();
		signalStatuses.add(SignalStatus.EXPIRED.name());
		
		HashMap<LocationDto, List<DrSignalDto>> toBeEvaluated = new HashMap<LocationDto, List<DrSignalDto>>();
		
		LocationListResultContainer locations = dataPort.getAllLocations(authenticationToken);
		if("OK".equals(locations.getStatus().value())){
			
			for(LocationDto loc : locations.getLocations()){
				
				List<DrSignalDto> toEval = new ArrayList<DrSignalDto>();
				DrSignalListResultContainer signals = drPort.getDRSignalsByLocationAndStatuses(authenticationToken, loc.getFullQualifiedName(), signalStatuses);
			
				// Put the result in list
				if("OK".equals(signals.getStatus().value())){
					for(DrSignalDto signal : signals.getSignals()){
						toEval.add(signal);
					}
				}
				
				toBeEvaluated.put(loc,  toEval);
			}
		}
		
		return toBeEvaluated;
	}

	/**
	 * 
	 * @param args
	 * @throws RemoteException
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws RemoteException, ParseException{
		WebserverDataReader reader = new WebserverDataReader();
		Date startDate = new SimpleDateFormat(ch.epfl.lsir.wattalyst.baseline.constants.Constants.DATETIME_FORMAT).parse("2013-07-03 00:00:00");
		Date endDate = new SimpleDateFormat(ch.epfl.lsir.wattalyst.baseline.constants.Constants.DATETIME_FORMAT).parse("2013-07-03 23:59:00");
		SensorReadings readings = reader.getValuesForSensorByRange("mheqzghwnhh+", "wattalyst.lulea.location_120.sensor_1460", startDate, endDate, true);
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
		
		SensorReadings baseline = reader.getBaselineData("mheqzghwnhh+", "wattalyst.lulea.location_120.sensor_1460.baseline_CAISO", 
				startDate, endDate);
		System.out.println(baseline.toStringAsc());
	}

}
