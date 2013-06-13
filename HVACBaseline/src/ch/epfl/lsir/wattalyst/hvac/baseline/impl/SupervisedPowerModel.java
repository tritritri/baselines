package ch.epfl.lsir.wattalyst.hvac.baseline.impl;

import java.io.IOException;

import weka.core.Instance;
import ch.epfl.lsir.wattalyst.hvac.baseline.PowerModel;

public class SupervisedPowerModel extends SupervisedModel implements PowerModel {

	/**
	 * 
	 * @param modelFile
	 * @param headerFile
	 * @throws IOException
	 */
	public SupervisedPowerModel(String modelFile, String headerFile)
			throws IOException {
		super(modelFile, headerFile);
	}

	/*
	 * (non-Javadoc)
	 * @see ch.epfl.lsir.wattalyst.hvac.baseline.PowerModel#getNextPower(double[], double[], double[], double[], double[])
	 */
	@Override
	public double getNextPower(double[] set_temp, double[] ind_temp, double[] ext_temp,
			String[] mode, double[] power) throws Exception {
		
		// Create a new instance to classify
		Instance newInst = new Instance(30);
		newInst.setValue(structure.attribute("set_temp_t_6"), set_temp[0]);
		newInst.setValue(structure.attribute("ind_temp_t_6"), ind_temp[0]);
		newInst.setValue(structure.attribute("ext_temp_t_6"), ext_temp[0]);
		newInst.setValue(structure.attribute("mode_t_6"), mode[0]);
		newInst.setValue(structure.attribute("power_t_6"), power[0]);
		newInst.setValue(structure.attribute("set_temp_t_5"), set_temp[1]);
		newInst.setValue(structure.attribute("ind_temp_t_5"), ind_temp[1]);
		newInst.setValue(structure.attribute("ext_temp_t_5"), ext_temp[1]);
		newInst.setValue(structure.attribute("mode_t_5"), mode[1]);
		newInst.setValue(structure.attribute("power_t_5"), power[1]);
		newInst.setValue(structure.attribute("set_temp_t_4"), set_temp[2]);
		newInst.setValue(structure.attribute("ind_temp_t_4"), ind_temp[2]);
		newInst.setValue(structure.attribute("ext_temp_t_4"), ext_temp[2]);
		newInst.setValue(structure.attribute("mode_t_4"), mode[2]);
		newInst.setValue(structure.attribute("power_t_4"), power[2]);
		newInst.setValue(structure.attribute("set_temp_t_3"), set_temp[3]);
		newInst.setValue(structure.attribute("ind_temp_t_3"), ind_temp[3]);
		newInst.setValue(structure.attribute("ext_temp_t_3"), ext_temp[3]);
		newInst.setValue(structure.attribute("mode_t_3"), mode[3]);
		newInst.setValue(structure.attribute("power_t_3"), power[3]);
		newInst.setValue(structure.attribute("set_temp_t_2"), set_temp[4]);
		newInst.setValue(structure.attribute("ind_temp_t_2"), ind_temp[4]);
		newInst.setValue(structure.attribute("ext_temp_t_2"), ext_temp[4]);
		newInst.setValue(structure.attribute("mode_t_2"), mode[4]);
		newInst.setValue(structure.attribute("power_t_2"), power[4]);
		newInst.setValue(structure.attribute("set_temp_t_1"), set_temp[5]);
		newInst.setValue(structure.attribute("ind_temp_t_1"), ind_temp[5]);
		newInst.setValue(structure.attribute("ext_temp_t_1"), ext_temp[5]);
		newInst.setValue(structure.attribute("mode_t_1"), mode[5]);
		newInst.setValue(structure.attribute("power_t_1"), power[5]);
				
		structure.add(newInst);
		structure.setClassIndex(structure.numAttributes() - 1);  
		
		// Get prediction 
		double power_t = classifier.classifyInstance(structure.firstInstance());
		structure.delete();
		return power_t;
	}

}
