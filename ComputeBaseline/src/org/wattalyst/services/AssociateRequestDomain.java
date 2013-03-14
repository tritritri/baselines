
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for associateRequestDomain complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="associateRequestDomain">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.wattalyst.org/}requestDomain">
 *       &lt;sequence>
 *         &lt;element name="targetEntityId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "associateRequestDomain", propOrder = {
    "targetEntityId"
})
@XmlSeeAlso({
    AddSensorsToLocationRequest.class,
    AddValuesToSensorRequest.class,
    AddSubLocationsToLocationRequest.class,
    AddMetaDatasToSensorRequest.class,
    SetFullQualifiedNameInFullQualifiedEntityRequest.class
})
public abstract class AssociateRequestDomain
    extends RequestDomain
{

    protected Long targetEntityId;

    /**
     * Gets the value of the targetEntityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTargetEntityId() {
        return targetEntityId;
    }

    /**
     * Sets the value of the targetEntityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTargetEntityId(Long value) {
        this.targetEntityId = value;
    }

}
