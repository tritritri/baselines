
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for category.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="category">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="COOLING"/>
 *     &lt;enumeration value="ENERGY_COOLING"/>
 *     &lt;enumeration value="ENERGY_ELECTRICITY"/>
 *     &lt;enumeration value="ENERGY_HEATING"/>
 *     &lt;enumeration value="HEATING"/>
 *     &lt;enumeration value="OTHER"/>
 *     &lt;enumeration value="ROOM_SENSORS"/>
 *     &lt;enumeration value="UNDEFINED"/>
 *     &lt;enumeration value="VENTILATION"/>
 *     &lt;enumeration value="WEATHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "category")
@XmlEnum
public enum Category {

    COOLING,
    ENERGY_COOLING,
    ENERGY_ELECTRICITY,
    ENERGY_HEATING,
    HEATING,
    OTHER,
    ROOM_SENSORS,
    UNDEFINED,
    VENTILATION,
    WEATHER;

    public String value() {
        return name();
    }

    public static Category fromValue(String v) {
        return valueOf(v);
    }

}
