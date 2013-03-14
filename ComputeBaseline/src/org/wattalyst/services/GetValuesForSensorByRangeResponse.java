
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getValuesForSensorByRangeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getValuesForSensorByRangeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="valueListResultContainer" type="{http://services.wattalyst.org/}valueListResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getValuesForSensorByRangeResponse", propOrder = {
    "valueListResultContainer"
})
public class GetValuesForSensorByRangeResponse {

    protected ValueListResultContainer valueListResultContainer;

    /**
     * Gets the value of the valueListResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link ValueListResultContainer }
     *     
     */
    public ValueListResultContainer getValueListResultContainer() {
        return valueListResultContainer;
    }

    /**
     * Sets the value of the valueListResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueListResultContainer }
     *     
     */
    public void setValueListResultContainer(ValueListResultContainer value) {
        this.valueListResultContainer = value;
    }

}
