package ch.epfl.lsir.wattalyst.webserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

import javax.xml.ws.BindingProvider;

import org.wattalyst.services.secured.AValueDto;
import org.wattalyst.services.secured.BaselineDto;
import org.wattalyst.services.secured.BaselineListResultContainer;
import org.wattalyst.services.secured.DrSignalDto;
import org.wattalyst.services.secured.DrSignalListResultContainer;
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

	/**
	 * 
	 * @param port
	 * @return the authentication token
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private String setConfigBinding(SecuredDataAccess port) throws FileNotFoundException, IOException{
		BindingProvider bp = (BindingProvider) port;
		Properties prop = new Properties();
    	prop.load(new FileInputStream("webserver.config"));
		String dataEndpointUrl = prop.getProperty("dataEndpointUrl");
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, dataEndpointUrl);
		String authenticationToken = prop.getProperty("authenticationToken");
		return authenticationToken;
	}
	
	/**
	 * 
	 * @param port
	 * @return the authentication token
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private String setConfigBinding(SecuredDRSignalManagement port) throws FileNotFoundException, IOException{
		BindingProvider bp = (BindingProvider) port;
		Properties prop = new Properties();
    	prop.load(new FileInputStream("webserver.config"));
		String drSignalEndpointUrl = prop.getProperty("drSignalEndpointUrl");
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, drSignalEndpointUrl);
		String authenticationToken = prop.getProperty("authenticationToken");
		return authenticationToken;
	}
	
	/**
	 * 
	 * @param sensorName
	 * @param startDate these dates are in executing machine time zone
	 * @param endDate these dates are in executing machine time zone
	 * @param useDifferenceMethod
	 * @return
	 */
	public SensorReadings getValuesForSensorByRange(String sensorName, Date startDate, Date endDate, 
			boolean useDifferenceMethod) {

		// Create a sensor readings data structure 
		SensorReadings readings = new SensorReadings();
		
		try{
			// Invoke the web service and retrieve the result
			SecuredDataAccessService service = new SecuredDataAccessService();
			SecuredDataAccess port = service.getSecuredDataAccessPort();

			String authenticationToken = setConfigBinding(port);
			
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
						Double upper = findLastValueBeforeOrEqual(sortedSet, current.getTime().getTime() + 3600000); //3600000 = 1*60*60*1000: 1 hour shift

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
				// Return the raw sensor values
				else{
					for(AValueDto v : sortedSet){
						if(v instanceof NumericValueDto){
							readings.insert(v.getTimestamp(), ((NumericValueDto)v).getValue());
						}
					}
				}
			}
			else{
				System.err.println("Error when retrieving sensor data. \n" +
						"Method getValuesForSensorByRange() returned message " + result.getStatusMessage());
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
	 * @param sensor
	 * @return
	 */
	public List<String> getBaselines(String sensor) {
		
		List<String> baselines = new ArrayList<String>();
		try{
			// Invoke the web service and retrieve the result
			SecuredDRSignalManagementService service = new SecuredDRSignalManagementService();
			SecuredDRSignalManagement port = service.getSecuredDRSignalManagementPort();
			
			String authenticationToken = setConfigBinding(port);
			
			BaselineListResultContainer result = port.getBaselines(authenticationToken, sensor);
			if("OK".equals(result.getStatus().value())){
				for(BaselineDto r : result.getBaselines()){
					baselines.add(r.getFullQualifiedName());
				}
			}
			else{
				System.err.println("Error when retrieving baseline IDs. \n" +
						"Method getBaselines() returned message " + result.getStatusMessage());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return baselines;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getSensors() {
		
		List<String> sensorList = new ArrayList<String>();
		
		try{
			// Invoke the web service and retrieve the result
			SecuredDataAccessService service = new SecuredDataAccessService();
			SecuredDataAccess port = service.getSecuredDataAccessPort();
			
			String authenticationToken = setConfigBinding(port);
					
			LocationListResultContainer result = port.getAllLocations(authenticationToken);
			if("OK".equals(result.getStatus().value())){
				for(LocationDto loc : result.getLocations()){
					SensorListResultContainer sensors = port.getLocationSensors(authenticationToken, loc.getFullQualifiedName());
					if("OK".equals(sensors.getStatus().value())){
						for(SensorDto s : sensors.getSensors()){
							sensorList.add(s.getFullQualifiedName());
						}
					}
				}
			}
			else{
				System.err.println("Error when retrieving sensor IDs. \n" +
						"Method getBaselines() returned message " + result.getStatusMessage());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return sensorList;
	}
	
	/**
	 * 
	 * @param location
	 * @param category
	 * @return
	 */
	public List<String> getLocationSensorsByCategory(String location, String category) {
		
		List<String> sensorList = new ArrayList<String>();
		
		try{
			// Invoke the web service and retrieve the result
			SecuredDataAccessService service = new SecuredDataAccessService();
			SecuredDataAccess port = service.getSecuredDataAccessPort();
			
			String authenticationToken = setConfigBinding(port);
						
			SensorListResultContainer result = port.getLocationSensorsByCategory(authenticationToken, location, 
					category);
			
			if("OK".equals(result.getStatus().value())){
				for(SensorDto s : result.getSensors()){
					sensorList.add(s.getFullQualifiedName());
				}
			}
			else{
				System.err.println("Error when retrieving sensor IDs. \n" +
						"Method getLocationSensorsByCategory() returned message " + result.getStatusMessage());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return sensorList;
	}

	
	/**
	 * 
	 * @param baselineID
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public SensorReadings getBaselineData(String baselineID, Date startDate, Date endDate) {
		
		SensorReadings readings = new SensorReadings();
		
		try{
			// Invoke the web service and retrieve the result
			SecuredDRSignalManagementService service = new SecuredDRSignalManagementService();
			SecuredDRSignalManagement port = service.getSecuredDRSignalManagementPort();
			
			String authenticationToken = setConfigBinding(port);
							
			ValueListResultContainer result = port.getBaselineData(authenticationToken, baselineID, startDate.getTime(),
					endDate.getTime());
			
			// Put the result in sensor readingas
			if("OK".equals(result.getStatus().value())){
				for(AValueDto r : result.getValues()){
					if(r instanceof NumericValueDto){
						readings.insert(r.getTimestamp(), ((NumericValueDto)r).getValue());
					}
				}
			}
			else{
				System.err.println("Error when retrieving baseline data. \n" +
						"Method getBaselineData() returned message " + result.getStatusMessage());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return readings;
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMap<LocationDto, List<DrSignalDto>> getNotEvaluatedDRSignals() {
		
		HashMap<LocationDto, List<DrSignalDto>> toBeEvaluated = new HashMap<LocationDto, List<DrSignalDto>>();
		
		try{
			// Invoke the web service and retrieve the result
			SecuredDRSignalManagementService drService = new SecuredDRSignalManagementService();
			SecuredDRSignalManagement drPort = drService.getSecuredDRSignalManagementPort();
					
			String authenticationToken = setConfigBinding(drPort);
			
			SecuredDataAccessService dataService = new SecuredDataAccessService();
			SecuredDataAccess dataPort = dataService.getSecuredDataAccessPort();
			
			setConfigBinding(dataPort);
					
			List<String> signalStatuses = new ArrayList<String>();
			signalStatuses.add(SignalStatus.EXPIRED.name());
			
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
					else{
						System.err.println("Error when retrieving DR signal data. \n" +
								"Method getDRSignalsByLocationAndStatuses() returned message " + signals.getStatusMessage());
					}
					
					if(!toEval.isEmpty())
						toBeEvaluated.put(loc,  toEval);
				}
			}
			else{
				System.err.println("Error when retrieving location data. \n" +
						"Method getAllLocations() returned message " + locations.getStatusMessage());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return toBeEvaluated;
	}
	
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

	
}
