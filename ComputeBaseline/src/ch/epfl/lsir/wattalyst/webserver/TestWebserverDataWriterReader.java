package ch.epfl.lsir.wattalyst.webserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.wattalyst.services.secured.SignalStatus;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;

public class TestWebserverDataWriterReader {
	
	/**
	 * 
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException{
		createLuleaBaselineIDs();
//		createSampolBaselineIDs();
//		restoreDRSignaStatus();
//		readEnergyValuesAndBaselines();
	}
	
	/**
	 * 
	 */
	public static void restoreDRSignaStatus(){
		
    	WebserverDataWriter writer = new WebserverDataWriter();
		writer.setDRSignalStatus("wattalyst.lulea.campaign_3.signal_1", "wattalyst.lulea.location_43", SignalStatus.EXPIRED.name());
		writer.setDRSignalStatus("wattalyst.lulea.campaign_4_1.signal_1", "wattalyst.lulea.location_43", SignalStatus.EXPIRED.name());
		writer.setDRSignalStatus("wattalyst.lulea.campaign_3.signal_1", "wattalyst.lulea.location_46", SignalStatus.EXPIRED.name());
		writer.setDRSignalStatus("wattalyst.lulea.campaign_4.signal_1", "wattalyst.lulea.location_46", SignalStatus.EXPIRED.name());
		writer.setDRSignalStatus("wattalyst.lulea.campaign_4_2.signal_2", "wattalyst.lulea.location_46", SignalStatus.EXPIRED.name());
		writer.setDRSignalStatus("wattalyst.lulea.campaign_3.signal_1", "wattalyst.lulea.location_76", SignalStatus.EXPIRED.name());
		writer.setDRSignalStatus("wattalyst.lulea.campaign_4.signal_1", "wattalyst.lulea.location_76", SignalStatus.EXPIRED.name());
		writer.setDRSignalStatus("wattalyst.lulea.campaign_4_4.signal_4", "wattalyst.lulea.location_76", SignalStatus.EXPIRED.name());
		writer.setDRSignalStatus("wattalyst.lulea.campaign_3.signal_1", "wattalyst.lulea.location_160", SignalStatus.EXPIRED.name());
		writer.setDRSignalStatus("wattalyst.lulea.campaign_4.signal_1", "wattalyst.lulea.location_160", SignalStatus.EXPIRED.name());
		writer.setDRSignalStatus("wattalyst.lulea.campaign_6.signal_6", "wattalyst.lulea.location_160", SignalStatus.EXPIRED.name());
	}
	
	/**
	 * 
	 */
	public static void createLuleaBaselineIDs(){
		
		/*********************************************************************/
		/** LULEA                                                           */
		/*********************************************************************/		
		
		try{
			WebserverDataWriter writer = new WebserverDataWriter();
			BufferedReader br = new BufferedReader(new FileReader(new File("LULEA-sensors.txt")));
			String sensor = "";
			List<String> sensors = new ArrayList<String>();
			while((sensor = br.readLine()) != null){
				sensors.add(sensor);
			}
			br.close();
			
			for(String s : sensors){
				writer.addBaseline("CAISO baseline", "CAISO", s);
				Thread.sleep(2000);
			}
			
			WebserverDataReader reader = new WebserverDataReader();
			List<String> baselines = new ArrayList<String>();
			for(String s : sensors){
				baselines = reader.getBaselines(s);
				System.out.println("Sensor " + s);
				for(String b : baselines){
					System.out.println("Retrieved baseline " + b);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public static void createSampolBaselineIDs(){
	
		/*********************************************************************/
		/** SAMPOL                                                           */
		/*********************************************************************/

		try{
			WebserverDataWriter writer = new WebserverDataWriter();
			BufferedReader br = new BufferedReader(new FileReader(new File("SAMPOL-sensors.txt")));
			String sensor = "";
			List<String> sensors = new ArrayList<String>();
			while((sensor = br.readLine()) != null){
				sensors.add(sensor);
			}
			br.close();
			
			for(String s : sensors){
				writer.addBaseline("CAISO baseline", "CAISO", s);
				Thread.sleep(2000);
			}
			
			WebserverDataReader reader = new WebserverDataReader();
			List<String> baselines = new ArrayList<String>();
			for(String s : sensors){
				baselines = reader.getBaselines(s);
				System.out.println("Sensor " + s);
				for(String b : baselines){
					System.out.println("Retrieved baseline " + b);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void readEnergyValuesAndBaselines() throws ParseException{
		WebserverDataReader reader = new WebserverDataReader();
		Date startDate = new SimpleDateFormat(ch.epfl.lsir.wattalyst.baseline.constants.Constants.DATETIME_FORMAT).parse("2013-07-03 00:00:00");
		Date endDate = new SimpleDateFormat(ch.epfl.lsir.wattalyst.baseline.constants.Constants.DATETIME_FORMAT).parse("2013-07-03 23:59:00");
		SensorReadings readings = reader.getValuesForSensorByRange("wattalyst.lulea.location_120.sensor_1460", startDate, endDate, true);
		System.out.println(readings.toStringAsc());
//		try{
//			java.io.BufferedWriter r = new java.io.BufferedWriter(new java.io.FileWriter("/tmp/out.txt"));
//			for(long date = readings.getMinDate(); date <= readings.getMaxDate(); date = date + 10*60*1000){
//				r.write("insert into daily_profile values(" + date + 
//						", \'wattalyst.lulea.location_43.sensor_348\', " + (date/1000) + 
//						", " + readings.get(date) + ");");
//				r.newLine();
//			}
//			r.flush();
//			r.close();
//		}
//		catch(Exception e){}
		
		SensorReadings baseline = reader.getBaselineData("wattalyst.lulea.location_120.sensor_1460.baseline_CAISO", 
				startDate, endDate);
		System.out.println(baseline.toStringAsc());
	}


}
