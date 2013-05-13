
package org.wattalyst.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for calculationUnit.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="calculationUnit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ABSOLUTE"/>
 *     &lt;enumeration value="PERCENTAGE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "calculationUnit")
@XmlEnum
public enum CalculationUnit {

    ABSOLUTE,
    PERCENTAGE;

    public String value() {
        return name();
    }

    public static CalculationUnit fromValue(String v) {
        return valueOf(v);
    }

}
