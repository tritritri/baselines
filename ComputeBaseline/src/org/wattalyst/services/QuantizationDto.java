
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for quantizationDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="quantizationDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.wattalyst.org/}entityDto">
 *       &lt;sequence>
 *         &lt;element name="base" type="{http://services.wattalyst.org/}quantizationBase" minOccurs="0"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "quantizationDto", propOrder = {
    "base",
    "value"
})
public class QuantizationDto
    extends EntityDto
{

    protected QuantizationBase base;
    protected Integer value;

    /**
     * Gets the value of the base property.
     * 
     * @return
     *     possible object is
     *     {@link QuantizationBase }
     *     
     */
    public QuantizationBase getBase() {
        return base;
    }

    /**
     * Sets the value of the base property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantizationBase }
     *     
     */
    public void setBase(QuantizationBase value) {
        this.base = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setValue(Integer value) {
        this.value = value;
    }

}
