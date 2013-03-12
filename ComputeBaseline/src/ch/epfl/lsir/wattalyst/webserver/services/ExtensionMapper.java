/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

package ch.epfl.lsir.wattalyst.webserver.services;

/**
 * ExtensionMapper class
 */
@SuppressWarnings({ "unchecked", "unused" })
public class ExtensionMapper {

	public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
			java.lang.String typeName, javax.xml.stream.XMLStreamReader reader)
			throws java.lang.Exception {

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getLocationSensors".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensors.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "locationListResultContainer".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.LocationListResultContainer.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "sensorDto".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.SensorDto.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "sensorResultContainer".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.SensorResultContainer.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getSubLocationsResponse".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetSubLocationsResponse.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "quantizationDto".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.QuantizationDto.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getAllLocationsResponse".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetAllLocationsResponse.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "wsResultContainer".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.WsResultContainer.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "locationTypeDto".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.LocationTypeDto.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "locationDto".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.LocationDto.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getLocationSensorsByAnnotation".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsByAnnotation.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getLocation".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetLocation.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "valueResultContainer".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.ValueResultContainer.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getSensor".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetSensor.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "aValueDto".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.AValueDto.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "metaDatumDto".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.MetaDatumDto.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getLocationSensorsResponse".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsResponse.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getSubLocations".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetSubLocations.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "status".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.Status.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "categoryDto".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.CategoryDto.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getLastValueForSensorResponse".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetLastValueForSensorResponse.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getLocationSensorsByCategory".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsByCategory.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "fullQualifiedNameDto".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.FullQualifiedNameDto.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getAllLocations".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetAllLocations.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getValuesForSensorByRangeResponse".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorByRangeResponse.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "sensorListResultContainer".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.SensorListResultContainer.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getLocationSensorsByAnnotationResponse".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsByAnnotationResponse.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getValuesForSensor".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensor.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getLocationSensorsByCategoryResponse".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetLocationSensorsByCategoryResponse.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getValuesForSensorResponse".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorResponse.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "baseDtoDomain".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.BaseDtoDomain.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "unitDto".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.UnitDto.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "baseDto".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.BaseDto.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getSensorResponse".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetSensorResponse.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "fullQualifiedEntityDto".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.FullQualifiedEntityDto.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "request".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.Request.Factory.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getLocationResponse".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetLocationResponse.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getLastValueForSensor".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetLastValueForSensor.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "valueListResultContainer".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.ValueListResultContainer.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "locationResultContainer".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.LocationResultContainer.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "quantizationBase".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.QuantizationBase.Factory
					.parse(reader);

		}

		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "getValuesForSensorByRange".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.GetValuesForSensorByRange.Factory
					.parse(reader);

		}
		
		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "numericValueDto".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.NumericValueDto.Factory
					.parse(reader);

		}
		
		if ("http://services.wattalyst.org/".equals(namespaceURI)
				&& "missingValueDto".equals(typeName)) {

			return ch.epfl.lsir.wattalyst.webserver.services.MissingValueDto.Factory
					.parse(reader);

		}

		throw new org.apache.axis2.databinding.ADBException("Unsupported type "
				+ namespaceURI + " " + typeName);
	}

}
