package ch.epfl.lsir.wattalyst.kpi.kpis;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ch.epfl.lsir.wattalyst.baseline.constants.Constants;

public class RMSE extends KPI {

	private Double rmse;
	private SimpleDateFormat dayFormatter;
	private SimpleDateFormat hourFormatter;
	
	/**
	 * Round mean square error
	 */
	public RMSE(){
		rmse = null;
		dayFormatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		hourFormatter = new SimpleDateFormat("hh");
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#getResultDescription()
	 */
	public String getResultDescription(){
		return this.getClass().getSimpleName() + "," + dayFormatter.format(startCal.getTime()) + "," + 
				hourFormatter.format(startCal.getTime()) + "," + hourFormatter.format(endCal.getTime()) + "," +
				rmse + "\n";
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#getResult()
	 */
	public Double getResult(){
		return rmse;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.kpi.kpis.KPI#doCompute()
	 */
	@Override
	protected void doCompute() {

		rmse = 0.0;
		int count = 0;
				
		Calendar current = Calendar.getInstance();
		current.setTime(startCal.getTime());
		while(!current.after(endCal)){
			rmse = rmse + Math.pow(consumption.get(current.getTimeInMillis()) - baseline.get(current.getTimeInMillis()), 2);
			count++;
			current.add(Calendar.HOUR_OF_DAY, 1);
		}
		
		rmse = Math.sqrt(rmse/count);
	}

}
