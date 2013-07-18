
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getNewDRSignalByLocationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getNewDRSignalByLocationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="drSignalListResultContainer" type="{http://secured.services.wattalyst.org/}drSignalListResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNewDRSignalByLocationResponse", propOrder = {
    "drSignalListResultContainer"
})
public class GetNewDRSignalByLocationResponse {

    protected DrSignalListResultContainer drSignalListResultContainer;

    /**
     * Gets the value of the drSignalListResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link DrSignalListResultContainer }
     *     
     */
    public DrSignalListResultContainer getDrSignalListResultContainer() {
        return drSignalListResultContainer;
    }

    /**
     * Sets the value of the drSignalListResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrSignalListResultContainer }
     *     
     */
    public void setDrSignalListResultContainer(DrSignalListResultContainer value) {
        this.drSignalListResultContainer = value;
    }

}
