
package org.wattalyst.services.secured;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for drSignalPerfomaceStatusResultContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="drSignalPerfomaceStatusResultContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secured.services.wattalyst.org/}wsResultContainer">
 *       &lt;sequence>
 *         &lt;element name="taskPerformanceStatuses" type="{http://secured.services.wattalyst.org/}drTaskPerformanceStatusDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="signalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recipient" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "drSignalPerfomaceStatusResultContainer", propOrder = {
    "taskPerformanceStatuses",
    "signalId",
    "recipient"
})
public class DrSignalPerfomaceStatusResultContainer
    extends WsResultContainer
{

    protected List<DrTaskPerformanceStatusDto> taskPerformanceStatuses;
    protected String signalId;
    protected String recipient;

    /**
     * Gets the value of the taskPerformanceStatuses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taskPerformanceStatuses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaskPerformanceStatuses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DrTaskPerformanceStatusDto }
     * 
     * 
     */
    public List<DrTaskPerformanceStatusDto> getTaskPerformanceStatuses() {
        if (taskPerformanceStatuses == null) {
            taskPerformanceStatuses = new ArrayList<DrTaskPerformanceStatusDto>();
        }
        return this.taskPerformanceStatuses;
    }

    /**
     * Gets the value of the signalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignalId() {
        return signalId;
    }

    /**
     * Sets the value of the signalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignalId(String value) {
        this.signalId = value;
    }

    /**
     * Gets the value of the recipient property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Sets the value of the recipient property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipient(String value) {
        this.recipient = value;
    }

}
