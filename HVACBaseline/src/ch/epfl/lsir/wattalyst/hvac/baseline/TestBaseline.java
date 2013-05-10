package ch.epfl.lsir.wattalyst.hvac.baseline;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Stroke;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import weka.core.Instances;

public class TestBaseline {

	/**
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		
		Baseline baseline = BaselineFactory.createBaseline();
		
		// Set the room
		String room = "R5D"; // R1F R1G R1D R1L R4A
		
		// Read the test set
		BufferedReader reader = new BufferedReader(new FileReader("./arff/HVAC-test-" + room + ".arff"));
		Instances testSet = new Instances(reader);
		reader.close();
		
		int timeWindow = testSet.numInstances(); 
		
		double[] externalTemperatureForecast = new double[timeWindow];
		double[] trueHvacPower = new double[timeWindow];
		double[] trueIndoorTemperature = new double[timeWindow];
		String[] trueHvacMode = new String[timeWindow];
		double setpointTemperature = testSet.instance(0).value(testSet.attribute("setpoint-temperature"));
		double initialIndoorTemperature = testSet.instance(0).value(testSet.attribute("indoor-temperature"));
		String initialHvacMode = testSet.attribute("hvac-mode").value((int) testSet.instance(0).value(testSet.attribute("hvac-mode")));
		
		for(int i = 0; i < timeWindow; i++){
			externalTemperatureForecast[i] = testSet.instance(i).value(testSet.attribute("external-temperature"));
			trueHvacPower[i] = testSet.instance(i).value(testSet.attribute("hvac-power"));
			trueIndoorTemperature[i] = testSet.instance(i).value(testSet.attribute("indoor-temperature"));
			trueHvacMode[i] = testSet.attribute("hvac-mode").value((int) testSet.instance(i).value(testSet.attribute("hvac-mode")));
		}
		
		Object[] result = baseline.compute(externalTemperatureForecast, room, setpointTemperature,
				initialIndoorTemperature, initialHvacMode);
		double[] hvacPower = (double[]) result[0];
		double[] indoorTemperature = (double[]) result[1];
		String[] hvacMode = (String[]) result[2];

		plotResults(hvacPower, trueHvacPower, indoorTemperature, trueIndoorTemperature, hvacMode, trueHvacMode);
		printResults(hvacPower, trueHvacPower, indoorTemperature, trueIndoorTemperature, hvacMode, trueHvacMode);
	}
		
	/*
	 * 
	 */
	private static void printResults(double[] hvacPower, double[] trueHvacPower, double[] indoorTemperature, double[] trueIndoorTemperature, String[] hvacMode, String[] trueHvacMode){
		System.out.print("Predicted hvac power,");
		for(double power : hvacPower){
			System.out.print(power + ",");
		}
		System.out.println();
		System.out.print("True hvac power,");
		for(double power : trueHvacPower){
			System.out.print(power + ",");
		}
		
		double RMSE = 0;
		double E = 0;
		for(int i = 0; i < trueHvacPower.length; i++){
			RMSE = RMSE + Math.pow(hvacPower[i] - trueHvacPower[i], 2);
			E = E + hvacPower[i] - trueHvacPower[i];
		}
		RMSE = RMSE/trueHvacPower.length;
		RMSE = Math.sqrt(RMSE);
		E = E / (trueHvacPower.length / 6); // :no. of time slots * 10 minutes / 60 minutes = hours
		System.out.print("\nPower RMSE: " + RMSE + " Energy E: " + E);
		
		System.out.println();
		System.out.println();
		System.out.print("Predicted indoor temperature,");
		for(double temp : indoorTemperature){
			System.out.print(temp + ",");
		}
		System.out.println();
		System.out.print("True indoor temperature,");
		for(double temp : trueIndoorTemperature){
			System.out.print(temp + ",");
		}

		System.out.println();
		System.out.println();
		System.out.print("Predicted hvac mode,");
		for(String mode : hvacMode){
			System.out.print(mode + ",");
		}
		System.out.println();
		System.out.print("True hvac mode,");
		for(String mode : trueHvacMode){
			System.out.print(mode + ",");
		}
		System.out.println();
	}
		
	/*
	 * 
	 */
	private static void plotResults(double[] hvacPower, double[] trueHvacPower, double[] indoorTemperature, double[] trueIndoorTemperature, String[] hvacMode, String[] trueHvacMode) {
		XYSeriesCollection powerDataset = createDataset(hvacPower, "Predicted HVAC power", trueHvacPower, "True HVAC power");
		JFreeChart powerChart = createChart(powerDataset, "Power", "Time", "kW");
        
		XYSeriesCollection indoorTempDataset = createDataset(indoorTemperature, "Predicted indoor temperature", trueIndoorTemperature, "True indoor temperature");
        JFreeChart indoorTempChart = createChart(indoorTempDataset, "Temperature", "Time", "\u2103");
        
        XYSeriesCollection modeDataset = createDataset(hvacMode, "Predicted HVAC mode", trueHvacMode, "True HVAC mode");
        JFreeChart modeChart = createChart(modeDataset, "Mode", "Time", "# mode");
        
        createChartFrame(powerChart, indoorTempChart, modeChart);
    }

	/*
	 * 
	 */
	private static void createChartFrame(JFreeChart powerChart, JFreeChart indoorTempChart, JFreeChart modeChart) {
		ChartPanel powerChartPanel = new ChartPanel(powerChart);
        powerChartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        
        ChartPanel indoorTempChartPanel = new ChartPanel(indoorTempChart);
        indoorTempChartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        
        ChartPanel modeChartPanel = new ChartPanel(modeChart);
        modeChartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(powerChartPanel);
        panel.add(indoorTempChartPanel);
        panel.add(modeChartPanel);
        
        ApplicationFrame frame = new ApplicationFrame("HVAC");
        frame.setContentPane(panel);
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
	}

	/*
	 * 
	 */
	private static XYSeriesCollection createDataset(double[] predictedValues, String labelPred, double[] trueValues, String labelTrue) {
		 XYSeries seriesPred = new XYSeries(labelPred);
		 for(int i = 0; i < predictedValues.length; i++){
			 seriesPred.add(i, predictedValues[i]);
		 }
		 
		 XYSeries seriesTrue = new XYSeries(labelTrue);
		 for(int i = 0; i < trueValues.length; i++){
			 seriesTrue.add(i, trueValues[i]);
		 }

		 XYSeriesCollection dataset = new XYSeriesCollection();
	     dataset.addSeries(seriesPred);
	     dataset.addSeries(seriesTrue);
	     return dataset;
	}
	
	/*
	 * 
	 */
	private static XYSeriesCollection createDataset(String[] predictedValues, String labelPred, String[] trueValues, String labelTrue) {
		 XYSeries seriesPred = new XYSeries(labelPred);
		 for(int i = 0; i < predictedValues.length; i++){
			 seriesPred.add(i, Double.valueOf(predictedValues[i]));
		 }
		 
		 XYSeries seriesTrue = new XYSeries(labelTrue);
		 for(int i = 0; i < trueValues.length; i++){
			 seriesTrue.add(i, Double.valueOf(trueValues[i]));
		 }

		 XYSeriesCollection dataset = new XYSeriesCollection();
	     dataset.addSeries(seriesPred);
	     dataset.addSeries(seriesTrue);
	     return dataset;
	}
	
	/*
	 * 
	 */
    private static JFreeChart createChart(XYSeriesCollection dataset, String title, String xLabel, String yLabel) {
        
        // create the chart...
        JFreeChart chart = ChartFactory.createXYLineChart(
            title,      // chart title
            xLabel,                      // x axis label
            yLabel,                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.WHITE);
        
        // get a reference to the plot for further customisation...
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        
        double min = Double.POSITIVE_INFINITY;
        for(int i=0; i < dataset.getSeriesCount(); i++) {
        	if(min > dataset.getSeries(i).getMinY()){
        		min = dataset.getSeries(i).getMinY();
        	}
        }
        double max = Double.NEGATIVE_INFINITY;
        for(int i=0; i < dataset.getSeriesCount(); i++) {
        	if(max < dataset.getSeries(i).getMaxY()){
        		max = dataset.getSeries(i).getMaxY();
        	}
        }
        plot.getRangeAxis().setRange(min - 0.1*min, max + 0.1*max);
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        Stroke stroke = new BasicStroke(3.0f);
        for(int i=0; i < dataset.getSeriesCount(); i++) {
             renderer.setSeriesStroke(i, stroke);
             renderer.setSeriesShapesVisible(i, false);
        }
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getDomainAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
                
        return chart;
        
    }
}
