
package org.wattalyst.services.secured;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sensorsWithBinaryValuesResultContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sensorsWithBinaryValuesResultContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secured.services.wattalyst.org/}wsResultContainer">
 *       &lt;sequence>
 *         &lt;element name="sensorWithValues" type="{http://secured.services.wattalyst.org/}sensorWithBinaryValuesDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sensorsWithBinaryValuesResultContainer", propOrder = {
    "sensorWithValues"
})
public class SensorsWithBinaryValuesResultContainer
    extends WsResultContainer
{

    protected List<SensorWithBinaryValuesDto> sensorWithValues;

    /**
     * Gets the value of the sensorWithValues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sensorWithValues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSensorWithValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SensorWithBinaryValuesDto }
     * 
     * 
     */
    public List<SensorWithBinaryValuesDto> getSensorWithValues() {
        if (sensorWithValues == null) {
            sensorWithValues = new ArrayList<SensorWithBinaryValuesDto>();
        }
        return this.sensorWithValues;
    }

}
