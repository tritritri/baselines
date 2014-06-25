
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDRSignalPerformanceStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getDRSignalPerformanceStatusResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="drSignalPerfomaceStatusResultContainer" type="{http://secured.services.wattalyst.org/}drSignalPerfomaceStatusResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDRSignalPerformanceStatusResponse", propOrder = {
    "drSignalPerfomaceStatusResultContainer"
})
public class GetDRSignalPerformanceStatusResponse {

    protected DrSignalPerfomaceStatusResultContainer drSignalPerfomaceStatusResultContainer;

    /**
     * Gets the value of the drSignalPerfomaceStatusResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link DrSignalPerfomaceStatusResultContainer }
     *     
     */
    public DrSignalPerfomaceStatusResultContainer getDrSignalPerfomaceStatusResultContainer() {
        return drSignalPerfomaceStatusResultContainer;
    }

    /**
     * Sets the value of the drSignalPerfomaceStatusResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrSignalPerfomaceStatusResultContainer }
     *     
     */
    public void setDrSignalPerfomaceStatusResultContainer(DrSignalPerfomaceStatusResultContainer value) {
        this.drSignalPerfomaceStatusResultContainer = value;
    }

}
