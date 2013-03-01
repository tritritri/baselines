package ch.epfl.lsir.wattalyst.baseline.baselines;
/**
 * Implement NYISO baseline.
 * 
 * @author Tri Kurniawan Wijaya
 * @date   2013.02.23
 */
public class NYISO extends HighXOfY implements Baseline{
	public NYISO () {
		
		WEEKDAY_X = 5;
		WEEKDAY_Y = 10;
		
		WEEKEND_X = 2;
		WEEKEND_Y = 3;
	}


}
