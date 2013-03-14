
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fullQualifiedEntityRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fullQualifiedEntityRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.wattalyst.org/}borderRequestDomain">
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
@XmlType(name = "fullQualifiedEntityRequest")
@XmlSeeAlso({
    LocationRequest.class,
    SensorRequest.class
})
public class FullQualifiedEntityRequest
    extends BorderRequestDomain
{


}
