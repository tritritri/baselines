package ch.epfl.lsir.wattalyst.hvac.baseline.impl;

import ch.epfl.lsir.wattalyst.hvac.baseline.ModeModel;

public class DummyModeModel implements ModeModel {

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.hvac.baseline.ModeModel#getNextMode(double[], double[], double[], java.lang.String[], double[])
	 */
	@Override
	public String getNextMode(double[] set_temp, double[] ind_temp, double[] ext_temp,
			String[] mode, double[] power) throws Exception {
		
		return "30";
	}

}
