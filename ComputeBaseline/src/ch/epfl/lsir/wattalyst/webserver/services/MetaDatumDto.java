/**
 * MetaDatumDto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

package ch.epfl.lsir.wattalyst.webserver.services;

/**
 * MetaDatumDto bean class
 */
@SuppressWarnings({ "unchecked", "unused" })
public class MetaDatumDto extends ch.epfl.lsir.wattalyst.webserver.services.BaseDtoDomain
		implements org.apache.axis2.databinding.ADBBean {
	/*
	 * This type was generated from the piece of schema that had name =
	 * metaDatumDto Namespace URI = http://services.wattalyst.org/ Namespace
	 * Prefix = ns1
	 */

	/**
	 * field for Key
	 */

	protected java.lang.String localKey;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localKeyTracker = false;

	public boolean isKeySpecified() {
		return localKeyTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getKey() {
		return localKey;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Key
	 */
	public void setKey(java.lang.String param) {
		localKeyTracker = param != null;

		this.localKey = param;

	}

	/**
	 * field for Value
	 */

	protected java.lang.String localValue;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localValueTracker = false;

	public boolean isValueSpecified() {
		return localValueTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getValue() {
		return localValue;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Value
	 */
	public void setValue(java.lang.String param) {
		localValueTracker = param != null;

		this.localValue = param;

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
					"type", namespacePrefix + ":metaDatumDto", xmlWriter);
		} else {
			writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance",
					"type", "metaDatumDto", xmlWriter);
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
		if (localKeyTracker) {
			namespace = "";
			writeStartElement(null, namespace, "key", xmlWriter);

			if (localKey == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException(
						"key cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localKey);

			}

			xmlWriter.writeEndElement();
		}
		if (localValueTracker) {
			namespace = "";
			writeStartElement(null, namespace, "value", xmlWriter);

			if (localValue == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException(
						"value cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localValue);

			}

			xmlWriter.writeEndElement();
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
				"http://services.wattalyst.org/", "metaDatumDto"));
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
		if (localKeyTracker) {
			elementList.add(new javax.xml.namespace.QName("", "key"));

			if (localKey != null) {
				elementList
						.add(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localKey));
			} else {
				throw new org.apache.axis2.databinding.ADBException(
						"key cannot be null!!");
			}
		}
		if (localValueTracker) {
			elementList.add(new javax.xml.namespace.QName("", "value"));

			if (localValue != null) {
				elementList
						.add(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localValue));
			} else {
				throw new org.apache.axis2.databinding.ADBException(
						"value cannot be null!!");
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
		public static MetaDatumDto parse(javax.xml.stream.XMLStreamReader reader)
				throws java.lang.Exception {
			MetaDatumDto object = new MetaDatumDto();

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

						if (!"metaDatumDto".equals(type)) {
							// find namespace for the prefix
							java.lang.String nsUri = reader
									.getNamespaceContext().getNamespaceURI(
											nsPrefix);
							return (MetaDatumDto) ch.epfl.lsir.wattalyst.webserver.services.ExtensionMapper
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
						&& new javax.xml.namespace.QName("", "key")
								.equals(reader.getName())) {

					nillableValue = reader.getAttributeValue(
							"http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue)
							|| "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "key" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setKey(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("", "value")
								.equals(reader.getName())) {

					nillableValue = reader.getAttributeValue(
							"http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue)
							|| "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "value" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setValue(org.apache.axis2.databinding.utils.ConverterUtil
							.convertToString(content));

					reader.next();

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
