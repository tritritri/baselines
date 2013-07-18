package ch.epfl.lsir.wattalyst.webserver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

import javax.xml.ws.BindingProvider;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.wattalyst.services.secured.AValueDto;
import org.wattalyst.services.secured.NumericValueDto;
import org.wattalyst.services.secured.SecuredDataAccess;
import org.wattalyst.services.secured.SecuredDataAccessService;
import org.wattalyst.services.secured.ValueListResultContainer;

/**
 * 
 * @author Tri Kurniawan Wijaya <tri-kurniawan.wijaya@epfl.ch>
 * @date 2013-04-11 10:36
 *
 */
public class RetrieveSensorData {

	/**
	 * 
	 * @param args
	 * @throws org.apache.commons.cli.ParseException 
	 * @throws RemoteException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws org.apache.commons.cli.ParseException, ParseException, RemoteException, FileNotFoundException {
		
		// Parse available options
		Options opts = createOptions();
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(opts, args);
		
		// if help needed
		if (cmd.hasOption("h") || args.length==0) {
			HelpFormatter help = new HelpFormatter();
			help.setWidth(160);
			String helpString = "java -jar RetrieveSensorData.jar [OPTIONS] SENSORNAME STARTTIME ENDTIME \n" 
					+ "Display instantenous reading of sensor SENSORNAME from STARTTIME to ENDTIME\n"
					+ "Local machine timezone is used to display the time\n"
					+ "Example: java -jar RetrieveSensorData.jar wattalyst.lulea.location_43.sensor_345 2013-02-21--00:00 2013-02-26--23:59\n"
					+ "STARTTIME and ENDTIME are of form yyyy-MM-dd--HH:mm\n"
					+ "\n OPTIONS: \n";
			help.printHelp(helpString, opts);
			return;
		} 

		// process default operand
		String authenticationToken = "mheqzghwnhh+";
		String sensorName = args[args.length-3];
		String startTimeString = args[args.length-2];
		String endTimeString = args[args.length-1];
		
		// check if endDate >= startDate
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd--HH:mm");
		Date startDate = formatter.parse(startTimeString);
		Date endDate = formatter.parse(endTimeString);
		if ( endDate.getTime() < startDate.getTime() ) {
			System.err.println("[Error] end date should not be earlier than start date");
			System.exit(1);
		}
		
		// Invoke the web service and retrieve the result
		SecuredDataAccessService service = new SecuredDataAccessService();
		SecuredDataAccess port = service.getSecuredDataAccessPort();
				
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ch.epfl.lsir.wattalyst.webserver.Constants.DATA_ENDPOINT_URL);
		
		ValueListResultContainer result = port.getValuesForSensorByRange(authenticationToken, sensorName, startDate.getTime(), endDate.getTime());
		
		// Put the result in a sorted set
		
		if("OK".equals(result.getStatus().value())){
			TreeSet<AValueDto> sortedSet = new TreeSet<AValueDto>();
			for(AValueDto r : result.getValues()){
				sortedSet.add(r);
			}
			PrintWriter fOut=null;
			boolean writeToFile=false;
			if (cmd.hasOption("o")){
				fOut = new PrintWriter(cmd.getOptionValue("o"));
				writeToFile=true;
			}
			
			for(AValueDto v : sortedSet){
				if(v instanceof NumericValueDto){
					Date dateRead = new Date(v.getTimestamp());
					double valueRead = ((NumericValueDto)v).getValue();
					if (writeToFile==true){
						fOut.println(dateRead + "," + valueRead);
					} else {
						System.out.println(dateRead + "," + valueRead);
					}
				}
			}		
			if (writeToFile==true) {
				fOut.close();
			}
		}
				
		
	}
	
	/*
	 * 
	 */
	private static Options createOptions(){
		Options options = new Options();
		options.addOption("o", "output", true, "Write the energy data into a file.");
		options.addOption("h", "help", false, "Help. Print this message.");		
		return options;	
	}
}
