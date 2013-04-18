package ch.epfl.lsir.wattalyst.weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;
import ch.epfl.lsir.wattalyst.baseline.util.Util;

public class RetrieveTemperatureData {

	/**
	 * 
	 * @param args
	 * @throws org.apache.commons.cli.ParseException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws org.apache.commons.cli.ParseException, InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {
		
		// Parse available options
		Options opts = createOptions();
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(opts, args);
		
		// if help needed
		if (cmd.hasOption("h") || args.length==0) {
			HelpFormatter help = new HelpFormatter();
			help.setWidth(160);
			String helpString = "java -jar RetrieveTemperatureData.jar [OPTIONS] DATASOURCE APIKEY STARTDATE ENDDATE COUNTRY PLACE\n" 
					+ "Example: java -jar RetrieveTemperatureData.jar Wunderground 1234abcd 2013-02-21 2013-02-26 Sweden Lulea \n"
					+ "DATASOURCE is Wunderground \n"
					+ "APIKEY is 1234abcd \n"
					+ "STARTDATE and ENDDATE are of form yyyy-MM-dd\n"
					+ "\n OPTIONS: \n";
			help.printHelp(helpString, opts);
			return;
		} 

		// process default operand
		String temperature = "ch.epfl.lsir.wattalyst.weather." + args[args.length-6];
		String apikey = args[args.length-5];
		String startDate = args[args.length-4];
		String endDate = args[args.length-3];
		String country = args[args.length-2];
		String place = args[args.length-1];
		
		// check if endDate >= startDate
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date start = formatter.parse(startDate);
		Date end = formatter.parse(endDate);
		if ( end.getTime() < start.getTime() ) {
			System.err.println("[Error] end date should not be earlier than start date");
			System.exit(0);
		}
		
		// initialize and retrieve the temperature
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		Util.setToTheBeginningOfTheDay(calendar);
		start = calendar.getTime();
		calendar.setTime(end);
		Util.setToTheBeginningOfTheDay(calendar);
		end = calendar.getTime();
		
		Temperature t = (Temperature) Class.forName(temperature).newInstance(); 
		t.setApyKey(apikey);
		t.compute(start, end, country, place);
		
		// output the result
		if (cmd.hasOption("o")){
			t.writeResultToFile(cmd.getOptionValue("o"));
		} else {
			t.writeResult(System.out);
		}
		
	}
	
	/*
	 * 
	 */
	private static Options createOptions(){
		Options options = new Options();
		options.addOption("o", "output", true, "Write the temperatures into a file.");
		options.addOption("h", "help", false, "Help. Print this message.");		
		return options;	
	}
}
