
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getUserFeedbackForLocationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getUserFeedbackForLocationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="feedbackResultContainer" type="{http://secured.services.wattalyst.org/}feedbackResultContainer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUserFeedbackForLocationResponse", propOrder = {
    "feedbackResultContainer"
})
public class GetUserFeedbackForLocationResponse {

    protected FeedbackResultContainer feedbackResultContainer;

    /**
     * Gets the value of the feedbackResultContainer property.
     * 
     * @return
     *     possible object is
     *     {@link FeedbackResultContainer }
     *     
     */
    public FeedbackResultContainer getFeedbackResultContainer() {
        return feedbackResultContainer;
    }

    /**
     * Sets the value of the feedbackResultContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link FeedbackResultContainer }
     *     
     */
    public void setFeedbackResultContainer(FeedbackResultContainer value) {
        this.feedbackResultContainer = value;
    }

}
