
package org.wattalyst.services.secured;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for drSignalDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="drSignalDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secured.services.wattalyst.org/}fullQualifiedEntityDto">
 *       &lt;sequence>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="plannedDelivery" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="plannedExpiry" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="recipients" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="eventStatus" type="{http://secured.services.wattalyst.org/}drEventStatus" minOccurs="0"/>
 *         &lt;element name="tasks" type="{http://secured.services.wattalyst.org/}taskDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="states" type="{http://secured.services.wattalyst.org/}drStatusDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "drSignalDto", propOrder = {
    "message",
    "plannedDelivery",
    "plannedExpiry",
    "recipients",
    "eventStatus",
    "tasks",
    "states"
})
public class DrSignalDto
    extends FullQualifiedEntityDto
{

    protected String message;
    protected Long plannedDelivery;
    protected Long plannedExpiry;
    @XmlElement(nillable = true)
    protected List<String> recipients;
    protected DrEventStatus eventStatus;
    @XmlElement(nillable = true)
    protected List<TaskDto> tasks;
    @XmlElement(nillable = true)
    protected List<DrStatusDto> states;

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
     * Gets the value of the plannedDelivery property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPlannedDelivery() {
        return plannedDelivery;
    }

    /**
     * Sets the value of the plannedDelivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPlannedDelivery(Long value) {
        this.plannedDelivery = value;
    }

    /**
     * Gets the value of the plannedExpiry property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPlannedExpiry() {
        return plannedExpiry;
    }

    /**
     * Sets the value of the plannedExpiry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPlannedExpiry(Long value) {
        this.plannedExpiry = value;
    }

    /**
     * Gets the value of the recipients property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recipients property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecipients().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRecipients() {
        if (recipients == null) {
            recipients = new ArrayList<String>();
        }
        return this.recipients;
    }

    /**
     * Gets the value of the eventStatus property.
     * 
     * @return
     *     possible object is
     *     {@link DrEventStatus }
     *     
     */
    public DrEventStatus getEventStatus() {
        return eventStatus;
    }

    /**
     * Sets the value of the eventStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrEventStatus }
     *     
     */
    public void setEventStatus(DrEventStatus value) {
        this.eventStatus = value;
    }

    /**
     * Gets the value of the tasks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tasks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTasks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaskDto }
     * 
     * 
     */
    public List<TaskDto> getTasks() {
        if (tasks == null) {
            tasks = new ArrayList<TaskDto>();
        }
        return this.tasks;
    }

    /**
     * Gets the value of the states property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the states property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DrStatusDto }
     * 
     * 
     */
    public List<DrStatusDto> getStates() {
        if (states == null) {
            states = new ArrayList<DrStatusDto>();
        }
        return this.states;
    }

}
