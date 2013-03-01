package ch.epfl.lsir.wattalyst.baseline.baselines;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Interface for all baselines.
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.23
 */
public interface Baseline {
	
	public void compute(String fileInput, String startDate, String endDate);	
	public ArrayList<String> getResultString();
	public void writeResult(PrintStream out);
	public void writeResultToFile(String fileName);

}
