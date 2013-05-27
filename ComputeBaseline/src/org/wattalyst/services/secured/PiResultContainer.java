
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for piResultContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="piResultContainer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secured.services.wattalyst.org/}wsResultContainer">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://secured.services.wattalyst.org/}performanceIndicatorDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "piResultContainer", propOrder = {
    "result"
})
public class PiResultContainer
    extends WsResultContainer
{

    protected PerformanceIndicatorDto result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link PerformanceIndicatorDto }
     *     
     */
    public PerformanceIndicatorDto getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link PerformanceIndicatorDto }
     *     
     */
    public void setResult(PerformanceIndicatorDto value) {
        this.result = value;
    }

}
