package ch.epfl.lsir.wattalyst.hvac.baseline;

public interface ModeModel {

	/**
	 * 
	 * Implement the HVAC mode dynamic model
	 * 
	 * @param set_temp
	 * @param ind_temp
	 * @param ext_temp
	 * @param mode
	 * @param power
	 * @return
	 * @throws Exception
	 */
	public String getNextMode(double[] set_temp, double[] ind_temp, double[] ext_temp,
			String[] mode, double[] power) throws Exception;

}
