package ch.epfl.lsir.wattalyst.webserver;

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
		
		String endpointURL = "https://wattalyst-ci.se.rwth-aachen.de/SecuredDRSignalManagementService/SecuredDRSignalManagement";
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
				
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
				
		String endpointURL = "https://wattalyst-ci.se.rwth-aachen.de/SecuredDRSignalManagementService/SecuredDRSignalManagement";
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
		
		BooleanResultContainer result = port.uploadBaselineData(authenticationToken, baselineID, values);
		return result.isResult();
	}
	
	/**
	 * 
	 * @param authenticationToken
	 * @param drSignalID
	 * @param username
	 * @param kpiValue
	 * @param description
	 * @param successStatus
	 */
	public boolean setPerformanceIndicator(String authenticationToken, String drSignalID, String username,	double kpiValue, 
			String description, String successStatus) {
		
		// Invoke the web service 
		SecuredDRSignalManagementService service = new SecuredDRSignalManagementService();
		SecuredDRSignalManagement port = service.getSecuredDRSignalManagementPort();
		
		String endpointURL = "https://wattalyst-ci.se.rwth-aachen.de/SecuredDRSignalManagementService/SecuredDRSignalManagement";
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
				
		BooleanResultContainer result = port.setPerformanceIndicator(authenticationToken, drSignalID, username, kpiValue, description, successStatus);
		return result.isResult();
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
//		WebserverDataWriter writer = new WebserverDataWriter();
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_43.sensor_344");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_43.sensor_346");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_43.sensor_348");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_43.sensor_590");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_43.sensor_1083");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_76.sensor_820");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_76.sensor_822");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_76.sensor_824");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_76.sensor_826");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_92.sensor_1062");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_92.sensor_1064");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_92.sensor_1066");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_92.sensor_1068");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_92.sensor_1618");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_92.sensor_1956");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_156.sensor_1942");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_156.sensor_1944");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_156.sensor_1946");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_156.sensor_1948");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_75.sensor_1460");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_75.sensor_1462");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_75.sensor_1464");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_75.sensor_1466");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_120.sensor_1460");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_120.sensor_1462");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_120.sensor_1464");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_120.sensor_1466");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_159.sensor_1978");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_159.sensor_1980");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_159.sensor_1982");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_159.sensor_1984");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_115.sensor_1390");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_115.sensor_1392");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_115.sensor_1394");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_115.sensor_1396");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_123.sensor_1502");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_123.sensor_1504");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_123.sensor_1506");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_123.sensor_1508");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_46.sensor_515");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_46.sensor_546");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_46.sensor_560");
//		writer.addBaseline("mheqzghwnhh+", "CAISO baseline", "CAISO", "wattalyst.lulea.location_46.sensor_562");
				
		WebserverDataReader reader = new WebserverDataReader();
		List<String> baselines = reader.getBaselines("mheqzghwnhh+", "wattalyst.lulea.location_159.sensor_1984");
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
				reader.getBaselineData("mheqzghwnhh+", "wattalyst.lulea.location_43.sensor_344.baseline_CAISO", startDate, endDate));
	}

}
