package ch.epfl.lsir.wattalyst.webserver;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

import org.apache.axis2.AxisFault;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;
import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.webserver.services.AValueDto;
import ch.epfl.lsir.wattalyst.webserver.services.DataAccessServiceStub;
import ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorByRange;
import ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorByRangeE;
import ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorByRangeResponse;
import ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorByRangeResponseE;
import ch.epfl.lsir.wattalyst.webserver.services.NumericValueDto;
import ch.epfl.lsir.wattalyst.webserver.services.ValueListResultContainer;

public class WebserverDataReader {
	
	private DataAccessServiceStub stub;
	
	/**
	 * @throws AxisFault 
	 * 
	 */
	public WebserverDataReader() throws AxisFault{
		stub = new DataAccessServiceStub();
	}
	
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
			boolean useDifferenceMethod) throws RemoteException{
		
		// Prepare the method to invoke
		GetValuesForSensorByRangeE method = new GetValuesForSensorByRangeE();
		GetValuesForSensorByRange param = new GetValuesForSensorByRange();
		param.setFullQualifiedName(sensorName);
		param.setStart(startDate.getTime());
		param.setEnd(endDate.getTime());
		method.setGetValuesForSensorByRange(param);
		
		// Invoke the web service and retrieve the result
		GetValuesForSensorByRangeResponseE response = stub.getValuesForSensorByRange(method);
		GetValuesForSensorByRangeResponse value = response.getGetValuesForSensorByRangeResponse();
		
		// Create a sensor readings data structure 
		SensorReadings readings = new SensorReadings();
		
		// Put the result in a sorted set
		ValueListResultContainer valueListResult = value.getValueListResultContainer();
		if(valueListResult.isStatusSpecified() && valueListResult.getStatus().getValue().equals("OK")){
			TreeSet<AValueDto> sortedSet = new TreeSet<AValueDto>();
			for(AValueDto result : valueListResult.getValues()){
				sortedSet.add(result);
			}
//// TODO comment this part	
//				if(result instanceof NumericValueDto){
//					Calendar c = Calendar.getInstance();
//					c.setTimeInMillis(result.getTimestamp());
//					//System.out.println(result.getTimestamp() + "," + ((NumericValueDto) result).getValue()); 
//					System.out.println(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) +
//						"," + c.get(Calendar.HOUR_OF_DAY) + "," + c.get(Calendar.MINUTE) + "," +
//						((NumericValueDto)result).getValue());
//				}
//			}
//			System.out.println();
//// TODO end comment	
			
			// Generate a sensor readings data set using the difference method (e.g. for energy)
			if(useDifferenceMethod){
				Calendar current = Calendar.getInstance();
				current.setTime(startDate);
				while(!current.getTime().after(endDate)){
					double lower = findLastValueBeforeOrEqual(sortedSet, current.getTime().getTime());
					double upper = findLastValueBeforeOrEqual(sortedSet, current.getTime().getTime() + 3600000 - 1); //3600000 = 1*60*60*1000: 1 hour shift
					
					if(lower == Double.NaN || upper == Double.NaN){
						readings.insert(current.getTimeInMillis(), Double.NaN);
					}
					else {
						readings.insert(current.getTimeInMillis(), (upper - lower));
					}
					
					current.add(Calendar.HOUR_OF_DAY, 1);
				}
			}
			// Generate a sensor readings data set aggregating different readings in the same hour (e.g. for power)
			else{
				throw new RuntimeException("Not implemented yet!");
			}
		}
		
		return readings;
	}
	
	/*
	 * 
	 */
	private double findLastValueBeforeOrEqual(TreeSet<AValueDto> sortedSet, long time) {
		double lastValue = Double.NaN;
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
		Date startDate = new SimpleDateFormat(Constants.DATETIME_FORMAT).parse("2013-03-07 22:00:00");
		Date endDate = new SimpleDateFormat(Constants.DATETIME_FORMAT).parse("2013-03-08 23:59:59");
		SensorReadings readings = reader.getValuesForSensorByRange("wattalyst.lulea.location_43.sensor_346", startDate, endDate, true);
		System.out.println(readings.toStringAsc());
	}
}
