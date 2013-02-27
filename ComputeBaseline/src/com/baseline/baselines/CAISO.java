package com.baseline.baselines;
/**
 * Implement CAISO baseline.
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.23
 *
 */
public class CAISO extends HighXOfY implements Baseline{
	public CAISO () {
		
		WEEKDAY_X = 10;
		WEEKDAY_Y = 10;
		
		WEEKEND_X = 4;
		WEEKEND_Y = 4;
	}


}
