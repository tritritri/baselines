
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for locationTypeDto.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="locationTypeDto">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UNDEFINED"/>
 *     &lt;enumeration value="OTHER"/>
 *     &lt;enumeration value="APARTMENT"/>
 *     &lt;enumeration value="PRIVATE_HOME"/>
 *     &lt;enumeration value="INDUSTRIAL_PREMISES"/>
 *     &lt;enumeration value="ROOM"/>
 *     &lt;enumeration value="TRIAL_SITE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "locationTypeDto")
@XmlEnum
public enum LocationTypeDto {

    UNDEFINED,
    OTHER,
    APARTMENT,
    PRIVATE_HOME,
    INDUSTRIAL_PREMISES,
    ROOM,
    TRIAL_SITE;

    public String value() {
        return name();
    }

    public static LocationTypeDto fromValue(String v) {
        return valueOf(v);
    }

}
