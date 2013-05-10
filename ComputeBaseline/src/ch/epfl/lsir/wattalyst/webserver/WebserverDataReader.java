package ch.epfl.lsir.wattalyst.webserver;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

import org.wattalyst.services.AValueDto;
import org.wattalyst.services.DataAccess;
import org.wattalyst.services.DataAccessService;
import org.wattalyst.services.NumericValueDto;
import org.wattalyst.services.ValueListResultContainer;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;
import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;

public class WebserverDataReader {
		
	/**
	 * 
	 * @param sensorName
	 * @param startDate these dates are in executing machine time zone
	 * @param endDate these dates are in executing these dates are in machine timethese dates are in machine timemachine time
	 * @param useDifferenceMethod
	 * @return
	 * @throws RemoteException 
	 */
	SensorReadings getValuesForSensorByRange(String sensorName, Date startDate, Date endDate, 
			boolean useDifferenceMethod) {
		
		// Invoke the web service and retrieve the result
		DataAccessService service = new DataAccessService();
		DataAccess port = service.getDataAccessPort();
		
		// Create a sensor readings data structure 
		SensorReadings readings = new SensorReadings();
					
		try{
			ValueListResultContainer result = port.getValuesForSensorByRange(sensorName, startDate.getTime(), endDate.getTime());
			
			// Put the result in a sorted set
			if("OK".equals(result.getStatus().value())){
				TreeSet<AValueDto> sortedSet = new TreeSet<AValueDto>();
				for(AValueDto r : result.getValues()){
					sortedSet.add(r);
				}
	// TODO comment this part	
//					if(r instanceof NumericValueDto){
//						Calendar c = Calendar.getInstance();
//						c.setTimeInMillis(r.getTimestamp());
//						System.out.println(r.getTimestamp() + "," + ((NumericValueDto) r).getValue()); 
//						System.out.println(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) +
//							"," + c.get(Calendar.HOUR_OF_DAY) + "," + c.get(Calendar.MINUTE) + "," +
//							((NumericValueDto)r).getValue());
//					}
//				}
//				System.out.println();
	// TODO end comment	
				
				// Generate a sensor readings data set using the difference method (e.g. for energy)
				if(useDifferenceMethod){
					Calendar current = Calendar.getInstance();
					current.setTime(startDate);
					while(!current.getTime().after(endDate)){
						Double lower = findLastValueBeforeOrEqual(sortedSet, current.getTime().getTime());
						Double upper = findLastValueBeforeOrEqual(sortedSet, current.getTime().getTime() + 3600000 - 1); //3600000 = 1*60*60*1000: 1 hour shift
						
						if(lower.isNaN() || upper.isNaN()){
							readings.insert(current.getTimeInMillis(), Double.NaN);
						}
						else {
							readings.insert(current.getTimeInMillis(), (upper - lower));
						}
						
						current.add(Calendar.HOUR_OF_DAY, 1);
						//current.add(Calendar.MINUTE, 10);
					}
				}
				// Generate a sensor readings data set aggregating different readings in the same hour (e.g. for power)
				else{
					throw new RuntimeException("Not implemented yet!");
				}
			}
		}
		catch(Exception e){
			
		}
		
		return readings;
	}
	
	/*
	 * 
	 */
	private double findLastValueBeforeOrEqual(TreeSet<AValueDto> sortedSet, long time) {
		Double lastValue = Double.NaN;
		for(AValueDto v : sortedSet){
			if(v.getTimestamp() <= time){
				if(v instanceof NumericValueDto){
					lastValue = ((NumericValueDto)v).getValue();
				}
			}
			else{
				break;
			}
		}
		return lastValue;
	}

	/**
	 * 
	 * @param args
	 * @throws RemoteException
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws RemoteException, ParseException{
		WebserverDataReader reader = new WebserverDataReader();
		Date startDate = new SimpleDateFormat(Constants.DATETIME_FORMAT).parse("2012-12-01 00:00:00");
		Date endDate = new SimpleDateFormat(Constants.DATETIME_FORMAT).parse("2013-03-31 23:59:00");
		SensorReadings readings = reader.getValuesForSensorByRange("wattalyst.lulea.location_43.sensor_348", startDate, endDate, true);
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
	}
}
