package com.baseline.test;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.baseline.baselines.Baseline;


public class Test {

	/**
	 * Set to be run inside Eclipse
	 * 
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		// Run several test

		String[] baseline={"PJMEco", "CAISO", "NYISO", "Mid4Of6"};
		String fileInput="examples/input-example.txt";
		String fileRefDir = "examples/tests/";
		
		String baselinePackage="com.baseline.baselines";
		String dateStr = "2013-02-21"; 
		 
		for (int i=0; i<baseline.length; i++){
			String fileRef =  fileRefDir+ baseline[i]+"-"+dateStr+".txt";
			String msg = "Test "+baseline[i]+"-"+dateStr;
			if (testBaselines(baselinePackage+"."+baseline[i], fileInput, dateStr, fileRef)){
				System.out.println(msg+": passed");
			} else {
				System.out.println(msg+": failed");
				return;
			}
		}
		
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
	private static boolean testBaselines(String baseline, String fileInput, String dateStr, String fileReference) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {

		// build the baseline
		Baseline b = (Baseline) Class.forName(baseline).newInstance(); 
		b.compute(fileInput, dateStr, dateStr);
		
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
