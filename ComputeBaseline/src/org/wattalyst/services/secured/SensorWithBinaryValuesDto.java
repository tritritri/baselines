
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sensorWithBinaryValuesDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sensorWithBinaryValuesDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fullQualifiedName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sensorCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="values" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
@XmlType(name = "sensorWithBinaryValuesDto", propOrder = {
    "fullQualifiedName",
    "sensorCategory",
    "values",
    "unit"
})
public class SensorWithBinaryValuesDto {

    protected String fullQualifiedName;
    protected String sensorCategory;
    protected byte[] values;
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
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getValues() {
        return values;
    }

    /**
     * Sets the value of the values property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setValues(byte[] value) {
        this.values = value;
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
