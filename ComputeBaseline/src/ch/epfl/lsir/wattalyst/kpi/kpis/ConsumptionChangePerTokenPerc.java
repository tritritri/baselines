package ch.epfl.lsir.wattalyst.kpi.kpis;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;

public class ConsumptionChangePerTokenPerc extends KPI {

	private double consumptionChangePerToken;
	private SimpleDateFormat dayFormatter;
	private SimpleDateFormat hourFormatter;
	
	
	/**
	 * Consumption change in percentage terms per token received: ((consumption - baseline)/baseline)/tokens
	 */
	public ConsumptionChangePerTokenPerc(){
		consumptionChangePerToken = Double.NaN;
		dayFormatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		hourFormatter = new SimpleDateFormat("hh");
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#doCompute()
	 */
	@Override
	protected void doCompute() {

		consumptionChangePerToken = 0;
		
		double totalBaseline = 0.0;
				
		Calendar current = Calendar.getInstance();
		current.setTime(startCal.getTime());
		while(!current.after(endCal)){
			consumptionChangePerToken = consumptionChangePerToken + consumption.get(current.getTimeInMillis()) - baseline.get(current.getTimeInMillis());
			totalBaseline = totalBaseline + baseline.get(current.getTimeInMillis());
			current.add(Calendar.HOUR_OF_DAY, 1);
		}
		
		consumptionChangePerToken = consumptionChangePerToken/totalBaseline;
		consumptionChangePerToken = consumptionChangePerToken/numTokens;
	}

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#getResult()
	 */
	public String getResult(){
		return this.getClass().getSimpleName() + "," + dayFormatter.format(startCal.getTime()) + "," + 
				hourFormatter.format(startCal.getTime()) + "," + hourFormatter.format(endCal.getTime()) + "," +
				consumptionChangePerToken + "\n";
	}
	
}
