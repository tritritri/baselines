
package org.wattalyst.services.secured;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for drStatusDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="drStatusDto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://secured.services.wattalyst.org/}entityDto">
 *       &lt;sequence>
 *         &lt;element name="earnedTokens" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="feedbacks" type="{http://secured.services.wattalyst.org/}feedbackDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="goalAttainment" type="{http://secured.services.wattalyst.org/}kpiPerformanceDto" minOccurs="0"/>
 *         &lt;element name="locationname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentStatus" type="{http://secured.services.wattalyst.org/}signalStatus" minOccurs="0"/>
 *         &lt;element name="history">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://secured.services.wattalyst.org/}signalStatus" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="acceptance" type="{http://secured.services.wattalyst.org/}acceptanceStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "drStatusDto", propOrder = {
    "earnedTokens",
    "feedbacks",
    "goalAttainment",
    "locationname",
    "currentStatus",
    "history",
    "acceptance"
})
public class DrStatusDto
    extends EntityDto
{

    protected Integer earnedTokens;
    @XmlElement(nillable = true)
    protected List<FeedbackDto> feedbacks;
    protected KpiPerformanceDto goalAttainment;
    protected String locationname;
    protected SignalStatus currentStatus;
    @XmlElement(required = true)
    protected DrStatusDto.History history;
    protected AcceptanceStatus acceptance;

    /**
     * Gets the value of the earnedTokens property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEarnedTokens() {
        return earnedTokens;
    }

    /**
     * Sets the value of the earnedTokens property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEarnedTokens(Integer value) {
        this.earnedTokens = value;
    }

    /**
     * Gets the value of the feedbacks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the feedbacks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeedbacks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FeedbackDto }
     * 
     * 
     */
    public List<FeedbackDto> getFeedbacks() {
        if (feedbacks == null) {
            feedbacks = new ArrayList<FeedbackDto>();
        }
        return this.feedbacks;
    }

    /**
     * Gets the value of the goalAttainment property.
     * 
     * @return
     *     possible object is
     *     {@link KpiPerformanceDto }
     *     
     */
    public KpiPerformanceDto getGoalAttainment() {
        return goalAttainment;
    }

    /**
     * Sets the value of the goalAttainment property.
     * 
     * @param value
     *     allowed object is
     *     {@link KpiPerformanceDto }
     *     
     */
    public void setGoalAttainment(KpiPerformanceDto value) {
        this.goalAttainment = value;
    }

    /**
     * Gets the value of the locationname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationname() {
        return locationname;
    }

    /**
     * Sets the value of the locationname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationname(String value) {
        this.locationname = value;
    }

    /**
     * Gets the value of the currentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link SignalStatus }
     *     
     */
    public SignalStatus getCurrentStatus() {
        return currentStatus;
    }

    /**
     * Sets the value of the currentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignalStatus }
     *     
     */
    public void setCurrentStatus(SignalStatus value) {
        this.currentStatus = value;
    }

    /**
     * Gets the value of the history property.
     * 
     * @return
     *     possible object is
     *     {@link DrStatusDto.History }
     *     
     */
    public DrStatusDto.History getHistory() {
        return history;
    }

    /**
     * Sets the value of the history property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrStatusDto.History }
     *     
     */
    public void setHistory(DrStatusDto.History value) {
        this.history = value;
    }

    /**
     * Gets the value of the acceptance property.
     * 
     * @return
     *     possible object is
     *     {@link AcceptanceStatus }
     *     
     */
    public AcceptanceStatus getAcceptance() {
        return acceptance;
    }

    /**
     * Sets the value of the acceptance property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcceptanceStatus }
     *     
     */
    public void setAcceptance(AcceptanceStatus value) {
        this.acceptance = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://secured.services.wattalyst.org/}signalStatus" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class History {

        protected List<DrStatusDto.History.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DrStatusDto.History.Entry }
         * 
         * 
         */
        public List<DrStatusDto.History.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<DrStatusDto.History.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *         &lt;element name="value" type="{http://secured.services.wattalyst.org/}signalStatus" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected Long key;
            protected SignalStatus value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setKey(Long value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link SignalStatus }
             *     
             */
            public SignalStatus getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link SignalStatus }
             *     
             */
            public void setValue(SignalStatus value) {
                this.value = value;
            }

        }

    }

}
