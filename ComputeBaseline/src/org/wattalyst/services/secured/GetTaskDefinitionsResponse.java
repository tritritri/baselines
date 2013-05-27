
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getTaskDefinitionsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getTaskDefinitionsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="taskListResultContainer" type="{http://secured.services.wattalyst.org/}taskListResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTaskDefinitionsResponse", propOrder = {
    "taskListResultContainer"
})
public class GetTaskDefinitionsResponse {

    protected TaskListResultContainer taskListResultContainer;

    /**
     * Gets the value of the taskListResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link TaskListResultContainer }
     *     
     */
    public TaskListResultContainer getTaskListResultContainer() {
        return taskListResultContainer;
    }

    /**
     * Sets the value of the taskListResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaskListResultContainer }
     *     
     */
    public void setTaskListResultContainer(TaskListResultContainer value) {
        this.taskListResultContainer = value;
    }

}
