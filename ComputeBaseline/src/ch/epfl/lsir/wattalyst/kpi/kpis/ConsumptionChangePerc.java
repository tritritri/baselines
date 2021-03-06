package ch.epfl.lsir.wattalyst.kpi.kpis;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;

public class ConsumptionChangePerc extends KPI {

	private Double consumptionChange;
	private SimpleDateFormat dayFormatter;
	private SimpleDateFormat hourFormatter;
	
	
	/**
	 * Consumption change in percentage terms: (consumption - baseline)/baseline
	 */
	public ConsumptionChangePerc(){
		consumptionChange = null;
		dayFormatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		hourFormatter = new SimpleDateFormat("hh");
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#doCompute()
	 */
	@Override
	protected void doCompute() {

		consumptionChange = 0.0;
		
		double totalBaseline = 0.0;
				
		Calendar current = Calendar.getInstance();
		current.setTime(startCal.getTime());
		while(!current.after(endCal)){
			consumptionChange = consumptionChange + consumption.get(current.getTimeInMillis()) - baseline.get(current.getTimeInMillis());
			totalBaseline = totalBaseline + baseline.get(current.getTimeInMillis());
			current.add(Calendar.HOUR_OF_DAY, 1);
		}
		
		// If total baseline is 0, then the KPI is not applicable.
		// Set to null and manage it in KPITask
		if(totalBaseline == 0)
		{
			consumptionChange = null;
		}
		else{
			consumptionChange = 100.0*consumptionChange/totalBaseline;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#getResult()
	 */
	public String getResultDescription(){
		return this.getClass().getSimpleName() + "," + dayFormatter.format(startCal.getTime()) + "," + 
				hourFormatter.format(startCal.getTime()) + "," + hourFormatter.format(endCal.getTime()) + "," +
				consumptionChange + "\n";
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#getResult()
	 */
	public Double getResult(){
		return consumptionChange;
	}
}
