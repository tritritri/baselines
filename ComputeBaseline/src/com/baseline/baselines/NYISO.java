package com.baseline.baselines;

public class NYISO extends HighXOfY implements Baseline{
	public NYISO () {
		
		WEEKDAY_X = 5;
		WEEKDAY_Y = 10;
		
		WEEKEND_X = 2;
		WEEKEND_Y = 3;
	}


}
