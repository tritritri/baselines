package ch.epfl.lsir.wattalyst.kpi.kpis;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;

public class TokenPerConsumptionChangeAbs extends KPI {

	private double tokensPerConsumptionChange;
	private SimpleDateFormat dayFormatter;
	private SimpleDateFormat hourFormatter;
	
	
	/**
	 * Tokens per consumption change in absolute terms: tokens/(consumption - baseline)
	 * The sign indicates the direction of the token flow. A negative value means that
	 * tokens flows from DR system to consumer, since the consumer reduced the consumption.
	 * A positive value means (theoretically) that tokens flows from the consumer to 
	 * the DR system, since the consumer increased the consumption.
	 */
	public TokenPerConsumptionChangeAbs(){
		tokensPerConsumptionChange = Double.NaN;
		dayFormatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		hourFormatter = new SimpleDateFormat("hh");
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#getResult()
	 */
	public String getResultDescription(){
		return this.getClass().getSimpleName() + "," + dayFormatter.format(startCal.getTime()) + "," + 
				hourFormatter.format(startCal.getTime()) + "," + hourFormatter.format(endCal.getTime()) + "," +
				tokensPerConsumptionChange + "\n";
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#getResult()
	 */
	public double getResult(){
		return tokensPerConsumptionChange;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#doCompute()
	 */
	@Override
	protected void doCompute() {
				
		tokensPerConsumptionChange = 0;
		
		Calendar current = Calendar.getInstance();
		current.setTime(startCal.getTime());
		while(!current.after(endCal)){
			tokensPerConsumptionChange = tokensPerConsumptionChange + consumption.get(current.getTimeInMillis()) - baseline.get(current.getTimeInMillis());
			current.add(Calendar.HOUR_OF_DAY, 1);
		}
		
		tokensPerConsumptionChange = numTokens/tokensPerConsumptionChange;
	}


}
