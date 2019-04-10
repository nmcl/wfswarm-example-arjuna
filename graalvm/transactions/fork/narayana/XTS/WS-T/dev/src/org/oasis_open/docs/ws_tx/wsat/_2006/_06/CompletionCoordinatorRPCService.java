package org.oasis_open.docs.ws_tx.wsat._2006._06;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2-12/14/2009 02:16 PM(ramkris)-
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CompletionCoordinatorRPCService", targetNamespace = "http://docs.oasis-open.org/ws-tx/wsat/2006/06", wsdlLocation = "wsdl/wsat-completion-coordinator-rpc-binding.wsdl")
public class CompletionCoordinatorRPCService
    extends Service
{

    private final static URL COMPLETIONCOORDINATORRPCSERVICE_WSDL_LOCATION;
    private final static WebServiceException COMPLETIONCOORDINATORRPCSERVICE_EXCEPTION;
    private final static QName COMPLETIONCOORDINATORRPCSERVICE_QNAME = new QName("http://docs.oasis-open.org/ws-tx/wsat/2006/06", "CompletionCoordinatorRPCService");

    static {
        COMPLETIONCOORDINATORRPCSERVICE_WSDL_LOCATION = CompletionCoordinatorRPCService.class.getResource("wsdl/wsat-completion-coordinator-rpc-binding.wsdl");
        WebServiceException e = null;
        if (COMPLETIONCOORDINATORRPCSERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'wsdl/wsat-completion-coordinator-rpc-binding.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        COMPLETIONCOORDINATORRPCSERVICE_EXCEPTION = e;
    }

    public CompletionCoordinatorRPCService() {
        super(__getWsdlLocation(), COMPLETIONCOORDINATORRPCSERVICE_QNAME);
    }

    public CompletionCoordinatorRPCService(URL wsdlLocation) {
        super(wsdlLocation, COMPLETIONCOORDINATORRPCSERVICE_QNAME);
    }

    public CompletionCoordinatorRPCService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns CompletionCoordinatorRPCPortType
     */
    @WebEndpoint(name = "CompletionCoordinatorRPCPortType")
    public CompletionCoordinatorRPCPortType getCompletionCoordinatorRPCPortType() {
        return super.getPort(new QName("http://docs.oasis-open.org/ws-tx/wsat/2006/06", "CompletionCoordinatorRPCPortType"), CompletionCoordinatorRPCPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CompletionCoordinatorRPCPortType
     */
    @WebEndpoint(name = "CompletionCoordinatorRPCPortType")
    public CompletionCoordinatorRPCPortType getCompletionCoordinatorRPCPortType(WebServiceFeature... features) {
        return super.getPort(new QName("http://docs.oasis-open.org/ws-tx/wsat/2006/06", "CompletionCoordinatorRPCPortType"), CompletionCoordinatorRPCPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (COMPLETIONCOORDINATORRPCSERVICE_EXCEPTION!= null) {
            throw COMPLETIONCOORDINATORRPCSERVICE_EXCEPTION;
        }
        return COMPLETIONCOORDINATORRPCSERVICE_WSDL_LOCATION;
    }

}
