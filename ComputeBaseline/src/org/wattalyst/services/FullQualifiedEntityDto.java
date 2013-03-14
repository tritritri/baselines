
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fullQualifiedEntityDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fullQualifiedEntityDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.wattalyst.org/}baseDtoDomain">
 *       &lt;sequence>
 *         &lt;element name="fullQualifiedName" type="{http://services.wattalyst.org/}fullQualifiedNameDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fullQualifiedEntityDto", propOrder = {
    "fullQualifiedName"
})
@XmlSeeAlso({
    SensorDto.class,
    LocationDto.class
})
public class FullQualifiedEntityDto
    extends BaseDtoDomain
{

    protected FullQualifiedNameDto fullQualifiedName;

    /**
     * Gets the value of the fullQualifiedName property.
     * 
     * @return
     *     possible object is
     *     {@link FullQualifiedNameDto }
     *     
     */
    public FullQualifiedNameDto getFullQualifiedName() {
        return fullQualifiedName;
    }

    /**
     * Sets the value of the fullQualifiedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link FullQualifiedNameDto }
     *     
     */
    public void setFullQualifiedName(FullQualifiedNameDto value) {
        this.fullQualifiedName = value;
    }

}
