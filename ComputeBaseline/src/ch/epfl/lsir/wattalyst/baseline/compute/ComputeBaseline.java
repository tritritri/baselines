package ch.epfl.lsir.wattalyst.baseline.compute;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import ch.epfl.lsir.wattalyst.baseline.baselines.Baseline;
import ch.epfl.lsir.wattalyst.baseline.constants.Constants;
 
/**
 * Main class for computing baselines.
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.23
 */
public class ComputeBaseline {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ParseException 
	 * @throws org.apache.commons.cli.ParseException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException, org.apache.commons.cli.ParseException, IOException {
		
		// baseline = PJMEco, CAISO, NYISO, Mid4of6, ISONE
		
		/*
		String baseline="com.baseline.baselines.Mid4Of6";
		String fileInput = "344.txt";
		String startDate = "2013-02-23";
		String endDate = "2013-02-23";
		*/	
		
		
		// Parse available options
		Options opts = createOptions();
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(opts, args);
		
		// if help needed
		if (cmd.hasOption("h") || args.length==0) {
			HelpFormatter help = new HelpFormatter();
			help.setWidth(90);
			String helpString = "java -jar ComputeBaseline.jar [OPTIONS] BASELINE INPUT TARGETDATE\n" 
					+ "Example: java -jar ComputeBaseline.jar PJMEco input-example.txt 2013-02-21\n"
					+ "BASELINE is PJMEco | CAISO | NYISO | Mid4Of6 | Regression \n"
					+ "TARGETDATE is of form yyyy-MM-dd\n"
					+ "INPUT is a SENSORFILE: a file text of lines DATE,HOUR,READINGS, where:\n" +
					  "... DATE is of form yyyy-MM-dd, \n"
					+ "... HOUR is 0-23, and \n" +
					  "... READINGS is value measured at DATE,HOUR. \n" +
					  "..... For example, energy consumed during DATE,HOUR in kWh.\n" +
					  ". Except when BASELINE is Regression, INPUT is a configuration file \n" +
					  ". Example: java -jar ComputeBaseline.jar Regression config-reg.txt 2013-02-21\n"
					  /*
					  ". Except when BASELINE is Regression, INPUT is of form \n" +
					  "... SENSORFILE1,SENSORFILE2,ALGFILE, where \n" +
					  "... SENSORFILE1 is the target baseline, \n" +
					  "... SENSORFILE2 is a context, e.g., temperature sensor, and \n" +
					  "... ALGFILE is one line or Weka classifier name\n" +
					  ".... Example: load.txt,temperature.txt,alg.txt\n"
					  */
					+ "\n OPTIONS: \n";
			help.printHelp(helpString, opts);
			return;
		} 

		// process default operand
		String baseline="ch.epfl.lsir.wattalyst.baseline.baselines." + args[args.length-3];
		String fileInput = args[args.length-2];
		String startDate = args[args.length-1];
		String endDate = startDate;
		
		// if horizon is set
		if (cmd.hasOption("z")){
			int hz = Integer.parseInt(cmd.getOptionValue("z")) - 1;
			SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(formatter.parse(startDate));
			endCal.add(Calendar.DAY_OF_MONTH, hz);
			endDate = formatter.format(endCal.getTime());
		}
	
		// check if endDate >= startDate
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date start = formatter.parse(startDate);
		Date end = formatter.parse(endDate);
		if ( end.getTime() < start.getTime() ) {
			System.err.println("[Error] end date should not be earlier than start date");
			System.exit(1);
		}
		

		// initialize and compute the baseline
		Baseline b = (Baseline) Class.forName(baseline).newInstance();

		// check if we have option -e : exclude some dates
		// Long is the date, while Boolean is for dummy element/value
		if (cmd.hasOption("e")){
			HashMap<Long,Byte> exclDays = parseExcludedDays(cmd.getOptionValue("e"));
			b.compute(fileInput, startDate, endDate, exclDays);
		} else {
			b.compute(fileInput, startDate, endDate);
		}
				
		
		// output the result
		if (cmd.hasOption("o")){
			b.writeResultToFile(cmd.getOptionValue("o"));
		} else {
			b.writeResult(System.out);
		}	
	}
		
	public static Options createOptions(){
		Options options = new Options();
		options.addOption("o", "output", true, "Write the baseline into a file.");
		options.addOption("z", "horizon", true, "Baseline horizon. The number of days the baseline computed (starts from the starting date).");
		options.addOption("e", "excludeDays", true, "A file containing a list of date to be excluded from historical data for computing baseline. One date per line with format yyyy-MM-dd");
		options.addOption("h", "help", false, "Help. Print this message.");		
		return options;	
	}
	
	private static HashMap<Long,Byte> parseExcludedDays(String fileInput) throws IOException, ParseException{

		HashMap<Long,Byte> exclDays = new HashMap<Long,Byte>(); 

		// read file input for a list of excluded days
		BufferedReader excIn = new BufferedReader(new FileReader(fileInput));
		String line="";
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		while ( (line = excIn.readLine()) != null ) {
			exclDays.put(formatter.parse(line).getTime(), (byte) 0);				
		}
		excIn.close();
		
		return exclDays;
	}
}




