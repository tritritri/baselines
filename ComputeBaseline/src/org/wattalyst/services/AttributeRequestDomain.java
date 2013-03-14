
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for attributeRequestDomain complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="attributeRequestDomain">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.wattalyst.org/}requestDomain">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attributeRequestDomain", propOrder = {
    "result"
})
@XmlSeeAlso({
    SetTypeInLocationRequest.class,
    SetValueInNumericValueRequest.class,
    SetDecoratedSensorInSensorRequest.class,
    SetValueInTextValueRequest.class,
    SetIdentifierInFullQualifiedNameRequest.class,
    SetValueInBooleanValueRequest.class,
    SetAddressInLocationRequest.class,
    SetKeyInMetaDatumRequest.class,
    SetValueInMetaDatumRequest.class,
    SetTimestampInAValueRequest.class
})
public abstract class AttributeRequestDomain
    extends RequestDomain
{

    protected Object result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setResult(Object value) {
        this.result = value;
    }

}
