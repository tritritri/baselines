
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for drEventStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="drEventStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SCHEDULED"/>
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="TERMINATED"/>
 *     &lt;enumeration value="EVALUATED"/>
 *     &lt;enumeration value="CANCELED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "drEventStatus")
@XmlEnum
public enum DrEventStatus {

    SCHEDULED,
    ACTIVE,
    TERMINATED,
    EVALUATED,
    CANCELED;

    public String value() {
        return name();
    }

    public static DrEventStatus fromValue(String v) {
        return valueOf(v);
    }

}
