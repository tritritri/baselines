package ch.epfl.lsir.wattalyst.webserver;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.wattalyst.services.BooleanResultContainer;
import org.wattalyst.services.DRSignalManagement;
import org.wattalyst.services.DRSignalManagementService;
import org.wattalyst.services.StringResultContainer;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.baseline.util.Util;

public class WebserverDataWriter {
		
	/**
	 * 
	 * @param description
	 * @param baselineType
	 * @param sensor
	 * @return 
	 */
	String addBaseline(String description, String baselineType, String sensor) {
		
		// Invoke the web service 
		DRSignalManagementService service = new DRSignalManagementService();
		DRSignalManagement port = service.getDRSignalManagementPort();
		
		StringResultContainer result = port.addBaseline(description, baselineType, sensor);
		return result.getResult();
	}

	/**
	 * 
	 * @param baselineID
	 * @param baselineData 
	 * @return
	 */
	boolean updateBaselineData(String baselineID, SensorReadings baselineData){

		// Prepare the string to write in the DB
		String values = baselineData.toStringAscDBFormat();
		
		// Invoke the web service 
		DRSignalManagementService service = new DRSignalManagementService();
		DRSignalManagement port = service.getDRSignalManagementPort();
				
		BooleanResultContainer result = port.uploadBaselineData(baselineID, values);
		return result.isResult();
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		WebserverDataWriter writer = new WebserverDataWriter();
		//String bl = writer.addBaseline("CAISO baseline", "CAISO", "wattalyst.lulea.location_43.sensor_348");
		//System.out.println("Added baseline " + bl);
		
		WebserverDataReader reader = new WebserverDataReader();
		List<String> baselines = reader.getBaselines("wattalyst.lulea.location_43.sensor_344");
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
				reader.getBaselineData("wattalyst.lulea.location_43.sensor_344.baseline_caiso", startDate, endDate));
	}
}
