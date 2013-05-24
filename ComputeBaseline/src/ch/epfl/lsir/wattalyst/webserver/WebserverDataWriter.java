package ch.epfl.lsir.wattalyst.webserver;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.wattalyst.services.secured.BooleanResultContainer;
import org.wattalyst.services.secured.SecuredDRSignalManagement;
import org.wattalyst.services.secured.SecuredDRSignalManagementService;
import org.wattalyst.services.secured.StringResultContainer;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.baseline.util.Util;

public class WebserverDataWriter {
		
	/**
	 * 
	 * @param authenticationToken
	 * @param description
	 * @param baselineType
	 * @param sensor
	 * @return
	 */
	String addBaseline(String authenticationToken, String description, String baselineType, String sensor) {
		
		// Invoke the web service 
		SecuredDRSignalManagementService service = new SecuredDRSignalManagementService();
		SecuredDRSignalManagement port = service.getSecuredDRSignalManagementPort();
		
		StringResultContainer result = port.addBaseline(authenticationToken, description, baselineType, sensor);
		return result.getResult();
	}

	/**
	 * 
	 * @param authenticationToken
	 * @param baselineID
	 * @param baselineData
	 * @return
	 */
	boolean updateBaselineData(String authenticationToken, String baselineID, SensorReadings baselineData){

		// Prepare the string to write in the DB
		String values = baselineData.toStringAscDBFormat();
		
		// Invoke the web service 
		SecuredDRSignalManagementService service = new SecuredDRSignalManagementService();
		SecuredDRSignalManagement port = service.getSecuredDRSignalManagementPort();
				
		BooleanResultContainer result = port.uploadBaselineData(authenticationToken, baselineID, values);
		return result.isResult();
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		//WebserverDataWriter writer = new WebserverDataWriter();
		//String bl = writer.addBaseline("CAISO baseline", "CAISO", "wattalyst.lulea.location_43.sensor_348");
		//System.out.println("Added baseline " + bl);
		
		WebserverDataReader reader = new WebserverDataReader();
		List<String> baselines = reader.getBaselines("XXX", "wattalyst.lulea.location_43.sensor_344");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		
		SensorReadings baseline = new SensorReadings();
		Calendar current = Calendar.getInstance();
		Util.setToTheBeginningOfTheHour(current);
		current.add(Calendar.HOUR_OF_DAY, -1);
		Date startDate = current.getTime();
		
		current.add(Calendar.HOUR_OF_DAY, 1);
		baseline.insert(current.getTime().getTime(), 3);
		
		current.add(Calendar.HOUR_OF_DAY, 1);
		baseline.insert(current.getTime().getTime(), 2);
		
		current.add(Calendar.HOUR_OF_DAY, 1);
		baseline.insert(current.getTime().getTime(), 1);
		
		current.add(Calendar.HOUR_OF_DAY, 1);
		Date endDate = current.getTime();
		
//		System.out.println(
//				writer.updateBaselineData("wattalyst.lulea.location_43.sensor_348.baseline_caiso", baseline));
//		System.out.println(baseline.toStringAscDBFormat());
		
		System.out.println(
				reader.getBaselineData("XXX", "wattalyst.lulea.location_43.sensor_344.baseline_caiso", startDate, endDate));
	}
}
