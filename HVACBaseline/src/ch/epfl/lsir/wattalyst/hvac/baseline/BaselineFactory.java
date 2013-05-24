package ch.epfl.lsir.wattalyst.hvac.baseline;

import java.io.IOException;

import ch.epfl.lsir.wattalyst.hvac.baseline.impl.SupervisedHVACPowerConsumptionModel;
import ch.epfl.lsir.wattalyst.hvac.baseline.impl.SupervisedIndoorTemperatureModel;

public class BaselineFactory {

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static Baseline createBaseline() throws IOException{
		HVACPowerConsumptionModel hvacPowerModel = 
				new SupervisedHVACPowerConsumptionModel("classifier/model/HVAC-power-IBk", 
						"classifier/model/HVAC-power-IBk.h");

		//HVACModeModel hvacModeModel = new SupervisedHVACModeModel("./classifier/model/R1A-hvac-mode-dynamics-Bagging", 
		//		"./classifier/model/R1A-hvac-mode-dynamics-Bagging.h");
		HVACModeModel hvacModeModel = new HVACModeModel(){
			@Override
			public String getNextHVACMode(double externalTemperatureForecast,
					String hvacMode, String room, double indoorTemperature,
					double setpointTemperature, double hvacPower)
					throws Exception {
				return "30";
			}
		};

		IndoorTemperatureModel indoorTemperatureModel = 
				new SupervisedIndoorTemperatureModel("classifier/model/HVAC-indoor-temperature-LinearRegression", 
						"classifier/model/HVAC-indoor-temperature-LinearRegression.h");

		return new Baseline(hvacPowerModel, hvacModeModel, indoorTemperatureModel);
	}
	
	/**
	 * 
	 * @param hvacPowerModel
	 * @param indoorTemperatureModel
	 * @return
	 * @throws IOException
	 */
	public static Baseline createBaseline(String hvacPowerModelFile, String indoorTemperatureModelFile) throws IOException{
		HVACPowerConsumptionModel hvacPowerModel = 
				new SupervisedHVACPowerConsumptionModel(hvacPowerModelFile, hvacPowerModelFile + ".h");

		//HVACModeModel hvacModeModel = new SupervisedHVACModeModel("./classifier/model/R1A-hvac-mode-dynamics-Bagging", 
		//		"./classifier/model/R1A-hvac-mode-dynamics-Bagging.h");
		HVACModeModel hvacModeModel = new HVACModeModel(){
			@Override
			public String getNextHVACMode(double externalTemperatureForecast,
					String hvacMode, String room, double indoorTemperature,
					double setpointTemperature, double hvacPower)
					throws Exception {
				return "30";
			}
		};

		IndoorTemperatureModel indoorTemperatureModel = 
				new SupervisedIndoorTemperatureModel(indoorTemperatureModelFile, indoorTemperatureModelFile + ".h");

		return new Baseline(hvacPowerModel, hvacModeModel, indoorTemperatureModel);
	}
}
