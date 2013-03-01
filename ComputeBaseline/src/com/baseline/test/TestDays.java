package com.baseline.test;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.baseline.constants.Constants;

/**
 * Test baselines on X days
 * 
 * @author Tri Kurniawan Wijaya
 * created: 2013.02.27
 */
public class TestDays {

	int numOfDays;
	
	public TestDays(){
		this.numOfDays=1;
	}
	
	public TestDays(int numOfDays){
		this.numOfDays = numOfDays;
	}
	
	/**
	 * Set to be run inside Eclipse
	 * 
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @return the number of failed test
	 */
	public int run() {
		
		int failedCount=0;

		try {
			
			// Run test for several baselines
			String[] baseline={"PJMEco", "CAISO", "NYISO", "Mid4Of6"};
			String dirSep = System.getProperty("file.separator");
			String fileInput="examples" + dirSep + "input-example.txt";
			String fileRefDir = "examples" + dirSep + "tests" + dirSep;
			
			String baselinePackage="com.baseline.baselines";
			
			// initial date
			String startDateStr = "2013-02-21"; 
			
			// determine end date
			SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(formatter.parse(startDateStr));
			endCal.add(Calendar.DAY_OF_MONTH, numOfDays-1);
			String endDateStr = formatter.format(endCal.getTime());
			
			// check baselines one by one
			for (int i=0; i<baseline.length; i++){
				String fileRef =  fileRefDir+ "z" + numOfDays + "-" + baseline[i] + "-" + startDateStr + ".txt";
				if (UtilForTest.testBaselines(baselinePackage+"."+baseline[i], fileInput, startDateStr, endDateStr, fileRef)==false ){
					failedCount++;
				} 
			}
			
		} catch (ParseException | InstantiationException | IllegalAccessException
				| ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return failedCount;
		
	}



}
