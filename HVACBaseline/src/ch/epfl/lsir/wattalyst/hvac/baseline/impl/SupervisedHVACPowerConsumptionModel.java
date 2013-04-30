package ch.epfl.lsir.wattalyst.hvac.baseline.impl;

import java.io.IOException;

import weka.core.Instance;
import ch.epfl.lsir.wattalyst.hvac.baseline.HVACPowerConsumptionModel;

public class SupervisedHVACPowerConsumptionModel extends SupervisedModel implements HVACPowerConsumptionModel {

	/**
	 * 
	 * @param modelFile
	 * @param headerFile
	 * @throws IOException
	 */
	public SupervisedHVACPowerConsumptionModel(String modelFile, String headerFile)
			throws IOException {
		super(modelFile, headerFile);
	}

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.hvac.baseline.HVACPowerConsumptionModel#getPower(double, java.lang.String, double, double)
	 */
	@Override
	public double getPower(double externalTemperatureForecast, String hvacMode, String room, 
			double indoorTemperature, double setpointTemperature) throws Exception {
		
		// Create a new instance to classify
		Instance newInst = new Instance(6);
		newInst.setValue(structure.attribute("external-temperature"), externalTemperatureForecast);
		newInst.setValue(structure.attribute("hvac-mode"), hvacMode);
		newInst.setValue(structure.attribute("room"), room);
		newInst.setValue(structure.attribute("indoor-temperature"), indoorTemperature);
		newInst.setValue(structure.attribute("setpoint-temperature"), setpointTemperature);
				
		structure.add(newInst);
		structure.setClassIndex(structure.numAttributes() - 1);  
		
		// Get prediction 
		double predictedPower = classifier.classifyInstance(structure.firstInstance());
		structure.delete();
		return predictedPower;
	}

}
