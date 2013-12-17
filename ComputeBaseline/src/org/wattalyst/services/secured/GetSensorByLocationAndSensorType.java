
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSensorByLocationAndSensorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSensorByLocationAndSensorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authenticationToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fullQualifiedLocationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sensorType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSensorByLocationAndSensorType", propOrder = {
    "authenticationToken",
    "fullQualifiedLocationName",
    "sensorType"
})
public class GetSensorByLocationAndSensorType {

    protected String authenticationToken;
    protected String fullQualifiedLocationName;
    protected String sensorType;

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
     * Gets the value of the fullQualifiedLocationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullQualifiedLocationName() {
        return fullQualifiedLocationName;
    }

    /**
     * Sets the value of the fullQualifiedLocationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullQualifiedLocationName(String value) {
        this.fullQualifiedLocationName = value;
    }

    /**
     * Gets the value of the sensorType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSensorType() {
        return sensorType;
    }

    /**
     * Sets the value of the sensorType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSensorType(String value) {
        this.sensorType = value;
    }

}
