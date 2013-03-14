
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sensorResultContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sensorResultContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.wattalyst.org/}wsResultContainer">
 *       &lt;sequence>
 *         &lt;element name="sensor" type="{http://services.wattalyst.org/}sensorDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sensorResultContainer", propOrder = {
    "sensor"
})
public class SensorResultContainer
    extends WsResultContainer
{

    protected SensorDto sensor;

    /**
     * Gets the value of the sensor property.
     * 
     * @return
     *     possible object is
     *     {@link SensorDto }
     *     
     */
    public SensorDto getSensor() {
        return sensor;
    }

    /**
     * Sets the value of the sensor property.
     * 
     * @param value
     *     allowed object is
     *     {@link SensorDto }
     *     
     */
    public void setSensor(SensorDto value) {
        this.sensor = value;
    }

}
