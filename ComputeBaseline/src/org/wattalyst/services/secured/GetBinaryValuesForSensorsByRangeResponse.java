
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBinaryValuesForSensorsByRangeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBinaryValuesForSensorsByRangeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sensorsWithBinaryValuesListResultContainer" type="{http://secured.services.wattalyst.org/}sensorsWithBinaryValuesResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBinaryValuesForSensorsByRangeResponse", propOrder = {
    "sensorsWithBinaryValuesListResultContainer"
})
public class GetBinaryValuesForSensorsByRangeResponse {

    protected SensorsWithBinaryValuesResultContainer sensorsWithBinaryValuesListResultContainer;

    /**
     * Gets the value of the sensorsWithBinaryValuesListResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link SensorsWithBinaryValuesResultContainer }
     *     
     */
    public SensorsWithBinaryValuesResultContainer getSensorsWithBinaryValuesListResultContainer() {
        return sensorsWithBinaryValuesListResultContainer;
    }

    /**
     * Sets the value of the sensorsWithBinaryValuesListResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link SensorsWithBinaryValuesResultContainer }
     *     
     */
    public void setSensorsWithBinaryValuesListResultContainer(SensorsWithBinaryValuesResultContainer value) {
        this.sensorsWithBinaryValuesListResultContainer = value;
    }

}
