package ch.epfl.lsir.wattalyst.hvac.baseline;

import java.io.IOException;

import ch.epfl.lsir.wattalyst.hvac.baseline.impl.DummyModeModel;
import ch.epfl.lsir.wattalyst.hvac.baseline.impl.SupervisedPowerModel;
import ch.epfl.lsir.wattalyst.hvac.baseline.impl.SupervisedIndoorTemperatureModel;

public class BaselineFactory {
	
	/**
	 * 
	 * @param powerModelFile
	 * @param indoorTemperatureModelFile
	 * @return
	 * @throws IOException
	 */
	public static Baseline createBaseline(String powerModelFile, String indoorTemperatureModelFile) throws IOException{
		PowerModel powerModel = new SupervisedPowerModel(powerModelFile, powerModelFile + ".h");
		IndoorTemperatureModel indoorTemperatureModel = new SupervisedIndoorTemperatureModel(indoorTemperatureModelFile, indoorTemperatureModelFile + ".h");
		ModeModel modeModel = new DummyModeModel();
		return new Baseline(powerModel, indoorTemperatureModel, modeModel);
	}
}
