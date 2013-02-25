package com.baseline.baselines;

import java.util.ArrayList;
import java.util.Calendar;

import com.baseline.util.Util;

public class Mid4Of6 extends HighXOfY implements Baseline{
	public Mid4Of6 () {
		
		WEEKDAY_X = 4;
		WEEKDAY_Y = 6;
		
		WEEKEND_X = 4;
		WEEKEND_Y = 6;
	}

	
	protected void daysRemoval(Calendar targetCal, ArrayList<Double> avgs, ArrayList<Calendar> result) {
		/*
		System.out.println("Mid");
		for (Calendar r:result) {
			System.out.println(r.getTime() + "," + super.baseline.get(r.getTimeInMillis()));
		}
		*/
		
		int X = super.getXDOWType(Util.getDOWType(targetCal));

		while (result.size()>X){

			// remove the min
			
			// get the minimum avg
			int minIdx = Util.getMinIdx(avgs);
			avgs.remove(minIdx);
			// remove it from potential baseline's source
			result.remove(minIdx);
			
			if (result.size()>X) {
				// remove the max
				// get the maximum avg
				int maxIdx = Util.getMaxIdx(avgs);
				avgs.remove(maxIdx);
				// remove it from potential baseline's source
				result.remove(maxIdx);
				
			}
		}
				
		/*
		for (Calendar r:result) {
			System.out.println(r.getTime() );
		}
		*/

	}
}
