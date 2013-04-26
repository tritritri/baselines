package ch.epfl.lsir.wattalyst.hvac.baseline;

public interface HVACModeModel {

	/**
	 * Implement the HVAC mode dynamic model
	 * 
	 * @param externalTemperatureForecast
	 * @param hvacMode
	 * @param indoorTemperature
	 * @param setpointTemperature
	 * @param hvacPower
	 * @return
	 * @throws Exception 
	 */
	public String getNextHVACMode(double externalTemperatureForecast, String hvacMode, 
			double indoorTemperature, double setpointTemperature, double hvacPower) throws Exception;
}
