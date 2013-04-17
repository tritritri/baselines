package ch.epfl.lsir.wattalyst.baseline.test;

import java.io.IOException;
import java.util.ArrayList;

import ch.epfl.lsir.wattalyst.baseline.baselines.Baseline;
import ch.epfl.lsir.wattalyst.baseline.util.Util;

public class UtilForTest {
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
	public static boolean testBaselines(String baseline, String fileInput, String startDateStr, String endDateStr, String fileReference, boolean optionHistory) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {

		// build the baseline
		Baseline b = (Baseline) Class.forName(baseline).newInstance();
		b.setInputHistoryOption(optionHistory);
		b.compute(fileInput, startDateStr, endDateStr);
		
		// get the result
		ArrayList<String> arrResult = b.getResultString();
		int compare = Util.isEqual(fileReference, arrResult);
		if (compare==-1){
			System.err.println("[Test] The number of lines in "+ fileReference +" and size of result is mismatch");
			System.out.println("Test "+ fileReference + ": failed");
			return false;
		} else 
		if (compare >=1 && compare<=arrResult.size()){
			System.err.println("[Test] Mismatch in line "+compare+". Line expected: " + arrResult.get(compare-1));
			System.out.println("Test "+ fileReference + ": failed");
			return false;
		}
		System.out.println("Test "+ fileReference + ": passed");		
		return true;
	}

	public static boolean testBaselines(String baseline, String fileInput, String startDateStr, String endDateStr, String fileReference) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		return testBaselines(baseline, fileInput, startDateStr, endDateStr, fileReference, false);
	}
}
