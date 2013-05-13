
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDetailsByUsernameAndDRSignalIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDetailsByUsernameAndDRSignalIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="drSignalResultContainer" type="{http://services.wattalyst.org/}drSignalResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDetailsByUsernameAndDRSignalIdResponse", propOrder = {
    "drSignalResultContainer"
})
public class GetDetailsByUsernameAndDRSignalIdResponse {

    protected DrSignalResultContainer drSignalResultContainer;

    /**
     * Gets the value of the drSignalResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link DrSignalResultContainer }
     *     
     */
    public DrSignalResultContainer getDrSignalResultContainer() {
        return drSignalResultContainer;
    }

    /**
     * Sets the value of the drSignalResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrSignalResultContainer }
     *     
     */
    public void setDrSignalResultContainer(DrSignalResultContainer value) {
        this.drSignalResultContainer = value;
    }

}
