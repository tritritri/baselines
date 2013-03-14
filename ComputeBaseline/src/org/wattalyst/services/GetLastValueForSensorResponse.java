
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getLastValueForSensorResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getLastValueForSensorResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="valueResultContainer" type="{http://services.wattalyst.org/}valueResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLastValueForSensorResponse", propOrder = {
    "valueResultContainer"
})
public class GetLastValueForSensorResponse {

    protected ValueResultContainer valueResultContainer;

    /**
     * Gets the value of the valueResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link ValueResultContainer }
     *     
     */
    public ValueResultContainer getValueResultContainer() {
        return valueResultContainer;
    }

    /**
     * Sets the value of the valueResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueResultContainer }
     *     
     */
    public void setValueResultContainer(ValueResultContainer value) {
        this.valueResultContainer = value;
    }

}
