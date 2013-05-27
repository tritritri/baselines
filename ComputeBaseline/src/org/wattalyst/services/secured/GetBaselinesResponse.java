
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBaselinesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBaselinesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="baselineListResultContainer" type="{http://secured.services.wattalyst.org/}baselineListResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBaselinesResponse", propOrder = {
    "baselineListResultContainer"
})
public class GetBaselinesResponse {

    protected BaselineListResultContainer baselineListResultContainer;

    /**
     * Gets the value of the baselineListResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link BaselineListResultContainer }
     *     
     */
    public BaselineListResultContainer getBaselineListResultContainer() {
        return baselineListResultContainer;
    }

    /**
     * Sets the value of the baselineListResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaselineListResultContainer }
     *     
     */
    public void setBaselineListResultContainer(BaselineListResultContainer value) {
        this.baselineListResultContainer = value;
    }

}
