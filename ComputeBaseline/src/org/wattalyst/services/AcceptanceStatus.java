
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for acceptanceStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="acceptanceStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NA"/>
 *     &lt;enumeration value="ACCEPTED"/>
 *     &lt;enumeration value="DECLINED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "acceptanceStatus")
@XmlEnum
public enum AcceptanceStatus {

    NA,
    ACCEPTED,
    DECLINED;

    public String value() {
        return name();
    }

    public static AcceptanceStatus fromValue(String v) {
        return valueOf(v);
    }

}
