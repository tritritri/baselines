
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSubLocationsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSubLocationsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sublocationListResultContainer" type="{http://secured.services.wattalyst.org/}locationListResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSubLocationsResponse", propOrder = {
    "sublocationListResultContainer"
})
public class GetSubLocationsResponse {

    protected LocationListResultContainer sublocationListResultContainer;

    /**
     * Gets the value of the sublocationListResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link LocationListResultContainer }
     *     
     */
    public LocationListResultContainer getSublocationListResultContainer() {
        return sublocationListResultContainer;
    }

    /**
     * Sets the value of the sublocationListResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationListResultContainer }
     *     
     */
    public void setSublocationListResultContainer(LocationListResultContainer value) {
        this.sublocationListResultContainer = value;
    }

}
