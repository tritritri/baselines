package ch.epfl.lsir.wattalyst.hvac.baseline.impl;

import java.io.IOException;

import weka.core.Instance;
import ch.epfl.lsir.wattalyst.hvac.baseline.IndoorTemperatureModel;

public class SupervisedIndoorTemperatureModel extends SupervisedModel implements IndoorTemperatureModel {

	/**
	 * 
	 * @param modelFile
	 * @param headerFile
	 * @throws IOException
	 */
	public SupervisedIndoorTemperatureModel(String modelFile, String headerFile)
			throws IOException {
		super(modelFile, headerFile);
	}

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.hvac.baseline.IndoorTemperatureModel#getNextIndoorTemperature(double, java.lang.String, double, double, double)
	 */
	@Override
	public double getNextIndoorTemperature(double externalTemperatureForecast,
			String hvacMode, double indoorTemperature, double setpointTemperature, double hvacPower) throws Exception {
		
		// Create a new instance to classify
		Instance newInst = new Instance(6);
		newInst.setValue(structure.attribute("external-temperature"), externalTemperatureForecast);
		newInst.setValue(structure.attribute("hvac-mode"), hvacMode);
		newInst.setValue(structure.attribute("indoor-temperature"), indoorTemperature);
		newInst.setValue(structure.attribute("setpoint-temperature"), setpointTemperature);
		newInst.setValue(structure.attribute("hvac-power"), hvacPower);
				
		structure.add(newInst);
		structure.setClassIndex(structure.numAttributes() - 1);  
		
		// Get prediction 
		double predictedTemp = classifier.classifyInstance(structure.firstInstance());
		structure.delete();
		return predictedTemp;
	}

}
