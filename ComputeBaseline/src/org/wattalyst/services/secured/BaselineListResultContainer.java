
package org.wattalyst.services.secured;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for baselineListResultContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="baselineListResultContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secured.services.wattalyst.org/}wsResultContainer">
 *       &lt;sequence>
 *         &lt;element name="baselines" type="{http://secured.services.wattalyst.org/}baselineDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baselineListResultContainer", propOrder = {
    "baselines"
})
public class BaselineListResultContainer
    extends WsResultContainer
{

    protected List<BaselineDto> baselines;

    /**
     * Gets the value of the baselines property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the baselines property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBaselines().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BaselineDto }
     * 
     * 
     */
    public List<BaselineDto> getBaselines() {
        if (baselines == null) {
            baselines = new ArrayList<BaselineDto>();
        }
        return this.baselines;
    }

}
