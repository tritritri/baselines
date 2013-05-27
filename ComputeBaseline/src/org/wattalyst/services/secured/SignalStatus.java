
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for signalStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="signalStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PLANNED"/>
 *     &lt;enumeration value="ISSUED"/>
 *     &lt;enumeration value="RECEIVED"/>
 *     &lt;enumeration value="EXPIRED"/>
 *     &lt;enumeration value="EVALUATED"/>
 *     &lt;enumeration value="COMPLETED"/>
 *     &lt;enumeration value="REVOKED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "signalStatus")
@XmlEnum
public enum SignalStatus {

    PLANNED,
    ISSUED,
    RECEIVED,
    EXPIRED,
    EVALUATED,
    COMPLETED,
    REVOKED;

    public String value() {
        return name();
    }

    public static SignalStatus fromValue(String v) {
        return valueOf(v);
    }

}
