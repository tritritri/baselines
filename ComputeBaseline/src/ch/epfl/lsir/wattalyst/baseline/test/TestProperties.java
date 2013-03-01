package ch.epfl.lsir.wattalyst.baseline.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		
		Properties prop = new Properties();
		prop.load(new FileInputStream("examples/properties.txt"));
		System.out.println(prop.get("energy-file"));
		System.out.println(prop.get("temperature-file"));
		

	}

}
