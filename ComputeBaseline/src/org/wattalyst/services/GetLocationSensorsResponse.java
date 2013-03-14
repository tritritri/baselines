
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getLocationSensorsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getLocationSensorsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sensorListResultContainer" type="{http://services.wattalyst.org/}sensorListResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLocationSensorsResponse", propOrder = {
    "sensorListResultContainer"
})
public class GetLocationSensorsResponse {

    protected SensorListResultContainer sensorListResultContainer;

    /**
     * Gets the value of the sensorListResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link SensorListResultContainer }
     *     
     */
    public SensorListResultContainer getSensorListResultContainer() {
        return sensorListResultContainer;
    }

    /**
     * Sets the value of the sensorListResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link SensorListResultContainer }
     *     
     */
    public void setSensorListResultContainer(SensorListResultContainer value) {
        this.sensorListResultContainer = value;
    }

}
