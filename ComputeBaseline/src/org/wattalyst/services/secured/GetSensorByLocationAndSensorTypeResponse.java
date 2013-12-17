
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSensorByLocationAndSensorTypeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSensorByLocationAndSensorTypeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sensorResultContainer" type="{http://secured.services.wattalyst.org/}sensorResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSensorByLocationAndSensorTypeResponse", propOrder = {
    "sensorResultContainer"
})
public class GetSensorByLocationAndSensorTypeResponse {

    protected SensorResultContainer sensorResultContainer;

    /**
     * Gets the value of the sensorResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link SensorResultContainer }
     *     
     */
    public SensorResultContainer getSensorResultContainer() {
        return sensorResultContainer;
    }

    /**
     * Sets the value of the sensorResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link SensorResultContainer }
     *     
     */
    public void setSensorResultContainer(SensorResultContainer value) {
        this.sensorResultContainer = value;
    }

}
