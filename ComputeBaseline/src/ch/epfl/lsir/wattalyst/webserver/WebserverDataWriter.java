package ch.epfl.lsir.wattalyst.webserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import org.wattalyst.services.secured.BooleanResultContainer;
import org.wattalyst.services.secured.SecuredDRSignalManagement;
import org.wattalyst.services.secured.SecuredDRSignalManagementService;
import org.wattalyst.services.secured.SignalStatus;
import org.wattalyst.services.secured.StringResultContainer;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.baseline.util.Util;

public class WebserverDataWriter {
	
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
	 * @param description
	 * @param baselineType
	 * @param sensor
	 * @return
	 */
	public boolean addBaseline(String description, String baselineType, String sensor) {
		
		boolean ok = false;
		
		try{
			
			// Invoke the web service 
			SecuredDRSignalManagementService service = new SecuredDRSignalManagementService();
			SecuredDRSignalManagement port = service.getSecuredDRSignalManagementPort();
			
			String authenticationToken = setConfigBinding(port);
					
			StringResultContainer result = port.addBaseline(authenticationToken, description, baselineType, sensor);
			if("OK".equals(result.getStatus().value())){
				ok = true;
			}
			else{
				ok = false;
				System.err.println("Error when storing baseline ID. \n" +
						"Method addBaseline() returned message " + result.getStatusMessage());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return ok;
	}

	/**
	 * 
	 * @param baselineID
	 * @param baselineData
	 * @return
	 */
	public boolean updateBaselineData(String baselineID, SensorReadings baselineData){

		boolean ok = false;
		
		try{
		
			// Prepare the string to write in the DB
			String values = baselineData.toStringAscDBFormat();
			
			// Invoke the web service 
			SecuredDRSignalManagementService service = new SecuredDRSignalManagementService();
			SecuredDRSignalManagement port = service.getSecuredDRSignalManagementPort();
					
			String authenticationToken = setConfigBinding(port);
			
			BooleanResultContainer result = port.uploadBaselineData(authenticationToken, baselineID, values);
			if("OK".equals(result.getStatus().value())){
				ok = true;
			}
			else{
				ok = false;
				System.err.println("Error when storing baseline data. \n" +
						"Method uploadBaselineData() returned message " + result.getStatusMessage());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return ok;
	}
	
	/**
	 * 
	 * @param drSignalID
	 * @param location
	 * @param kpiValue
	 * @param description
	 * @param successStatus
	 * @return
	 */
	public boolean setPerformanceIndicator(String drSignalID, String location, double kpiValue, 
			String description, String successStatus) {
		
		boolean ok = false;
		
		try{
		
			// Invoke the web service 
			SecuredDRSignalManagementService service = new SecuredDRSignalManagementService();
			SecuredDRSignalManagement port = service.getSecuredDRSignalManagementPort();
			
			String authenticationToken = setConfigBinding(port);
					
			BooleanResultContainer result = port.setPerformanceIndicator(authenticationToken, drSignalID, location, kpiValue, description, successStatus);
			if("OK".equals(result.getStatus().value())){
				ok = true;
			}
			else{
				ok = false;
				System.err.println("Error when storing KPI. \n" +
						"Method setPerformanceIndicator() returned message " + result.getStatusMessage());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
			
		return ok;
	}
	
	/**
	 * 
	 * @param signalID
	 * @param location
	 * @param status
	 * @return
	 */
	public boolean setDRSignalStatus(String signalID, String location, String status) {
		
		boolean ok = false;
		
		try{
		
			// Invoke the web service 
			SecuredDRSignalManagementService service = new SecuredDRSignalManagementService();
			SecuredDRSignalManagement port = service.getSecuredDRSignalManagementPort();
			
			String authenticationToken = setConfigBinding(port);
					
			BooleanResultContainer result = port.setDRSignalStatus(authenticationToken, signalID, location, status);
			if("OK".equals(result.getStatus().value())){
				ok = true;
			}
			else{
				ok = false;
				System.err.println("Error when set DR signal status. \n" +
						"Method setDRSignalStatus() returned message " + result.getStatusMessage());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
			
		return ok;
	}
	
	/**
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException{
		
//    	WebserverDataWriter writer = new WebserverDataWriter();
//		writer.setDRSignalStatus("wattalyst.lulea.campaign_3.signal_1", "wattalyst.lulea.location_43", SignalStatus.EXPIRED.name());
//		writer.setDRSignalStatus("wattalyst.lulea.campaign_4_1.signal_1", "wattalyst.lulea.location_43", SignalStatus.EXPIRED.name());
//		writer.setDRSignalStatus("wattalyst.lulea.campaign_3.signal_1", "wattalyst.lulea.location_46", SignalStatus.EXPIRED.name());
//		writer.setDRSignalStatus("wattalyst.lulea.campaign_4.signal_1", "wattalyst.lulea.location_46", SignalStatus.EXPIRED.name());
//		writer.setDRSignalStatus("wattalyst.lulea.campaign_4_2.signal_2", "wattalyst.lulea.location_46", SignalStatus.EXPIRED.name());
//		writer.setDRSignalStatus("wattalyst.lulea.campaign_3.signal_1", "wattalyst.lulea.location_76", SignalStatus.EXPIRED.name());
//		writer.setDRSignalStatus("wattalyst.lulea.campaign_4.signal_1", "wattalyst.lulea.location_76", SignalStatus.EXPIRED.name());
//		writer.setDRSignalStatus("wattalyst.lulea.campaign_4_4.signal_4", "wattalyst.lulea.location_76", SignalStatus.EXPIRED.name());
//		writer.setDRSignalStatus("wattalyst.lulea.campaign_3.signal_1", "wattalyst.lulea.location_160", SignalStatus.EXPIRED.name());
//		writer.setDRSignalStatus("wattalyst.lulea.campaign_4.signal_1", "wattalyst.lulea.location_160", SignalStatus.EXPIRED.name());
//		writer.setDRSignalStatus("wattalyst.lulea.campaign_6.signal_6", "wattalyst.lulea.location_160", SignalStatus.EXPIRED.name());
//		System.exit(0);
		
		/*********************************************************************/
		/** LULEA                                                           */
		/*********************************************************************/		
		
		try{
			WebserverDataWriter writer = new WebserverDataWriter();
			BufferedReader br = new BufferedReader(new FileReader(new File("LULEA-sensors.txt")));
			String sensor = "";
			List<String> sensors = new ArrayList<String>();
			while((sensor = br.readLine()) != null){
				sensors.add(sensor);
			}
			br.close();
			
			for(String s : sensors){
				writer.addBaseline("CAISO baseline", "CAISO", s);
				Thread.sleep(2000);
			}
			
			WebserverDataReader reader = new WebserverDataReader();
			List<String> baselines = new ArrayList<String>();
			for(String s : sensors){
				baselines = reader.getBaselines(s);
				System.out.println("Sensor " + s);
				for(String b : baselines){
					System.out.println("Retrieved baseline " + b);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
//		SensorReadings baseline = new SensorReadings();
//		Calendar current = Calendar.getInstance();
//		Util.setToTheBeginningOfTheHour(current);
//		current.add(Calendar.HOUR_OF_DAY, -1);
//		Date startDate = current.getTime();
//		
//		current.add(Calendar.HOUR_OF_DAY, 1);
//		baseline.insert(current.getTime().getTime(), 3);
//		
//		current.add(Calendar.HOUR_OF_DAY, 1);
//		baseline.insert(current.getTime().getTime(), 2);
//		
//		current.add(Calendar.HOUR_OF_DAY, 1);
//		baseline.insert(current.getTime().getTime(), 1);
//		
//		current.add(Calendar.HOUR_OF_DAY, 1);
//		Date endDate = current.getTime();
		
//		System.out.println(
//				writer.updateBaselineData("wattalyst.lulea.location_43.sensor_348.baseline_caiso", baseline));
//		System.out.println(baseline.toStringAscDBFormat());
		
//		System.out.println(
//				reader.getBaselineData("wattalyst.lulea.location_43.sensor_344.baseline_CAISO", startDate, endDate));
		
		/*********************************************************************/
		/** SAMPOL                                                           */
		/*********************************************************************/

		try{
			WebserverDataWriter writer = new WebserverDataWriter();
			BufferedReader br = new BufferedReader(new FileReader(new File("SAMPOL-sensors.txt")));
			String sensor = "";
			List<String> sensors = new ArrayList<String>();
			while((sensor = br.readLine()) != null){
				sensors.add(sensor);
			}
			br.close();
			
//			for(String s : sensors){
//				writer.addBaseline("CAISO baseline", "CAISO", s);
//				Thread.sleep(2000);
//			}
			
			WebserverDataReader reader = new WebserverDataReader();
			List<String> baselines = new ArrayList<String>();
			for(String s : sensors){
				baselines = reader.getBaselines(s);
				System.out.println("Sensor " + s);
				for(String b : baselines){
					System.out.println("Retrieved baseline " + b);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
