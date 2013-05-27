
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for drSignalResultContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="drSignalResultContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secured.services.wattalyst.org/}wsResultContainer">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://secured.services.wattalyst.org/}drSignalDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "drSignalResultContainer", propOrder = {
    "result"
})
public class DrSignalResultContainer
    extends WsResultContainer
{

    protected DrSignalDto result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link DrSignalDto }
     *     
     */
    public DrSignalDto getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrSignalDto }
     *     
     */
    public void setResult(DrSignalDto value) {
        this.result = value;
    }

}
