package com.baseline.compute;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import com.baseline.baselines.Baseline;
import com.baseline.constants.Constants;
 

public class ComputeBaseline {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ParseException 
	 * @throws org.apache.commons.cli.ParseException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException, org.apache.commons.cli.ParseException {
		
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
			help.setWidth(80);
			String helpString = "java -jar ComputeBaseline.jar [OPTIONS] BASELINE INPUTFILE TARGETDATE\n" 
					+ "Example: java -jar ComputeBaseline.jar PJMEco input.txt 2013-02-21\n"
					+ "BASELINE is PJMEco | CAISO | NYISO | Mid4Of6 \n"
					+ "INPUTFILE is a file text of lines DATE,HOUR,KWH, where: \n" +
					  "... DATE is of form yyyy-MM-dd, \n"
					+ "... HOUR is 0-23, and \n" +
					  "... KWH is energy consumed during that date and hour in kWh.\n"
					+ "TARGETDATE is of form yyyy-MM-dd\n"
					+ "\n OPTIONS: \n";
			help.printHelp(helpString, opts);
			return;
		} 

		// process default operand
		String baseline="com.baseline.baselines." + args[args.length-3];
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
			System.exit(0);
		}
		
		// initialize and compute the baseline
		Baseline b = (Baseline) Class.forName(baseline).newInstance(); 
		b.compute(fileInput, startDate, endDate);
		
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
		options.addOption("h", "help", false, "Help. Print this message.");		
		return options;	
	}
}




