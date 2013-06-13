package ch.epfl.lsir.wattalyst.webserver;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

import org.wattalyst.services.secured.ConstraintDto;
import org.wattalyst.services.secured.DrSignalDto;
import org.wattalyst.services.secured.SuccessStatus;
import org.wattalyst.services.secured.TaskDto;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.kpi.kpis.ConsumptionChangeAbs;
import ch.epfl.lsir.wattalyst.kpi.kpis.ConsumptionChangePerTokenAbs;
import ch.epfl.lsir.wattalyst.kpi.kpis.ConsumptionChangePerTokenPerc;
import ch.epfl.lsir.wattalyst.kpi.kpis.ConsumptionChangePerc;
import ch.epfl.lsir.wattalyst.kpi.kpis.TokenPerConsumptionChangeAbs;


public class KPITask {

	/**
	 * 
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		
		String authenticationToken = "mheqzghwnhh+";
				
		WebserverDataReader reader = new WebserverDataReader();
		WebserverDataWriter writer = new WebserverDataWriter();
		
		// 1. Retrieve all DR messages that have not been evaluated
		for(DrSignalDto drSignal : reader.getNotEvaluatedDRSignals(authenticationToken)){
			
			String drSignalID = drSignal.getFullQualifiedName();
			
			for(TaskDto task : drSignal.getTasks()){
				
				// 2. Retrieve start time and end time of the signal
				Date start = new Date(task.getTarget().getStartDate());
				Date end = new Date(task.getTarget().getEndDate());
				
				// 3. Retrieve baseline, sensor and reward tokens
				for(ConstraintDto constraint : task.getConstraints()){
					
					String baselineID = constraint.getBaseline().getFullQualifiedName();
					String sensorID = constraint.getBaseline().getReferenceSensor();
					double numTokens = constraint.getRewardTokens();
					
					// 4. Retrieve baseline data and real consumption data
					SensorReadings realConsumption = 
							reader.getValuesForSensorByRange(authenticationToken, sensorID, start, end, true);
					SensorReadings baselineConsumption =
							reader.getBaselineData(authenticationToken, baselineID, start, end);
				
					// 5. Compute KPIs
					double cca = computeConsumptionChangeAbs(baselineConsumption, realConsumption, start, end, numTokens);
					double ccp = computeConsumptionChangePerc(baselineConsumption, realConsumption, start, end, numTokens);
					double cca_t = computeConsumptionChangePerTokenAbs(baselineConsumption, realConsumption, start, end, numTokens);
					double ccp_t = computeConsumptionChangePerTokenPerc(baselineConsumption, realConsumption, start, end, numTokens);
					double t_cca = computeTokenPerConsumptionChangeAbs(baselineConsumption, realConsumption, start, end, numTokens);
					
					String username = "XXX"; //REDUCED_CONSUMPTION --> real over the time window = baseline - absolute change +- tolerance 
					
					// 6. Store KPIs
					writer.setPerformanceIndicator(authenticationToken, drSignalID, username, cca, "Absolute change in consumption (kWh)", SuccessStatus.NA.name());
					writer.setPerformanceIndicator(authenticationToken, drSignalID, username, ccp, "Percentage change in consumption (%)", SuccessStatus.NA.name());
					writer.setPerformanceIndicator(authenticationToken, drSignalID, username, cca_t, "Absolute change in consumption per reward token (kWh/token)", SuccessStatus.NA.name());
					writer.setPerformanceIndicator(authenticationToken, drSignalID, username, ccp_t, "Percentage change in consumption per reward token (%/token)", SuccessStatus.NA.name());
					writer.setPerformanceIndicator(authenticationToken, drSignalID, username, t_cca, "Number of reward tokens per absolute change in consumption (token/kWh)", SuccessStatus.NA.name());
				}
			}
		}
	}

	/*
	 * 
	 */
	private static double computeConsumptionChangeAbs(SensorReadings baselineConsumption, 
			SensorReadings realConsumption, Date start, Date end, double numTokens) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		
		ConsumptionChangeAbs consumptionChangeAbs = new ConsumptionChangeAbs();
		consumptionChangeAbs.compute(baselineConsumption, realConsumption, startCal, endCal, numTokens);
		return consumptionChangeAbs.getResult();
	}
	
	/*
	 * 
	 */
	private static double computeConsumptionChangePerc(SensorReadings baselineConsumption, 
			SensorReadings realConsumption, Date start, Date end, double numTokens) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		
		ConsumptionChangePerc consumptionChangePerc = new ConsumptionChangePerc();
		consumptionChangePerc.compute(baselineConsumption, realConsumption, startCal, endCal, numTokens);
		return consumptionChangePerc.getResult();
	}
	
	/*
	 * 
	 */
	private static double computeConsumptionChangePerTokenAbs(SensorReadings baselineConsumption, 
			SensorReadings realConsumption, Date start, Date end, double numTokens) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		
		ConsumptionChangePerTokenAbs consumptionChangePerTokenAbs = new ConsumptionChangePerTokenAbs();
		consumptionChangePerTokenAbs.compute(baselineConsumption, realConsumption, startCal, endCal, numTokens);
		return consumptionChangePerTokenAbs.getResult();
	}
		
	/*
	 * 
	 */
	private static double computeConsumptionChangePerTokenPerc(SensorReadings baselineConsumption, 
			SensorReadings realConsumption, Date start, Date end, double numTokens) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		
		ConsumptionChangePerTokenPerc consumptionChangePerTokenPerc = new ConsumptionChangePerTokenPerc();
		consumptionChangePerTokenPerc.compute(baselineConsumption, realConsumption, startCal, endCal, numTokens);
		return consumptionChangePerTokenPerc.getResult();
	}


	/*
	 * 
	 */
	private static double computeTokenPerConsumptionChangeAbs(SensorReadings baselineConsumption, 
			SensorReadings realConsumption, Date start, Date end, double numTokens) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		
		TokenPerConsumptionChangeAbs tokenPerConsumptionChangeAbs = new TokenPerConsumptionChangeAbs();
		tokenPerConsumptionChangeAbs.compute(baselineConsumption, realConsumption, startCal, endCal, numTokens);
		return tokenPerConsumptionChangeAbs.getResult();
	}
}
