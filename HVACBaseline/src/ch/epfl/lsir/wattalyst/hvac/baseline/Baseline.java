package ch.epfl.lsir.wattalyst.hvac.baseline;


public class Baseline {

	private HVACPowerConsumptionModel hvacPowerModel;
	private HVACModeModel hvacModeModel;
	private IndoorTemperatureModel indoorTemperatureModel;

	/**
	 * 
	 */
	public Baseline(HVACPowerConsumptionModel hvacPowerModel, HVACModeModel hvacModeModel, 
			IndoorTemperatureModel indoorTemperatureModel){
		this.hvacPowerModel = hvacPowerModel;
		this.hvacModeModel = hvacModeModel;
		this.indoorTemperatureModel = indoorTemperatureModel;
	}
	
	/**
	 * Compute the HVAC power consumption, indoor temperature and HVAC mode
	 * over a time period equal to the number of 10 min timeslots
	 * in the array of external temperature forecast
	 * 
	 * @param externalTemperatureForecast
	 * @param room
	 * @param setpointTemperature
	 * @param initialIndoorTemperature
	 * @param initialHvacMode
	 * @return an Object[] containing three arrays: \n
	 * 		double[] hvacPower \n
	 * 		double[] indoorTemperature \n
	 * 		String[] hvacMode
	 * @throws Exception
	 */
	public Object[] compute(double[] externalTemperatureForecast, String room, double setpointTemperature,
			double initialIndoorTemperature, String initialHvacMode) throws Exception{
				
		double[] indoorTemperature = new double[externalTemperatureForecast.length];
		indoorTemperature[0] = initialIndoorTemperature;
		String[] hvacMode = new String[externalTemperatureForecast.length];
		hvacMode[0] = initialHvacMode; 
		
		double[] hvacPower = new double[externalTemperatureForecast.length];
		for(int t = 0; t < externalTemperatureForecast.length; t++){
			hvacPower[t] = hvacPowerModel.getPower(externalTemperatureForecast[t], hvacMode[t], room,
					indoorTemperature[t], setpointTemperature);
			if(t < externalTemperatureForecast.length - 1){
				indoorTemperature[t + 1] = indoorTemperatureModel.getNextIndoorTemperature(externalTemperatureForecast[t], 
						hvacMode[t], room, indoorTemperature[t], setpointTemperature, hvacPower[t]);
				hvacMode[t + 1] = hvacModeModel.getNextHVACMode(externalTemperatureForecast[t],  
						hvacMode[t], room, indoorTemperature[t], setpointTemperature, hvacPower[t]);
			}
		}
		return new Object[]{hvacPower, indoorTemperature, hvacMode};
	}
}
