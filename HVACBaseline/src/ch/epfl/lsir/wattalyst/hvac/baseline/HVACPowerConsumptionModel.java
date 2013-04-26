package ch.epfl.lsir.wattalyst.hvac.baseline;

public interface HVACPowerConsumptionModel {

	/**
	 * Implement the HVAC power consumption model
	 * 
	 * @param externalTemperatureForecast
	 * @param hvacMode
	 * @param indoorTemperature
	 * @param setpointTemperature
	 * @return
	 * @throws Exception 
	 */
	public double getPower(double externalTemperatureForecast, String hvacMode, 
			double indoorTemperature, double setpointTemperature) throws Exception;

}
