
/**
 * DataAccessService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package ch.epfl.lsir.wattalyst.webserver.services;

/*
 *  DataAccessService java interface
 */

public interface DataAccessService {

	/**
	 * Auto generated method signature
	 * 
	 * @param getValuesForSensor0
	 */

	public ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorResponseE getValuesForSensor(

	ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorE getValuesForSensor0)
			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param getValuesForSensor0
	 */
	public void startgetValuesForSensor(

	ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorE getValuesForSensor0,

	final ch.epfl.lsir.wattalyst.webserver.services.DataAccessServiceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param getLocation2
	 */

	public ch.epfl.lsir.wattalyst.webserver.services.GetLocationResponseE getLocation(

	ch.epfl.lsir.wattalyst.webserver.services.GetLocationE getLocation2)
			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param getLocation2
	 */
	public void startgetLocation(

	ch.epfl.lsir.wattalyst.webserver.services.GetLocationE getLocation2,

	final ch.epfl.lsir.wattalyst.webserver.services.DataAccessServiceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param getSensor4
	 */

	public ch.epfl.lsir.wattalyst.webserver.services.GetSensorResponseE getSensor(

	ch.epfl.lsir.wattalyst.webserver.services.GetSensorE getSensor4)
			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param getSensor4
	 */
	public void startgetSensor(

	ch.epfl.lsir.wattalyst.webserver.services.GetSensorE getSensor4,

	final ch.epfl.lsir.wattalyst.webserver.services.DataAccessServiceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param getLastValueForSensor6
	 */

	public ch.epfl.lsir.wattalyst.webserver.services.GetLastValueForSensorResponseE getLastValueForSensor(

	ch.epfl.lsir.wattalyst.webserver.services.GetLastValueForSensorE getLastValueForSensor6)
			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param getLastValueForSensor6
	 */
	public void startgetLastValueForSensor(

	ch.epfl.lsir.wattalyst.webserver.services.GetLastValueForSensorE getLastValueForSensor6,

	final ch.epfl.lsir.wattalyst.webserver.services.DataAccessServiceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param getLocationSensorsByAnnotation8
	 */

	public ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsByAnnotationResponseE getLocationSensorsByAnnotation(

			ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsByAnnotationE getLocationSensorsByAnnotation8)
			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param getLocationSensorsByAnnotation8
	 */
	public void startgetLocationSensorsByAnnotation(

			ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsByAnnotationE getLocationSensorsByAnnotation8,

			final ch.epfl.lsir.wattalyst.webserver.services.DataAccessServiceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param getAllLocations10
	 */

	public ch.epfl.lsir.wattalyst.webserver.services.GetAllLocationsResponseE getAllLocations(

	ch.epfl.lsir.wattalyst.webserver.services.GetAllLocationsE getAllLocations10)
			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param getAllLocations10
	 */
	public void startgetAllLocations(

	ch.epfl.lsir.wattalyst.webserver.services.GetAllLocationsE getAllLocations10,

	final ch.epfl.lsir.wattalyst.webserver.services.DataAccessServiceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param getLocationSensors12
	 */

	public ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsResponseE getLocationSensors(

	ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsE getLocationSensors12)
			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param getLocationSensors12
	 */
	public void startgetLocationSensors(

	ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsE getLocationSensors12,

	final ch.epfl.lsir.wattalyst.webserver.services.DataAccessServiceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param getValuesForSensorByRange14
	 */

	public ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorByRangeResponseE getValuesForSensorByRange(

			ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorByRangeE getValuesForSensorByRange14)
			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param getValuesForSensorByRange14
	 */
	public void startgetValuesForSensorByRange(

			ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorByRangeE getValuesForSensorByRange14,

			final ch.epfl.lsir.wattalyst.webserver.services.DataAccessServiceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param getSubLocations16
	 */

	public ch.epfl.lsir.wattalyst.webserver.services.GetSubLocationsResponseE getSubLocations(

	ch.epfl.lsir.wattalyst.webserver.services.GetSubLocationsE getSubLocations16)
			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param getSubLocations16
	 */
	public void startgetSubLocations(

	ch.epfl.lsir.wattalyst.webserver.services.GetSubLocationsE getSubLocations16,

	final ch.epfl.lsir.wattalyst.webserver.services.DataAccessServiceCallbackHandler callback)

	throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature
	 * 
	 * @param getLocationSensorsByCategory18
	 */

	public ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsByCategoryResponseE getLocationSensorsByCategory(

			ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsByCategoryE getLocationSensorsByCategory18)
			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @param getLocationSensorsByCategory18
	 */
	public void startgetLocationSensorsByCategory(

			ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsByCategoryE getLocationSensorsByCategory18,

			final ch.epfl.lsir.wattalyst.webserver.services.DataAccessServiceCallbackHandler callback)

	throws java.rmi.RemoteException;

	//
}
