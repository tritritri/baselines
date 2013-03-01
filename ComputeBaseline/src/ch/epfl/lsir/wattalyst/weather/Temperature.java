package ch.epfl.lsir.wattalyst.weather;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

public interface Temperature {

	public void compute(Date startDate, Date endDate, String country, String place);
	public ArrayList<String> getResultString();
	public void writeResult(PrintStream out);
	public void writeResultToFile(String fileName);
	
}
