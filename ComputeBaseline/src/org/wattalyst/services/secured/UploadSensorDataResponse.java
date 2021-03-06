
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for uploadSensorDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="uploadSensorDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="booleanResultContainer" type="{http://secured.services.wattalyst.org/}booleanResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadSensorDataResponse", propOrder = {
    "booleanResultContainer"
})
public class UploadSensorDataResponse {

    protected BooleanResultContainer booleanResultContainer;

    /**
     * Gets the value of the booleanResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanResultContainer }
     *     
     */
    public BooleanResultContainer getBooleanResultContainer() {
        return booleanResultContainer;
    }

    /**
     * Sets the value of the booleanResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanResultContainer }
     *     
     */
    public void setBooleanResultContainer(BooleanResultContainer value) {
        this.booleanResultContainer = value;
    }

}
