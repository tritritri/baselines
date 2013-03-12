/**
 * SensorDto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

package ch.epfl.lsir.wattalyst.webserver.services;

/**
 * SensorDto bean class
 */
@SuppressWarnings({ "unchecked", "unused" })
public class SensorDto extends ch.epfl.lsir.wattalyst.webserver.services.FullQualifiedEntityDto
		implements org.apache.axis2.databinding.ADBBean {
	/*
	 * This type was generated from the piece of schema that had name =
	 * sensorDto Namespace URI = http://services.wattalyst.org/ Namespace Prefix
	 * = ns1
	 */

	/**
	 * field for Quantization
	 */

	protected ch.epfl.lsir.wattalyst.webserver.services.QuantizationDto localQuantization;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localQuantizationTracker = false;

	public boolean isQuantizationSpecified() {
		return localQuantizationTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return org.wattalyst.services.QuantizationDto
	 */
	public ch.epfl.lsir.wattalyst.webserver.services.QuantizationDto getQuantization() {
		return localQuantization;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Quantization
	 */
	public void setQuantization(ch.epfl.lsir.wattalyst.webserver.services.QuantizationDto param) {
		localQuantizationTracker = param != null;

		this.localQuantization = param;

	}

	/**
	 * field for Category
	 */

	protected ch.epfl.lsir.wattalyst.webserver.services.CategoryDto localCategory;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localCategoryTracker = false;

	public boolean isCategorySpecified() {
		return localCategoryTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return org.wattalyst.services.CategoryDto
	 */
	public ch.epfl.lsir.wattalyst.webserver.services.CategoryDto getCategory() {
		return localCategory;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Category
	 */
	public void setCategory(ch.epfl.lsir.wattalyst.webserver.services.CategoryDto param) {
		localCategoryTracker = param != null;

		this.localCategory = param;

	}

	/**
	 * field for Maximum
	 */

	protected double localMaximum;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localMaximumTracker = false;

	public boolean isMaximumSpecified() {
		return localMaximumTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return double
	 */
	public double getMaximum() {
		return localMaximum;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Maximum
	 */
	public void setMaximum(double param) {

		// setting primitive attribute tracker to true
		localMaximumTracker = !java.lang.Double.isNaN(param);

		this.localMaximum = param;

	}

	/**
	 * field for Minimum
	 */

	protected double localMinimum;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localMinimumTracker = false;

	public boolean isMinimumSpecified() {
		return localMinimumTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return double
	 */
	public double getMinimum() {
		return localMinimum;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Minimum
	 */
	public void setMinimum(double param) {

		// setting primitive attribute tracker to true
		localMinimumTracker = !java.lang.Double.isNaN(param);

		this.localMinimum = param;

	}

	/**
	 * field for FirstValueTimestamp
	 */

	protected long localFirstValueTimestamp;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localFirstValueTimestampTracker = false;

	public boolean isFirstValueTimestampSpecified() {
		return localFirstValueTimestampTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return long
	 */
	public long getFirstValueTimestamp() {
		return localFirstValueTimestamp;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            FirstValueTimestamp
	 */
	public void setFirstValueTimestamp(long param) {

		// setting primitive attribute tracker to true
		localFirstValueTimestampTracker = param != java.lang.Long.MIN_VALUE;

		this.localFirstValueTimestamp = param;

	}

	/**
	 * field for LastValueTimestamp
	 */

	protected long localLastValueTimestamp;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localLastValueTimestampTracker = false;

	public boolean isLastValueTimestampSpecified() {
		return localLastValueTimestampTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return long
	 */
	public long getLastValueTimestamp() {
		return localLastValueTimestamp;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            LastValueTimestamp
	 */
	public void setLastValueTimestamp(long param) {

		// setting primitive attribute tracker to true
		localLastValueTimestampTracker = param != java.lang.Long.MIN_VALUE;

		this.localLastValueTimestamp = param;

	}

	/**
	 * field for Comment
	 */

	protected java.lang.String localComment;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localCommentTracker = false;

	public boolean isCommentSpecified() {
		return localCommentTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getComment() {
		return localComment;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Comment
	 */
	public void setComment(java.lang.String param) {
		localCommentTracker = param != null;

		this.localComment = param;

	}

	/**
	 * field for Unit
	 */

	protected ch.epfl.lsir.wattalyst.webserver.services.UnitDto localUnit;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localUnitTracker = false;

	public boolean isUnitSpecified() {
		return localUnitTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return org.wattalyst.services.UnitDto
	 */
	public ch.epfl.lsir.wattalyst.webserver.services.UnitDto getUnit() {
		return localUnit;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Unit
	 */
	public void setUnit(ch.epfl.lsir.wattalyst.webserver.services.UnitDto param) {
		localUnitTracker = param != null;

		this.localUnit = param;

	}

	/**
	 * field for MetaDatas This was an Array!
	 */

	protected ch.epfl.lsir.wattalyst.webserver.services.MetaDatumDto[] localMetaDatas;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localMetaDatasTracker = false;

	public boolean isMetaDatasSpecified() {
		return localMetaDatasTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return org.wattalyst.services.MetaDatumDto[]
	 */
	public ch.epfl.lsir.wattalyst.webserver.services.MetaDatumDto[] getMetaDatas() {
		return localMetaDatas;
	}

	/**
	 * validate the array for MetaDatas
	 */
	protected void validateMetaDatas(ch.epfl.lsir.wattalyst.webserver.services.MetaDatumDto[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            MetaDatas
	 */
	public void setMetaDatas(ch.epfl.lsir.wattalyst.webserver.services.MetaDatumDto[] param) {

		validateMetaDatas(param);

		localMetaDatasTracker = true;

		this.localMetaDatas = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            org.wattalyst.services.MetaDatumDto
	 */
	public void addMetaDatas(ch.epfl.lsir.wattalyst.webserver.services.MetaDatumDto param) {
		if (localMetaDatas == null) {
			localMetaDatas = new ch.epfl.lsir.wattalyst.webserver.services.MetaDatumDto[] {};
		}

		// update the setting tracker
		localMetaDatasTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil
				.toList(localMetaDatas);
		list.add(param);
		this.localMetaDatas = (ch.epfl.lsir.wattalyst.webserver.services.MetaDatumDto[]) list
				.toArray(new ch.epfl.lsir.wattalyst.webserver.services.MetaDatumDto[list.size()]);

	}

	/**
	 * 
	 * @param parentQName
	 * @param factory
	 * @return org.apache.axiom.om.OMElement
	 */
	public org.apache.axiom.om.OMElement getOMElement(
			final javax.xml.namespace.QName parentQName,
			final org.apache.axiom.om.OMFactory factory)
			throws org.apache.axis2.databinding.ADBException {

		org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(
				this, parentQName);
		return factory.createOMElement(dataSource, parentQName);

	}

	public void serialize(final javax.xml.namespace.QName parentQName,
			javax.xml.stream.XMLStreamWriter xmlWriter)
			throws javax.xml.stream.XMLStreamException,
			org.apache.axis2.databinding.ADBException {
		serialize(parentQName, xmlWriter, false);
	}

	public void serialize(final javax.xml.namespace.QName parentQName,
			javax.xml.stream.XMLStreamWriter xmlWriter, boolean serializeType)
			throws javax.xml.stream.XMLStreamException,
			org.apache.axis2.databinding.ADBException {

		java.lang.String prefix = null;
		java.lang.String namespace = null;

		prefix = parentQName.getPrefix();
		namespace = parentQName.getNamespaceURI();
		writeStartElement(prefix, namespace, parentQName.getLocalPart(),
				xmlWriter);

		java.lang.String namespacePrefix = registerPrefix(xmlWriter,
				"http://services.wattalyst.org/");
		if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
			writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance",
					"type", namespacePrefix + ":sensorDto", xmlWriter);
		} else {
			writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance",
					"type", "sensorDto", xmlWriter);
		}

		if (localEntityIdTracker) {
			namespace = "";
			writeStartElement(null, namespace, "entityId", xmlWriter);

			if (localEntityId == java.lang.Long.MIN_VALUE) {

				throw new org.apache.axis2.databinding.ADBException(
						"entityId cannot be null!!");

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localEntityId));
			}

			xmlWriter.writeEndElement();
		}
		if (localRequestsTracker) {
			if (localRequests != null) {
				for (int i = 0; i < localRequests.length; i++) {
					if (localRequests[i] != null) {
						localRequests[i].serialize(
								new javax.xml.namespace.QName("", "requests"),
								xmlWriter);
					} else {

						writeStartElement(null, "", "requests", xmlWriter);

						// write the nil attribute
						writeAttribute("xsi",
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil", "1", xmlWriter);
						xmlWriter.writeEndElement();

					}

				}
			} else {

				writeStartElement(null, "", "requests", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi",
						"http://www.w3.org/2001/XMLSchema-instance", "nil",
						"1", xmlWriter);
				xmlWriter.writeEndElement();

			}
		}
		if (localFullQualifiedNameTracker) {
			if (localFullQualifiedName == null) {
				throw new org.apache.axis2.databinding.ADBException(
						"fullQualifiedName cannot be null!!");
			}
			localFullQualifiedName.serialize(new javax.xml.namespace.QName("",
					"fullQualifiedName"), xmlWriter);
		}
		if (localQuantizationTracker) {
			if (localQuantization == null) {
				throw new org.apache.axis2.databinding.ADBException(
						"quantization cannot be null!!");
			}
			localQuantization.serialize(new javax.xml.namespace.QName("",
					"quantization"), xmlWriter);
		}
		if (localCategoryTracker) {
			if (localCategory == null) {
				throw new org.apache.axis2.databinding.ADBException(
						"category cannot be null!!");
			}
			localCategory.serialize(new javax.xml.namespace.QName("",
					"category"), xmlWriter);
		}
		if (localMaximumTracker) {
			namespace = "";
			writeStartElement(null, namespace, "maximum", xmlWriter);

			if (java.lang.Double.isNaN(localMaximum)) {

				throw new org.apache.axis2.databinding.ADBException(
						"maximum cannot be null!!");

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localMaximum));
			}

			xmlWriter.writeEndElement();
		}
		if (localMinimumTracker) {
			namespace = "";
			writeStartElement(null, namespace, "minimum", xmlWriter);

			if (java.lang.Double.isNaN(localMinimum)) {

				throw new org.apache.axis2.databinding.ADBException(
						"minimum cannot be null!!");

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localMinimum));
			}

			xmlWriter.writeEndElement();
		}
		if (localFirstValueTimestampTracker) {
			namespace = "";
			writeStartElement(null, namespace, "firstValueTimestamp", xmlWriter);

			if (localFirstValueTimestamp == java.lang.Long.MIN_VALUE) {

				throw new org.apache.axis2.databinding.ADBException(
						"firstValueTimestamp cannot be null!!");

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localFirstValueTimestamp));
			}

			xmlWriter.writeEndElement();
		}
		if (localLastValueTimestampTracker) {
			namespace = "";
			writeStartElement(null, namespace, "lastValueTimestamp", xmlWriter);

			if (localLastValueTimestamp == java.lang.Long.MIN_VALUE) {

				throw new org.apache.axis2.databinding.ADBException(
						"lastValueTimestamp cannot be null!!");

			} else {
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localLastValueTimestamp));
			}

			xmlWriter.writeEndElement();
		}
		if (localCommentTracker) {
			namespace = "";
			writeStartElement(null, namespace, "comment", xmlWriter);

			if (localComment == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException(
						"comment cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localComment);

			}

			xmlWriter.writeEndElement();
		}
		if (localUnitTracker) {
			if (localUnit == null) {
				throw new org.apache.axis2.databinding.ADBException(
						"unit cannot be null!!");
			}
			localUnit.serialize(new javax.xml.namespace.QName("", "unit"),
					xmlWriter);
		}
		if (localMetaDatasTracker) {
			if (localMetaDatas != null) {
				for (int i = 0; i < localMetaDatas.length; i++) {
					if (localMetaDatas[i] != null) {
						localMetaDatas[i].serialize(
								new javax.xml.namespace.QName("", "metaDatas"),
								xmlWriter);
					} else {

						writeStartElement(null, "", "metaDatas", xmlWriter);

						// write the nil attribute
						writeAttribute("xsi",
								"http://www.w3.org/2001/XMLSchema-instance",
								"nil", "1", xmlWriter);
						xmlWriter.writeEndElement();

					}

				}
			} else {

				writeStartElement(null, "", "metaDatas", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi",
						"http://www.w3.org/2001/XMLSchema-instance", "nil",
						"1", xmlWriter);
				xmlWriter.writeEndElement();

			}
		}
		xmlWriter.writeEndElement();

	}

	private static java.lang.String generatePrefix(java.lang.String namespace) {
		if (namespace.equals("http://services.wattalyst.org/")) {
			return "ns1";
		}
		return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
	}

	/**
	 * Utility method to write an element start tag.
	 */
	private void writeStartElement(java.lang.String prefix,
			java.lang.String namespace, java.lang.String localPart,
			javax.xml.stream.XMLStreamWriter xmlWriter)
			throws javax.xml.stream.XMLStreamException {
		java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
		if (writerPrefix != null) {
			xmlWriter.writeStartElement(namespace, localPart);
		} else {
			if (namespace.length() == 0) {
				prefix = "";
			} else if (prefix == null) {
				prefix = generatePrefix(namespace);
			}

			xmlWriter.writeStartElement(prefix, localPart, namespace);
			xmlWriter.writeNamespace(prefix, namespace);
			xmlWriter.setPrefix(prefix, namespace);
		}
	}

	/**
	 * Util method to write an attribute with the ns prefix
	 */
	private void writeAttribute(java.lang.String prefix,
			java.lang.String namespace, java.lang.String attName,
			java.lang.String attValue,
			javax.xml.stream.XMLStreamWriter xmlWriter)
			throws javax.xml.stream.XMLStreamException {
		if (xmlWriter.getPrefix(namespace) == null) {
			xmlWriter.writeNamespace(prefix, namespace);
			xmlWriter.setPrefix(prefix, namespace);
		}
		xmlWriter.writeAttribute(namespace, attName, attValue);
	}

	/**
	 * Util method to write an attribute without the ns prefix
	 */
	private void writeAttribute(java.lang.String namespace,
			java.lang.String attName, java.lang.String attValue,
			javax.xml.stream.XMLStreamWriter xmlWriter)
			throws javax.xml.stream.XMLStreamException {
		if (namespace.equals("")) {
			xmlWriter.writeAttribute(attName, attValue);
		} else {
			registerPrefix(xmlWriter, namespace);
			xmlWriter.writeAttribute(namespace, attName, attValue);
		}
	}

	/**
	 * Util method to write an attribute without the ns prefix
	 */
	private void writeQNameAttribute(java.lang.String namespace,
			java.lang.String attName, javax.xml.namespace.QName qname,
			javax.xml.stream.XMLStreamWriter xmlWriter)
			throws javax.xml.stream.XMLStreamException {

		java.lang.String attributeNamespace = qname.getNamespaceURI();
		java.lang.String attributePrefix = xmlWriter
				.getPrefix(attributeNamespace);
		if (attributePrefix == null) {
			attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
		}
		java.lang.String attributeValue;
		if (attributePrefix.trim().length() > 0) {
			attributeValue = attributePrefix + ":" + qname.getLocalPart();
		} else {
			attributeValue = qname.getLocalPart();
		}

		if (namespace.equals("")) {
			xmlWriter.writeAttribute(attName, attributeValue);
		} else {
			registerPrefix(xmlWriter, namespace);
			xmlWriter.writeAttribute(namespace, attName, attributeValue);
		}
	}

	/**
	 * method to handle Qnames
	 */

	private void writeQName(javax.xml.namespace.QName qname,
			javax.xml.stream.XMLStreamWriter xmlWriter)
			throws javax.xml.stream.XMLStreamException {
		java.lang.String namespaceURI = qname.getNamespaceURI();
		if (namespaceURI != null) {
			java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
			if (prefix == null) {
				prefix = generatePrefix(namespaceURI);
				xmlWriter.writeNamespace(prefix, namespaceURI);
				xmlWriter.setPrefix(prefix, namespaceURI);
			}

			if (prefix.trim().length() > 0) {
				xmlWriter.writeCharacters(prefix
						+ ":"
						+ org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			} else {
				// i.e this is the default namespace
				xmlWriter
						.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(qname));
			}

		} else {
			xmlWriter
					.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(qname));
		}
	}

	private void writeQNames(javax.xml.namespace.QName[] qnames,
			javax.xml.stream.XMLStreamWriter xmlWriter)
			throws javax.xml.stream.XMLStreamException {

		if (qnames != null) {
			// we have to store this data until last moment since it is not
			// possible to write any
			// namespace data after writing the charactor data
			java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
			java.lang.String namespaceURI = null;
			java.lang.String prefix = null;

			for (int i = 0; i < qnames.length; i++) {
				if (i > 0) {
					stringToWrite.append(" ");
				}
				namespaceURI = qnames[i].getNamespaceURI();
				if (namespaceURI != null) {
					prefix = xmlWriter.getPrefix(namespaceURI);
					if ((prefix == null) || (prefix.length() == 0)) {
						prefix = generatePrefix(namespaceURI);
						xmlWriter.writeNamespace(prefix, namespaceURI);
						xmlWriter.setPrefix(prefix, namespaceURI);
					}

					if (prefix.trim().length() > 0) {
						stringToWrite
								.append(prefix)
								.append(":")
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil
										.convertToString(qnames[i]));
					}
				} else {
					stringToWrite
							.append(org.apache.axis2.databinding.utils.ConverterUtil
									.convertToString(qnames[i]));
				}
			}
			xmlWriter.writeCharacters(stringToWrite.toString());
		}

	}

	/**
	 * Register a namespace prefix
	 */
	private java.lang.String registerPrefix(
			javax.xml.stream.XMLStreamWriter xmlWriter,
			java.lang.String namespace)
			throws javax.xml.stream.XMLStreamException {
		java.lang.String prefix = xmlWriter.getPrefix(namespace);
		if (prefix == null) {
			prefix = generatePrefix(namespace);
			javax.xml.namespace.NamespaceContext nsContext = xmlWriter
					.getNamespaceContext();
			while (true) {
				java.lang.String uri = nsContext.getNamespaceURI(prefix);
				if (uri == null || uri.length() == 0) {
					break;
				}
				prefix = org.apache.axis2.databinding.utils.BeanUtil
						.getUniquePrefix();
			}
			xmlWriter.writeNamespace(prefix, namespace);
			xmlWriter.setPrefix(prefix, namespace);
		}
		return prefix;
	}

	/**
	 * databinding method to get an XML representation of this object
	 * 
	 */
	public javax.xml.stream.XMLStreamReader getPullParser(
			javax.xml.namespace.QName qName)
			throws org.apache.axis2.databinding.ADBException {

		java.util.ArrayList elementList = new java.util.ArrayList();
		java.util.ArrayList attribList = new java.util.ArrayList();

		attribList.add(new javax.xml.namespace.QName(
				"http://www.w3.org/2001/XMLSchema-instance", "type"));
		attribList.add(new javax.xml.namespace.QName(
				"http://services.wattalyst.org/", "sensorDto"));
		if (localEntityIdTracker) {
			elementList.add(new javax.xml.namespace.QName("", "entityId"));

			elementList.add(org.apache.axis2.databinding.utils.ConverterUtil
					.convertToString(localEntityId));
		}
		if (localRequestsTracker) {
			if (localRequests != null) {
				for (int i = 0; i < localRequests.length; i++) {

					if (localRequests[i] != null) {
						elementList.add(new javax.xml.namespace.QName("",
								"requests"));
						elementList.add(localRequests[i]);
					} else {

						elementList.add(new javax.xml.namespace.QName("",
								"requests"));
						elementList.add(null);

					}

				}
			} else {

				elementList.add(new javax.xml.namespace.QName("", "requests"));
				elementList.add(localRequests);

			}

		}
		if (localFullQualifiedNameTracker) {
			elementList.add(new javax.xml.namespace.QName("",
					"fullQualifiedName"));

			if (localFullQualifiedName == null) {
				throw new org.apache.axis2.databinding.ADBException(
						"fullQualifiedName cannot be null!!");
			}
			elementList.add(localFullQualifiedName);
		}
		if (localQuantizationTracker) {
			elementList.add(new javax.xml.namespace.QName("", "quantization"));

			if (localQuantization == null) {
				throw new org.apache.axis2.databinding.ADBException(
						"quantization cannot be null!!");
			}
			elementList.add(localQuantization);
		}
		if (localCategoryTracker) {
			elementList.add(new javax.xml.namespace.QName("", "category"));

			if (localCategory == null) {
				throw new org.apache.axis2.databinding.ADBException(
						"category cannot be null!!");
			}
			elementList.add(localCategory);
		}
		if (localMaximumTracker) {
			elementList.add(new javax.xml.namespace.QName("", "maximum"));

			elementList.add(org.apache.axis2.databinding.utils.ConverterUtil
					.convertToString(localMaximum));
		}
		if (localMinimumTracker) {
			elementList.add(new javax.xml.namespace.QName("", "minimum"));

			elementList.add(org.apache.axis2.databinding.utils.ConverterUtil
					.convertToString(localMinimum));
		}
		if (localFirstValueTimestampTracker) {
			elementList.add(new javax.xml.namespace.QName("",
					"firstValueTimestamp"));

			elementList.add(org.apache.axis2.databinding.utils.ConverterUtil
					.convertToString(localFirstValueTimestamp));
		}
		if (localLastValueTimestampTracker) {
			elementList.add(new javax.xml.namespace.QName("",
					"lastValueTimestamp"));

			elementList.add(org.apache.axis2.databinding.utils.ConverterUtil
					.convertToString(localLastValueTimestamp));
		}
		if (localCommentTracker) {
			elementList.add(new javax.xml.namespace.QName("", "comment"));

			if (localComment != null) {
				elementList
						.add(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localComment));
			} else {
				throw new org.apache.axis2.databinding.ADBException(
						"comment cannot be null!!");
			}
		}
		if (localUnitTracker) {
			elementList.add(new javax.xml.namespace.QName("", "unit"));

			if (localUnit == null) {
				throw new org.apache.axis2.databinding.ADBException(
						"unit cannot be null!!");
			}
			elementList.add(localUnit);
		}
		if (localMetaDatasTracker) {
			if (localMetaDatas != null) {
				for (int i = 0; i < localMetaDatas.length; i++) {

					if (localMetaDatas[i] != null) {
						elementList.add(new javax.xml.namespace.QName("",
								"metaDatas"));
						elementList.add(localMetaDatas[i]);
					} else {

						elementList.add(new javax.xml.namespace.QName("",
								"metaDatas"));
						elementList.add(null);

					}

				}
			} else {

				elementList.add(new javax.xml.namespace.QName("", "metaDatas"));
				elementList.add(localMetaDatas);

			}

		}

		return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(
				qName, elementList.toArray(), attribList.toArray());

	}

	/**
	 * Factory class that keeps the parse method
	 */
	public static class Factory {

		/**
		 * static method to create the object Precondition: If this object is an
		 * element, the current or next start element starts this object and any
		 * intervening reader events are ignorable If this object is not an
		 * element, it is a complex type and the reader is at the event just
		 * after the outer start element Postcondition: If this object is an
		 * element, the reader is positioned at its end element If this object
		 * is a complex type, the reader is positioned at the end element of its
		 * outer element
		 */
		public static SensorDto parse(javax.xml.stream.XMLStreamReader reader)
				throws java.lang.Exception {
			SensorDto object = new SensorDto();

			int event;
			java.lang.String nillableValue = null;
			java.lang.String prefix = "";
			java.lang.String namespaceuri = "";
			try {

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.getAttributeValue(
						"http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
					java.lang.String fullTypeName = reader
							.getAttributeValue(
									"http://www.w3.org/2001/XMLSchema-instance",
									"type");
					if (fullTypeName != null) {
						java.lang.String nsPrefix = null;
						if (fullTypeName.indexOf(":") > -1) {
							nsPrefix = fullTypeName.substring(0,
									fullTypeName.indexOf(":"));
						}
						nsPrefix = nsPrefix == null ? "" : nsPrefix;

						java.lang.String type = fullTypeName
								.substring(fullTypeName.indexOf(":") + 1);

						if (!"sensorDto".equals(type)) {
							// find namespace for the prefix
							java.lang.String nsUri = reader
									.getNamespaceContext().getNamespaceURI(
											nsPrefix);
							return (SensorDto) ch.epfl.lsir.wattalyst.webserver.services.ExtensionMapper
									.getTypeObject(nsUri, type, reader);
						}

					}

				}

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.
				java.util.Vector handledAttributes = new java.util.Vector();

				reader.next();

				java.util.ArrayList list2 = new java.util.ArrayList();

				java.util.ArrayList list12 = new java.util.ArrayList();

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("", "entityId")
								.equals(reader.getName())) {

					nillableValue = reader.getAttributeValue(
							"http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue)
							|| "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "entityId"
										+ "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setEntityId(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToLong(content));

					reader.next();

				} // End of if for expected property start element

				else {

					object.setEntityId(java.lang.Long.MIN_VALUE);

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("", "requests")
								.equals(reader.getName())) {

					// Process the array and step past its final element's end.

					nillableValue = reader.getAttributeValue(
							"http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue)
							|| "1".equals(nillableValue)) {
						list2.add(null);
						reader.next();
					} else {
						list2.add(ch.epfl.lsir.wattalyst.webserver.services.Request.Factory
								.parse(reader));
					}
					// loop until we find a start element that is not part of
					// this array
					boolean loopDone2 = false;
					while (!loopDone2) {
						// We should be at the end element, but make sure
						while (!reader.isEndElement())
							reader.next();
						// Step out of this element
						reader.next();
						// Step to next element event.
						while (!reader.isStartElement()
								&& !reader.isEndElement())
							reader.next();
						if (reader.isEndElement()) {
							// two continuous end elements means we are exiting
							// the xml structure
							loopDone2 = true;
						} else {
							if (new javax.xml.namespace.QName("", "requests")
									.equals(reader.getName())) {

								nillableValue = reader
										.getAttributeValue(
												"http://www.w3.org/2001/XMLSchema-instance",
												"nil");
								if ("true".equals(nillableValue)
										|| "1".equals(nillableValue)) {
									list2.add(null);
									reader.next();
								} else {
									list2.add(ch.epfl.lsir.wattalyst.webserver.services.Request.Factory
											.parse(reader));
								}
							} else {
								loopDone2 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setRequests((ch.epfl.lsir.wattalyst.webserver.services.Request[]) org.apache.axis2.databinding.utils.ConverterUtil
							.convertToArray(
									ch.epfl.lsir.wattalyst.webserver.services.Request.class, list2));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("",
								"fullQualifiedName").equals(reader.getName())) {

					object.setFullQualifiedName(ch.epfl.lsir.wattalyst.webserver.services.FullQualifiedNameDto.Factory
							.parse(reader));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("", "quantization")
								.equals(reader.getName())) {

					object.setQuantization(ch.epfl.lsir.wattalyst.webserver.services.QuantizationDto.Factory
							.parse(reader));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("", "category")
								.equals(reader.getName())) {

					object.setCategory(ch.epfl.lsir.wattalyst.webserver.services.CategoryDto.Factory
							.parse(reader));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("", "maximum")
								.equals(reader.getName())) {

					nillableValue = reader.getAttributeValue(
							"http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue)
							|| "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "maximum"
										+ "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setMaximum(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToDouble(content));

					reader.next();

				} // End of if for expected property start element

				else {

					object.setMaximum(java.lang.Double.NaN);

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("", "minimum")
								.equals(reader.getName())) {

					nillableValue = reader.getAttributeValue(
							"http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue)
							|| "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "minimum"
										+ "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setMinimum(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToDouble(content));

					reader.next();

				} // End of if for expected property start element

				else {

					object.setMinimum(java.lang.Double.NaN);

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("",
								"firstValueTimestamp").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue(
							"http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue)
							|| "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "firstValueTimestamp"
										+ "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setFirstValueTimestamp(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToLong(content));

					reader.next();

				} // End of if for expected property start element

				else {

					object.setFirstValueTimestamp(java.lang.Long.MIN_VALUE);

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("",
								"lastValueTimestamp").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue(
							"http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue)
							|| "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "lastValueTimestamp"
										+ "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setLastValueTimestamp(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToLong(content));

					reader.next();

				} // End of if for expected property start element

				else {

					object.setLastValueTimestamp(java.lang.Long.MIN_VALUE);

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("", "comment")
								.equals(reader.getName())) {

					nillableValue = reader.getAttributeValue(
							"http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue)
							|| "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "comment"
										+ "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setComment(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("", "unit")
								.equals(reader.getName())) {

					object.setUnit(ch.epfl.lsir.wattalyst.webserver.services.UnitDto.Factory
							.parse(reader));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("", "metaDatas")
								.equals(reader.getName())) {

					// Process the array and step past its final element's end.

					nillableValue = reader.getAttributeValue(
							"http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue)
							|| "1".equals(nillableValue)) {
						list12.add(null);
						reader.next();
					} else {
						list12.add(ch.epfl.lsir.wattalyst.webserver.services.MetaDatumDto.Factory
								.parse(reader));
					}
					// loop until we find a start element that is not part of
					// this array
					boolean loopDone12 = false;
					while (!loopDone12) {
						// We should be at the end element, but make sure
						while (!reader.isEndElement())
							reader.next();
						// Step out of this element
						reader.next();
						// Step to next element event.
						while (!reader.isStartElement()
								&& !reader.isEndElement())
							reader.next();
						if (reader.isEndElement()) {
							// two continuous end elements means we are exiting
							// the xml structure
							loopDone12 = true;
						} else {
							if (new javax.xml.namespace.QName("", "metaDatas")
									.equals(reader.getName())) {

								nillableValue = reader
										.getAttributeValue(
												"http://www.w3.org/2001/XMLSchema-instance",
												"nil");
								if ("true".equals(nillableValue)
										|| "1".equals(nillableValue)) {
									list12.add(null);
									reader.next();
								} else {
									list12.add(ch.epfl.lsir.wattalyst.webserver.services.MetaDatumDto.Factory
											.parse(reader));
								}
							} else {
								loopDone12 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setMetaDatas((ch.epfl.lsir.wattalyst.webserver.services.MetaDatumDto[]) org.apache.axis2.databinding.utils.ConverterUtil
							.convertToArray(
									ch.epfl.lsir.wattalyst.webserver.services.MetaDatumDto.class,
									list12));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement())
					// A start element we are not expecting indicates a trailing
					// invalid property
					throw new org.apache.axis2.databinding.ADBException(
							"Unexpected subelement " + reader.getName());

			} catch (javax.xml.stream.XMLStreamException e) {
				throw new java.lang.Exception(e);
			}

			return object;
		}

	}// end of factory class

}
