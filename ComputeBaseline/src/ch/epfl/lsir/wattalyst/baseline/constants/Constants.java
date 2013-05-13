package ch.epfl.lsir.wattalyst.baseline.constants;

import java.util.Calendar;

/**
 * All constants needed
 * 
 * @author tritritri
 * @date   2013.02.27
 */
public class Constants {

	public static int VERBOSE = 0;
	public static String DATE_FORMAT = "yyyy-MM-dd";
	public static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String DB_DATETIME_FORMAT = "dd.MM.yyyy;HH:mm";
	
	/* set time zone to the local time zone
	 * in ComputeBaseline, if option timezone is specified, then TIMEZONE_REF is changed
	 * see in ComputeBaseline.java: if (cmd.hasOption("t")) ... 
	 */
	public static String TIMEZONE_REF = Calendar.getInstance().getTimeZone().getID();;
}
