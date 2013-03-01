package ch.epfl.lsir.wattalyst.baseline.baselines;

/**
 * Implement Mid4Of6 baseline.
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.03.27
 */
public class Mid4Of6 extends MidXOfY implements Baseline{
	public Mid4Of6 () {
		
		WEEKDAY_X = 4;
		WEEKDAY_Y = 6;
		
		WEEKEND_X = 4;
		WEEKEND_Y = 6;
	}

	
}
