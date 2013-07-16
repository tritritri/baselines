
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recipientContextDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="recipientContextDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secured.services.wattalyst.org/}entityDto">
 *       &lt;sequence>
 *         &lt;element name="locationname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://secured.services.wattalyst.org/}drStatusDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recipientContextDto", propOrder = {
    "locationname",
    "status"
})
public class RecipientContextDto
    extends EntityDto
{

    protected String locationname;
    protected DrStatusDto status;

    /**
     * Gets the value of the locationname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationname() {
        return locationname;
    }

    /**
     * Sets the value of the locationname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationname(String value) {
        this.locationname = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link DrStatusDto }
     *     
     */
    public DrStatusDto getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrStatusDto }
     *     
     */
    public void setStatus(DrStatusDto value) {
        this.status = value;
    }

}
