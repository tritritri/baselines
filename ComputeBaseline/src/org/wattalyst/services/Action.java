
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for action.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="action">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RESTRICT_CONSUMPTION"/>
 *     &lt;enumeration value="SHIFT_CONSUMPTION"/>
 *     &lt;enumeration value="REDUCE_CONSUMPTION"/>
 *     &lt;enumeration value="INCREASE_CONSUMPTION"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "action")
@XmlEnum
public enum Action {

    RESTRICT_CONSUMPTION,
    SHIFT_CONSUMPTION,
    REDUCE_CONSUMPTION,
    INCREASE_CONSUMPTION;

    public String value() {
        return name();
    }

    public static Action fromValue(String v) {
        return valueOf(v);
    }

}
