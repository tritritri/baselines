package ch.epfl.lsir.wattalyst.baseline.test;


import java.io.IOException;



/**
 * A main file to run all of the test
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.28
 */ 
public class TestSupervised {

	public int run ()  {
		String baseline = null;
		String startDateStr = null; 
		String endDateStr = null;
		String fileInput = null;
		String fileRef = null;
		int failedCount = 0;
		boolean testResult = false;
		String dirSep = System.getProperty("file.separator");
		String examplesDir = "examples" + dirSep;
		String testsDir = examplesDir + "tests" + dirSep;
		
		try {
			baseline="ch.epfl.lsir.wattalyst.baseline.baselines.Supervised";
			// fileInput = examplesDir+"load-dummy.txt,"+examplesDir+"temp-dummy.txt,"+
			//			examplesDir+"algLinearRegression.txt";

			fileInput = examplesDir + "config-reg.txt";
			
			startDateStr = "2013-02-15"; 
			endDateStr = "2013-02-15";
			fileRef = testsDir + "z1-Supervised-2013-02-15.txt";
			testResult = UtilForTest.testBaselines(baseline, fileInput, startDateStr, endDateStr, fileRef);
			if (testResult == false) failedCount ++;
			
			startDateStr = "2013-02-15"; 
			endDateStr = "2013-02-17";
			fileRef = testsDir + "z3-Supervised-2013-02-15.txt";
			testResult = UtilForTest.testBaselines(baseline, fileInput, startDateStr, endDateStr, fileRef);
			if (testResult == false) failedCount ++;

			startDateStr = "2013-02-17"; 
			endDateStr = "2013-02-18";
			fileRef = testsDir + "z2-Supervised-2013-02-17.txt";
			testResult = UtilForTest.testBaselines(baseline, fileInput, startDateStr, endDateStr, fileRef);
			if (testResult == false) failedCount ++;

			// for inputHistory option
			startDateStr = "2013-02-11"; 
			endDateStr = "2013-02-12";
			fileInput = examplesDir + "config-reg-3.txt";
			fileRef = testsDir + "i-z2-Supervised-2013-02-11.txt";
			testResult = UtilForTest.testBaselines(baseline, fileInput, startDateStr, endDateStr, fileRef, true);
			if (testResult == false) failedCount ++;
			
		} catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return failedCount;
		
		
		
	}


}
