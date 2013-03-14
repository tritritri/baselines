
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
 *     &lt;extension base="{http://services.wattalyst.org/}baseDtoDomain">
 *       &lt;sequence>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
public class AValueDto
    extends BaseDtoDomain implements Comparable<AValueDto>
{

    protected long timestamp;

    /**
     * Gets the value of the timestamp property.
     * 
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     */
    public void setTimestamp(long value) {
        this.timestamp = value;
    }
    
    /*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AValueDto otherValue) {
		if(this.timestamp == otherValue.getTimestamp()){
			return 0;
		}
		else if(this.timestamp < otherValue.getTimestamp()){
			return -1;
		}
		else{
			return 1;
		}
	}

}
