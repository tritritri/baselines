
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for entityDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="entityDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "entityDto", propOrder = {
    "entityId"
})
@XmlSeeAlso({
    ConstraintDto.class,
    KpiPerformanceDto.class,
    MetaDatumDto.class,
    QuantizationDto.class,
    FeedbackDto.class,
    AValueDto.class,
    ScheduleDto.class,
    TimeIntervalDto.class,
    RecipientContextDto.class,
    UnitDto.class,
    DrUseCaseDto.class,
    TaskDto.class,
    DrStatusDto.class,
    FullQualifiedEntityDto.class
})
public abstract class EntityDto {

    protected Long entityId;

    /**
     * Gets the value of the entityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEntityId() {
        return entityId;
    }

    /**
     * Sets the value of the entityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEntityId(Long value) {
        this.entityId = value;
    }

}
