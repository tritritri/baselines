package ch.epfl.lsir.wattalyst.baseline.baselines;

/**
 * Implement Low5of10 baseline.
 * The low version of NYISO
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.05.27
 */
public class Low5Of10 extends LowXOfY implements Baseline{
	public Low5Of10 () {
		
		WEEKDAY_X = 5;
		WEEKDAY_Y = 10;
		
		WEEKEND_X = 5;
		WEEKEND_Y = 10;
	}

	
}
