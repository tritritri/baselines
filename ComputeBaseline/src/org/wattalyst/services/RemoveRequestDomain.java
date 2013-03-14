
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for removeRequestDomain complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="removeRequestDomain">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.wattalyst.org/}requestDomain">
 *       &lt;sequence>
 *         &lt;element name="removeEntityId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removeRequestDomain", propOrder = {
    "removeEntityId"
})
@XmlSeeAlso({
    RemoveMetaDatasFromSensorRequest.class,
    RemoveSensorsFromLocationRequest.class,
    RemoveValuesFromSensorRequest.class,
    RemoveSubLocationsFromLocationRequest.class
})
public abstract class RemoveRequestDomain
    extends RequestDomain
{

    protected Long removeEntityId;

    /**
     * Gets the value of the removeEntityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRemoveEntityId() {
        return removeEntityId;
    }

    /**
     * Sets the value of the removeEntityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRemoveEntityId(Long value) {
        this.removeEntityId = value;
    }

}
