
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDetailsByLocationAndDRSignalIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDetailsByLocationAndDRSignalIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="drSignalResultContainer" type="{http://secured.services.wattalyst.org/}drSignalResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDetailsByLocationAndDRSignalIdResponse", propOrder = {
    "drSignalResultContainer"
})
public class GetDetailsByLocationAndDRSignalIdResponse {

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
