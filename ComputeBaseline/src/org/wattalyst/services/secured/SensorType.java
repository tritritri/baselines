
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sensorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="sensorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UNDEFINED"/>
 *     &lt;enumeration value="OTHER"/>
 *     &lt;enumeration value="OUTDOOR_TEMP"/>
 *     &lt;enumeration value="INDOOR_TEMP"/>
 *     &lt;enumeration value="TOTAL_ELECTRICITY_ENERGY"/>
 *     &lt;enumeration value="TOTAL_ELECTRICITY_POWER"/>
 *     &lt;enumeration value="TOTAL_HEATING_ENERGY"/>
 *     &lt;enumeration value="TOTAL_HEATING_POWER"/>
 *     &lt;enumeration value="HOT_WATER_POWER"/>
 *     &lt;enumeration value="HOT_WATER_ENERGY"/>
 *     &lt;enumeration value="HEATING_POWER"/>
 *     &lt;enumeration value="HEATING_ENERGY"/>
 *     &lt;enumeration value="COOLING_ENERGY"/>
 *     &lt;enumeration value="COOLING_POWER"/>
 *     &lt;enumeration value="APP_WASHINGMACHINE_ENERGY"/>
 *     &lt;enumeration value="APP_WASHINGMACHINE_POWER"/>
 *     &lt;enumeration value="APP_DISHWASHER_ENERGY"/>
 *     &lt;enumeration value="APP_DISHWASHER_POWER"/>
 *     &lt;enumeration value="APP_OVEN_ENERGY"/>
 *     &lt;enumeration value="APP_OVEN_POWER"/>
 *     &lt;enumeration value="APP_MULTIMEDIA_POWER"/>
 *     &lt;enumeration value="APP_MULTIMEDIA_ENERGY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "sensorType")
@XmlEnum
public enum SensorType {

    UNDEFINED,
    OTHER,
    OUTDOOR_TEMP,
    INDOOR_TEMP,
    TOTAL_ELECTRICITY_ENERGY,
    TOTAL_ELECTRICITY_POWER,
    TOTAL_HEATING_ENERGY,
    TOTAL_HEATING_POWER,
    HOT_WATER_POWER,
    HOT_WATER_ENERGY,
    HEATING_POWER,
    HEATING_ENERGY,
    COOLING_ENERGY,
    COOLING_POWER,
    APP_WASHINGMACHINE_ENERGY,
    APP_WASHINGMACHINE_POWER,
    APP_DISHWASHER_ENERGY,
    APP_DISHWASHER_POWER,
    APP_OVEN_ENERGY,
    APP_OVEN_POWER,
    APP_MULTIMEDIA_POWER,
    APP_MULTIMEDIA_ENERGY;

    public String value() {
        return name();
    }

    public static SensorType fromValue(String v) {
        return valueOf(v);
    }

}
