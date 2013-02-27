package com.baseline.baselines;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import com.baseline.util.SensorReadings;
import com.baseline.util.Util;

public class Regression {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		// manual input for now
		// for quick and dirty implementation
		
		
		// target date: 
		String targetDate = "2013-02-20";
		
		
		// assume that we have only two features: historical load and temperature 
		String[] featureStr = {"load","temp"};
		int featureCount = featureStr.length;
		
		
		// insert them into hashmap
		HashMap<String,Integer> featureMap = new HashMap<String,Integer>();
		for (int i=0; i<featureCount; i++){
			featureMap.put(featureStr[i], i);			
		}
						

		// read two feature files
		String dirSep = System.getProperty("file.separator");
		String[] fileStr = {"examples" + dirSep + "input-example.txt", "examples" + dirSep + "temperature.txt"};
		
		// put the features
		ArrayList<SensorReadings> arrFeatureData = new ArrayList<SensorReadings>();
		for (int i=0; i<featureCount; i++){
			SensorReadings featureData = new SensorReadings();
			Util.hourlyCSVToSensorReadings(fileStr[i], featureData);
			arrFeatureData.add(featureData);
		}
		
		// specify feature vector
		
		// for hour=1 to 24
			// build the feature vector [minDate, startDate-1]
			// train the regression
			// predict
		
		
		System.err.println("done.");
		

	}

}
