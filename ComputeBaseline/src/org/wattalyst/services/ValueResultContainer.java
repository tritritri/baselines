
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for valueResultContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="valueResultContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.wattalyst.org/}wsResultContainer">
 *       &lt;sequence>
 *         &lt;element name="value" type="{http://services.wattalyst.org/}aValueDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "valueResultContainer", propOrder = {
    "value"
})
public class ValueResultContainer
    extends WsResultContainer
{

    protected AValueDto value;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link AValueDto }
     *     
     */
    public AValueDto getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link AValueDto }
     *     
     */
    public void setValue(AValueDto value) {
        this.value = value;
    }

}
