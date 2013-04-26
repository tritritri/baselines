package ch.epfl.lsir.wattalyst.hvac.model;

public class DataTuple {

	private long timestamp;
	private double extTemp;
	private double power;
	private String mode;
	private double setpointTemp;
	private double indTemp;

	/**
	 * 
	 * @return
	 */
	public long getTimestamp() {
		return timestamp;
	}
	
	/**
	 * 
	 * @param timestamp
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getExtTemp() {
		return extTemp;
	}
	
	/**
	 * 
	 * @param extTemp
	 */
	public void setExtTemp(double extTemp) {
		this.extTemp = extTemp;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getPower() {
		return power;
	}
	
	/**
	 * 
	 * @param power
	 */
	public void setPower(double power) {
		this.power = power;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMode() {
		return mode;
	}
	
	/**
	 * 
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getSetpointTemp() {
		return setpointTemp;
	}
	
	/**
	 * 
	 * @param setpointTemp
	 */
	public void setSetpointTemp(double setpointTemp) {
		this.setpointTemp = setpointTemp;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getIndTemp() {
		return indTemp;
	}
	
	/**
	 * 
	 * @param indTemp
	 */
	public void setIndTemp(double indTemp) {
		this.indTemp = indTemp;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return timestamp + ";" + extTemp + ";" + power + ";" + mode + ";" + 
				setpointTemp  + ";" + indTemp;
	}
	
}
