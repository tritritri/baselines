package ch.epfl.lsir.wattalyst.baseline.baselines;

/**
 * Implement Low10of20 baseline.
 *  
 * @author Tri Kurniawan Wijaya
 * @date   2013.05.27
 */
public class Low10Of20 extends LowXOfY implements Baseline{
	public Low10Of20 () {
		
		WEEKDAY_X = 10;
		WEEKDAY_Y = 20;
		
		WEEKEND_X = 10;
		WEEKEND_Y = 20;
	}

	
}
