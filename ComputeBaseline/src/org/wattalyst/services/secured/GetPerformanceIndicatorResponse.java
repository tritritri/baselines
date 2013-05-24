
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPerformanceIndicatorResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPerformanceIndicatorResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="piResultContainer" type="{http://secured.services.wattalyst.org/}piResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPerformanceIndicatorResponse", propOrder = {
    "piResultContainer"
})
public class GetPerformanceIndicatorResponse {

    protected PiResultContainer piResultContainer;

    /**
     * Gets the value of the piResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link PiResultContainer }
     *     
     */
    public PiResultContainer getPiResultContainer() {
        return piResultContainer;
    }

    /**
     * Sets the value of the piResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link PiResultContainer }
     *     
     */
    public void setPiResultContainer(PiResultContainer value) {
        this.piResultContainer = value;
    }

}
