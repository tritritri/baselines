
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
 *         &lt;element name="thresholdTimeFrame" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="rewardTokens" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="multipleRewards" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="amountUnit" type="{http://secured.services.wattalyst.org/}calculationUnit" minOccurs="0"/>
 *         &lt;element name="baselineType" type="{http://secured.services.wattalyst.org/}baselineType" minOccurs="0"/>
 *         &lt;element name="sensorType" type="{http://secured.services.wattalyst.org/}sensorType" minOccurs="0"/>
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
    "amountUnit",
    "baselineType",
    "sensorType"
})
public class ConstraintDto
    extends EntityDto
{

    protected Double energyAmount;
    protected Double thresholdAmount;
    protected Long thresholdTimeFrame;
    protected Integer rewardTokens;
    protected Boolean multipleRewards;
    protected CalculationUnit amountUnit;
    protected BaselineType baselineType;
    protected SensorType sensorType;

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
     *     {@link Long }
     *     
     */
    public Long getThresholdTimeFrame() {
        return thresholdTimeFrame;
    }

    /**
     * Sets the value of the thresholdTimeFrame property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setThresholdTimeFrame(Long value) {
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
     * Gets the value of the amountUnit property.
     * 
     * @return
     *     possible object is
     *     {@link CalculationUnit }
     *     
     */
    public CalculationUnit getAmountUnit() {
        return amountUnit;
    }

    /**
     * Sets the value of the amountUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationUnit }
     *     
     */
    public void setAmountUnit(CalculationUnit value) {
        this.amountUnit = value;
    }

    /**
     * Gets the value of the baselineType property.
     * 
     * @return
     *     possible object is
     *     {@link BaselineType }
     *     
     */
    public BaselineType getBaselineType() {
        return baselineType;
    }

    /**
     * Sets the value of the baselineType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaselineType }
     *     
     */
    public void setBaselineType(BaselineType value) {
        this.baselineType = value;
    }

    /**
     * Gets the value of the sensorType property.
     * 
     * @return
     *     possible object is
     *     {@link SensorType }
     *     
     */
    public SensorType getSensorType() {
        return sensorType;
    }

    /**
     * Sets the value of the sensorType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SensorType }
     *     
     */
    public void setSensorType(SensorType value) {
        this.sensorType = value;
    }

}
