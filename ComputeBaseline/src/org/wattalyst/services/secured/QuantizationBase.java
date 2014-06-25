
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for quantizationBase.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="quantizationBase">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="minute"/>
 *     &lt;enumeration value="hour"/>
 *     &lt;enumeration value="day"/>
 *     &lt;enumeration value="week"/>
 *     &lt;enumeration value="month"/>
 *     &lt;enumeration value="quarter"/>
 *     &lt;enumeration value="year"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "quantizationBase")
@XmlEnum
public enum QuantizationBase {

    @XmlEnumValue("minute")
    MINUTE("minute"),
    @XmlEnumValue("hour")
    HOUR("hour"),
    @XmlEnumValue("day")
    DAY("day"),
    @XmlEnumValue("week")
    WEEK("week"),
    @XmlEnumValue("month")
    MONTH("month"),
    @XmlEnumValue("quarter")
    QUARTER("quarter"),
    @XmlEnumValue("year")
    YEAR("year");
    private final String value;

    QuantizationBase(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QuantizationBase fromValue(String v) {
        for (QuantizationBase c: QuantizationBase.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
