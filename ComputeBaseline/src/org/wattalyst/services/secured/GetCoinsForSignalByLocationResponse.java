
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getCoinsForSignalByLocationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCoinsForSignalByLocationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="integerResultContainer" type="{http://secured.services.wattalyst.org/}integerResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCoinsForSignalByLocationResponse", propOrder = {
    "integerResultContainer"
})
public class GetCoinsForSignalByLocationResponse {

    protected IntegerResultContainer integerResultContainer;

    /**
     * Gets the value of the integerResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link IntegerResultContainer }
     *     
     */
    public IntegerResultContainer getIntegerResultContainer() {
        return integerResultContainer;
    }

    /**
     * Sets the value of the integerResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegerResultContainer }
     *     
     */
    public void setIntegerResultContainer(IntegerResultContainer value) {
        this.integerResultContainer = value;
    }

}
