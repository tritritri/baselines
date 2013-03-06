package ch.epfl.lsir.wattalyst.kpi.compute;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import ch.epfl.lsir.wattalyst.kpi.kpis.KPI;

public class ComputeKPI {

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
		
		// Parse available options
		Options opts = createOptions();
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(opts, args);
		
		// if help needed
		if (cmd.hasOption("h") || args.length==0 || (cmd.hasOption("o") && args.length != 8) || (!cmd.hasOption("o") && args.length != 6)) {
			HelpFormatter help = new HelpFormatter();
			help.setWidth(90);
			String helpString = "java -jar ComputeKPI.jar [OPTIONS] BASELINE CONSUMPTION KPI STARTTIME ENDTIME TOKENS \n" 
					+ "Example: java -jar ComputeKPI.jar baseline.txt real.txt ConsumptionChangeAbs 2013-02-15:03 2013-02-15:06 4\n"
					+ "KPI is RMSE | ConsumptionChangeAbs | ConsumptionChangePerc | ConsumptionChangePerTokenAbs \n"
					+ "... ConsumptionChangePerTokenPerc | TokenPerConsumptionChangeAbs"
					+ "BASELINE is a SENSORFILE with the estimated consumption: a file text of lines DATE,HOUR,READINGS, where:\n" +
					  "... DATE is of form yyyy-MM-dd, \n" + 
					  "... HOUR is 0-23, and \n" +
					  "... READINGS is value measured at DATE,HOUR. \n" +
					  "..... For example, energy consumed during DATE,HOUR in kWh.\n" 
					+ "CONSUMPTION is a SENSORFILE with the real consumption: a file text of lines DATE,HOUR,READINGS, where:\n" +
					  "... DATE is of form yyyy-MM-dd, \n" + 
					  "... HOUR is 0-23, and \n" +
					  "... READINGS is value measured at DATE,HOUR. \n" +
					  "..... For example, energy consumed during DATE,HOUR in kWh.\n"
					+ "STARTTIME and ENDTIME are of form yyyy-MM-dd:hh \n"  
					+ "... where yyyy-MM-dd is the same date. \n"	
					+ "TOKENS is the number of tokens in the DR signal \n" 
					+ "\n OPTIONS: \n";
			help.printHelp(helpString, opts);
			return;
		} 

		// process args
		int shift = 0;
		if(cmd.hasOption("o")){
			shift = 2;
		}
		
		String baselineFileInput = args[shift + 0];
		String consumptionFileInput = args[shift + 1];
		String kpi = "ch.epfl.lsir.wattalyst.kpi.kpis." + args[shift + 2];
		String startTime = args[shift + 3];
		String endTime = args[shift + 4];
		String tokens = args[shift + 5];
		
		// check if startTime >= endTime
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd:HH");
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		startCal.setTime(formatter.parse(startTime));
		endCal.setTime(formatter.parse(endTime));
		if(endCal.before(startCal) || endCal.equals(startCal)) {
			System.err.println("[Error] ENDTIME should not be earlier than STARTTIME");
			System.exit(0);
		}
		// check if startTime and endTime are the same day
		if(startCal.get(Calendar.YEAR) != endCal.get(Calendar.YEAR) || 
				startCal.get(Calendar.MONTH) != endCal.get(Calendar.MONTH) ||
				startCal.get(Calendar.DAY_OF_MONTH) != endCal.get(Calendar.DAY_OF_MONTH)){
			System.err.println("[Error] STARTTIME and ENDTIME should be in the same day");
			System.exit(0);
		}
		// check if tokens is a positive integer
		int numTokens = 0;
		try{
			numTokens = Integer.parseInt(tokens);
			if(numTokens < 0)
				throw new NumberFormatException();
		}
		catch(NumberFormatException e){
			System.err.println("[Error] TOKENS should be a positive integer");
			System.exit(0);
		}
		
		// initialize and compute the KPI
		KPI k = (KPI) Class.forName(kpi).newInstance(); 
		k.compute(baselineFileInput, consumptionFileInput, startCal, endCal, numTokens);
		
		// output the result
		if (cmd.hasOption("o")){
			k.writeResultToFile(cmd.getOptionValue("o"));
		} else {
			k.writeResult(System.out);
		}
		
	}
	
	public static Options createOptions(){
		Options options = new Options();
		options.addOption("o", "output", true, "Write the KPIs into a file.");
		options.addOption("h", "help", false, "Help. Print this message.");		
		return options;	
	}
}
