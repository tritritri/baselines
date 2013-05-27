
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBaselineDataAsCSVResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBaselineDataAsCSVResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stringResultContainer" type="{http://secured.services.wattalyst.org/}stringResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBaselineDataAsCSVResponse", propOrder = {
    "stringResultContainer"
})
public class GetBaselineDataAsCSVResponse {

    protected StringResultContainer stringResultContainer;

    /**
     * Gets the value of the stringResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link StringResultContainer }
     *     
     */
    public StringResultContainer getStringResultContainer() {
        return stringResultContainer;
    }

    /**
     * Sets the value of the stringResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringResultContainer }
     *     
     */
    public void setStringResultContainer(StringResultContainer value) {
        this.stringResultContainer = value;
    }

}
