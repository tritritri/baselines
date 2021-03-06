package ch.epfl.lsir.wattalyst.kpi.kpis;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;

public class ConsumptionChangePerTokenPerc extends KPI {

	private Double consumptionChangePerToken;
	private SimpleDateFormat dayFormatter;
	private SimpleDateFormat hourFormatter;
	
	
	/**
	 * Consumption change in percentage terms per token received: ((consumption - baseline)/baseline)/tokens
	 */
	public ConsumptionChangePerTokenPerc(){
		consumptionChangePerToken = null;
		dayFormatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		hourFormatter = new SimpleDateFormat("hh");
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#doCompute()
	 */
	@Override
	protected void doCompute() {

		consumptionChangePerToken = 0.0;
		
		double totalBaseline = 0.0;
				
		Calendar current = Calendar.getInstance();
		current.setTime(startCal.getTime());
		while(!current.after(endCal)){
			consumptionChangePerToken = consumptionChangePerToken + consumption.get(current.getTimeInMillis()) - baseline.get(current.getTimeInMillis());
			totalBaseline = totalBaseline + baseline.get(current.getTimeInMillis());
			current.add(Calendar.HOUR_OF_DAY, 1);
		}
		
		// If total baseline is 0, then the KPI is not applicable.
		// Set to null and manage it in KPITask
		if(totalBaseline == 0){
			consumptionChangePerToken = null;
		}
		else{
			consumptionChangePerToken = consumptionChangePerToken/totalBaseline;
			consumptionChangePerToken = 100.0 * consumptionChangePerToken/numTokens;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#getResult()
	 */
	public String getResultDescription(){
		return this.getClass().getSimpleName() + "," + dayFormatter.format(startCal.getTime()) + "," + 
				hourFormatter.format(startCal.getTime()) + "," + hourFormatter.format(endCal.getTime()) + "," +
				consumptionChangePerToken + "\n";
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#getResult()
	 */
	public Double getResult(){
		return consumptionChangePerToken;
	}
	
}
