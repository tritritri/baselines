
package org.wattalyst.services.secured;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
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
 *     &lt;enumeration value="APP_WASHINGMACHINE__REACTIVE_POWER"/>
 *     &lt;enumeration value="APP_WASHINGMACHINE_POWER"/>
 *     &lt;enumeration value="APP_DISHWASHER_ENERGY"/>
 *     &lt;enumeration value="APP_DISHWASHER_POWER"/>
 *     &lt;enumeration value="APP_DISHWASHER_REACTIVE_POWER"/>
 *     &lt;enumeration value="APP_OVEN_ENERGY"/>
 *     &lt;enumeration value="APP_OVEN_POWER"/>
 *     &lt;enumeration value="APP_OVEN_REACTIVE_POWER"/>
 *     &lt;enumeration value="APP_MULTIMEDIA_POWER"/>
 *     &lt;enumeration value="APP_MULTIMEDIA_REACTIVE_POWER"/>
 *     &lt;enumeration value="APP_MULTIMEDIA_ENERGY"/>
 *     &lt;enumeration value="APP_CAR_HEATER_ENERGY"/>
 *     &lt;enumeration value="APP_CAR_HEATER_POWER"/>
 *     &lt;enumeration value="APP_CAR_HEATER_REACTIVE_POWER"/>
 *     &lt;enumeration value="APP_OTHER_POWER"/>
 *     &lt;enumeration value="APP_OTHER_ENERGY"/>
 *     &lt;enumeration value="APP_OTHER_REACTIVE_POWER"/>
 *     &lt;enumeration value="LIGHTING_POWER"/>
 *     &lt;enumeration value="LIGHTING_ENERGY"/>
 *     &lt;enumeration value="LIGHTING_REACTIVE_POWER"/>
 *     &lt;enumeration value="TOTAL_ELECTRICITY_REACTIVE_POWER"/>
 *     &lt;enumeration value="TOTAL_ELECTRICITY_VOLTAGE"/>
 *     &lt;enumeration value="COOLING_REACTIVE_POWER"/>
 *     &lt;enumeration value="ROOM_HVAC_MODE"/>
 *     &lt;enumeration value="ROOM_HVAC_POWER"/>
 *     &lt;enumeration value="ROOM_INDOOR_TEMP"/>
 *     &lt;enumeration value="ROOM_SETPOINT_TEMP"/>
 *     &lt;enumeration value="CUSTOMER_ENERGY_SAVING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "sensorType")
@XmlEnum
public enum SensorType {

    UNDEFINED("UNDEFINED"),
    OTHER("OTHER"),
    OUTDOOR_TEMP("OUTDOOR_TEMP"),
    INDOOR_TEMP("INDOOR_TEMP"),
    TOTAL_ELECTRICITY_ENERGY("TOTAL_ELECTRICITY_ENERGY"),
    TOTAL_ELECTRICITY_POWER("TOTAL_ELECTRICITY_POWER"),
    TOTAL_HEATING_ENERGY("TOTAL_HEATING_ENERGY"),
    TOTAL_HEATING_POWER("TOTAL_HEATING_POWER"),
    HOT_WATER_POWER("HOT_WATER_POWER"),
    HOT_WATER_ENERGY("HOT_WATER_ENERGY"),
    HEATING_POWER("HEATING_POWER"),
    HEATING_ENERGY("HEATING_ENERGY"),
    COOLING_ENERGY("COOLING_ENERGY"),
    COOLING_POWER("COOLING_POWER"),
    APP_WASHINGMACHINE_ENERGY("APP_WASHINGMACHINE_ENERGY"),
    @XmlEnumValue("APP_WASHINGMACHINE__REACTIVE_POWER")
    APP_WASHINGMACHINE_REACTIVE_POWER("APP_WASHINGMACHINE__REACTIVE_POWER"),
    APP_WASHINGMACHINE_POWER("APP_WASHINGMACHINE_POWER"),
    APP_DISHWASHER_ENERGY("APP_DISHWASHER_ENERGY"),
    APP_DISHWASHER_POWER("APP_DISHWASHER_POWER"),
    APP_DISHWASHER_REACTIVE_POWER("APP_DISHWASHER_REACTIVE_POWER"),
    APP_OVEN_ENERGY("APP_OVEN_ENERGY"),
    APP_OVEN_POWER("APP_OVEN_POWER"),
    APP_OVEN_REACTIVE_POWER("APP_OVEN_REACTIVE_POWER"),
    APP_MULTIMEDIA_POWER("APP_MULTIMEDIA_POWER"),
    APP_MULTIMEDIA_REACTIVE_POWER("APP_MULTIMEDIA_REACTIVE_POWER"),
    APP_MULTIMEDIA_ENERGY("APP_MULTIMEDIA_ENERGY"),
    APP_CAR_HEATER_ENERGY("APP_CAR_HEATER_ENERGY"),
    APP_CAR_HEATER_POWER("APP_CAR_HEATER_POWER"),
    APP_CAR_HEATER_REACTIVE_POWER("APP_CAR_HEATER_REACTIVE_POWER"),
    APP_OTHER_POWER("APP_OTHER_POWER"),
    APP_OTHER_ENERGY("APP_OTHER_ENERGY"),
    APP_OTHER_REACTIVE_POWER("APP_OTHER_REACTIVE_POWER"),
    LIGHTING_POWER("LIGHTING_POWER"),
    LIGHTING_ENERGY("LIGHTING_ENERGY"),
    LIGHTING_REACTIVE_POWER("LIGHTING_REACTIVE_POWER"),
    TOTAL_ELECTRICITY_REACTIVE_POWER("TOTAL_ELECTRICITY_REACTIVE_POWER"),
    TOTAL_ELECTRICITY_VOLTAGE("TOTAL_ELECTRICITY_VOLTAGE"),
    COOLING_REACTIVE_POWER("COOLING_REACTIVE_POWER"),
    ROOM_HVAC_MODE("ROOM_HVAC_MODE"),
    ROOM_HVAC_POWER("ROOM_HVAC_POWER"),
    ROOM_INDOOR_TEMP("ROOM_INDOOR_TEMP"),
    ROOM_SETPOINT_TEMP("ROOM_SETPOINT_TEMP"),
    CUSTOMER_ENERGY_SAVING("CUSTOMER_ENERGY_SAVING");
    private final String value;

    SensorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SensorType fromValue(String v) {
        for (SensorType c: SensorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
