
package org.wattalyst.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sensorDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sensorDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.wattalyst.org/}fullQualifiedEntityDto">
 *       &lt;sequence>
 *         &lt;element name="quantization" type="{http://services.wattalyst.org/}quantizationDto" minOccurs="0"/>
 *         &lt;element name="category" type="{http://services.wattalyst.org/}category" minOccurs="0"/>
 *         &lt;element name="maximum" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="minimum" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="firstValueTimestamp" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="lastValueTimestamp" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unit" type="{http://services.wattalyst.org/}unitDto" minOccurs="0"/>
 *         &lt;element name="timezoneOffset" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="metaData" type="{http://services.wattalyst.org/}metaDatumDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sensorDto", propOrder = {
    "quantization",
    "category",
    "maximum",
    "minimum",
    "firstValueTimestamp",
    "lastValueTimestamp",
    "comment",
    "unit",
    "timezoneOffset",
    "metaData"
})
@XmlSeeAlso({
    BaselineDto.class
})
public class SensorDto
    extends FullQualifiedEntityDto
{

    protected QuantizationDto quantization;
    protected Category category;
    protected Double maximum;
    protected Double minimum;
    protected Long firstValueTimestamp;
    protected Long lastValueTimestamp;
    protected String comment;
    protected UnitDto unit;
    protected Double timezoneOffset;
    protected List<MetaDatumDto> metaData;

    /**
     * Gets the value of the quantization property.
     * 
     * @return
     *     possible object is
     *     {@link QuantizationDto }
     *     
     */
    public QuantizationDto getQuantization() {
        return quantization;
    }

    /**
     * Sets the value of the quantization property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantizationDto }
     *     
     */
    public void setQuantization(QuantizationDto value) {
        this.quantization = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link Category }
     *     
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link Category }
     *     
     */
    public void setCategory(Category value) {
        this.category = value;
    }

    /**
     * Gets the value of the maximum property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaximum() {
        return maximum;
    }

    /**
     * Sets the value of the maximum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaximum(Double value) {
        this.maximum = value;
    }

    /**
     * Gets the value of the minimum property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinimum() {
        return minimum;
    }

    /**
     * Sets the value of the minimum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinimum(Double value) {
        this.minimum = value;
    }

    /**
     * Gets the value of the firstValueTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFirstValueTimestamp() {
        return firstValueTimestamp;
    }

    /**
     * Sets the value of the firstValueTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFirstValueTimestamp(Long value) {
        this.firstValueTimestamp = value;
    }

    /**
     * Gets the value of the lastValueTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLastValueTimestamp() {
        return lastValueTimestamp;
    }

    /**
     * Sets the value of the lastValueTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLastValueTimestamp(Long value) {
        this.lastValueTimestamp = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link UnitDto }
     *     
     */
    public UnitDto getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitDto }
     *     
     */
    public void setUnit(UnitDto value) {
        this.unit = value;
    }

    /**
     * Gets the value of the timezoneOffset property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTimezoneOffset() {
        return timezoneOffset;
    }

    /**
     * Sets the value of the timezoneOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTimezoneOffset(Double value) {
        this.timezoneOffset = value;
    }

    /**
     * Gets the value of the metaData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metaData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetaData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetaDatumDto }
     * 
     * 
     */
    public List<MetaDatumDto> getMetaData() {
        if (metaData == null) {
            metaData = new ArrayList<MetaDatumDto>();
        }
        return this.metaData;
    }

}
