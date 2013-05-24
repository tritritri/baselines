
package org.wattalyst.services.secured;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SecuredDataAccess", targetNamespace = "http://secured.services.wattalyst.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SecuredDataAccess {


    /**
     * 
     * @param fullQualifiedName
     * @param authenticationToken
     * @return
     *     returns org.wattalyst.services.secured.LocationResultContainer
     */
    @WebMethod
    @WebResult(name = "locationResultContainer", targetNamespace = "")
    @RequestWrapper(localName = "getLocation", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetLocation")
    @ResponseWrapper(localName = "getLocationResponse", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetLocationResponse")
    @Action(input = "http://secured.services.wattalyst.org/SecuredDataAccess/getLocationRequest", output = "http://secured.services.wattalyst.org/SecuredDataAccess/getLocationResponse")
    public LocationResultContainer getLocation(
        @WebParam(name = "authenticationToken", targetNamespace = "")
        String authenticationToken,
        @WebParam(name = "fullQualifiedName", targetNamespace = "")
        String fullQualifiedName);

    /**
     * 
     * @param fullQualifiedName
     * @param authenticationToken
     * @return
     *     returns org.wattalyst.services.secured.SensorResultContainer
     */
    @WebMethod
    @WebResult(name = "sensorResultContainer", targetNamespace = "")
    @RequestWrapper(localName = "getSensor", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetSensor")
    @ResponseWrapper(localName = "getSensorResponse", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetSensorResponse")
    @Action(input = "http://secured.services.wattalyst.org/SecuredDataAccess/getSensorRequest", output = "http://secured.services.wattalyst.org/SecuredDataAccess/getSensorResponse")
    public SensorResultContainer getSensor(
        @WebParam(name = "authenticationToken", targetNamespace = "")
        String authenticationToken,
        @WebParam(name = "fullQualifiedName", targetNamespace = "")
        String fullQualifiedName);

    /**
     * 
     * @param fullQualifiedName
     * @param authenticationToken
     * @return
     *     returns org.wattalyst.services.secured.ValueResultContainer
     */
    @WebMethod
    @WebResult(name = "valueResultContainer", targetNamespace = "")
    @RequestWrapper(localName = "getLastValueForSensor", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetLastValueForSensor")
    @ResponseWrapper(localName = "getLastValueForSensorResponse", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetLastValueForSensorResponse")
    @Action(input = "http://secured.services.wattalyst.org/SecuredDataAccess/getLastValueForSensorRequest", output = "http://secured.services.wattalyst.org/SecuredDataAccess/getLastValueForSensorResponse")
    public ValueResultContainer getLastValueForSensor(
        @WebParam(name = "authenticationToken", targetNamespace = "")
        String authenticationToken,
        @WebParam(name = "fullQualifiedName", targetNamespace = "")
        String fullQualifiedName);

    /**
     * 
     * @param fullQualifiedName
     * @param authenticationToken
     * @return
     *     returns org.wattalyst.services.secured.ValueListResultContainer
     */
    @WebMethod
    @WebResult(name = "valueListResultContainer", targetNamespace = "")
    @RequestWrapper(localName = "getValuesForSensor", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetValuesForSensor")
    @ResponseWrapper(localName = "getValuesForSensorResponse", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetValuesForSensorResponse")
    @Action(input = "http://secured.services.wattalyst.org/SecuredDataAccess/getValuesForSensorRequest", output = "http://secured.services.wattalyst.org/SecuredDataAccess/getValuesForSensorResponse")
    public ValueListResultContainer getValuesForSensor(
        @WebParam(name = "authenticationToken", targetNamespace = "")
        String authenticationToken,
        @WebParam(name = "fullQualifiedName", targetNamespace = "")
        String fullQualifiedName);

    /**
     * 
     * @param fullQualifiedName
     * @param authenticationToken
     * @return
     *     returns org.wattalyst.services.secured.ByteResultContainer
     */
    @WebMethod
    @WebResult(name = "byteResultContainer", targetNamespace = "")
    @RequestWrapper(localName = "getExportedData", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetExportedData")
    @ResponseWrapper(localName = "getExportedDataResponse", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetExportedDataResponse")
    @Action(input = "http://secured.services.wattalyst.org/SecuredDataAccess/getExportedDataRequest", output = "http://secured.services.wattalyst.org/SecuredDataAccess/getExportedDataResponse")
    public ByteResultContainer getExportedData(
        @WebParam(name = "authenticationToken", targetNamespace = "")
        String authenticationToken,
        @WebParam(name = "fullQualifiedName", targetNamespace = "")
        String fullQualifiedName);

    /**
     * 
     * @param fullQualifiedName
     * @param authenticationToken
     * @return
     *     returns org.wattalyst.services.secured.SensorListResultContainer
     */
    @WebMethod
    @WebResult(name = "sensorListResultContainer", targetNamespace = "")
    @RequestWrapper(localName = "getLocationSensors", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetLocationSensors")
    @ResponseWrapper(localName = "getLocationSensorsResponse", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetLocationSensorsResponse")
    @Action(input = "http://secured.services.wattalyst.org/SecuredDataAccess/getLocationSensorsRequest", output = "http://secured.services.wattalyst.org/SecuredDataAccess/getLocationSensorsResponse")
    public SensorListResultContainer getLocationSensors(
        @WebParam(name = "authenticationToken", targetNamespace = "")
        String authenticationToken,
        @WebParam(name = "fullQualifiedName", targetNamespace = "")
        String fullQualifiedName);

    /**
     * 
     * @param fullQualifiedName
     * @param authenticationToken
     * @return
     *     returns org.wattalyst.services.secured.LocationListResultContainer
     */
    @WebMethod
    @WebResult(name = "sublocationListResultContainer", targetNamespace = "")
    @RequestWrapper(localName = "getSubLocations", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetSubLocations")
    @ResponseWrapper(localName = "getSubLocationsResponse", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetSubLocationsResponse")
    @Action(input = "http://secured.services.wattalyst.org/SecuredDataAccess/getSubLocationsRequest", output = "http://secured.services.wattalyst.org/SecuredDataAccess/getSubLocationsResponse")
    public LocationListResultContainer getSubLocations(
        @WebParam(name = "authenticationToken", targetNamespace = "")
        String authenticationToken,
        @WebParam(name = "fullQualifiedName", targetNamespace = "")
        String fullQualifiedName);

    /**
     * 
     * @param authenticationToken
     * @return
     *     returns org.wattalyst.services.secured.LocationListResultContainer
     */
    @WebMethod
    @WebResult(name = "locationListResultContainer", targetNamespace = "")
    @RequestWrapper(localName = "getAllLocations", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetAllLocations")
    @ResponseWrapper(localName = "getAllLocationsResponse", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetAllLocationsResponse")
    @Action(input = "http://secured.services.wattalyst.org/SecuredDataAccess/getAllLocationsRequest", output = "http://secured.services.wattalyst.org/SecuredDataAccess/getAllLocationsResponse")
    public LocationListResultContainer getAllLocations(
        @WebParam(name = "authenticationToken", targetNamespace = "")
        String authenticationToken);

    /**
     * 
     * @param sensorCategory
     * @param fullQualifiedName
     * @param authenticationToken
     * @return
     *     returns org.wattalyst.services.secured.SensorListResultContainer
     */
    @WebMethod
    @WebResult(name = "sensorListResultContainer", targetNamespace = "")
    @RequestWrapper(localName = "getLocationSensorsByCategory", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetLocationSensorsByCategory")
    @ResponseWrapper(localName = "getLocationSensorsByCategoryResponse", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetLocationSensorsByCategoryResponse")
    @Action(input = "http://secured.services.wattalyst.org/SecuredDataAccess/getLocationSensorsByCategoryRequest", output = "http://secured.services.wattalyst.org/SecuredDataAccess/getLocationSensorsByCategoryResponse")
    public SensorListResultContainer getLocationSensorsByCategory(
        @WebParam(name = "authenticationToken", targetNamespace = "")
        String authenticationToken,
        @WebParam(name = "fullQualifiedName", targetNamespace = "")
        String fullQualifiedName,
        @WebParam(name = "sensorCategory", targetNamespace = "")
        String sensorCategory);

    /**
     * 
     * @param annotationValue
     * @param annotationKey
     * @param fullQualifiedName
     * @param authenticationToken
     * @return
     *     returns org.wattalyst.services.secured.SensorListResultContainer
     */
    @WebMethod
    @WebResult(name = "sensorListResultContainer", targetNamespace = "")
    @RequestWrapper(localName = "getLocationSensorsByAnnotation", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetLocationSensorsByAnnotation")
    @ResponseWrapper(localName = "getLocationSensorsByAnnotationResponse", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetLocationSensorsByAnnotationResponse")
    @Action(input = "http://secured.services.wattalyst.org/SecuredDataAccess/getLocationSensorsByAnnotationRequest", output = "http://secured.services.wattalyst.org/SecuredDataAccess/getLocationSensorsByAnnotationResponse")
    public SensorListResultContainer getLocationSensorsByAnnotation(
        @WebParam(name = "authenticationToken", targetNamespace = "")
        String authenticationToken,
        @WebParam(name = "fullQualifiedName", targetNamespace = "")
        String fullQualifiedName,
        @WebParam(name = "annotationKey", targetNamespace = "")
        String annotationKey,
        @WebParam(name = "annotationValue", targetNamespace = "")
        String annotationValue);

    /**
     * 
     * @param start
     * @param fullQualifiedName
     * @param authenticationToken
     * @param end
     * @return
     *     returns org.wattalyst.services.secured.ValueListResultContainer
     */
    @WebMethod
    @WebResult(name = "valueListResultContainer", targetNamespace = "")
    @RequestWrapper(localName = "getValuesForSensorByRange", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetValuesForSensorByRange")
    @ResponseWrapper(localName = "getValuesForSensorByRangeResponse", targetNamespace = "http://secured.services.wattalyst.org/", className = "org.wattalyst.services.secured.GetValuesForSensorByRangeResponse")
    @Action(input = "http://secured.services.wattalyst.org/SecuredDataAccess/getValuesForSensorByRangeRequest", output = "http://secured.services.wattalyst.org/SecuredDataAccess/getValuesForSensorByRangeResponse")
    public ValueListResultContainer getValuesForSensorByRange(
        @WebParam(name = "authenticationToken", targetNamespace = "")
        String authenticationToken,
        @WebParam(name = "fullQualifiedName", targetNamespace = "")
        String fullQualifiedName,
        @WebParam(name = "start", targetNamespace = "")
        long start,
        @WebParam(name = "end", targetNamespace = "")
        long end);

}
