
package org.wattalyst.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for borderRequestDomain complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="borderRequestDomain">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.wattalyst.org/}requestDomain">
 *       &lt;sequence>
 *         &lt;element name="requests" type="{http://services.wattalyst.org/}request" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "borderRequestDomain", propOrder = {
    "requests"
})
@XmlSeeAlso({
    FullQualifiedNameRequest.class,
    SetValuesInSensorRequest.class,
    SetSubLocationsInLocationRequest.class,
    MetaDatumRequest.class,
    AValueRequest.class,
    FullQualifiedEntityRequest.class,
    SetSensorsInLocationRequest.class,
    SetMetaDatasInSensorRequest.class
})
public abstract class BorderRequestDomain
    extends RequestDomain
{

    @XmlElement(nillable = true)
    protected List<Request> requests;

    /**
     * Gets the value of the requests property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requests property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequests().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Request }
     * 
     * 
     */
    public List<Request> getRequests() {
        if (requests == null) {
            requests = new ArrayList<Request>();
        }
        return this.requests;
    }

}
