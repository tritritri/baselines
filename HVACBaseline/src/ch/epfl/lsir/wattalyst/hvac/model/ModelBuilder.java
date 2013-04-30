package ch.epfl.lsir.wattalyst.hvac.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Debug;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ArffSaver;

public class ModelBuilder {

	/**
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		
		// Parse args
		Options opts = createOptions();
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(opts, args);

		// If help needed
		if (cmd.hasOption("h") || args.length < 3 || args.length > 4) {
			printHelp(opts);
			return;
		} 
				
		// Read the training set
		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		Instances trainingSet = new Instances(reader);
		reader.close();
		
		// Setting class attribute
		trainingSet.setClassIndex(trainingSet.numAttributes() - 1);
	      
		// Read the Weka classifier configuration file
		BufferedReader classifierConfig = new BufferedReader(new FileReader(args[1]));
		String line = classifierConfig.readLine();
		String[] params = Utils.splitOptions(line);
		String classifierName = params[0];
		params[0] = "";
		classifierConfig.close();
		
		// Build the classifier
		Classifier classifier = (Classifier) Utils.forName(Classifier.class, classifierName, params);
		System.out.print("Building model ...");
		classifier.buildClassifier(trainingSet);
		System.out.println(" Done");
		
		System.out.print("Saving model ...");
		// Save the model of the classifier 
		Debug.saveToFile(args[2], classifier);
		
		// Save the header of the model
		Instances header = new Instances(trainingSet, 0);
		ArffSaver saver = new ArffSaver();
		saver.setInstances(header);
		saver.setFile(new File(args[2] + ".h"));
		saver.writeBatch();
		System.out.println(" Done");
		
		System.out.println("Evaluating model ...");
		// Perform 10-fold cross-validation
		int runs  = 10;
	    int folds = 10;
	    for (int i = 0; i < runs; i++) {
	    	
	    	// randomize data
		    int seed = i + 1;
		    Random rand = new Random(seed);
		    Instances randData = new Instances(trainingSet);
		    randData.randomize(rand);
		    if (randData.classAttribute().isNominal()){
		        randData.stratify(folds);
		    }
	
		    Evaluation eval = new Evaluation(randData);
		    for (int n = 0; n < folds; n++) {
		    	Instances train = randData.trainCV(folds, n);
		        Instances test = randData.testCV(folds, n);
		        
		        // build and evaluate classifier
		        Classifier clsCopy = Classifier.makeCopy(classifier);
		        clsCopy.buildClassifier(train);
		        eval.evaluateModel(clsCopy, test);
		    }
	    
		    // output evaluation
		    System.out.println();
		    System.out.println("=== Setup run " + (i+1) + " ===");
		    System.out.println("Classifier: " + classifier.getClass().getName() + " " + Utils.joinOptions(classifier.getOptions()));
		    System.out.println("Dataset: " + trainingSet.relationName());
		    System.out.println("Folds: " + folds);
		    System.out.println("Seed: " + seed);
		    System.out.println();
		    System.out.println(eval.toSummaryString("=== " + folds + "-fold Cross-validation run " + (i+1) + "===", false));
	    }
	    System.out.println("\nDone");
	}
	
	/*
	 * 
	 */
	private static void printHelp(Options opts) {

		HelpFormatter help = new HelpFormatter();
		help.setWidth(90);
		String helpString = "java -jar ModelBuilder.jar [OPTIONS] TRAININGSET CLASSIFIER OUTPUT\n" 
				+ "Example: java -jar ModelBuilder.jar arff/dataset.arff classifier/config/LinearRegression.txt classifier/model/dataset-LinearRegression\n";
		help.printHelp(helpString, opts);
	}

	/*
	 * 
	 */
	private static Options createOptions(){
		Options options = new Options();
		options.addOption("h", "help", false, "Help. Print this message.");
		return options;	
	}
}
