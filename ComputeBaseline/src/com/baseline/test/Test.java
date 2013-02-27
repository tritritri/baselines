package com.baseline.test;


import java.io.IOException;


/**
 * A main file to run all of the test
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.27
 */
public class Test {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		int failedCount = 0;
		TestDays t = new TestDays();
		
		failedCount += t.run();
		
		t = new TestDays(3);
		failedCount += t.run();
		
		if ( failedCount == 0 ){
			System.out.println("All tests succeed.");
		} else 
		if (failedCount == 1) {
			System.out.println("1 test failed.");
		} else 
		if (failedCount >= 1) {
			System.out.println(failedCount + " tests failed.");
		}
	}


}
