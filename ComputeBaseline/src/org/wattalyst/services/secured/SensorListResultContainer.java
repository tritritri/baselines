
package org.wattalyst.services.secured;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sensorListResultContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sensorListResultContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secured.services.wattalyst.org/}wsResultContainer">
 *       &lt;sequence>
 *         &lt;element name="sensors" type="{http://secured.services.wattalyst.org/}sensorDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sensorListResultContainer", propOrder = {
    "sensors"
})
public class SensorListResultContainer
    extends WsResultContainer
{

    protected List<SensorDto> sensors;

    /**
     * Gets the value of the sensors property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sensors property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSensors().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SensorDto }
     * 
     * 
     */
    public List<SensorDto> getSensors() {
        if (sensors == null) {
            sensors = new ArrayList<SensorDto>();
        }
        return this.sensors;
    }

}
