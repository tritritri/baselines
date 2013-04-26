package ch.epfl.lsir.wattalyst.hvac.baseline.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import weka.classifiers.misc.SerializedClassifier;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;

public abstract class SupervisedModel {

	protected SerializedClassifier classifier;
	protected Instances structure;

	/**
	 * 
	 * @param modelFile
	 * @param headerFile
	 * @throws IOException 
	 */
	public SupervisedModel(String modelFile, String headerFile) throws IOException{
		classifier = new SerializedClassifier();
		classifier.setModelFile(new File(modelFile));
		
		BufferedReader reader = new BufferedReader(new FileReader(headerFile));
		ArffReader arff = new ArffReader(reader);
		structure = arff.getStructure();
	}
}
