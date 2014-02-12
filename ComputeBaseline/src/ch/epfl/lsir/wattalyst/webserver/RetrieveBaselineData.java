package ch.epfl.lsir.wattalyst.webserver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
		
		if(args.length != 5){
			System.err.println();
			System.err.println("Usage   : java -jar RetrieveBaselineData.jar -o FILENAME BASELINEID STARTDATE ENDDATE");
			System.err.println("        : ");
			System.err.println("Example : java -jar RetrieveBaselineData.jar -o sampol-bl.csv wattalyst.mallorca.sampol.Building.sensor_105.baseline_CAISO 2014-01-24--00:00 2014-01-24--23:59");
			System.err.println();
			System.exit(0);
		}
		
		WebserverDataReader reader = new WebserverDataReader();
		Date startDate = new SimpleDateFormat(ch.epfl.lsir.wattalyst.baseline.constants.Constants.DATETIME_COMPACTFORMAT).parse(args[3]);
		Date endDate = new SimpleDateFormat(ch.epfl.lsir.wattalyst.baseline.constants.Constants.DATETIME_COMPACTFORMAT).parse(args[4]);
		
		SensorReadings baseline = reader.getBaselineData(args[2], startDate, endDate);
		
		PrintWriter fileOut;
		try {
			fileOut = new PrintWriter(args[1]);
			fileOut.print(baseline.toStringAsc(startDate.getTime(), endDate.getTime()));
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
