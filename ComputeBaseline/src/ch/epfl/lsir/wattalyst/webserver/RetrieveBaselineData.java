package ch.epfl.lsir.wattalyst.webserver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;

public class RetrieveBaselineData {
	
	/**
	 * 
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException{
		
		if(args.length != 3){
			System.err.println();
			System.err.println("Usage   : java -jar RetrieveBaselineData.jar [BASELINEID] [START_DATETIME] [END_DATETIME]");
			System.err.println("        : ");
			System.err.println("Example : java -jar RetrieveBaselineData.jar wattalyst.mallorca.sampol.Building.sensor_105.baseline_CAISO 2014-01-24--00:00 2014-01-24--23:59");
			System.err.println();
			System.exit(0);
		}
		
		WebserverDataReader reader = new WebserverDataReader();
		Date startDate = new SimpleDateFormat(ch.epfl.lsir.wattalyst.baseline.constants.Constants.DATETIME_COMPACTFORMAT).parse(args[1]);
		Date endDate = new SimpleDateFormat(ch.epfl.lsir.wattalyst.baseline.constants.Constants.DATETIME_COMPACTFORMAT).parse(args[2]);
		
		SensorReadings baseline = reader.getBaselineData(args[0], startDate, endDate);
		System.out.println(baseline.toStringAsc());
	}

}
