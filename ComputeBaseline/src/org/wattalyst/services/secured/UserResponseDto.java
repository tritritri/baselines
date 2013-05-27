
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userResponseDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userResponseDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secured.services.wattalyst.org/}entityDto">
 *       &lt;sequence>
 *         &lt;element name="usefulness" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="convenience" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="decisionDate" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="acceptance" type="{http://secured.services.wattalyst.org/}acceptanceStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userResponseDto", propOrder = {
    "usefulness",
    "convenience",
    "message",
    "decisionDate",
    "acceptance"
})
public class UserResponseDto
    extends EntityDto
{

    protected Integer usefulness;
    protected Integer convenience;
    protected String message;
    protected Long decisionDate;
    protected AcceptanceStatus acceptance;

    /**
     * Gets the value of the usefulness property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUsefulness() {
        return usefulness;
    }

    /**
     * Sets the value of the usefulness property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUsefulness(Integer value) {
        this.usefulness = value;
    }

    /**
     * Gets the value of the convenience property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getConvenience() {
        return convenience;
    }

    /**
     * Sets the value of the convenience property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setConvenience(Integer value) {
        this.convenience = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the decisionDate property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDecisionDate() {
        return decisionDate;
    }

    /**
     * Sets the value of the decisionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDecisionDate(Long value) {
        this.decisionDate = value;
    }

    /**
     * Gets the value of the acceptance property.
     * 
     * @return
     *     possible object is
     *     {@link AcceptanceStatus }
     *     
     */
    public AcceptanceStatus getAcceptance() {
        return acceptance;
    }

    /**
     * Sets the value of the acceptance property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcceptanceStatus }
     *     
     */
    public void setAcceptance(AcceptanceStatus value) {
        this.acceptance = value;
    }

}
