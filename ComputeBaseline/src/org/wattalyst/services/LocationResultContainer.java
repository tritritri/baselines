
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for locationResultContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="locationResultContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.wattalyst.org/}wsResultContainer">
 *       &lt;sequence>
 *         &lt;element name="location" type="{http://services.wattalyst.org/}locationDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "locationResultContainer", propOrder = {
    "location"
})
public class LocationResultContainer
    extends WsResultContainer
{

    protected LocationDto location;

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link LocationDto }
     *     
     */
    public LocationDto getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationDto }
     *     
     */
    public void setLocation(LocationDto value) {
        this.location = value;
    }

}
