
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for drTaskPerformanceStatusDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="drTaskPerformanceStatusDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actualTotalValueInTimeframe" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="estimatedTotalValueInTimeframe" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="theTask" type="{http://secured.services.wattalyst.org/}taskDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "drTaskPerformanceStatusDto", propOrder = {
    "actualTotalValueInTimeframe",
    "estimatedTotalValueInTimeframe",
    "theTask"
})
public class DrTaskPerformanceStatusDto {

    protected Double actualTotalValueInTimeframe;
    protected Double estimatedTotalValueInTimeframe;
    protected TaskDto theTask;

    /**
     * Gets the value of the actualTotalValueInTimeframe property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getActualTotalValueInTimeframe() {
        return actualTotalValueInTimeframe;
    }

    /**
     * Sets the value of the actualTotalValueInTimeframe property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setActualTotalValueInTimeframe(Double value) {
        this.actualTotalValueInTimeframe = value;
    }

    /**
     * Gets the value of the estimatedTotalValueInTimeframe property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEstimatedTotalValueInTimeframe() {
        return estimatedTotalValueInTimeframe;
    }

    /**
     * Sets the value of the estimatedTotalValueInTimeframe property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEstimatedTotalValueInTimeframe(Double value) {
        this.estimatedTotalValueInTimeframe = value;
    }

    /**
     * Gets the value of the theTask property.
     * 
     * @return
     *     possible object is
     *     {@link TaskDto }
     *     
     */
    public TaskDto getTheTask() {
        return theTask;
    }

    /**
     * Sets the value of the theTask property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaskDto }
     *     
     */
    public void setTheTask(TaskDto value) {
        this.theTask = value;
    }

}
