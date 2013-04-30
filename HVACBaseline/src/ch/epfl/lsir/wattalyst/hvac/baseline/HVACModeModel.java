package ch.epfl.lsir.wattalyst.hvac.baseline;

public interface HVACModeModel {

	/**
	 * Implement the HVAC mode dynamic model
	 * 
	 * @param externalTemperatureForecast
	 * @param hvacMode
	 * @param room 
	 * @param indoorTemperature
	 * @param setpointTemperature
	 * @param hvacPower
	 * @return
	 * @throws Exception 
	 */
	public String getNextHVACMode(double externalTemperatureForecast, String hvacMode, 
			String room, double indoorTemperature, double setpointTemperature, double hvacPower) throws Exception;
}
