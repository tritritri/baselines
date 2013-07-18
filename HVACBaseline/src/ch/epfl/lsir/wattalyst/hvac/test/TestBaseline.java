package ch.epfl.lsir.wattalyst.hvac.test;

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

import ch.epfl.lsir.wattalyst.hvac.baseline.Baseline;
import ch.epfl.lsir.wattalyst.hvac.baseline.BaselineFactory;

import weka.core.Instances;

public class TestBaseline {

	/**
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		
		Baseline baseline = BaselineFactory.createBaseline("./classifier/model/R1D-power-SMOReg.model", "./classifier/model/R1D-ind_temp-SMOReg.model");
		
		// Read the test sets
		BufferedReader indoorTemperatureReader = new BufferedReader(new FileReader("./arff/R1D-ind_temp-test-2.arff"));
		BufferedReader powerReader = new BufferedReader(new FileReader("./arff/R1D-power-test-2.arff"));
		
		Instances indoorTemperatureTestSet = new Instances(indoorTemperatureReader);
		indoorTemperatureReader.close();
		Instances powerTestSet = new Instances(powerReader);
		powerReader.close();
		
		int timeWindow = powerTestSet.numInstances(); 
		
		double[] ext_temp_future = new double[timeWindow];
		double[] set_temp_future = new double[timeWindow];
		
		double[] set_temp = new double[6];
		double[] ind_temp = new double[6]; 
		double[] ext_temp = new double[6]; 
		String[] mode = new String[6]; 
		double[] power = new double[6];
				
		double[] truePower = new double[timeWindow];
		double[] trueIndoorTemperature = new double[timeWindow];
		String[] trueMode = new String[timeWindow];
		
		for(int i = 0; i < set_temp.length; i++){
			set_temp[i] = powerTestSet.instance(0).value(powerTestSet.attribute("set_temp_t_" + (set_temp.length-i) ));
			ind_temp[i] = powerTestSet.instance(0).value(powerTestSet.attribute("ind_temp_t_" + (ind_temp.length-i) ));
			ext_temp[i] = powerTestSet.instance(0).value(powerTestSet.attribute("ext_temp_t_" + (ext_temp.length-i) ));
			mode[i] = powerTestSet.attribute("mode_t_" + (mode.length-i)).value( (int) powerTestSet.instance(0).value(powerTestSet.attribute("mode_t_" + (mode.length-i) )));
			power[i] = powerTestSet.instance(0).value(powerTestSet.attribute("power_t_" + (power.length-i) ));
		}
		
		for(int i = 0; i < timeWindow; i++){
			truePower[i] = powerTestSet.instance(i).value(powerTestSet.attribute("power_t"));
			trueIndoorTemperature[i] = indoorTemperatureTestSet.instance(i).value(indoorTemperatureTestSet.attribute("ind_temp_t"));
			trueMode[i] = "30";
		}
		
		for(int i = 0; i < timeWindow-1; i++){
			ext_temp_future[i] = powerTestSet.instance(1 + i).value(powerTestSet.attribute("ext_temp_t_1"));
			set_temp_future[i] = 24;
		}
		ext_temp_future[ext_temp_future.length-1] = 0;
		set_temp_future[set_temp_future.length-1] = 0;
		
		Object[] result = baseline.compute(ext_temp_future, set_temp_future, set_temp, ind_temp, ext_temp, mode, power);
		double[] resultPower = (double[]) result[0];
		double[] resultIndoorTemperature = (double[]) result[1];
		String[] resultMode = (String[]) result[2];

		plotResults(resultPower, truePower, resultIndoorTemperature, trueIndoorTemperature, resultMode, trueMode);
		printResults(resultPower, truePower, resultIndoorTemperature, trueIndoorTemperature, resultMode, trueMode);
	}
		
	/*
	 * 
	 */
	private static void printResults(double[] power, double[] truePower, double[] indoorTemperature, double[] trueIndoorTemperature, String[] mode, String[] trueMode){
		System.out.print("Predicted hvac power,");
		for(double p : power){
			System.out.print(p + ",");
		}
		System.out.println();
		System.out.print("True hvac power,");
		for(double p : truePower){
			System.out.print(p + ",");
		}
		
		double RMSE = 0;
		double E = 0;
		for(int i = 0; i < truePower.length; i++){
			RMSE = RMSE + Math.pow(power[i] - truePower[i], 2);
			E = E + power[i] - truePower[i];
		}
		RMSE = RMSE/truePower.length;
		RMSE = Math.sqrt(RMSE);
		E = E / (truePower.length / 6); // :no. of time slots * 10 minutes / 60 minutes = hours
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
		for(String m : mode){
			System.out.print(m + ",");
		}
		System.out.println();
		System.out.print("True hvac mode,");
		for(String m : trueMode){
			System.out.print(m + ",");
		}
		System.out.println();
	}
		
	/*
	 * 
	 */
	private static void plotResults(double[] hvacPower, double[] trueHvacPower, double[] indoorTemperature, double[] trueIndoorTemperature, String[] hvacMode, String[] trueHvacMode) {
		XYSeriesCollection powerDataset = createDataset(hvacPower, "Predicted power", trueHvacPower, "True power");
		JFreeChart powerChart = createChart(powerDataset, "Power", "Time", "kW");
        
		XYSeriesCollection indoorTempDataset = createDataset(indoorTemperature, "Predicted indoor temperature", trueIndoorTemperature, "True indoor temperature");
        JFreeChart indoorTempChart = createChart(indoorTempDataset, "Temperature", "Time", "\u2103");
        
        XYSeriesCollection modeDataset = createDataset(hvacMode, "Predicted mode", trueHvacMode, "True mode");
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
