package ch.epfl.lsir.wattalyst.webserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import org.wattalyst.services.secured.BooleanResultContainer;
import org.wattalyst.services.secured.SecuredDRSignalManagement;
import org.wattalyst.services.secured.SecuredDRSignalManagementService;
import org.wattalyst.services.secured.StringResultContainer;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;

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
	
}
