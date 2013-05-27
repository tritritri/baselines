package ch.epfl.lsir.wattalyst.baseline.baselines;

import java.util.ArrayList;
import java.util.Calendar;

import ch.epfl.lsir.wattalyst.baseline.util.Util;
/**
 * Generic class for Middle X out of Y days baseline.
 * Such as: Mid4Of6.
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.27
 */
public class LowXOfY extends HighXOfY implements Baseline{
	public LowXOfY () {

		WEEKDAY_X = 4;
		WEEKDAY_Y = 5;
		
		WEEKEND_X = 4;
		WEEKEND_Y = 5;
	}

	
	protected void daysRemoval(Calendar targetCal, ArrayList<Double> avgs, ArrayList<Calendar> result) {
		
		int X = super.getXDOWType(Util.getDOWType(targetCal));
		while (result.size()>X){

			// remove the max, get the maximum avg
			int maxIdx = Util.getMaxIdx(avgs);
			avgs.remove(maxIdx);
			// remove it from potential baseline's source
			result.remove(maxIdx);
			
		}

	}
}
