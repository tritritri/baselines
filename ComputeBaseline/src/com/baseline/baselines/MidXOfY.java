package com.baseline.baselines;

import java.util.ArrayList;
import java.util.Calendar;

import com.baseline.util.Util;
/**
 * Generic class for Middle X out of Y days baseline.
 * Such as: Mid4Of6.
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.27
 */
public class MidXOfY extends HighXOfY implements Baseline{
	public MidXOfY () {
		
		// as default, take Mid4Of6 configuration
		WEEKDAY_X = 4;
		WEEKDAY_Y = 6;
		
		WEEKEND_X = 4;
		WEEKEND_Y = 6;
	}

	
	protected void daysRemoval(Calendar targetCal, ArrayList<Double> avgs, ArrayList<Calendar> result) {
		
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
				

	}
}
