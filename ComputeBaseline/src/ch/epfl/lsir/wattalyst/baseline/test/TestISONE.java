package ch.epfl.lsir.wattalyst.baseline.test;


import java.io.IOException;


/**
 * Test ISONE baseline 
 * 
 * @author Tri Kurniawan Wijaya
 * created: 2013.02.27
 */
public class TestISONE {

	
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
			String baselinePackage="ch.epfl.lsir.wattalyst.baseline.baselines";
			String baseline = "ISONE";

			String dirSep = System.getProperty("file.separator");
			String fileInput="examples" + dirSep + "input-example.txt";
			String testsDir = "examples" + dirSep + "tests";
			
			
			int numOfDays = 4;
			
			String targetDate = "2013-02-15";
			String endDate = "2013-02-18";
			String fileRef =  testsDir+ dirSep + "z" + numOfDays + "-" + baseline + "-" + targetDate + ".txt";
			if (UtilForTest.testBaselines(baselinePackage+"."+baseline, fileInput, targetDate, endDate, fileRef)==false )
				failedCount++;
			
			targetDate = "2013-02-22";
			endDate = "2013-02-25";
			fileRef =  testsDir+ dirSep + "z" + numOfDays + "-" + baseline + "-" + targetDate + ".txt";
			if (UtilForTest.testBaselines(baselinePackage+"."+baseline, fileInput, targetDate, endDate, fileRef)==false )
				failedCount++;
			 

			targetDate = "2013-03-01";
			endDate = "2013-03-04";
			fileRef =  testsDir+ dirSep + "z" + numOfDays + "-" + baseline + "-" + targetDate + ".txt";
			if (UtilForTest.testBaselines(baselinePackage+"."+baseline, fileInput, targetDate, endDate, fileRef)==false )
				failedCount++;
			

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | IOException e) {
			e.printStackTrace();
			failedCount++;
		}
		
		return failedCount;
		
	}



}
