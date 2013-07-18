package ch.epfl.lsir.wattalyst.webserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;

/**
 * 
 * @author vasirani
 *
 */
public class StoreBaselineData {

	/**
	 * 
	 * @param args
	 * @throws org.apache.commons.cli.ParseException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws org.apache.commons.cli.ParseException, ParseException, IOException {
		
		// Parse available options
		Options opts = createOptions();
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(opts, args);
		
		// if help needed
		if (cmd.hasOption("h") || args.length!=3) {
			HelpFormatter help = new HelpFormatter();
			help.setWidth(160);
			String helpString = "java -jar StoreBaselineData.jar [OPTIONS] SENSOR BASELINE INPUTFILE \n" 
					+ "Store the baselien data contained in INPUTFILE and referring to BASELINEID\n"
					+ "Example: java -jar StoreBaselineData.jar wattalyst.lulea.location_43.sensor_344 wattalyst.lulea.location_43.sensor_344.baseline_caiso data.txt\n"
					+ "The data in INPUTFILE are in baseline output format \"date,hour,value,timestamp\"\n"
					+ "and are converted in DB format \"date;hour:min;value\"\n"
					+ "\n OPTIONS: \n";
			help.printHelp(helpString, opts);
			return;
		} 

		// process default operand
		String authenticationToken = "mheqzghwnhh+";
		String sensor = args[args.length-3];
		String baseline = args[args.length-2];
		String inputfile = args[args.length-1];
		
		// Check if the baseline ID exists
		WebserverDataReader reader = new WebserverDataReader();
		boolean foundBaseline = false;
		for(String bl : reader.getBaselines(authenticationToken, sensor)){
			if(bl.equals(baseline)){
				foundBaseline = true;
				break;
			}
		}
		if(!foundBaseline){
			System.out.println("The baseline " + baseline + " for sensor " + sensor + " does not exist");
			return;
		}
		
		// Parse the inputfile and create a sensor readings data structure
		BufferedReader br = new BufferedReader(new FileReader(inputfile));
		String line = "";
		SensorReadings baselineData = new SensorReadings();
		while ( (line = br.readLine()) != null ){
			baselineData.insert(Long.valueOf(line.split(",")[3]), Double.valueOf(line.split(",")[2]));
		}
		br.close();
		
		// Write the sensor readings data structure into the DB
		WebserverDataWriter writer = new WebserverDataWriter();
		boolean result = writer.updateBaselineData(authenticationToken, baseline, baselineData);
		System.out.println("Baseline data storage: " + (result ? "OK" : "FAILED"));
	}
	
	/*
	 * 
	 */
	private static Options createOptions(){
		Options options = new Options();
		options.addOption("h", "help", false, "Help. Print this message.");		
		return options;	
	}
}
