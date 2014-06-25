
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSensorValuesAsCsvZipFileResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSensorValuesAsCsvZipFileResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="zippedByteConainer" type="{http://secured.services.wattalyst.org/}stringResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSensorValuesAsCsvZipFileResponse", propOrder = {
    "zippedByteConainer"
})
public class GetSensorValuesAsCsvZipFileResponse {

    protected StringResultContainer zippedByteConainer;

    /**
     * Gets the value of the zippedByteConainer property.
     * 
     * @return
     *     possible object is
     *     {@link StringResultContainer }
     *     
     */
    public StringResultContainer getZippedByteConainer() {
        return zippedByteConainer;
    }

    /**
     * Sets the value of the zippedByteConainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringResultContainer }
     *     
     */
    public void setZippedByteConainer(StringResultContainer value) {
        this.zippedByteConainer = value;
    }

}
