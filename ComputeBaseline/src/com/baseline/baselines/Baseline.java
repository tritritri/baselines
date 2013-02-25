package com.baseline.baselines;

import java.io.PrintStream;

/**
 * Interface for all baselines
 * 
 * @author tritritri
 * created: 2013.02.23
 */
public interface Baseline {
	
	public void compute(String fileInput, String startDate, String endDate);
	public void writeResult(PrintStream out);
	public void writeResultToFile(String fileName);

}
