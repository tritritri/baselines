
package org.wattalyst.services.secured;

import java.net.MalformedURLException;
import java.net.URL;
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
@WebServiceClient(name = "SecuredDataAccessService", targetNamespace = "http://secured.services.wattalyst.org/", wsdlLocation = "http://wattalyst-ci.se.rwth-aachen.de:7070/SecuredDataAccessService/SecuredDataAccess?wsdl")
public class SecuredDataAccessService
    extends Service
{

    private final static URL SECUREDDATAACCESSSERVICE_WSDL_LOCATION;
    private final static WebServiceException SECUREDDATAACCESSSERVICE_EXCEPTION;
    private final static QName SECUREDDATAACCESSSERVICE_QNAME = new QName("http://secured.services.wattalyst.org/", "SecuredDataAccessService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://wattalyst-ci.se.rwth-aachen.de/SecuredDataAccessService/SecuredDataAccess?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SECUREDDATAACCESSSERVICE_WSDL_LOCATION = url;
        SECUREDDATAACCESSSERVICE_EXCEPTION = e;
    }

    public SecuredDataAccessService() {
        super(__getWsdlLocation(), SECUREDDATAACCESSSERVICE_QNAME);
    }

    public SecuredDataAccessService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SECUREDDATAACCESSSERVICE_QNAME, features);
    }

    public SecuredDataAccessService(URL wsdlLocation) {
        super(wsdlLocation, SECUREDDATAACCESSSERVICE_QNAME);
    }

    public SecuredDataAccessService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SECUREDDATAACCESSSERVICE_QNAME, features);
    }

    public SecuredDataAccessService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SecuredDataAccessService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SecuredDataAccess
     */
    @WebEndpoint(name = "SecuredDataAccessPort")
    public SecuredDataAccess getSecuredDataAccessPort() {
        return super.getPort(new QName("http://secured.services.wattalyst.org/", "SecuredDataAccessPort"), SecuredDataAccess.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SecuredDataAccess
     */
    @WebEndpoint(name = "SecuredDataAccessPort")
    public SecuredDataAccess getSecuredDataAccessPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://secured.services.wattalyst.org/", "SecuredDataAccessPort"), SecuredDataAccess.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SECUREDDATAACCESSSERVICE_EXCEPTION!= null) {
            throw SECUREDDATAACCESSSERVICE_EXCEPTION;
        }
        return SECUREDDATAACCESSSERVICE_WSDL_LOCATION;
    }

}
