
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAllDRUseCasesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAllDRUseCasesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="useCaseListResultContainer" type="{http://secured.services.wattalyst.org/}drUseCaseListResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllDRUseCasesResponse", propOrder = {
    "useCaseListResultContainer"
})
public class GetAllDRUseCasesResponse {

    protected DrUseCaseListResultContainer useCaseListResultContainer;

    /**
     * Gets the value of the useCaseListResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link DrUseCaseListResultContainer }
     *     
     */
    public DrUseCaseListResultContainer getUseCaseListResultContainer() {
        return useCaseListResultContainer;
    }

    /**
     * Sets the value of the useCaseListResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrUseCaseListResultContainer }
     *     
     */
    public void setUseCaseListResultContainer(DrUseCaseListResultContainer value) {
        this.useCaseListResultContainer = value;
    }

}
