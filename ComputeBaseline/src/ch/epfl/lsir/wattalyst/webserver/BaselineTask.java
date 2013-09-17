package ch.epfl.lsir.wattalyst.webserver;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ch.epfl.lsir.wattalyst.baseline.baselines.Baseline;
import ch.epfl.lsir.wattalyst.baseline.constants.Constants;
import ch.epfl.lsir.wattalyst.baseline.util.Util;


public class BaselineTask {

	/**
	 * 
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		
		Calendar history = Calendar.getInstance();
		Date endDate = history.getTime();
		history.add(Calendar.DAY_OF_YEAR, -60);
		Util.setToTheBeginningOfTheDay(history);
		Date startDate = history.getTime();
				
		Calendar target = Calendar.getInstance();
		target.add(Calendar.DAY_OF_YEAR, 2);
		Util.setToTheEndOfTheDay(history);
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		String targetDate = formatter.format(target.getTime());
				
		WebserverDataReader reader = new WebserverDataReader();
		
		// 1. Retrieve all sensors
		for(String sensor : reader.getSensors()){
			// 2. Retrieve baselines for the sensor
			List<String> baselines = reader.getBaselines(sensor);
			if(!baselines.isEmpty()){
				// 3. Retrieve sensor historic data and write to file
				EnergyData e = new EnergyData();
				e.compute(sensor, startDate, endDate, true);
				e.removeOutliers(sensor);
				e.writeResultToFile(sensor + ".txt");
				// 4. Cycle over baselines
				for(String baselineID : baselines){
					
					try {
					
						// 4. Compute baseline
						String baselineClass = getBaselineClass(baselineID);
						Baseline b = (Baseline) Class.forName(baselineClass).newInstance();
						b.compute(sensor + ".txt", targetDate, targetDate);
						
						// 5. Store baseline
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
					}
				}
			}
		}
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
}
