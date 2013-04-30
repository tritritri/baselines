package ch.epfl.lsir.wattalyst.hvac.baseline;

public interface IndoorTemperatureModel {

	/**
	 * Implement the indoor temperature dynamic model
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
	public double getNextIndoorTemperature(double externalTemperatureForecast, String hvacMode, 
			String room, double indoorTemperature, double setpointTemperature, double hvacPower) throws Exception;
}
