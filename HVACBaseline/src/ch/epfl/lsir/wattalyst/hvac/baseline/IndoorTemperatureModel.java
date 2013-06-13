package ch.epfl.lsir.wattalyst.hvac.baseline;

public interface IndoorTemperatureModel {

	/**
	 * 
	 * Implement the indoor temperature dynamic model
	 * 
	 * @param set_temp
	 * @param ind_temp
	 * @param ext_temp
	 * @param mode
	 * @param power
	 * @return
	 * @throws Exception
	 */
	public double getNextIndoorTemperature(double[] set_temp, double[] ind_temp, double[] ext_temp,
			String[] mode, double[] power) throws Exception;
	
}
