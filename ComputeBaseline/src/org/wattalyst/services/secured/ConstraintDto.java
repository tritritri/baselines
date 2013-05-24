
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for constraintDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="constraintDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secured.services.wattalyst.org/}entityDto">
 *       &lt;sequence>
 *         &lt;element name="energyAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="thresholdAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="thresholdTimeFrame" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="rewardTokens" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="multipleRewards" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="baseline" type="{http://secured.services.wattalyst.org/}baselineDto" minOccurs="0"/>
 *         &lt;element name="calculationUnit" type="{http://secured.services.wattalyst.org/}calculationUnit" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "constraintDto", propOrder = {
    "energyAmount",
    "thresholdAmount",
    "thresholdTimeFrame",
    "rewardTokens",
    "multipleRewards",
    "baseline",
    "calculationUnit"
})
public class ConstraintDto
    extends EntityDto
{

    protected Double energyAmount;
    protected Double thresholdAmount;
    protected Integer thresholdTimeFrame;
    protected Integer rewardTokens;
    protected Boolean multipleRewards;
    protected BaselineDto baseline;
    protected CalculationUnit calculationUnit;

    /**
     * Gets the value of the energyAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEnergyAmount() {
        return energyAmount;
    }

    /**
     * Sets the value of the energyAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEnergyAmount(Double value) {
        this.energyAmount = value;
    }

    /**
     * Gets the value of the thresholdAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getThresholdAmount() {
        return thresholdAmount;
    }

    /**
     * Sets the value of the thresholdAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setThresholdAmount(Double value) {
        this.thresholdAmount = value;
    }

    /**
     * Gets the value of the thresholdTimeFrame property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getThresholdTimeFrame() {
        return thresholdTimeFrame;
    }

    /**
     * Sets the value of the thresholdTimeFrame property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setThresholdTimeFrame(Integer value) {
        this.thresholdTimeFrame = value;
    }

    /**
     * Gets the value of the rewardTokens property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRewardTokens() {
        return rewardTokens;
    }

    /**
     * Sets the value of the rewardTokens property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRewardTokens(Integer value) {
        this.rewardTokens = value;
    }

    /**
     * Gets the value of the multipleRewards property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMultipleRewards() {
        return multipleRewards;
    }

    /**
     * Sets the value of the multipleRewards property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMultipleRewards(Boolean value) {
        this.multipleRewards = value;
    }

    /**
     * Gets the value of the baseline property.
     * 
     * @return
     *     possible object is
     *     {@link BaselineDto }
     *     
     */
    public BaselineDto getBaseline() {
        return baseline;
    }

    /**
     * Sets the value of the baseline property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaselineDto }
     *     
     */
    public void setBaseline(BaselineDto value) {
        this.baseline = value;
    }

    /**
     * Gets the value of the calculationUnit property.
     * 
     * @return
     *     possible object is
     *     {@link CalculationUnit }
     *     
     */
    public CalculationUnit getCalculationUnit() {
        return calculationUnit;
    }

    /**
     * Sets the value of the calculationUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationUnit }
     *     
     */
    public void setCalculationUnit(CalculationUnit value) {
        this.calculationUnit = value;
    }

}
