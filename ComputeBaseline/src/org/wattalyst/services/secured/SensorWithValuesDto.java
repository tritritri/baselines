
package org.wattalyst.services.secured;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sensorWithValuesDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sensorWithValuesDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fullQualifiedName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sensorCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="values" type="{http://secured.services.wattalyst.org/}aValueDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="unit" type="{http://secured.services.wattalyst.org/}unitDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sensorWithValuesDto", propOrder = {
    "fullQualifiedName",
    "sensorCategory",
    "values",
    "unit"
})
public class SensorWithValuesDto {

    protected String fullQualifiedName;
    protected String sensorCategory;
    @XmlElement(nillable = true)
    protected List<AValueDto> values;
    protected UnitDto unit;

    /**
     * Gets the value of the fullQualifiedName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullQualifiedName() {
        return fullQualifiedName;
    }

    /**
     * Sets the value of the fullQualifiedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullQualifiedName(String value) {
        this.fullQualifiedName = value;
    }

    /**
     * Gets the value of the sensorCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSensorCategory() {
        return sensorCategory;
    }

    /**
     * Sets the value of the sensorCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSensorCategory(String value) {
        this.sensorCategory = value;
    }

    /**
     * Gets the value of the values property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the values property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AValueDto }
     * 
     * 
     */
    public List<AValueDto> getValues() {
        if (values == null) {
            values = new ArrayList<AValueDto>();
        }
        return this.values;
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

}
