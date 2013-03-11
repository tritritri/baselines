package ch.epfl.lsir.wattalyst.baseline.compute;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.baseline.util.Util;

public class ComputeError {

	/**
	 * Compute baseline error compared to original data file
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		
		
		// Parse available options
		Options opts = createOptions();
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(opts, args);

		if (cmd.hasOption("h") || args.length==0) {
			HelpFormatter help = new HelpFormatter();
			help.setWidth(90);
			String helpString = "java -jar ComputeError.jar [OPTIONS] BASELINEFILE REFERENCEFILE\n" 
					+ "Example: java -jar ComputeError.jar baseline-example.txt input-example.txt\n"
					+ "BASELINEFILE and REFERENCEFILE is a file text of lines DATE,HOUR,READINGS where:\n" +
					  "... DATE is of form yyyy-MM-dd, \n"
					+ "... HOUR is 0-23, and \n" +
					  "... READINGS is value measured at DATE,HOUR. \n" +
					  "..... For example, energy consumed during DATE,HOUR in kWh.\n" +
					  "OPTIONS: \n";
			help.printHelp(helpString, opts);
			return;
		} 
				
		String baselineFile = args[args.length-2];
		String referenceFile = args[args.length-1];

		SensorReadings baseline = new SensorReadings();
		Util.hourlyCSVToSensorReadings(baselineFile, baseline);
		
		SensorReadings reference = new SensorReadings();
		Util.hourlyCSVToSensorReadings(referenceFile, reference);
		
		// initialize data structrue for result 
		SensorReadings error = new SensorReadings(); 
		if ( baseline.comparedTo(reference, error) == false ) {
			System.err.println("[ERROR] missing values in " + referenceFile);
			System.exit(1);
		}
		
		if (cmd.hasOption("o")){
			try {
				PrintWriter fileOut = new PrintWriter(cmd.getOptionValue("o"));
				fileOut.print(error.toStringAsc());
				fileOut.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		} else {
			System.out.println(error.toStringAsc());
		}	

				
	}

	public static Options createOptions(){
		Options options = new Options();
		options.addOption("h", "help", false, "Help. Print this message.");		
		options.addOption("o", "output", true, "Write the error into a file.");
		return options;	
	}

}
