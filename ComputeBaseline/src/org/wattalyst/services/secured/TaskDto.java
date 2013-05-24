
package org.wattalyst.services.secured;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="source" type="{http://secured.services.wattalyst.org/}timeframeDto" minOccurs="0"/>
 *         &lt;element name="target" type="{http://secured.services.wattalyst.org/}timeframeDto" minOccurs="0"/>
 *         &lt;element name="constraints" type="{http://secured.services.wattalyst.org/}constraintDto" maxOccurs="unbounded" minOccurs="0"/>
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
    "constraints",
    "action"
})
public class TaskDto
    extends EntityDto
{

    protected TimeframeDto source;
    protected TimeframeDto target;
    protected List<ConstraintDto> constraints;
    protected Action action;

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link TimeframeDto }
     *     
     */
    public TimeframeDto getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeframeDto }
     *     
     */
    public void setSource(TimeframeDto value) {
        this.source = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link TimeframeDto }
     *     
     */
    public TimeframeDto getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeframeDto }
     *     
     */
    public void setTarget(TimeframeDto value) {
        this.target = value;
    }

    /**
     * Gets the value of the constraints property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the constraints property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConstraints().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConstraintDto }
     * 
     * 
     */
    public List<ConstraintDto> getConstraints() {
        if (constraints == null) {
            constraints = new ArrayList<ConstraintDto>();
        }
        return this.constraints;
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
