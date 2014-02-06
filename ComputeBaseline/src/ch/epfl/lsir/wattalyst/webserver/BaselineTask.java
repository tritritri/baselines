package ch.epfl.lsir.wattalyst.webserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import ch.epfl.lsir.wattalyst.baseline.baselines.Baseline;
import ch.epfl.lsir.wattalyst.baseline.constants.Constants;
import ch.epfl.lsir.wattalyst.baseline.util.Util;


public class BaselineTask {

	/**
	 * 
	 * @param args
	 * @throws RemoteException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws RemoteException {
		
		// Parse available options
		Options opts = createOptions();
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = null;
		
		try{
			cmd = parser.parse(opts, args);
		}
		catch(ParseException e){
			HelpFormatter help = new HelpFormatter();
			help.setWidth(160);
			String helpString = "java -jar BaselineTask.jar [OPTIONS] \n" 
							+ "Compute all the baselines and store them in the DB \n"
							+ "Example: java -jar BaselineTask.jar -t 1 -e exclude.txt -l LULEA\n"
							+ "\n OPTIONS: \n";
			help.printHelp(helpString, opts);
			return;
		}
		
		// if help needed
		if (cmd.hasOption("h") || args.length != 6) {
			HelpFormatter help = new HelpFormatter();
			help.setWidth(160);
			String helpString = "java -jar BaselineTask.jar [OPTIONS] \n" 
							+ "Compute all the baselines and store them in the DB \n"
							+ "Example: java -jar BaselineTask.jar -t 1 -e exclude.txt -l LULEA\n"
							+ "\n OPTIONS: \n";
			help.printHelp(helpString, opts);
			return;
		} 

		// process -t option
		String targetDayLagStr = args[1];
		int targetDayLag;
		try{
			targetDayLag = Integer.parseInt(targetDayLagStr);
			if(targetDayLag <= 0){
				throw new NumberFormatException();
			}
		}
		catch(NumberFormatException e){
			HelpFormatter help = new HelpFormatter();
			help.setWidth(160);
			String helpString = "java -jar BaselineTask.jar [OPTIONS] \n" 
							+ "Compute all the baselines and store them in the DB \n"
							+ "Example: java -jar BaselineTask.jar -t 1 -e exclude.txt -l LULEA\n"
							+ "\n OPTIONS: \n";
			help.printHelp(helpString, opts);
			return;
		}
		
		// process -e option
		String excludeDaysStr = args[3];
		File f = new File(excludeDaysStr);
		if(!f.exists()){
			HelpFormatter help = new HelpFormatter();
			help.setWidth(160);
			String helpString = "java -jar BaselineTask.jar [OPTIONS] \n" 
							+ "Compute all the baselines and store them in the DB \n"
							+ "Example: java -jar BaselineTask.jar -t 1 -e exclude.txt -l LULEA\n"
							+ "\n OPTIONS: \n";
			help.printHelp(helpString, opts);
			return;
		}
		
		// process -l option
		String locationStr = args[5];
		if(!"LULEA".equals(locationStr) && !"MALLORCA".equals(locationStr)){
			HelpFormatter help = new HelpFormatter();
			help.setWidth(160);
			String helpString = "java -jar BaselineTask.jar [OPTIONS] \n" 
							+ "Compute all the baselines and store them in the DB \n"
							+ "Example: java -jar BaselineTask.jar -t 1 -e exclude.txt -l LULEA\n"
							+ "\n OPTIONS: \n";
			help.printHelp(helpString, opts);
			return;
		}
							
		Calendar history = Calendar.getInstance();
		Date endDate = history.getTime();
		history.add(Calendar.DAY_OF_YEAR, -60);
		Util.setToTheBeginningOfTheDay(history);
		Date startDate = history.getTime();
				
		Calendar target = Calendar.getInstance();
		target.add(Calendar.DAY_OF_YEAR, targetDayLag);
		Util.setToTheEndOfTheDay(history);
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		String targetDate = formatter.format(target.getTime());
				
		WebserverDataReader reader = new WebserverDataReader();
		
		// 1. Retrieve all sensors
		for(String sensor : reader.getSensors()){
			
			// 2. Extract the field trial location of the sensor
			String loc = sensor.split("\\.")[1];
			if(loc.equalsIgnoreCase(locationStr)){
			
				// 3. Retrieve baselines for the sensor
				List<String> baselines = reader.getBaselines(sensor);
				if(!baselines.isEmpty()){
					// 4. Retrieve sensor historic data and write to file
					EnergyData e = new EnergyData();
					e.compute(sensor, startDate, endDate, true);
					e.removeOutliers(sensor);
					e.writeResultToFile(sensor + ".txt");
					// 5. Cycle over baselines
					for(String baselineID : baselines){
						
						try {
						
							// 6. Compute baseline
							HashMap<Long,Byte> exclDays = parseExcludedDays(excludeDaysStr);
							String baselineClass = getBaselineClass(baselineID);
							Baseline b = (Baseline) Class.forName(baselineClass).newInstance();
							b.compute(sensor + ".txt", targetDate, targetDate, exclDays);
							
							// 7. Store baseline
							b.writeResultToWattalystDB(baselineID);
							System.out.println("Computed baseline " + baselineID);
							b.writeResult(System.out);
							
						} catch (InstantiationException e1) {
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (RuntimeException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (ParseException e1) {
							e1.printStackTrace();
						} catch (java.text.ParseException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}
	}

	/*
	 * 
	 */
	private static HashMap<Long,Byte> parseExcludedDays(String fileInput) throws IOException, ParseException, java.text.ParseException{

		HashMap<Long,Byte> exclDays = new HashMap<Long,Byte>(); 

		// read file input for a list of excluded days
		BufferedReader excIn = new BufferedReader(new FileReader(fileInput));
		String line="";
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_REF));

		while ( (line = excIn.readLine()) != null ) {
			exclDays.put(formatter.parse(line).getTime(), (byte) 0);				
		}
		excIn.close();
		
		return exclDays;
	}
	
	/*
	 * 
	 */
	private static String getBaselineClass(String baselineID) {
		String type = baselineID.split("baseline_")[1];
		if(type.equals("CAISO")){
			return "ch.epfl.lsir.wattalyst.baseline.baselines.CAISO";
		}
		else if(type.equals("PJMEco")){
			return "ch.epfl.lsir.wattalyst.baseline.baselines.PJMEco";
		}
		else if(type.equals("ISONE")){
			return "ch.epfl.lsir.wattalyst.baseline.baselines.ISONE";
		}
		throw new RuntimeException("Type " + type + " not allowed");  
	}
	
	/*
	 * 
	 */
	private static Options createOptions(){
		Options options = new Options();
		options.addOption("h", "help", false, "Help. Print this message.");	
		options.addOption("t", "target", true, "Set the target day. A number > 0 is expected, where 1 means tomorrow, 2 the day after, etc.");		
		options.addOption("e", "exclude days", true, "Set the files with the days to exclude.");	
		options.addOption("l", "location", true, "Set the location. Possible values are LULEA or MALLORCA.");
		return options;	
	}
}
