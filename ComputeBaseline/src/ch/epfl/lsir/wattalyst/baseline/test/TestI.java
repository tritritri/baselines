package ch.epfl.lsir.wattalyst.baseline.test;


import java.io.IOException;


/**
 * Test baselines for inputhistory option
 * 
 * @author Tri Kurniawan Wijaya
 * created: 2013.02.27
 */
public class TestI {

	
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

			String dirSep = System.getProperty("file.separator");
			String fileInput="examples" + dirSep + "input-example.txt";
			String testsDir = "examples" + dirSep + "tests";
						
			int numOfDays = 2;
						
			String targetDate = "2013-02-11";
			String endDate = "2013-02-12";

			String baseline[] = {"PJMEco", "CAISO", "NYISO", "Mid4Of6", "ISONE"};
			for (int i=0; i<baseline.length; i++) {
				String fileRef =  testsDir+ dirSep + "i-z" + numOfDays + "-" + baseline[i] + "-" + targetDate + ".txt";
				if (UtilForTest.testBaselines(baselinePackage+"."+baseline[i], fileInput, targetDate, endDate, fileRef, true)==false )
					failedCount++;				
			}

			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | IOException e) {
			e.printStackTrace();
			failedCount++;
		}
		
		return failedCount;
		
	}



}
