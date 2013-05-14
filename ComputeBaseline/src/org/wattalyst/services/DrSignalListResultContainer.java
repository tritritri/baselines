
package org.wattalyst.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for drSignalListResultContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="drSignalListResultContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.wattalyst.org/}wsResultContainer">
 *       &lt;sequence>
 *         &lt;element name="signals" type="{http://services.wattalyst.org/}drSignalDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "drSignalListResultContainer", propOrder = {
    "signals"
})
public class DrSignalListResultContainer
    extends WsResultContainer
{

    protected List<DrSignalDto> signals;

    /**
     * Gets the value of the signals property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signals property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignals().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DrSignalDto }
     * 
     * 
     */
    public List<DrSignalDto> getSignals() {
        if (signals == null) {
            signals = new ArrayList<DrSignalDto>();
        }
        return this.signals;
    }

}
