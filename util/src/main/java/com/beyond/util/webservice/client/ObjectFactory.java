
package com.beyond.util.webservice.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.beyond.util.webservice.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _HYBMethod_QNAME = new QName("http://service.webservice.util.beyond.com/", "HYBMethod");
    private final static QName _HYBHeader_QNAME = new QName("http://service.webservice.util.beyond.com/", "HYBHeader");
    private final static QName _HYBMethodResponse_QNAME = new QName("http://service.webservice.util.beyond.com/", "HYBMethodResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.beyond.util.webservice.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HYBMethodResponse }
     * 
     */
    public HYBMethodResponse createHYBMethodResponse() {
        return new HYBMethodResponse();
    }

    /**
     * Create an instance of {@link HYBMethod }
     * 
     */
    public HYBMethod createHYBMethod() {
        return new HYBMethod();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HYBMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.util.beyond.com/", name = "HYBMethod")
    public JAXBElement<HYBMethod> createHYBMethod(HYBMethod value) {
        return new JAXBElement<HYBMethod>(_HYBMethod_QNAME, HYBMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.util.beyond.com/", name = "HYBHeader")
    public JAXBElement<String> createHYBHeader(String value) {
        return new JAXBElement<String>(_HYBHeader_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HYBMethodResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.util.beyond.com/", name = "HYBMethodResponse")
    public JAXBElement<HYBMethodResponse> createHYBMethodResponse(HYBMethodResponse value) {
        return new JAXBElement<HYBMethodResponse>(_HYBMethodResponse_QNAME, HYBMethodResponse.class, null, value);
    }

}
