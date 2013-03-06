package ch.epfl.lsir.wattalyst.kpi.kpis;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Calendar;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.baseline.util.Util;

/**
 * 
 * @author vasirani
 *
 */
public abstract class KPI {
	
	protected SensorReadings consumption;
	protected SensorReadings baseline;
	protected Calendar startCal;
	protected Calendar endCal;
	protected int numTokens;
	
	/**
	 * 
	 */
	public KPI(){
		consumption = new SensorReadings();
		baseline = new SensorReadings();
	}
	
	/**
	 * 
	 * @param baselineFileInput
	 * @param consumptionFileInput
	 * @param startCal
	 * @param endCal
	 * @param numTokens
	 */
	public final void compute(String baselineFileInput, String consumptionFileInput, 
			Calendar startCal, Calendar endCal, int numTokens){
		
		// read the baseline file input
		Util.hourlyCSVToSensorReadings(baselineFileInput, baseline);
			
		// read the consumption file input
		Util.hourlyCSVToSensorReadings(consumptionFileInput, consumption);
			
		this.startCal = startCal;
		this.endCal = endCal;
		this.numTokens = numTokens;
		
		doCompute();
	}
	
	/**
	 * 
	 */
	protected abstract void doCompute();
	

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#writeResult(java.io.PrintStream)
	 */
	public void writeResult(PrintStream out) {
		out.print(getResult());
	}

	/**
	 * 
	 * @return
	 */
	protected abstract String getResult();

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#writeResultToFile(java.lang.String)
	 */
	public void writeResultToFile(String fileName) {
		PrintWriter fileOut;
		try {
			fileOut = new PrintWriter(fileName);
			fileOut.print(getResult());
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
