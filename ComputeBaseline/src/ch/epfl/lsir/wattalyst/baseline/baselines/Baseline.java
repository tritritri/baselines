package ch.epfl.lsir.wattalyst.baseline.baselines;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface for all baselines.
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.23
 */
public interface Baseline {
	
	/**
	 * Method to compute the baseline
	 * @param fileInput
	 * @param startDate starting date of the baseline in format yyyy-MM-dd
	 * @param endDate ending date of the baseline in format yyyy-MM-dd
	 */
	public void compute(String fileInput, String startDate, String endDate);
	
	/**
	 * Compute the baseline in the existence of excluded days
	 * @param fileInput 
	 * @param startDate starting date of the baseline 
	 * @param endDate ending date of the baseline
	 * @param exclDays historical days to be excluded from baseline computation
	 */
	public void compute(String fileInput, String startDate, String endDate,	HashMap<Long, Byte> exclDays);

	/**
	 * Get baseline result in form of array of String
	 * @return array of string contain the baseline
	 */
	public ArrayList<String> getResultString();
	
	/**
	 * Print the baseline result to Printstream out
	 * @param out
	 */
	public void writeResult(PrintStream out);
	
	/**
	 * Write baseline into a file
	 * @param fileName
	 */
	public void writeResultToFile(String fileName);

	/**
	 * Set option inputHistory=flag
	 * @param flag boolean value for the inputHistory option
	 */
	public void setInputHistoryOption(boolean flag);

	/**
	 * 
	 * @param baselineID
	 */
	public void writeResultToWattalystDB(String baselineID);
}
