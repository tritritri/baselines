package com.baseline.test;


import com.baseline.baselines.Baseline;


/**
 * A main file to run all of the test
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.28
 */
public class TestRegression {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException  {
		String baseline="com.baseline.baselines.Regression";
		String startDateStr = "2013-02-17"; 
		String endDateStr = "2013-02-18";
		String fileInput = "examples/load.txt,examples/temp.txt,examples/algLinearRegression.txt";
		
		// build the baseline
		Baseline b = (Baseline) Class.forName(baseline).newInstance(); 
		b.compute(fileInput, startDateStr, endDateStr);
		
		System.out.println("\nBaseline: ");
		b.writeResult(System.out);
		System.out.println("done.");
		
	}


}
