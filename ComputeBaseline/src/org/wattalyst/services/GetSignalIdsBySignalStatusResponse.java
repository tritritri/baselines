
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSignalIdsBySignalStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSignalIdsBySignalStatusResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stringListResultContainer" type="{http://services.wattalyst.org/}stringListResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSignalIdsBySignalStatusResponse", propOrder = {
    "stringListResultContainer"
})
public class GetSignalIdsBySignalStatusResponse {

    protected StringListResultContainer stringListResultContainer;

    /**
     * Gets the value of the stringListResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link StringListResultContainer }
     *     
     */
    public StringListResultContainer getStringListResultContainer() {
        return stringListResultContainer;
    }

    /**
     * Sets the value of the stringListResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringListResultContainer }
     *     
     */
    public void setStringListResultContainer(StringListResultContainer value) {
        this.stringListResultContainer = value;
    }

}
