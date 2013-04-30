package ch.epfl.lsir.wattalyst.hvac.baseline.impl;

import java.io.IOException;

import weka.core.Instance;
import ch.epfl.lsir.wattalyst.hvac.baseline.HVACModeModel;

public class SupervisedHVACModeModel extends SupervisedModel implements HVACModeModel {
	
	/**
	 * 
	 * @param modelFile
	 * @param headerFile
	 * @throws IOException
	 */
	public SupervisedHVACModeModel(String modelFile, String headerFile)
			throws IOException {
		super(modelFile, headerFile);
	}

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.hvac.baseline.HVACModeModel#getNextHVACMode(double, java.lang.String, java.lang.String, double, double, double)
	 */
	@Override
	public String getNextHVACMode(double externalTemperatureForecast, String hvacMode,
			String room, double indoorTemperature,
			double setpointTemperature, double hvacPower) throws Exception {
		
		// Create a new instance to classify
		Instance newInst = new Instance(7);
		newInst.setValue(structure.attribute("external-temperature"), externalTemperatureForecast);
		newInst.setValue(structure.attribute("hvac-mode"), hvacMode);
		newInst.setValue(structure.attribute("room"), room);
		newInst.setValue(structure.attribute("indoor-temperature"), indoorTemperature);
		newInst.setValue(structure.attribute("setpoint-temperature"), setpointTemperature);
		newInst.setValue(structure.attribute("hvac-power"), hvacPower);
		
		structure.add(newInst);
		structure.setClassIndex(structure.numAttributes() - 1);  
		
		// Get prediction 
		String predictedMode = structure.attribute("next-hvac-mode").value((int) classifier.classifyInstance(structure.lastInstance()));
		structure.delete();
		return predictedMode;
	}

}
