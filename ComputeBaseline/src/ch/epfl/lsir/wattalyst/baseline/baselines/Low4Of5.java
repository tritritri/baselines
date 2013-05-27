package ch.epfl.lsir.wattalyst.baseline.baselines;

/**
 * Implement Low4Of5 baseline.
 * The low version of PJMEco.
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.05.27
 */
public class Low4Of5 extends LowXOfY implements Baseline{
	public Low4Of5 () {
		
		WEEKDAY_X = 4;
		WEEKDAY_Y = 5;
		
		WEEKEND_X = 4;
		WEEKEND_Y = 5;
	}

	
}
