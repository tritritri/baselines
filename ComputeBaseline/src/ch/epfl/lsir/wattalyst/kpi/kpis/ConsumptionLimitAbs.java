package ch.epfl.lsir.wattalyst.kpi.kpis;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;

public class ConsumptionLimitAbs extends KPI {

	private Double achieved;
	private SimpleDateFormat dayFormatter;
	private SimpleDateFormat hourFormatter;
	
	
	/**
	 * Consumption limit reached or not
	 */
	public ConsumptionLimitAbs(){
		achieved = null;
		dayFormatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		hourFormatter = new SimpleDateFormat("hh");
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#doCompute()
	 */
	@Override
	protected void doCompute() {
				
		achieved = -1.0;
		
		double cons = 0;
		
		Calendar current = Calendar.getInstance();
		current.setTime(startCal.getTime());
		while(!current.after(endCal)){
			cons = cons + consumption.get(current.getTimeInMillis());
			current.add(Calendar.HOUR_OF_DAY, 1);
		}
		
		achieved = cons <= consumptionLimit ? 1.0 : -1.0;
	}

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#getResult()
	 */
	public String getResultDescription(){
		return this.getClass().getSimpleName() + "," + dayFormatter.format(startCal.getTime()) + "," + 
				hourFormatter.format(startCal.getTime()) + "," + hourFormatter.format(endCal.getTime()) + "," +
				(achieved >= 0 ? "ACHIEVED" : "NOT ACHIEVED") + "\n";
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#getResult()
	 */
	public Double getResult(){
		return achieved;
	}

}
