
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for reportDREventDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="reportDREventDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="drSignalListResultContainer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reportDREventDataResponse", propOrder = {
    "drSignalListResultContainer"
})
public class ReportDREventDataResponse {

    protected String drSignalListResultContainer;

    /**
     * Gets the value of the drSignalListResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrSignalListResultContainer() {
        return drSignalListResultContainer;
    }

    /**
     * Sets the value of the drSignalListResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrSignalListResultContainer(String value) {
        this.drSignalListResultContainer = value;
    }

}
