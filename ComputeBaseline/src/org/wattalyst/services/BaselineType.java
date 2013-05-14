
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for baselineType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="baselineType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CAISO"/>
 *     &lt;enumeration value="PJM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "baselineType")
@XmlEnum
public enum BaselineType {

    CAISO,
    PJM;

    public String value() {
        return name();
    }

    public static BaselineType fromValue(String v) {
        return valueOf(v);
    }

}
