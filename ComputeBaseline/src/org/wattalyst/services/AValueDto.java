
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aValueDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aValueDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.wattalyst.org/}entityDto">
 *       &lt;sequence>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aValueDto", propOrder = {
    "timestamp"
})
@XmlSeeAlso({
    MissingValueDto.class,
    BooleanValueDto.class,
    TextValueDto.class,
    NumericValueDto.class
})
public abstract class AValueDto 
    extends EntityDto implements Comparable<AValueDto>
{

    protected Long timestamp;

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTimestamp(Long value) {
        this.timestamp = value;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(AValueDto value){
    	if(timestamp > value.getTimestamp()){
    		return 1;
    	}
    	else if(timestamp < value.getTimestamp()){
    		return -1;
    	}
    	else{
    		return 0;
    	}
    }
}
