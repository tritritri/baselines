/**
 * DataAccessServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package ch.epfl.lsir.wattalyst.webserver.services;

/**
 * DataAccessServiceCallbackHandler Callback class, Users can extend this class
 * and implement their own receiveResult and receiveError methods.
 */
public abstract class DataAccessServiceCallbackHandler {

	protected Object clientData;

	/**
	 * User can pass in any object that needs to be accessed once the
	 * NonBlocking Web service call is finished and appropriate method of this
	 * CallBack is called.
	 * 
	 * @param clientData
	 *            Object mechanism by which the user can pass in user data that
	 *            will be avilable at the time this callback is called.
	 */
	public DataAccessServiceCallbackHandler(Object clientData) {
		this.clientData = clientData;
	}

	/**
	 * Please use this constructor if you don't want to set any clientData
	 */
	public DataAccessServiceCallbackHandler() {
		this.clientData = null;
	}

	/**
	 * Get the client data
	 */

	public Object getClientData() {
		return clientData;
	}

	/**
	 * auto generated Axis2 call back method for getValuesForSensor method
	 * override this method for handling normal response from getValuesForSensor
	 * operation
	 */
	public void receiveResultgetValuesForSensor(
			ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorResponseE result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getValuesForSensor operation
	 */
	public void receiveErrorgetValuesForSensor(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getLocation method override
	 * this method for handling normal response from getLocation operation
	 */
	public void receiveResultgetLocation(
			ch.epfl.lsir.wattalyst.webserver.services.GetLocationResponseE result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getLocation operation
	 */
	public void receiveErrorgetLocation(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getSensor method override this
	 * method for handling normal response from getSensor operation
	 */
	public void receiveResultgetSensor(
			ch.epfl.lsir.wattalyst.webserver.services.GetSensorResponseE result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getSensor operation
	 */
	public void receiveErrorgetSensor(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getLastValueForSensor method
	 * override this method for handling normal response from
	 * getLastValueForSensor operation
	 */
	public void receiveResultgetLastValueForSensor(
			ch.epfl.lsir.wattalyst.webserver.services.GetLastValueForSensorResponseE result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getLastValueForSensor operation
	 */
	public void receiveErrorgetLastValueForSensor(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getLocationSensorsByAnnotation
	 * method override this method for handling normal response from
	 * getLocationSensorsByAnnotation operation
	 */
	public void receiveResultgetLocationSensorsByAnnotation(
			ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsByAnnotationResponseE result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getLocationSensorsByAnnotation operation
	 */
	public void receiveErrorgetLocationSensorsByAnnotation(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getAllLocations method override
	 * this method for handling normal response from getAllLocations operation
	 */
	public void receiveResultgetAllLocations(
			ch.epfl.lsir.wattalyst.webserver.services.GetAllLocationsResponseE result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getAllLocations operation
	 */
	public void receiveErrorgetAllLocations(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getLocationSensors method
	 * override this method for handling normal response from getLocationSensors
	 * operation
	 */
	public void receiveResultgetLocationSensors(
			ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsResponseE result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getLocationSensors operation
	 */
	public void receiveErrorgetLocationSensors(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getValuesForSensorByRange
	 * method override this method for handling normal response from
	 * getValuesForSensorByRange operation
	 */
	public void receiveResultgetValuesForSensorByRange(
			ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorByRangeResponseE result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getValuesForSensorByRange operation
	 */
	public void receiveErrorgetValuesForSensorByRange(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getSubLocations method override
	 * this method for handling normal response from getSubLocations operation
	 */
	public void receiveResultgetSubLocations(
			ch.epfl.lsir.wattalyst.webserver.services.GetSubLocationsResponseE result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getSubLocations operation
	 */
	public void receiveErrorgetSubLocations(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getLocationSensorsByCategory
	 * method override this method for handling normal response from
	 * getLocationSensorsByCategory operation
	 */
	public void receiveResultgetLocationSensorsByCategory(
			ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsByCategoryResponseE result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getLocationSensorsByCategory operation
	 */
	public void receiveErrorgetLocationSensorsByCategory(java.lang.Exception e) {
	}

}
