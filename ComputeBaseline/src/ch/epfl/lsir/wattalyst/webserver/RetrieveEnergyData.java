package ch.epfl.lsir.wattalyst.webserver;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

public class RetrieveEnergyData {

	/**
	 * 
	 * @param args
	 * @throws org.apache.commons.cli.ParseException 
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws org.apache.commons.cli.ParseException, ParseException, RemoteException {
		
		// Parse available options
		Options opts = createOptions();
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(opts, args);
		
		// if help needed
		if (cmd.hasOption("h") || args.length==0) {
			HelpFormatter help = new HelpFormatter();
			help.setWidth(160);
			String helpString = "java -jar RetrieveEnergyData.jar [OPTIONS] SENSORNAME STARTDATE ENDDATE \n" 
					+ "Compute hourly energy consumption read by (accumulative) energy sensor SENSORNAME \n"
					+ "Example: java -jar RetrieveEnergyData.jar wattalyst.lulea.location_43.sensor_346 2013-02-21--00:00 2013-02-26--23:59\n"
					+ "STARTDATE and ENDDATE are of form yyyy-MM-dd--HH:mm\n"
					+ "\n OPTIONS: \n";
			help.printHelp(helpString, opts);
			return;
		} 

		// process default operand
		String sensorName = args[args.length-3];
		String startDate = args[args.length-2];
		String endDate = args[args.length-1];
		
		// check if endDate >= startDate
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd--HH:mm");
		Date start = formatter.parse(startDate);
		Date end = formatter.parse(endDate);
		if ( end.getTime() < start.getTime() ) {
			System.err.println("[Error] end date should not be earlier than start date");
			System.exit(0);
		}
		
		// retrieve the energy data
		EnergyData e = new EnergyData();
		e.compute(sensorName, start, end, true);
		if(e.getData().size() > 0){
			e.removeOutliers(sensorName);
		}
		
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
