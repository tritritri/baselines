package com.baseline.test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.baseline.baselines.Baseline;
import com.baseline.constants.Constants;

/**
 * Test baselines on X days
 * 
 * @author tritritri
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
				String msg = "Test "+ "z" + numOfDays + "-" + baseline[i] + "-"+ startDateStr;
				if (testBaselines(baselinePackage+"."+baseline[i], fileInput, startDateStr, endDateStr, fileRef)){
					System.out.println(msg+": passed");
				} else {
					System.out.println(msg+": failed");
					failedCount++;
				}
			}
			
		} catch (ParseException | InstantiationException | IllegalAccessException
				| ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return failedCount;
		
	}

	/**
	 * Test each baseline
	 * @param baseline Baseline being tested
	 * @param fileInput input file contains the historical load data
	 * @param dateStr the target date of the baseline computation
	 * @param fileReference text file contain the expected answer
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private boolean testBaselines(String baseline, String fileInput, String startDateStr, String endDateStr, String fileReference) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {

		// build the baseline
		Baseline b = (Baseline) Class.forName(baseline).newInstance(); 
		b.compute(fileInput, startDateStr, endDateStr);
		
		// get the result
		ArrayList<String> arrResult = b.getResultString();
		
		// open the reference
		BufferedReader in = new BufferedReader(new FileReader(fileReference));

		// compare the result with the reference
		String line = null;
		int i=0;
		while ((line = in.readLine()) != null ){
			if (i>=arrResult.size()) {
				System.err.println("[Test] The number of lines in "+ fileReference +" and size of result is mismatch");
				in.close();
				return false;
			}
			if (!line.equals(arrResult.get(i))){
				System.err.println("[Test] Mismatch in line "+(i+1));
				in.close();
				return false;
			}
			i++;
		}
		in.close();
		return true;
	}


}
