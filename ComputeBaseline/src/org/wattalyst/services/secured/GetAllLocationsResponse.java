
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAllLocationsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAllLocationsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="locationListResultContainer" type="{http://secured.services.wattalyst.org/}locationListResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllLocationsResponse", propOrder = {
    "locationListResultContainer"
})
public class GetAllLocationsResponse {

    protected LocationListResultContainer locationListResultContainer;

    /**
     * Gets the value of the locationListResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link LocationListResultContainer }
     *     
     */
    public LocationListResultContainer getLocationListResultContainer() {
        return locationListResultContainer;
    }

    /**
     * Sets the value of the locationListResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationListResultContainer }
     *     
     */
    public void setLocationListResultContainer(LocationListResultContainer value) {
        this.locationListResultContainer = value;
    }

}
