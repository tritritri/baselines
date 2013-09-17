package ch.epfl.lsir.wattalyst.webserver;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

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
		
		// retrieve the energy data
		EnergyData e = new EnergyData();
		e.compute(sensorName, startDate, endDate, false);
			
		// output the result
		if (cmd.hasOption("o")){
			e.writeResultToFile(cmd.getOptionValue("o"));
		} else {
			e.writeResult(System.out);
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
