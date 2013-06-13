package ch.epfl.lsir.wattalyst.hvac.baseline;


public class Baseline {

	private PowerModel powerModel;
	private IndoorTemperatureModel indoorTemperatureModel;
	private ModeModel modeModel;

	/**
	 * 
	 * @param powerModel
	 * @param indoorTemperatureModel
	 * @param modeModel
	 */
	public Baseline(PowerModel powerModel, IndoorTemperatureModel indoorTemperatureModel, ModeModel modeModel){
		this.powerModel = powerModel;
		this.indoorTemperatureModel = indoorTemperatureModel;
		this.modeModel = modeModel;
	}
	
	/**
	 * Compute the HVAC power consumption, indoor temperature and HVAC mode
	 * over a time period equal to the number of 10 min timeslots
	 * in the array of external temperature forecast
	 * 
	 * @param ext_temp_future
	 * @param set_temp
	 * @param ind_temp
	 * @param ext_temp
	 * @param mode
	 * @param power
	 * @return an Object[] containing three arrays: \n
	 * 		double[] baseline_power \n
	 * 		double[] baseline_ind_temp \n
	 * 		double[] baseline_mode
	 * @throws Exception
	 */
	public Object[] compute(double[] ext_temp_future,
							double[] set_temp_future,
							double[] set_temp,
							double[] ind_temp, 
							double[] ext_temp, 
							String[] mode, 
							double[] power) throws Exception{
				
		double[] baseline_ind_temp = new double[ext_temp_future.length];
		double[] baseline_power = new double[ext_temp_future.length];
		String[] baseline_mode = new String[ext_temp_future.length];
		
		for(int t = 0; t < baseline_power.length; t++){
			baseline_power[t] = powerModel.getNextPower(set_temp, ind_temp, ext_temp, mode, power);
			baseline_ind_temp[t] = indoorTemperatureModel.getNextIndoorTemperature(set_temp, ind_temp, ext_temp, mode, power);
			baseline_mode[t] = modeModel.getNextMode(set_temp, ind_temp, ext_temp, mode, power);
			
			for(int i = 1; i < set_temp.length; i++){
				set_temp[i-1] = set_temp[i];
				ind_temp[i-1] = ind_temp[i];
				ext_temp[i-1] = ext_temp[i];
				mode[i-1] = mode[i];
				power[i-1] = power[i];
			}
			set_temp[set_temp.length-1] = set_temp_future[t];
			ind_temp[ind_temp.length-1] = baseline_ind_temp[t];
			ext_temp[ext_temp.length-1] = ext_temp_future[t];
			mode[mode.length-1] = baseline_mode[t];
			power[power.length-1] = baseline_power[t];
		}
		return new Object[]{baseline_power, baseline_ind_temp, baseline_mode};
	}
}
