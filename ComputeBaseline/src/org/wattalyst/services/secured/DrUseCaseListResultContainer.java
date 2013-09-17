
package org.wattalyst.services.secured;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for drUseCaseListResultContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="drUseCaseListResultContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secured.services.wattalyst.org/}wsResultContainer">
 *       &lt;sequence>
 *         &lt;element name="usecases" type="{http://secured.services.wattalyst.org/}drUseCaseDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "drUseCaseListResultContainer", propOrder = {
    "usecases"
})
public class DrUseCaseListResultContainer
    extends WsResultContainer
{

    protected List<DrUseCaseDto> usecases;

    /**
     * Gets the value of the usecases property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usecases property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsecases().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DrUseCaseDto }
     * 
     * 
     */
    public List<DrUseCaseDto> getUsecases() {
        if (usecases == null) {
            usecases = new ArrayList<DrUseCaseDto>();
        }
        return this.usecases;
    }

}
