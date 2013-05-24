
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getExportedDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getExportedDataResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="byteResultContainer" type="{http://secured.services.wattalyst.org/}byteResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getExportedDataResponse", propOrder = {
    "byteResultContainer"
})
public class GetExportedDataResponse {

    protected ByteResultContainer byteResultContainer;

    /**
     * Gets the value of the byteResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link ByteResultContainer }
     *     
     */
    public ByteResultContainer getByteResultContainer() {
        return byteResultContainer;
    }

    /**
     * Sets the value of the byteResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link ByteResultContainer }
     *     
     */
    public void setByteResultContainer(ByteResultContainer value) {
        this.byteResultContainer = value;
    }

}
