
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getLocationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getLocationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="locationResultContainer" type="{http://services.wattalyst.org/}locationResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLocationResponse", propOrder = {
    "locationResultContainer"
})
public class GetLocationResponse {

    protected LocationResultContainer locationResultContainer;

    /**
     * Gets the value of the locationResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link LocationResultContainer }
     *     
     */
    public LocationResultContainer getLocationResultContainer() {
        return locationResultContainer;
    }

    /**
     * Sets the value of the locationResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationResultContainer }
     *     
     */
    public void setLocationResultContainer(LocationResultContainer value) {
        this.locationResultContainer = value;
    }

}
