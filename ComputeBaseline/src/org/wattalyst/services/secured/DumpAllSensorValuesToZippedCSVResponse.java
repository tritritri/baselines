
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dumpAllSensorValuesToZippedCSVResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dumpAllSensorValuesToZippedCSVResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="downloadLink" type="{http://secured.services.wattalyst.org/}stringResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dumpAllSensorValuesToZippedCSVResponse", propOrder = {
    "downloadLink"
})
public class DumpAllSensorValuesToZippedCSVResponse {

    protected StringResultContainer downloadLink;

    /**
     * Gets the value of the downloadLink property.
     * 
     * @return
     *     possible object is
     *     {@link StringResultContainer }
     *     
     */
    public StringResultContainer getDownloadLink() {
        return downloadLink;
    }

    /**
     * Sets the value of the downloadLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringResultContainer }
     *     
     */
    public void setDownloadLink(StringResultContainer value) {
        this.downloadLink = value;
    }

}
