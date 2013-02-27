package com.baseline.baselines;

/**
 * Implement PJM Economic baseline.
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.23
 *
 */
public class PJMEco extends HighXOfY implements Baseline{
	public PJMEco () {
		
		WEEKDAY_X = 4;
		WEEKDAY_Y = 5;
		
		WEEKEND_X = 2;
		WEEKEND_Y = 3;
	}
}
