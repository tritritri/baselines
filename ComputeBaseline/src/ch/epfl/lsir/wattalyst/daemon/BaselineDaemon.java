package ch.epfl.lsir.wattalyst.daemon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.apache.commons.io.FileUtils;

import ch.epfl.lsir.wattalyst.baseline.baselines.Baseline;
import ch.epfl.lsir.wattalyst.baseline.constants.Constants;
import ch.epfl.lsir.wattalyst.baseline.util.Util;
import ch.epfl.lsir.wattalyst.weather.Temperature;
import ch.epfl.lsir.wattalyst.weather.Wunderground;
import ch.epfl.lsir.wattalyst.webserver.EnergyData;

/**
 * 
 * @author vasirani
 *
 */
public class BaselineDaemon implements Daemon {

	private static Timer timer = null;
	
	/**
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        timer = new Timer();
        Calendar firstTime = Calendar.getInstance();
        Util.setToTheEndOfTheDay(firstTime);
        long period = 24 * 60 * 60 * 1000; // One day
        
        // TODO 
        // to remove
        period = 2000;
        firstTime = Calendar.getInstance();
        // END
        
        timer.schedule(new BaselineTask(), firstTime.getTime(), period);
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.daemon.Daemon#init(org.apache.commons.daemon.DaemonContext)
     */
    @Override
    public void init(DaemonContext dc) throws DaemonInitException, Exception {
        System.out.println("Initializing BaselineDaemon ...");
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.daemon.Daemon#start()
     */
	@Override
    public void start() throws Exception {
        System.out.println("Starting BaselineDaemon ...");
        main(null);
    }

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.daemon.Daemon#stop()
	 */
    @Override
    public void stop() throws Exception {
        System.out.println("Stopping BaselineDaemon ...");
        if (timer != null) {
            timer.cancel();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.apache.commons.daemon.Daemon#destroy()
     */
    @Override
    public void destroy() {
        System.out.println("Destroying BaselineDaemon ...");
    }

 }

/**
 * 
 * @author vasirani
 *
 */
class BaselineTask extends TimerTask {

	private static final String BLDAEMON_CONFIG_FILE = "bldaemon.config";
	
	private static final String HORIZON_PARAM = "horizon";
	private static final String HISTORYLENGTH_PARAM = "history-length";
	private static final String HISTORYWEEKDAY_PARAM = "history-weekday";
	private static final Object HISTORYWEEKEND_PARAM = "history-weekend";
	private static final Object LAGWEEKDAY_PARAM = "lag-weekday";
	private static final Object LAGWEEKEND_PARAM = "lag-weekend";
	private static final Object MINVALUEMALLOWED_PARAM = "min-value-allowed";
	private static final Object REGRESSION_PARAM = "regression";
	
	private static final String ENERGYFILE_PARAM = "energy-file";
	private static final String TEMPERATUREFILE_PARAM = "temperature-file";
	private static final String ALGORITHMFILE_PARAM = "algorithm-file";
	
	private static final String HISTORIC_SENSOR_DATA_DIR = "sensor";
	private static final String HISTORIC_TEMP_DATA_DIR = "temp";
	private static final String BASELINE_DATA_DIR = "baseline";
	private static final String BASELINE_DATA_DIR_INPUT = "input";
	private static final String BASELINE_DATA_DIR_OUTPUT = "output";
		
	private SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
	private List<String> baselineIDs;
	
	private int horizon;
	private int historyLength;
	private int historyWeekday;
	private int historyWeekend;
	private int lagWeekday;
	private int lagWeekend;
	private int minValueAllowed;
	private String regression;
	
	/*
	 * (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
    @Override
    public void run() {
    	
    	System.out.println("Retrieving configuration parameters ...");
        if(retrieveConfigParameters()){
	        System.out.println("Done");
	        
	        System.out.println("Retrieving baselines to compute from Wattalyst DB ...");
	        retrieveBaselineIDs();
	        System.out.println("Done");
	        
	        System.out.println("Creating directories ...");
	        if(createDataDirectories()){
		        System.out.println("Done");
		        
		        System.out.println("Retrieving historic sensor data ...");
		        retrieveHistoricSensorData();
		        System.out.println("Done");
		        
		        System.out.println("Retrieving historic temperature data ...");
		        retrieveHistoricTemperatureData();
		        System.out.println("Done");
		        
		        System.out.println("Preparing baseline input files ...");
		        if(setupBaselineInputFiles()){
			        System.out.println("Done");
			        
			        System.out.println("Computing baselines ...");
			        computeBaselines();
			        System.out.println("Done");
		        }
	        }
        }
    }
    
    /**
     * 
     */
    protected boolean retrieveConfigParameters() {
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(new File(BLDAEMON_CONFIG_FILE)));
			String line;
			HashMap<String, String> params = new HashMap<String, String>();
			while((line = reader.readLine()) != null){
				params.put(line.split("=")[0], line.split("=")[1]);
			}
			
			for(String param : params.keySet()){
				if(param.equals(HORIZON_PARAM)){
					try{
						horizon = Integer.valueOf(params.get(HORIZON_PARAM));
					}
					catch(NumberFormatException e){
						System.out.println("[ERROR] Cannot read " + HORIZON_PARAM + " param in file " + BLDAEMON_CONFIG_FILE);
						reader.close();
						return false;
					}
				}
				else if(param.equals(HISTORYLENGTH_PARAM)){
					try{
						historyLength = Integer.valueOf(params.get(HISTORYLENGTH_PARAM));
					}
					catch(NumberFormatException e){
						System.out.println("[ERROR] Cannot read " + HISTORYLENGTH_PARAM + " param in file " + BLDAEMON_CONFIG_FILE);
						reader.close();
						return false;
					}
				}
				else if(param.equals(HISTORYWEEKDAY_PARAM)){
					try{
						historyWeekday = Integer.valueOf(params.get(HISTORYWEEKDAY_PARAM));
					}
					catch(NumberFormatException e){
						System.out.println("[ERROR] Cannot read " + HISTORYWEEKDAY_PARAM + " param in file " + BLDAEMON_CONFIG_FILE);
						reader.close();
						return false;
					}
				}
				else if(param.equals(HISTORYWEEKEND_PARAM)){
					try{
						historyWeekend = Integer.valueOf(params.get(HISTORYWEEKEND_PARAM));
					}
					catch(NumberFormatException e){
						System.out.println("[ERROR] Cannot read " + HISTORYWEEKEND_PARAM + " param in file " + BLDAEMON_CONFIG_FILE);
						reader.close();
						return false;
					}
				}
				else if(param.equals(LAGWEEKDAY_PARAM)){
					try{
						lagWeekday = Integer.valueOf(params.get(LAGWEEKDAY_PARAM));
					}
					catch(NumberFormatException e){
						System.out.println("[ERROR] Cannot read " + LAGWEEKDAY_PARAM + " param in file " + BLDAEMON_CONFIG_FILE);
						reader.close();
						return false;
					}
				}
				else if(param.equals(LAGWEEKEND_PARAM)){
					try{
						lagWeekend = Integer.valueOf(params.get(LAGWEEKEND_PARAM));
					}
					catch(NumberFormatException e){
						System.out.println("[ERROR] Cannot read " + LAGWEEKEND_PARAM + " param in file " + BLDAEMON_CONFIG_FILE);
						reader.close();
						return false;
					}
				}
				else if(param.equals(MINVALUEMALLOWED_PARAM)){
					try{
						minValueAllowed = Integer.valueOf(params.get(MINVALUEMALLOWED_PARAM));
					}
					catch(NumberFormatException e){
						System.out.println("[ERROR] Cannot read " + MINVALUEMALLOWED_PARAM + " param in file " + BLDAEMON_CONFIG_FILE);
						reader.close();
						return false;
					}
				}
				else if(param.equals(REGRESSION_PARAM)){
					try{
						regression = params.get(REGRESSION_PARAM);
					}
					catch(NumberFormatException e){
						System.out.println("[ERROR] Cannot read " + REGRESSION_PARAM + " param in file " + BLDAEMON_CONFIG_FILE);
						reader.close();
						return false;
					}
				}
				else{
					System.out.println("[WARNING] Unknown param " + param + " found in file " + BLDAEMON_CONFIG_FILE);
				}
			}
			
			if(horizon <= 0){
				System.out.println("[ERROR] The param " + HORIZON_PARAM + " cannot be negative");
				reader.close();
				return false;
			}
			else if(historyLength <= 0){
				System.out.println("[ERROR] The param " + HISTORYLENGTH_PARAM + " cannot be negative");
				reader.close();
				return false;
			}
			reader.close();
			return true;
		} 
		catch (FileNotFoundException e) {
			System.out.println("[ERROR] Cannot find file " + BLDAEMON_CONFIG_FILE);
			return false;
		} catch (IOException e) {
			System.out.println("[ERROR] Cannot read file " + BLDAEMON_CONFIG_FILE);
			return false;
		}
		
	}
    
    /**
     * 
     */
    protected void retrieveBaselineIDs() {
    	baselineIDs = new ArrayList<String>();
    	
    	System.out.println("[WARNING] Interface with Wattalyst DB not ready yet, return default list");
    	// Energy heating
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_346.PJMEco");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_346.CAISO");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_346.NYISO");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_346.Mid4Of6");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_346.ISONE");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_346.Regression");
    	
    	// Energy water
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_348.PJMEco");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_348.CAISO");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_348.NYISO");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_348.Mid4Of6");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_348.ISONE");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_348.Regression");
    	
    	// Energy electricity
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_590.PJMEco");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_590.CAISO");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_590.NYISO");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_590.Mid4Of6");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_590.ISONE");
    	baselineIDs.add("wattalyst.lulea.location_43.sensor_590.Regression");
    }

    /**
     * 
     */
    protected boolean createDataDirectories() {
		File dir = new File(HISTORIC_SENSOR_DATA_DIR);
		if(!dir.exists()){
			if(!dir.mkdirs()){
				System.out.println("[Error] Cannot create directory " + HISTORIC_SENSOR_DATA_DIR);
				return false;
			}
		}
		dir = new File(HISTORIC_TEMP_DATA_DIR);
		if(!dir.exists()){
			if(!dir.mkdirs()){
				System.out.println("[Error] Cannot create directory " + HISTORIC_TEMP_DATA_DIR);
				return false;
			}
		}
		dir = new File(BASELINE_DATA_DIR);
		if(!dir.exists()){
			if(!dir.mkdirs()){
				System.out.println("[Error] Cannot create directory " + BASELINE_DATA_DIR);
				return false;
			}
		}
		
		for(String blID : baselineIDs){
			String baselineType = this.getBaselineType(blID);
			dir = new File(BASELINE_DATA_DIR + System.getProperty("file.separator") + 
					baselineType + System.getProperty("file.separator") + BASELINE_DATA_DIR_INPUT);
			if(!dir.exists()){
				if(!dir.mkdirs()){
					System.out.println("[Error] Cannot create directory " + (BASELINE_DATA_DIR + System.getProperty("file.separator") + 
							baselineType + System.getProperty("file.separator") + BASELINE_DATA_DIR_INPUT));
					return false;
				}
			}
			dir = new File(BASELINE_DATA_DIR + System.getProperty("file.separator") + 
					baselineType + System.getProperty("file.separator") + BASELINE_DATA_DIR_OUTPUT);
			if(!dir.exists()){
				if(!dir.mkdirs()){
					System.out.println("[Error] Cannot create directory " + (BASELINE_DATA_DIR + System.getProperty("file.separator") + 
							baselineType + System.getProperty("file.separator") + BASELINE_DATA_DIR_OUTPUT));
					return false;
				}
			}
		}
		return true;	
	}
	
	/**
     * 
     */
    protected void retrieveHistoricSensorData() {
		
    	// Set the start date and end date for the historic values
    	Date histStartDate = getHistoryStartDate();
    	Date histEndDate = getHistoryEndDate();
    				
    	List<String> sensorIDs = new ArrayList<String>();
    	for(String blID : baselineIDs){
    		
    		// Set the sensor name from the baseline ID
    		String id = getSensorID(blID);
			
    		if(!sensorIDs.contains(id)){
    			sensorIDs.add(id);
    		}
    		
    	}
    	
    	for(String id : sensorIDs){
    		
			// Set the output file
    		String outputFile = HISTORIC_SENSOR_DATA_DIR + System.getProperty("file.separator") + getHistoricSensorDataFile(id, histStartDate, histEndDate);
    					
			// Retrieve the energy data
			EnergyData energyData = new EnergyData();
			try {
				energyData.compute(id, histStartDate, histEndDate);
				energyData.writeResultToFile(outputFile);
			} catch (Exception e) {
				System.out.println("[ERROR] Cannot retrieve historic data for " + id);
			}
			
		}
    	
	}	
    
    /**
     * 
     */
    protected void retrieveHistoricTemperatureData() {
    	
    	// Set the start date and end date for the historic values and forecast
    	Date tempStartDate = getHistoryStartDate();
    	Date tempEndDate = getBaselineEndDate();
    	
    	List<String> sensorIDs = new ArrayList<String>();
    	for(String blID : baselineIDs){
    		
    		// Set the sensor name from the baseline ID
    		String id = getSensorID(blID);
			
    		if(!sensorIDs.contains(id)){
    			sensorIDs.add(id);
    		}
    		
    	}
    	
    	for(String id : sensorIDs){
    					
	    	// Set the output file
    		String outputFile = getTemperatureDataFile(id, tempStartDate, tempEndDate);
    		
    		// Set the country and location
        	System.out.println("[WARNING] Must query Wattalyst DB for location of the sensor, assume Lulea only");
        	String country = "Sweden";
        	String place = "Lulea";
        	
	    	Temperature t = new Wunderground();
	    	t.compute(tempStartDate, tempEndDate, country, place);
			t.writeResultToFile(outputFile);

			// TODO Remove
			// For Wunderground call limit
			try {
				Thread.sleep(60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
	}
    
    /**
	 * 
	 * @return
	 */
    protected boolean setupBaselineInputFiles() {
    	
    	for(String blID : baselineIDs){
    		
    		String configFile = "";
    		
    		// Setup input file for Regression. 
    		if(this.getBaselineType(blID).equals("Regression")){
	    		
    			try {
    				String algLinearRegressionFile =  getBaselineInputDataDir(blID) + System.getProperty("file.separator") + "algLinearRegression.txt";
    				BufferedWriter writer = new BufferedWriter(new FileWriter(new File(algLinearRegressionFile)));
					writer.write(regression + "\n");
					writer.flush();
					writer.close();
					
    				configFile = getBaselineInputDataDir(blID) + System.getProperty("file.separator") + getBaselineInputDataFile(blID, getHistoryStartDate(), getHistoryEndDate());
    				writer = new BufferedWriter(new FileWriter(new File(configFile)));
					writer.write(ENERGYFILE_PARAM + " = " + HISTORIC_SENSOR_DATA_DIR + System.getProperty("file.separator") + getHistoricSensorDataFile(getSensorID(blID), getHistoryStartDate(), getHistoryEndDate()) + "\n");
					writer.write(TEMPERATUREFILE_PARAM + " = " + HISTORIC_TEMP_DATA_DIR + System.getProperty("file.separator") + getTemperatureDataFile(getSensorID(blID), getHistoryStartDate(), getBaselineEndDate()) + "\n");
					writer.write(ALGORITHMFILE_PARAM + " = " + algLinearRegressionFile + "\n");
					writer.write(HISTORYWEEKDAY_PARAM + " = " + historyWeekday + "\n");
					writer.write(HISTORYWEEKEND_PARAM + " = " + historyWeekend + "\n");
					writer.write(LAGWEEKDAY_PARAM + " = " + lagWeekday + "\n");
					writer.write(LAGWEEKEND_PARAM + " = " + lagWeekend + "\n");
					writer.write(MINVALUEMALLOWED_PARAM + " = " + minValueAllowed + "\n");
					
					writer.flush();
					writer.close();
				} catch (IOException e) {
					System.out.println("[Error] Cannot create input file " + configFile);
					return false;
				}
    		}
    		// For all the other baselines, the input file is simply the historic sensor data file
    		else{
    			String historicSensorDataFile = HISTORIC_SENSOR_DATA_DIR + System.getProperty("file.separator") + getHistoricSensorDataFile(getSensorID(blID), getHistoryStartDate(), getHistoryEndDate());
    			configFile = getBaselineInputDataDir(blID) + System.getProperty("file.separator") + getBaselineInputDataFile(blID, getHistoryStartDate(), getHistoryEndDate());
    			try {
					FileUtils.copyFile(new File(historicSensorDataFile), new File(configFile));
				} catch (IOException e) {
					System.out.println("[Error] Cannot create input file " + configFile);
					return false;
				}
    		}
    	}
    	return true;
	}

	/**
	 * 
	 */
    protected void computeBaselines() {
    	
    	// Set the start date and end date for the baseline
    	Date blStartDate = getBaselineStartDate();
    	Date blEndDate = getBaselineEndDate();
    	
    	// Set the start date and end date for the historic values
    	Date histStartDate = getHistoryStartDate();
    	Date histEndDate = getHistoryEndDate();
    	
    	for(String blID : baselineIDs){
    		
    		// Set the input file
    		String inputFile = getBaselineInputDataDir(blID) + System.getProperty("file.separator") + getBaselineInputDataFile(blID, histStartDate, histEndDate);
    		    		
    		// Set the output file
    		String outputFile = getBaselineOutputDataDir(blID) + System.getProperty("file.separator") + getBaselineOutputDataFile(blID, blStartDate, blEndDate);
    		
    		// Set the baseline method
    		String baseline = getBaselineClass(blID);
    		Baseline b;
			try {
				b = (Baseline) Class.forName(baseline).newInstance();
				b.compute(inputFile, formatter.format(blStartDate), formatter.format(blEndDate));
				b.writeResultToFile(outputFile);
			} 
			catch (InstantiationException e) {
				System.out.println("[Error] Cannot instantiate " + baseline);
			} 
			catch (IllegalAccessException e) {
				System.out.println("[Error] Cannot instantiate " + baseline);
			} 
			catch (ClassNotFoundException e) {
				System.out.println("[Error] Cannot instantiate " + baseline);
			}
			catch (Exception e) {
				System.out.println("[Error] Cannot compute baseline " + blID);
			}
			
    	}
	}
   
	/**
     * 
     * @return
     */
    private Date getHistoryStartDate() {
    	Calendar calendar = Calendar.getInstance();
    	Util.setToTheBeginningOfTheDay(calendar);
    	calendar.roll(Calendar.DAY_OF_YEAR, -historyLength);
    	return calendar.getTime();
	}

	/**
     * 
     * @return
     */
	private Date getHistoryEndDate() {
		Calendar calendar = Calendar.getInstance();
		Util.setToTheEndOfTheDay(calendar);
    	return calendar.getTime();
	}
	
    /**
     * 
     * @return
     */
	private Date getBaselineEndDate() {
		Calendar calendar = Calendar.getInstance();
		Util.setToTheEndOfTheDay(calendar);
    	calendar.add(Calendar.DAY_OF_YEAR, horizon);
    	return calendar.getTime();
	}

	/**
	 * 
	 * @return
	 */
	private Date getBaselineStartDate() {
		Calendar calendar = Calendar.getInstance();
		Util.setToTheBeginningOfTheDay(calendar);
    	return calendar.getTime();
	}
	
    /**
     * 
     * @param baselineID
     * @return
     */
	private String getBaselineInputDataDir(String baselineID) {
		return BASELINE_DATA_DIR + System.getProperty("file.separator") + getBaselineType(baselineID) + System.getProperty("file.separator") + 
				BASELINE_DATA_DIR_INPUT;
	}
	
	/**
     * 
     * @param baselineID
     * @return
     */
	private String getBaselineOutputDataDir(String baselineID) {
		return BASELINE_DATA_DIR + System.getProperty("file.separator") + getBaselineType(baselineID) + System.getProperty("file.separator") + 
				BASELINE_DATA_DIR_OUTPUT;
	}
	
    /**
     * 
     * @param sensorID
     * @param histStartDate
     * @param histEndDate
     * @return
     */
    private String getTemperatureDataFile(String sensorID, Date histStartDate,
			Date histEndDate) {
    	return sensorID + "." + formatter.format(histStartDate) + "--" + formatter.format(histEndDate) + ".txt"; 	
    			
	}
    
    /**
     * 
     * @param sensorID
     * @param histStartDate
     * @param histEndDate
     * @return
     */
	private String getHistoricSensorDataFile(String sensorID,
			Date histStartDate, Date histEndDate) {
		return sensorID + "." + formatter.format(histStartDate) + "--" + formatter.format(histEndDate) + ".txt"; 	 
	}
	
	/**
	 * 
	 * @param baselineID
	 * @param histStartDate
	 * @param histEndDate
	 * @return
	 */
	private String getBaselineInputDataFile(String baselineID, Date histStartDate, Date histEndDate) {
		return getSensorID(baselineID) + "." + formatter.format(histStartDate) + "--" + formatter.format(histEndDate) + ".txt"; 	
	}
	
    /**
     * 
     * @param baselineID
     * @param blStartDate
     * @param blEndDate
     * @return
     */
	private String getBaselineOutputDataFile(String baselineID, Date blStartDate,
			Date blEndDate) {
		return baselineID + "." + formatter.format(blStartDate) + "--" + formatter.format(blEndDate) + ".txt"; 	
	}
	
    /**
     * 
     * @param baselineID
     * @return
     */
	private String getSensorID(String baselineID) {
		String[] tokens = baselineID.split("\\.");
		String sensorID = "";
		for(int i = 0; i < tokens.length - 1; i++){
			sensorID = sensorID + tokens[i] + ".";
		}
		return sensorID.substring(0, sensorID.length() - 1);
	}
	
	/**
     * 
     * @param baselineID
     * @return
     */
	private String getBaselineType(String baselineID) {
		String[] tokens = baselineID.split("\\.");
		return tokens[tokens.length - 1];
	}
	
	/**
     * 
     * @param baselineID
     * @return
     */
	private String getBaselineClass(String baselineID) {
		String[] tokens = baselineID.split("\\.");
		return "ch.epfl.lsir.wattalyst.baseline.baselines." + tokens[tokens.length - 1];
	}
}
