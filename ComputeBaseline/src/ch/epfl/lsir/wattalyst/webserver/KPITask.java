package ch.epfl.lsir.wattalyst.webserver;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.wattalyst.services.secured.ConstraintDto;
import org.wattalyst.services.secured.DrSignalDto;
import org.wattalyst.services.secured.LocationDto;
import org.wattalyst.services.secured.SuccessStatus;
import org.wattalyst.services.secured.TaskDto;
import org.wattalyst.services.secured.TimeIntervalDto;
import org.wattalyst.services.secured.SignalStatus;

import ch.epfl.lsir.wattalyst.baseline.util.SensorReadings;
import ch.epfl.lsir.wattalyst.kpi.kpis.ConsumptionChangeAbs;
import ch.epfl.lsir.wattalyst.kpi.kpis.ConsumptionChangePerTokenAbs;
import ch.epfl.lsir.wattalyst.kpi.kpis.ConsumptionChangePerTokenPerc;
import ch.epfl.lsir.wattalyst.kpi.kpis.ConsumptionChangePerc;
import ch.epfl.lsir.wattalyst.kpi.kpis.ConsumptionLimitAbs;


public class KPITask {

	/**
	 * 
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
				
		WebserverDataReader reader = new WebserverDataReader();
		WebserverDataWriter writer = new WebserverDataWriter();
		
		//writer.setDRSignalStatus("wattalyst.lulea.campaign_7.signal_2", "wattalyst.lulea.location_159", SignalStatus.EXPIRED.name());
		
		
		// 1. Retrieve all DR messages that have not been evaluated
		HashMap<LocationDto, List<DrSignalDto>> toBeEvaluated = reader.getNotEvaluatedDRSignals();
		System.out.println(toBeEvaluated.size());
		// 2. For all the locations
		for(LocationDto location : toBeEvaluated.keySet()){
			System.out.println(location.getFullQualifiedName());
			
			// 3. For all the signals of the location
			for(DrSignalDto drSignal : toBeEvaluated.get(location)){
				
				String drSignalID = drSignal.getFullQualifiedName();
				System.out.println(drSignalID);
			
				// 3. For all the tasks of the signal
				for(TaskDto task : drSignal.getTasks()){
					
					// 4. Retrieve baseline and sensor type
					ConstraintDto constraint = task.getConstraint();
					String baselineType = constraint.getBaselineType().name();
					String sensorType = constraint.getSensorType().name();
					System.out.println(sensorType);
					
					// 5. Retrieve reward tokens and consumption limits
					// If the value is 0, the parameter has not been set,
					// therefore the KPIs that use it should not be computed
					double numTokens = constraint.getRewardTokens();
					double consumptionLimit = constraint.getThresholdAmount();
					
					// 6. For all the time intervals of the task
					for(TimeIntervalDto interval : task.getTarget().getTimeIntervals()){
						
						Date start = new Date(interval.getStartDate());
						Date end = new Date(interval.getEndDate());
						
						// 7. For all the sensors of the specified type
						//List<String> sensorsOfType = reader.getLocationSensorsByCategory(location.getFullQualifiedName(), sensorType);
						
						
						// TODO
						// Use new call getSensorByLocationAndSensorType()
						
						List<String> sensorsOfType = null;//reader.getSensorByLocationAndSensorType(location.getFullQualifiedName(), sensorType);
						for(String sensorID : sensorsOfType){
							
							System.out.println(sensorID);
							String baselineID = sensorID + ".baseline_" + baselineType;
						        
							// 8. Retrieve baseline data and real consumption data
							SensorReadings realConsumption = 
									reader.getValuesForSensorByRange(sensorID, start, end, true);
							
							SensorReadings baselineConsumption = 
									reader.getBaselineData(baselineID, start, end);
						
							// 9. Check if baseline and real data have correct size
							if(realConsumption.getMinDate() == start.getTime() && realConsumption.getMaxDate() == end.getTime() &&
									baselineConsumption.getMinDate() == start.getTime() && baselineConsumption.getMaxDate() == end.getTime()){
							
								// 10. Compute KPIs that involves baselines and store them
								Double cca = computeConsumptionChangeAbs(baselineConsumption, realConsumption, start, end, numTokens, consumptionLimit);
								writer.setPerformanceIndicator(drSignalID, location.getFullQualifiedName(), cca, 
										"Absolute change in consumption (kWh)", (cca >=0 ? SuccessStatus.ACCOMPLISHED.name() : SuccessStatus.NOT_ACCOMPLISHED.name()));
								
								Double ccp = computeConsumptionChangePerc(baselineConsumption, realConsumption, start, end, numTokens, consumptionLimit);
								if(ccp != null){
									writer.setPerformanceIndicator(drSignalID, location.getFullQualifiedName(), ccp, 
										"Percentage change in consumption (%)", (ccp >=0 ? SuccessStatus.ACCOMPLISHED.name() : SuccessStatus.NOT_ACCOMPLISHED.name()));
								}
								
								if(numTokens > 0){
									Double cca_t = computeConsumptionChangePerTokenAbs(baselineConsumption, realConsumption, start, end, numTokens, consumptionLimit);
									writer.setPerformanceIndicator(drSignalID, location.getFullQualifiedName(), cca_t, 
											"Absolute change in consumption per reward token (kWh/token)", (cca_t >=0 ? SuccessStatus.ACCOMPLISHED.name() : SuccessStatus.NOT_ACCOMPLISHED.name()));
								
									Double ccp_t = computeConsumptionChangePerTokenPerc(baselineConsumption, realConsumption, start, end, numTokens, consumptionLimit);
									if(ccp_t != null){
										writer.setPerformanceIndicator(drSignalID, location.getFullQualifiedName(), ccp_t, 
												"Percentage change in consumption per reward token (%/token)", (ccp_t >=0 ? SuccessStatus.ACCOMPLISHED.name() : SuccessStatus.NOT_ACCOMPLISHED.name()));
									}
								}
								
								//TODO 
								// REDUCED_CONSUMPTION --> real over the time window = baseline - absolute change +- tolerance 
							}
							
							// 11. Compute KPIs that do not involve baselines and store them
							if(consumptionLimit > 0){
								Double cla = computeConsumptionLimitAbs(baselineConsumption, realConsumption, start, end, numTokens, consumptionLimit);
								writer.setPerformanceIndicator(drSignalID, location.getFullQualifiedName(), cla, 
										"Consumption limit achieved", (cla >= 0 ? SuccessStatus.ACCOMPLISHED.name() : SuccessStatus.NOT_ACCOMPLISHED.name()));
							}
						}
					}
				}
			}
		}
	}

	/*
	 * 
	 */
	private static Double computeConsumptionChangeAbs(SensorReadings baselineConsumption, 
			SensorReadings realConsumption, Date start, Date end, double numTokens, double consumptionLimit) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		
		ConsumptionChangeAbs consumptionChangeAbs = new ConsumptionChangeAbs();
		consumptionChangeAbs.compute(baselineConsumption, realConsumption, startCal, endCal, numTokens, consumptionLimit);
		return consumptionChangeAbs.getResult();
	}
	
	/*
	 * 
	 */
	private static Double computeConsumptionChangePerc(SensorReadings baselineConsumption, 
			SensorReadings realConsumption, Date start, Date end, double numTokens, double consumptionLimit) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		
		ConsumptionChangePerc consumptionChangePerc = new ConsumptionChangePerc();
		consumptionChangePerc.compute(baselineConsumption, realConsumption, startCal, endCal, numTokens, consumptionLimit);
		return consumptionChangePerc.getResult();
	}
	
	/*
	 * 
	 */
	private static Double computeConsumptionChangePerTokenAbs(SensorReadings baselineConsumption, 
			SensorReadings realConsumption, Date start, Date end, double numTokens, double consumptionLimit) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		
		ConsumptionChangePerTokenAbs consumptionChangePerTokenAbs = new ConsumptionChangePerTokenAbs();
		consumptionChangePerTokenAbs.compute(baselineConsumption, realConsumption, startCal, endCal, numTokens, consumptionLimit);
		return consumptionChangePerTokenAbs.getResult();
	}
		
	/*
	 * 
	 */
	private static Double computeConsumptionChangePerTokenPerc(SensorReadings baselineConsumption, 
			SensorReadings realConsumption, Date start, Date end, double numTokens, double consumptionLimit) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		
		ConsumptionChangePerTokenPerc consumptionChangePerTokenPerc = new ConsumptionChangePerTokenPerc();
		consumptionChangePerTokenPerc.compute(baselineConsumption, realConsumption, startCal, endCal, numTokens, consumptionLimit);
		return consumptionChangePerTokenPerc.getResult();
	}


//	/*
//	 * 
//	 */
//	private static double computeTokenPerConsumptionChangeAbs(SensorReadings baselineConsumption, 
//			SensorReadings realConsumption, Date start, Date end, double numTokens, double consumptionLimit) {
//
//		Calendar startCal = Calendar.getInstance();
//		startCal.setTime(start);
//		Calendar endCal = Calendar.getInstance();
//		endCal.setTime(end);
//		
//		TokenPerConsumptionChangeAbs tokenPerConsumptionChangeAbs = new TokenPerConsumptionChangeAbs();
//		tokenPerConsumptionChangeAbs.compute(baselineConsumption, realConsumption, startCal, endCal, numTokens, consumptionLimit);
//		return tokenPerConsumptionChangeAbs.getResult();
//	}
	
	/*
	 * 
	 */
	private static Double computeConsumptionLimitAbs(SensorReadings baselineConsumption, 
			SensorReadings realConsumption, Date start, Date end, double numTokens, double consumptionLimit) {

		Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		
		ConsumptionLimitAbs consumptionLimitAbs = new ConsumptionLimitAbs();
		consumptionLimitAbs.compute(baselineConsumption, realConsumption, startCal, endCal, numTokens, consumptionLimit);
		return consumptionLimitAbs.getResult();
	}
}
