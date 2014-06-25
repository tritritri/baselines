
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getValuesForSensorsByRangeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getValuesForSensorsByRangeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sensorsWithValuesListResultContainer" type="{http://secured.services.wattalyst.org/}sensorsWithValueListResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getValuesForSensorsByRangeResponse", propOrder = {
    "sensorsWithValuesListResultContainer"
})
public class GetValuesForSensorsByRangeResponse {

    protected SensorsWithValueListResultContainer sensorsWithValuesListResultContainer;

    /**
     * Gets the value of the sensorsWithValuesListResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link SensorsWithValueListResultContainer }
     *     
     */
    public SensorsWithValueListResultContainer getSensorsWithValuesListResultContainer() {
        return sensorsWithValuesListResultContainer;
    }

    /**
     * Sets the value of the sensorsWithValuesListResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link SensorsWithValueListResultContainer }
     *     
     */
    public void setSensorsWithValuesListResultContainer(SensorsWithValueListResultContainer value) {
        this.sensorsWithValuesListResultContainer = value;
    }

}
