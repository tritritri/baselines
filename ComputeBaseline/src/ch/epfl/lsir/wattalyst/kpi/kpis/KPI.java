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
	protected double numTokens;
	protected double consumptionLimit;
	
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
	 * @param consumptionLimit
	 */
	public final void compute(String baselineFileInput, String consumptionFileInput, 
			Calendar startCal, Calendar endCal, double numTokens, double consumptionLimit){
		
		// read the baseline file input
		Util.hourlyCSVToSensorReadings(baselineFileInput, baseline);
			
		// read the consumption file input
		Util.hourlyCSVToSensorReadings(consumptionFileInput, consumption);
			
		this.startCal = startCal;
		this.endCal = endCal;
		this.numTokens = numTokens;
		this.consumptionLimit = consumptionLimit;
		
		doCompute();
	}
	
	/**
	 * 
	 * @param baselineConsumption
	 * @param realConsumption
	 * @param startCal
	 * @param endCal
	 * @param numTokens
	 * @param consumptionLimit
	 */
	public void compute(SensorReadings baselineConsumption, SensorReadings realConsumption, 
			Calendar startCal, Calendar endCal, double numTokens, double consumptionLimit) {
		
		baselineConsumption.copyHourly(baselineConsumption.getMinDate(), baselineConsumption.getMaxDate(), baseline);
		realConsumption.copyHourly(realConsumption.getMinDate(), realConsumption.getMaxDate(), consumption);
		
		this.startCal = startCal;
		this.endCal = endCal;
		this.numTokens = numTokens;
		this.consumptionLimit = consumptionLimit;
		
		doCompute();
	}
	
	/**
	 * 
	 */
	protected abstract void doCompute();
	
	/**
	 * 
	 * @return
	 */
	public abstract String getResultDescription();
	
	/**
	 * 
	 * @return
	 */
	public abstract double getResult();

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#writeResultDescription(java.io.PrintStream)
	 */
	public void writeResultDescription(PrintStream out) {
		out.print(getResultDescription());
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#writeResultDescriptionToFile(java.lang.String)
	 */
	public void writeResultDescriptionToFile(String fileName) {
		PrintWriter fileOut;
		try {
			fileOut = new PrintWriter(fileName);
			fileOut.print(getResultDescription());
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
}
