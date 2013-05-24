
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for byteResultContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="byteResultContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secured.services.wattalyst.org/}wsResultContainer">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "byteResultContainer", propOrder = {
    "result"
})
public class ByteResultContainer
    extends WsResultContainer
{

    protected byte[] result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setResult(byte[] value) {
        this.result = value;
    }

}
