
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBaselineData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBaselineData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authenticationToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="baslineId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBaselineData", propOrder = {
    "authenticationToken",
    "baslineId",
    "startDate",
    "endDate"
})
public class GetBaselineData {

    protected String authenticationToken;
    protected String baslineId;
    protected long startDate;
    protected long endDate;

    /**
     * Gets the value of the authenticationToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationToken() {
        return authenticationToken;
    }

    /**
     * Sets the value of the authenticationToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationToken(String value) {
        this.authenticationToken = value;
    }

    /**
     * Gets the value of the baslineId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaslineId() {
        return baslineId;
    }

    /**
     * Sets the value of the baslineId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaslineId(String value) {
        this.baslineId = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     */
    public long getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     */
    public void setStartDate(long value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     */
    public long getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     */
    public void setEndDate(long value) {
        this.endDate = value;
    }

}
