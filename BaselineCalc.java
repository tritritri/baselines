import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.StringTokenizer;
import java.util.TimeZone;


public class BaselineCalc {
  Calendar calendar;
	SimpleDateFormat dateformat;
	HashMap<Date, float[]> data;

	public BaselineCalc()
	{
		// get the supported ids for GMT-08:00 (Pacific Standard Time)
				 String[] ids = TimeZone.getAvailableIDs(1 * 60 * 60 * 1000);
				 // if no ids were returned, something is wrong. get out.
				 if (ids.length == 0)
				     System.exit(0);

				  // begin output
				 System.out.println("Current Time");

				 // create a time zone
				 SimpleTimeZone pdt = new SimpleTimeZone(1 * 60 * 60 * 1000, ids[0]);

				 // set up rules for Daylight Saving Time
				 pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
				 pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);

				 // create a GregorianCalendar with the Pacific Daylight time zone
				 // and the current date and time
				 calendar = new GregorianCalendar(pdt);
				 dateformat = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
				 
				 data = new HashMap<Date, float[]>();
	}
	/**
	 * @param args
	 */
	
	void summarize(String filename)
	{
		String sdate = null;
		String line=null;
		StringTokenizer strtoken = null;
		String svalue = null;
		float [] dayValues = null;
		int hour, currentHour=-1;
		int day, currentDay=-1;
		Date date = null, currDate=null;
		float value;
		float sum = 0;
		int count = 0;

				
		try {
			BufferedReader in
			   = new BufferedReader(new FileReader(filename));
			
			
			
			while((line=in.readLine()) != null)
			{
				System.out.println(line);
			
				strtoken = new StringTokenizer(line,",");
				sdate = strtoken.nextToken();
				svalue = strtoken.nextToken();
				date= dateformat.parse(sdate);
				value = Float.parseFloat(svalue);
				
				
				calendar.setTime(date);
				
						
				
				
				hour = calendar.get(Calendar.HOUR_OF_DAY);
				day = calendar.get(Calendar.DAY_OF_YEAR);
				
			
				if (dayValues == null)
				{
					System.out.println("-------- 1 -----------");
					dayValues = data.get(date);
					if (dayValues == null)
						dayValues = new float[24];
				}
				
				if (currentDay == -1 && currentHour == -1)
				{
					System.out.println("-------- 2 -----------");
					currentDay = day;
					currentHour = hour;
					currDate = date;
					sum += value;
					count++;
				}else if (day == currentDay && hour == currentHour)
				{
					System.out.println("-------- 3 -----------");
					sum += value;
						count++;
				} else if (day == currentDay) {
					System.out.println("-------- 4 -----------");
				
						dayValues[currentHour] = sum/count;
						System.out.println("Hour that was stored: "+currentHour+" with average "+dayValues[currentHour]);
						currentHour = hour;
						sum = value;
						count=1;
				} else {
					System.out.println("-------- 5 -----------");
						dayValues[currentHour] = sum/count;
						System.out.println("Hour that was stored: "+currentHour);
						calendar.set(currDate.getYear()+1900, currDate.getMonth(), currDate.getDate(), 0,0,0);
						Date d = calendar.getTime();
						System.out.println("Inserting : "+d.toString());
						data.put(d, dayValues);
						currDate = date;
						dayValues = data.get(date);
						if (dayValues == null)
							dayValues = new float[24];
						
						currentDay = day;
						currentHour = hour;
						sum = value;
						count=1;
						
				}
						
					
			}
				
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Date key : data.keySet()) {
			float[] temp = data.get(key);
			System.out.print(key.toString()+",");
			for (int j=0;j<temp.length;j++)
				System.out.print(temp[j]+",");
			System.out.println();
			}
		
		
		
	}
	
	
	void summarize_power(String filename)
	{
		String sdate = null;
		String line=null;
		StringTokenizer strtoken = null;
		String svalue = null;
		float [] dayValues = null;
		int hour, currentHour=-1;
		int day, currentDay=-1;
		int second = 0, currentSecond = 0;
		int minute = 0, currentMinute = 0;
		
		Date date = null, currDate=null;
		float value;
		float sum = 0;
		int count = 0;
		
		float currentConsumption = 0;

				
		try {
			BufferedReader in
			   = new BufferedReader(new FileReader(filename));
			
			
			
			while((line=in.readLine()) != null)
			{
				System.out.println(line);
			
				strtoken = new StringTokenizer(line,",");
				sdate = strtoken.nextToken();
				svalue = strtoken.nextToken();
				date= dateformat.parse(sdate);
				value = Float.parseFloat(svalue);
				
				
				calendar.setTime(date);
				
						
				
				
				hour = calendar.get(Calendar.HOUR_OF_DAY);
				day = calendar.get(Calendar.DAY_OF_YEAR);
				minute = calendar.get(Calendar.MINUTE);
				second = calendar.get(Calendar.SECOND);
				
			
				if (dayValues == null)
				{
					System.out.println("-------- 1 -----------");
					dayValues = data.get(date);
					if (dayValues == null)
						dayValues = new float[24];
				}
				
				if (currentDay == -1 && currentHour == -1)
				{
					System.out.println("-------- 2 -----------");
					currentDay = day;
					currentHour = hour;
					currDate = date;
					sum += value;
					count++;
				}else if (day == currentDay && hour == currentHour)
				{
					System.out.println("-------- 3 -----------");
					sum += value;
						count++;
				} else if (day == currentDay) {
					System.out.println("-------- 4 -----------");
				
						dayValues[currentHour] = sum/count;
						System.out.println("Hour that was stored: "+currentHour+" with average "+dayValues[currentHour]);
						currentHour = hour;
						sum = value;
						count=1;
				} else {
					System.out.println("-------- 5 -----------");
						dayValues[currentHour] = sum/count;
						System.out.println("Hour that was stored: "+currentHour);
						calendar.set(currDate.getYear()+1900, currDate.getMonth(), currDate.getDate(), 0,0,0);
						Date d = calendar.getTime();
						System.out.println("Inserting : "+d.toString());
						data.put(d, dayValues);
						currDate = date;
						dayValues = data.get(date);
						if (dayValues == null)
							dayValues = new float[24];
						
						currentDay = day;
						currentHour = hour;
						sum = value;
						count=1;
						
				}
						
					
			}
				
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Date key : data.keySet()) {
			float[] temp = data.get(key);
			System.out.print(key.toString()+",");
			for (int j=0;j<temp.length;j++)
				System.out.print(temp[j]+",");
			System.out.println();
			}
		
		
		
	}
	
	float RSE(float[] raw, float[] base, int start, int end)
	{
		float rse = 0, sum = 0, mean = 0;
		int count = 0;
		
		for (int i=start;i<end;i++)
		{
			sum += raw[i];
			count++;
		}
		mean = sum / count;
		
		for (int i=start;i<end;i++)
		{
			rse += Math.pow(base[i]-raw[i],2)/Math.pow(mean-raw[i],2);
		}
		return rse;
	}
	
	float RMSE(float[] raw, float[] base, int start, int end)
	{
		float rse = 0, sum = 0;
		int count = 0;
		
		for (int i=start;i<end;i++)
		{
			sum += Math.pow(raw[i]-base[i],2);
			count++;
		}
		
		
		return (float)Math.sqrt(sum/count);
	}
	
	//  tokens / (baseline - actual consumption) 
	float costPerToken(float[] raw, float[] base, int start, int end, float tokens)
	{
		float sum = 0;
		
		for (int i=start;i<end;i++)
			sum += (base[i]-raw[i]);
			
		return tokens / sum; 
	}
	
	boolean weekDay(Date d)
	{
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		
		if (weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY)
			return false;
		
		return true;
		
	}
	
	Date getPrevDay(Date curr) //of the same type weekday or weekend
	{
		boolean currWeekDay = weekDay(curr);
		boolean wd = false;
		
		calendar.setTime(curr);	
		
		while (true)
		{
			calendar.roll(Calendar.DAY_OF_YEAR, false);
			wd = weekDay(calendar.getTime());
			if (wd == currWeekDay)
				return calendar.getTime();
		}
			
	}
	
	float[] PJM(Date target)
	{
		float[] sums = new float[(weekDay(target)?5:3)];

		Date curr = target;
		float[] values = null;
		float[] sum = new float[24];
		float[][] allvalues = new float[(weekDay(target)?5:3)][24];
		boolean flag = true;

		for (int j=0;j<(weekDay(target)?5:3);j++)
		{
			flag = true;
			while (flag)
			{
				flag = false;
				curr = getPrevDay(curr);
				values = data.get(curr);
				if (values == null)
					flag = true;
//				else // if we want to omit days with gaps
//				for (int i=0;i<values.length;i++)
//					if (values[i] == 0)
//						flag = true;
			}
			
			System.out.println("Fetching : "+curr.toString());
			allvalues[j] = values;
			
			for (int i=0;i<values.length;i++)
			{
				sums[j] += values[i];	
			}
					
		}
		
		float min = Float.MAX_VALUE;
		int min_pos = -1;
		for (int j=0;j<sums.length;j++)
			if (sums[j] < min)
			{
				min = sums[j];
				min_pos = j;
			}
		
		
		for (int j=0;j<sums.length;j++)
		{
			if (min_pos == j)
				continue;
			for (int i=0;i<allvalues[j].length;i++)
				sum[i] += allvalues[j][i]/(weekDay(target)?4:2);
		}
		
		return sum;
		
	}
	
	float[] CAISO(Date target) // average of 10 weekdays or weekend days
	{
		float[] sum = new float[24];

		Date curr = target;
		float[] values = null;
		boolean flag = true;

		for (int j=0;j<10;j++)
		{
			flag = true;
			while (flag)
			{
				flag = false;
				curr = getPrevDay(curr);
				values = data.get(curr);
				if (values == null)
					flag = true;
//				else // if we want to omit days with gaps
//				for (int i=0;i<values.length;i++)
//					if (values[i] == 0)
//						flag = true;
			}
			
			System.out.println("Fetching : "+curr.toString());
			
			for (int i=0;i<values.length;i++)
			{
				sum[i] += values[i];	
			}
					
		}
		
		for (int j=0;j<sum.length;j++)
			sum[j] /= 10;
		
		return sum;
	}
	
	
	
	public static void main(String[] args) {

		BaselineCalc b = new BaselineCalc();
		b.summarize("C:/Users/apapaioa/Desktop/arne_sensors_20130208-20130220/344.csv");
		
		SimpleDateFormat  f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
		Date trialTime = null;
		try {
			trialTime = f.parse("2013-02-18 00:00:00");
		} catch(ParseException e) {
			e.printStackTrace();
		}
		
//		for(int i=0;i<10;i++)
//		{
//			trialTime = b.getPrevDay(trialTime);
//			System.out.println(trialTime.toString());
//		}
//
		
		System.out.println(trialTime.toString());
		
		float[] values0 = b.data.get(trialTime);
		
		float[] values1 = b.CAISO(trialTime);
		
		for(int i=0;i<values1.length;i++)
			System.out.print(values1[i]+",");
		System.out.println();
		
		if (values0 != null)
		{
			System.out.println("RSE for CAISO : "+b.RSE(values0, values1, 0, 24));
			System.out.println("RMSE for CAISO : "+b.RMSE(values0, values1, 0, 24));
			System.out.println("Tokens per reduction : "+b.costPerToken(values0, values1, 5, 9, 3));
		}


		float[] values2 = b.PJM(trialTime);

		for(int i=0;i<values2.length;i++)
			System.out.print(values2[i]+",");
		System.out.println();

		if (values0 != null)
		{
			System.out.println("RSE for PJM : "+b.RSE(values0, values2, 0, 24));
			System.out.println("RMSE for PJM : "+b.RMSE(values0, values2, 0, 24));
			System.out.println("Tokens per reduction : "+b.costPerToken(values0, values2, 5, 9, 3));
		}

		
		//		String date = null;
//		try {
//			BufferedReader in
//			   = new BufferedReader(new FileReader("C:/Users/apapaioa/Desktop/arne_sensors_201212-2013-02/345.csv"));
//			
//			String line=null;
//			line=in.readLine();
//			System.out.println(line);
//			
//			StringTokenizer strtoken = new StringTokenizer(line,",");
//			date = strtoken.nextToken();
//			System.out.println(date);
//		} 
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 
//		
//		Calendar calendar;
//		
//		// TODO Auto-generated method stub
//		
//		// get the supported ids for GMT-08:00 (Pacific Standard Time)
//		 String[] ids = TimeZone.getAvailableIDs(1 * 60 * 60 * 1000);
//		 // if no ids were returned, something is wrong. get out.
//		 if (ids.length == 0)
//		     System.exit(0);
//
//		  // begin output
//		 System.out.println("Current Time");
//
//		 // create a Pacific Standard Time time zone
//		 SimpleTimeZone pdt = new SimpleTimeZone(1 * 60 * 60 * 1000, ids[0]);
//
//		 // set up rules for Daylight Saving Time
//		 pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
//		 pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
//
//		 // create a GregorianCalendar with the Pacific Daylight time zone
//		 // and the current date and time
//		 calendar = new GregorianCalendar(pdt);
//		 
//		 //DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH);
//		 
//		 SimpleDateFormat df = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
//		 Date trialTime;
//		try {
//			trialTime = df.parse(date);
//			calendar.setTime(trialTime);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 
//
//		 // print out a bunch of interesting things
//		 System.out.println("ERA: " + calendar.get(Calendar.ERA));
//		 System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
//		 System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
//		 System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
//		 System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
//		 System.out.println("DATE: " + calendar.get(Calendar.DATE));
//		 System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
//		 System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
//		 System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
//		 System.out.println("DAY_OF_WEEK_IN_MONTH: "
//		                    + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
//		 System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
//		 System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
//		 System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
//		 System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
//		 System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
//		 System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
//		 System.out.println("ZONE_OFFSET: "
//		                    + (calendar.get(Calendar.ZONE_OFFSET)/(60*60*1000)));
//		 System.out.println("DST_OFFSET: "
//		                    + (calendar.get(Calendar.DST_OFFSET)/(60*60*1000)));

//		 System.out.println("Current Time, with hour reset to 3");
//		 calendar.clear(Calendar.HOUR_OF_DAY); // so doesn't override
//		 calendar.set(Calendar.HOUR, 3);
//		 System.out.println("ERA: " + calendar.get(Calendar.ERA));
//		 System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
//		 System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
//		 System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
//		 System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
//		 System.out.println("DATE: " + calendar.get(Calendar.DATE));
//		 System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
//		 System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
//		 System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
//		 System.out.println("DAY_OF_WEEK_IN_MONTH: "
//				 + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
//		 System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
//		 System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
//		 System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
//		 System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
//		 System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
//		 System.out.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
//		 System.out.println("ZONE_OFFSET: "
//				 + (calendar.get(Calendar.ZONE_OFFSET)/(60*60*1000))); // in hours
//		 System.out.println("DST_OFFSET: "
//		        + (calendar.get(Calendar.DST_OFFSET)/(60*60*1000))); // in hours

	}

}
