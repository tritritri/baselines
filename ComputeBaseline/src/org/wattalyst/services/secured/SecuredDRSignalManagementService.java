
package org.wattalyst.services.secured;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SecuredDRSignalManagementService", targetNamespace = "http://secured.services.wattalyst.org/", wsdlLocation = "https://wattalyst.se.rwth-aachen.de/SPEC/SecuredDRSignalManagement-PROD.wsdl")
public class SecuredDRSignalManagementService
    extends Service
{

    private final static URL SECUREDDRSIGNALMANAGEMENTSERVICE_WSDL_LOCATION;
    private final static WebServiceException SECUREDDRSIGNALMANAGEMENTSERVICE_EXCEPTION;
    private final static QName SECUREDDRSIGNALMANAGEMENTSERVICE_QNAME = new QName("http://secured.services.wattalyst.org/", "SecuredDRSignalManagementService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL(setConfigUrl());
        } catch (IOException ex) {
            e = new WebServiceException(ex);
        }
        SECUREDDRSIGNALMANAGEMENTSERVICE_WSDL_LOCATION = url;
        SECUREDDRSIGNALMANAGEMENTSERVICE_EXCEPTION = e;
    }

    /**
	 * 
	 * @param port
	 * @return the authentication token
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static String setConfigUrl() throws FileNotFoundException, IOException{
		Properties prop = new Properties();
    	prop.load(new FileInputStream("webserver.config"));
		String dataWsdlUrl = prop.getProperty("drSignalWsdlUrl");
		return dataWsdlUrl;
	}

    public SecuredDRSignalManagementService() {
        super(__getWsdlLocation(), SECUREDDRSIGNALMANAGEMENTSERVICE_QNAME);
    }

    public SecuredDRSignalManagementService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SECUREDDRSIGNALMANAGEMENTSERVICE_QNAME, features);
    }

    public SecuredDRSignalManagementService(URL wsdlLocation) {
        super(wsdlLocation, SECUREDDRSIGNALMANAGEMENTSERVICE_QNAME);
    }

    public SecuredDRSignalManagementService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SECUREDDRSIGNALMANAGEMENTSERVICE_QNAME, features);
    }

    public SecuredDRSignalManagementService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SecuredDRSignalManagementService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SecuredDRSignalManagement
     */
    @WebEndpoint(name = "SecuredDRSignalManagementPort")
    public SecuredDRSignalManagement getSecuredDRSignalManagementPort() {
        return super.getPort(new QName("http://secured.services.wattalyst.org/", "SecuredDRSignalManagementPort"), SecuredDRSignalManagement.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SecuredDRSignalManagement
     */
    @WebEndpoint(name = "SecuredDRSignalManagementPort")
    public SecuredDRSignalManagement getSecuredDRSignalManagementPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://secured.services.wattalyst.org/", "SecuredDRSignalManagementPort"), SecuredDRSignalManagement.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SECUREDDRSIGNALMANAGEMENTSERVICE_EXCEPTION!= null) {
            throw SECUREDDRSIGNALMANAGEMENTSERVICE_EXCEPTION;
        }
        return SECUREDDRSIGNALMANAGEMENTSERVICE_WSDL_LOCATION;
    }

}
