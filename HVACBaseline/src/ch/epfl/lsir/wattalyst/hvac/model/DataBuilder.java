package ch.epfl.lsir.wattalyst.hvac.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class DataBuilder {
	
	/**
	 * 
	 * @param selectedModes  if null, select all modes
	 * 
	 * @return an Hashmap where the key is the room name and the value is
	 * a list of tuples with the historical data
	 * @throws java.text.ParseException 
	 */
	public static HashMap<String, List<DataTuple>> loadData(String file, AtomicReference<List<String>> selectedModes) throws java.text.ParseException{
		
		HashMap<String, List<DataTuple>> data = new HashMap<String, List<DataTuple>>();
		
		// Data structure with the rooms and the starting column index (0-based) in the csv file
		HashMap<String, Integer> roomIndices = new HashMap<String, Integer>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
			
			// Read and skip the first line
			reader.readLine();
			
			// Retrieve the room names and starting column indices
			String[] fields = reader.readLine().split(",");
			for(int i = 0; i < fields.length; i++){
				if(fields[i].startsWith("R")){
					String room = fields[i].split("_")[0];
					if(!roomIndices.keySet().contains(room)){
						roomIndices.put(room, i);
					}
				}
			}
			reader.close();
			
			// Create the data for each room
			for(String room : roomIndices.keySet()){
				
				List<DataTuple> roomData = new ArrayList<DataTuple>();
				
				reader = new BufferedReader(new FileReader(new File(file)));
				
				// Skip the first three lines
				reader.readLine();
				reader.readLine();
				reader.readLine();
				
				// Check if it is necessary to filter out modes
				// If not, instantiate "selectedModes" to store all 
				// the modes in the dataset
				boolean hasToFilter = true;
				if(selectedModes.get() == null){
					hasToFilter = false;
					selectedModes.set(new ArrayList<String>());
				}
				
				String line = "";
				while((line = reader.readLine()) != null){
					fields = line.split(",");
					
					if(hasToFilter){
						// Filter out the not selected modes
						boolean filterOut = true;
						for(String mode : selectedModes.get()){
							if(fields[roomIndices.get(room) + 1].equals(mode))
							{
								filterOut = false;
								break;
							}
							
						}
						if(!filterOut){
							roomData.add(createTuple(fields, roomIndices, room));
						}
					}
					else{
						if(!selectedModes.get().contains(fields[roomIndices.get(room) + 1])){
							selectedModes.get().add(fields[roomIndices.get(room) + 1]);
						}
						roomData.add(createTuple(fields, roomIndices, room));
					}
				}
				
				reader.close();
				
				data.put(room, roomData);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Cannot find file " + file);
			return data;
		} catch (IOException e) {
			System.err.println("Error reading file " + file);
			return data;
		}
		
		return data;
	}
	
	/*
	 * 
	 */
	private static DataTuple createTuple(String[] fields, HashMap<String, Integer> roomIndices,
			String room) throws java.text.ParseException {

		DataTuple tuple = new DataTuple();
							
		// timestamp
		tuple.setTimestamp(Long.valueOf(fields[0]));
		
		// external temperature
		tuple.setExtTemp(Double.valueOf(fields[4]));
		
		// power;
		tuple.setPower(Double.valueOf(fields[roomIndices.get(room)]));
		
		// mode;
		tuple.setMode(fields[roomIndices.get(room) + 1]);
		
		// room;
		tuple.setRoom(room);
				
		//  setpoint temperature
		tuple.setSetpointTemp(Double.valueOf(fields[roomIndices.get(room) + 2]));
		
		// indoor temperature
		tuple.setIndTemp(Double.valueOf(fields[roomIndices.get(room) + 3]));
	
		return tuple;
	}

	/**
	 * Create three arff files, one for the hvac power consumption, one for
	 * the indoor temperature dynamics and one for the hvac mode dynamics
	 */
	public static void createArffFiles(HashMap<String, List<DataTuple>> data, AtomicReference<List<String>> selectedModes){

		
		// ***************************** //
		// Power consumption
		// ***************************** //
		String powerArffFile = "./arff/HVAC-power.arff";
				  
		// Set up attributes
		FastVector atts = new FastVector();
		atts.addElement(new Attribute("external-temperature"));
		
		FastVector hvacModeValues = new FastVector();
		for(String mode : selectedModes.get()){
			hvacModeValues.addElement(mode);
		}
		atts.addElement(new Attribute("hvac-mode", hvacModeValues));
		
		FastVector roomValues = new FastVector();
		for(String room : data.keySet()){
			roomValues.addElement(room);
		}
		atts.addElement(new Attribute("room", roomValues));
	    
		atts.addElement(new Attribute("indoor-temperature"));
		atts.addElement(new Attribute("setpoint-temperature"));
		atts.addElement(new Attribute("hvac-power"));
		
		// Create Instances object
	    Instances dataSet = new Instances("HVAC-power", atts, 0);
	    
	    // Fill with data
	    for(String room : data.keySet()){
		    for(DataTuple tuple : data.get(room)){
		    	double[] vals = new double[dataSet.numAttributes()];
		    	vals[0] = tuple.getExtTemp();
		    	vals[1] = hvacModeValues.indexOf(tuple.getMode());
		    	vals[2] = roomValues.indexOf(room);
		    	vals[3] = tuple.getIndTemp();
		    	vals[4] = tuple.getSetpointTemp();
		    	vals[5] = tuple.getPower();
		    	dataSet.add(new Instance(1.0, vals));
		    }
	    }
	    
	    ArffSaver saver = new ArffSaver();
	    saver.setInstances(dataSet);
	    try {
			saver.setFile(new File(powerArffFile));
			saver.writeBatch();
		} catch (IOException e) {
			System.err.println("Cannot save file " + powerArffFile);
		}		    
		    
	    // ***************************** //
	    // Mode dynamics
	    // ***************************** //
	   
	    String modeDynamicsArffFile = "./arff/HVAC-mode.arff";
		
		// Set up attributes
		atts = new FastVector();
		atts.addElement(new Attribute("external-temperature"));
		atts.addElement(new Attribute("hvac-mode", hvacModeValues));
		atts.addElement(new Attribute("room", roomValues));
	    atts.addElement(new Attribute("indoor-temperature"));
		atts.addElement(new Attribute("setpoint-temperature"));
		atts.addElement(new Attribute("hvac-power"));
		atts.addElement(new Attribute("next-hvac-mode", hvacModeValues));
		
		// Create Instances object
	    dataSet = new Instances("HVAC-mode", atts, 0);
	    
	    // Fill with data
	    for(String room : data.keySet()){
 		    for(int i = 0; i < data.get(room).size() - 1; i++){
 		    	// Check if the next tuple corresponds to the next 10 min time slot
 		    	if(data.get(room).get(i).getTimestamp() + 1000 * 60 * 10 == data.get(room).get(i+1).getTimestamp()){
 		    		double[] vals = new double[dataSet.numAttributes()];
	 		    	vals[0] = data.get(room).get(i).getExtTemp();
	 		    	vals[1] = hvacModeValues.indexOf(data.get(room).get(i).getMode());
	 		    	vals[2] = roomValues.indexOf(room);
	 		    	vals[3] = data.get(room).get(i).getIndTemp();
	 		    	vals[4] = data.get(room).get(i).getSetpointTemp();
	 		    	vals[5] = data.get(room).get(i).getPower();
	 		    	vals[6] = hvacModeValues.indexOf(data.get(room).get(i + 1).getMode());
	 		    	dataSet.add(new Instance(1.0, vals));
 		    	}
 		    }
	    }
	    
	    saver = new ArffSaver();
	    saver.setInstances(dataSet);
	    try {
			saver.setFile(new File(modeDynamicsArffFile));
			saver.writeBatch();
		} catch (IOException e) {
			System.err.println("Cannot save file " + modeDynamicsArffFile);
		}		    
		
		
	    // ***************************** //
	    // Indoor temperature dynamics
	    // ***************************** //
	   
	    String indoorTempDynamicsArffFile = "./arff/HVAC-indoor-temperature.arff";
		
		// Set up attributes
		atts = new FastVector();
		atts.addElement(new Attribute("external-temperature"));
		atts.addElement(new Attribute("hvac-mode", hvacModeValues));
		atts.addElement(new Attribute("room", roomValues));
	    atts.addElement(new Attribute("indoor-temperature"));
		atts.addElement(new Attribute("setpoint-temperature"));
		atts.addElement(new Attribute("hvac-power"));
		atts.addElement(new Attribute("next-indoor-temp"));
		
		// Create Instances object
	    dataSet = new Instances("HVAC-indoor-temperature", atts, 0);
	    
	    // Fill with data
	    for(String room : data.keySet()){
		    for(int i = 0; i < data.get(room).size() - 1; i++){
		    	// Check if the next tuple corresponds to the next 10 min time slot
 		    	if(data.get(room).get(i).getTimestamp() + 1000 * 60 * 10 == data.get(room).get(i+1).getTimestamp()){
 		    		double[] vals = new double[dataSet.numAttributes()];
			    	vals[0] = data.get(room).get(i).getExtTemp();
			    	vals[1] = hvacModeValues.indexOf(data.get(room).get(i).getMode());
			    	vals[2] = roomValues.indexOf(room);
			    	vals[3] = data.get(room).get(i).getIndTemp();
			    	vals[4] = data.get(room).get(i).getSetpointTemp();
			    	vals[5] = data.get(room).get(i).getPower();
			    	vals[6] = data.get(room).get(i + 1).getIndTemp();
			    	dataSet.add(new Instance(1.0, vals));
			    }
		    }
	    }
	    
	    saver = new ArffSaver();
	    saver.setInstances(dataSet);
	    try {
			saver.setFile(new File(indoorTempDynamicsArffFile));
			saver.writeBatch();
		} catch (IOException e) {
			System.err.println("Cannot save file " + indoorTempDynamicsArffFile);
		}
		
	}
	
	/**
	 * Create a csv file for DB import
	 * @throws IOException 
	 */
	public static void createCSVFile(HashMap<String, List<DataTuple>> data, AtomicReference<List<String>> selectedModes) {
		
		String dbfile = "./db.csv";
		
		BufferedWriter writer;
		try {
			
			writer = new BufferedWriter(new FileWriter(dbfile));
		
		
		    // Fill with data
		    for(String room : data.keySet()){
			    for(DataTuple tuple : data.get(room)){
			    	writer.write(tuple.getTimestamp() + "," +
			    			room + "," + (tuple.getTimestamp()/1000) + "," +
			    			tuple.getSetpointTemp() + "," + 
			    			tuple.getIndTemp() + "," +
			    			tuple.getExtTemp() + "," +
			    			tuple.getMode() + "," +
			    			tuple.getPower());
			    	writer.write("\n");
			    }
		    }
		    writer.flush();
		    writer.close();
		
		} catch (IOException e) {
			System.err.println("Cannot write file " + dbfile);
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param args
	 * @throws ParseException 
	 * @throws java.text.ParseException 
	 */
	public static void main(String[] args) throws ParseException, java.text.ParseException{
		
		// Parse args
		Options opts = createOptions();
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(opts, args);

		// If help needed
		if (cmd.hasOption("h") || args.length < 1 || args.length > 3 ||
				(!cmd.hasOption("m") && args.length != 1) ||
				(cmd.hasOption("m") && args.length != 3)) {
			printHelp(opts);
			return;
		} 
				
		int fileParamShift = 0;
		
		// Process -m option
		List<String> selectedModes = null;
		if(cmd.hasOption("m")){
			fileParamShift = fileParamShift + 2;
			if(cmd.getOptionValues("m") != null){
				selectedModes = new ArrayList<String>(Arrays.asList(cmd.getOptionValue("m").split(":")));
			}
			else{
				printHelp(opts);
				return;
			}
		}	
		
		AtomicReference<List<String>> refSelectedModes = new AtomicReference<List<String>>(selectedModes);
		HashMap<String, List<DataTuple>> data = DataBuilder.loadData(args[fileParamShift], refSelectedModes);
		DataBuilder.createArffFiles(data, refSelectedModes);
		DataBuilder.createCSVFile(data, refSelectedModes);
	}
	
	/*
	 * 
	 */
	private static void printHelp(Options opts) {

		HelpFormatter help = new HelpFormatter();
		help.setWidth(90);
		String helpString = "java -jar DataBuilder.jar [OPTIONS] CSVFILE\n" 
				+ "Example: java -jar DataBuilder.jar -m 40:41:42 hvac.csv\n"
						+ "\n OPTIONS: \n";
		help.printHelp(helpString, opts);
	}

	/*
	 * 
	 */
	private static Options createOptions(){
		Options options = new Options();
		options.addOption("h", "help", false, "Help. Print this message.");
		options.addOption("m", "mode", true, "Selected HVAC modes. Available modes are: \n" +
				"Off: 10 11 12 13 14\n" + 
				"Air injection: 20\n" +
				"Cooling: 30\n" + 
				"Heating: 40 41 42\n" + 
				"Combined: 50 51 52\n");
		return options;	
	}

}
