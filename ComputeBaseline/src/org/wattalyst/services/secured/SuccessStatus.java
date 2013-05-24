
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for successStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="successStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NA"/>
 *     &lt;enumeration value="NOT_ACCOMPLISHED"/>
 *     &lt;enumeration value="PARTIALLY_ACCOMPLISHED"/>
 *     &lt;enumeration value="ACCOMPLISHED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "successStatus")
@XmlEnum
public enum SuccessStatus {

    NA,
    NOT_ACCOMPLISHED,
    PARTIALLY_ACCOMPLISHED,
    ACCOMPLISHED;

    public String value() {
        return name();
    }

    public static SuccessStatus fromValue(String v) {
        return valueOf(v);
    }

}
