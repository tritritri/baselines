package ch.epfl.lsir.wattalyst.webserver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.ws.BindingProvider;

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
	public String addBaseline(String authenticationToken, String description, String baselineType, String sensor) {
		
		// Invoke the web service 
		SecuredDRSignalManagementService service = new SecuredDRSignalManagementService();
		SecuredDRSignalManagement port = service.getSecuredDRSignalManagementPort();
		
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ch.epfl.lsir.wattalyst.webserver.Constants.DRSIGNAL_ENDPOINT_URL);
				
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
	public boolean updateBaselineData(String authenticationToken, String baselineID, SensorReadings baselineData){

		// Prepare the string to write in the DB
		String values = baselineData.toStringAscDBFormat();
		
		// Invoke the web service 
		SecuredDRSignalManagementService service = new SecuredDRSignalManagementService();
		SecuredDRSignalManagement port = service.getSecuredDRSignalManagementPort();
				
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ch.epfl.lsir.wattalyst.webserver.Constants.DRSIGNAL_ENDPOINT_URL);
		
		BooleanResultContainer result = port.uploadBaselineData(authenticationToken, baselineID, values);
		return result.isResult();
	}
	
	/**
	 * 
	 * @param authenticationToken
	 * @param drSignalID
	 * @param location
	 * @param kpiValue
	 * @param description
	 * @param successStatus
	 * @return
	 */
	public boolean setPerformanceIndicator(String authenticationToken, String drSignalID, String location, double kpiValue, 
			String description, String successStatus) {
		
		// Invoke the web service 
		SecuredDRSignalManagementService service = new SecuredDRSignalManagementService();
		SecuredDRSignalManagement port = service.getSecuredDRSignalManagementPort();
		
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ch.epfl.lsir.wattalyst.webserver.Constants.DRSIGNAL_ENDPOINT_URL);
				
		BooleanResultContainer result = port.setPerformanceIndicator(authenticationToken, drSignalID, location, kpiValue, description, successStatus);
		return result.isResult();
	}
	
	/**
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException{
//		WebserverDataWriter writer = new WebserverDataWriter();
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_43.sensor_344");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_43.sensor_346");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_43.sensor_348");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_43.sensor_590");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_46.sensor_545");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_46.sensor_560");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_46.sensor_562");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_46.sensor_514");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_76.sensor_820");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_76.sensor_822");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_76.sensor_824");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_76.sensor_826");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_160.sensor_1992");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_160.sensor_1994");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_160.sensor_1996");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_160.sensor_1998");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_156.sensor_1942");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_156.sensor_1944");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_156.sensor_1946");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_156.sensor_1948");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_75.sensor_1460");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_75.sensor_1462");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_75.sensor_1464");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_75.sensor_1466");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_120.sensor_1460");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_120.sensor_1462");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_120.sensor_1464");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_120.sensor_1466");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_159.sensor_1978");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_159.sensor_1980");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_159.sensor_1982");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_159.sensor_1984");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_115.sensor_1390");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_115.sensor_1392");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_115.sensor_1394");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_115.sensor_1396");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_123.sensor_1502");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_123.sensor_1504");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_123.sensor_1506");
//		Thread.sleep(2000);
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_123.sensor_1508");
				
		WebserverDataReader reader = new WebserverDataReader();
		List<String> baselines = new ArrayList<String>();
		
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_43.sensor_344");
		System.out.println("Sensor wattalyst.lulea.location_43.sensor_344");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_43.sensor_346");
		System.out.println("Sensor wattalyst.lulea.location_43.sensor_346");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_43.sensor_348");
		System.out.println("Sensor wattalyst.lulea.location_43.sensor_348");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_43.sensor_590");
		System.out.println("Sensor wattalyst.lulea.location_43.sensor_590");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_46.sensor_545");
		System.out.println("Sensor wattalyst.lulea.location_46.sensor_545");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_46.sensor_560");
		System.out.println("Sensor wattalyst.lulea.location_46.sensor_560");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_46.sensor_562");
		System.out.println("Sensor wattalyst.lulea.location_46.sensor_562");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_46.sensor_514");
		System.out.println("Sensor wattalyst.lulea.location_46.sensor_514");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_76.sensor_820");
		System.out.println("Sensor wattalyst.lulea.location_76.sensor_820");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_76.sensor_822");
		System.out.println("Sensor wattalyst.lulea.location_76.sensor_822");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_76.sensor_824");
		System.out.println("Sensor wattalyst.lulea.location_76.sensor_824");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_76.sensor_826");
		System.out.println("Sensor wattalyst.lulea.location_76.sensor_826");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_160.sensor_1992");
		System.out.println("Sensor wattalyst.lulea.location_160.sensor_1992");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_160.sensor_1994");
		System.out.println("Sensor wattalyst.lulea.location_160.sensor_1994");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_160.sensor_1996");
		System.out.println("Sensor wattalyst.lulea.location_160.sensor_1996");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_160.sensor_1998");
		System.out.println("Sensor wattalyst.lulea.location_160.sensor_1998");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_156.sensor_1942");
		System.out.println("Sensor wattalyst.lulea.location_156.sensor_1942");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_156.sensor_1944");
		System.out.println("Sensor wattalyst.lulea.location_156.sensor_1944");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_156.sensor_1946");
		System.out.println("Sensor wattalyst.lulea.location_156.sensor_1946");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_156.sensor_1948");
		System.out.println("Sensor wattalyst.lulea.location_156.sensor_1948");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_75.sensor_1460");
		System.out.println("Sensor wattalyst.lulea.location_75.sensor_1460");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_75.sensor_1462");
		System.out.println("Sensor wattalyst.lulea.location_75.sensor_1462");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_75.sensor_1464");
		System.out.println("Sensor wattalyst.lulea.location_75.sensor_1464");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_75.sensor_1466");
		System.out.println("Sensor wattalyst.lulea.location_75.sensor_1466");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_120.sensor_1460");
		System.out.println("Sensor wattalyst.lulea.location_120.sensor_1460");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_120.sensor_1462");
		System.out.println("Sensor wattalyst.lulea.location_120.sensor_1462");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_120.sensor_1464");
		System.out.println("Sensor wattalyst.lulea.location_120.sensor_1464");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_120.sensor_1466");
		System.out.println("Sensor wattalyst.lulea.location_120.sensor_1466");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_159.sensor_1978");
		System.out.println("Sensor wattalyst.lulea.location_159.sensor_1978");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_159.sensor_1980");
		System.out.println("Sensor wattalyst.lulea.location_159.sensor_1980");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_159.sensor_1982");
		System.out.println("Sensor wattalyst.lulea.location_159.sensor_1982");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_159.sensor_1984");
		System.out.println("Sensor wattalyst.lulea.location_159.sensor_1984");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_115.sensor_1390");
		System.out.println("Sensor wattalyst.lulea.location_115.sensor_1390");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_115.sensor_1392");
		System.out.println("Sensor wattalyst.lulea.location_115.sensor_1392");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_115.sensor_1394");
		System.out.println("Sensor wattalyst.lulea.location_115.sensor_1394");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_115.sensor_1396");
		System.out.println("Sensor wattalyst.lulea.location_115.sensor_1396");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_123.sensor_1502");
		System.out.println("Sensor wattalyst.lulea.location_123.sensor_1502");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_123.sensor_1504");
		System.out.println("Sensor wattalyst.lulea.location_123.sensor_1504");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_123.sensor_1506");
		System.out.println("Sensor wattalyst.lulea.location_123.sensor_1506");
		for(String s : baselines){
			System.out.println("Retrieved baseline " + s);
		}
		baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_123.sensor_1508");
		System.out.println("Sensor wattalyst.lulea.location_123.sensor_1508");
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
		
//		System.out.println(
//				reader.getBaselineData("mheqzghwnhh+", "wattalyst.lulea.location_43.sensor_344.baseline_CAISO", startDate, endDate));
	}

}
