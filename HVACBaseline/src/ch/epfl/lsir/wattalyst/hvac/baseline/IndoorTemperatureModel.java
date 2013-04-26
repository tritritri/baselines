package ch.epfl.lsir.wattalyst.hvac.baseline;

public interface IndoorTemperatureModel {

	/**
	 * Implement the indoor temperature dynamic model
	 * 
	 * @param externalTemperatureForecast
	 * @param hvacMode
	 * @param indoorTemperature
	 * @param setpointTemperature
	 * @param hvacPower
	 * @return
	 * @throws Exception 
	 */
	public double getNextIndoorTemperature(double externalTemperatureForecast, String hvacMode, 
			double indoorTemperature, double setpointTemperature, double hvacPower) throws Exception;
}
