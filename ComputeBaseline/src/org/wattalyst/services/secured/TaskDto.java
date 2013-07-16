
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for taskDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="taskDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secured.services.wattalyst.org/}entityDto">
 *       &lt;sequence>
 *         &lt;element name="source" type="{http://secured.services.wattalyst.org/}scheduleDto" minOccurs="0"/>
 *         &lt;element name="target" type="{http://secured.services.wattalyst.org/}scheduleDto" minOccurs="0"/>
 *         &lt;element name="constraint" type="{http://secured.services.wattalyst.org/}constraintDto" minOccurs="0"/>
 *         &lt;element name="action" type="{http://secured.services.wattalyst.org/}action" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taskDto", propOrder = {
    "source",
    "target",
    "constraint",
    "action"
})
public class TaskDto
    extends EntityDto
{

    protected ScheduleDto source;
    protected ScheduleDto target;
    protected ConstraintDto constraint;
    protected Action action;

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link ScheduleDto }
     *     
     */
    public ScheduleDto getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleDto }
     *     
     */
    public void setSource(ScheduleDto value) {
        this.source = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link ScheduleDto }
     *     
     */
    public ScheduleDto getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleDto }
     *     
     */
    public void setTarget(ScheduleDto value) {
        this.target = value;
    }

    /**
     * Gets the value of the constraint property.
     * 
     * @return
     *     possible object is
     *     {@link ConstraintDto }
     *     
     */
    public ConstraintDto getConstraint() {
        return constraint;
    }

    /**
     * Sets the value of the constraint property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConstraintDto }
     *     
     */
    public void setConstraint(ConstraintDto value) {
        this.constraint = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link Action }
     *     
     */
    public Action getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link Action }
     *     
     */
    public void setAction(Action value) {
        this.action = value;
    }

}
