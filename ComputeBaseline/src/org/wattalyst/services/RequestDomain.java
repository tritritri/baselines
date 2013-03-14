
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for requestDomain complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="requestDomain">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.wattalyst.org/}request">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestDomain")
@XmlSeeAlso({
    NoDtoCollectionRequestDomain.class,
    AssociateRequestDomain.class,
    AttributeRequestDomain.class,
    RemoveRequestDomain.class,
    BorderRequestDomain.class
})
public abstract class RequestDomain
    extends Request
{


}
