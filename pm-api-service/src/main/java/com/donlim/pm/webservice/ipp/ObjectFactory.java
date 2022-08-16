
package com.donlim.pm.webservice.ipp;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.donlim.pm.webservice.ipp package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.donlim.pm.webservice.ipp
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DONLIMIMOSQUERYSYNCResponse }
     * 
     */
    public DONLIMIMOSQUERYSYNCResponse createDONLIMIMOSQUERYSYNCResponse() {
        return new DONLIMIMOSQUERYSYNCResponse();
    }

    /**
     * Create an instance of {@link SvcHdrsType }
     * 
     */
    public SvcHdrsType createSvcHdrsType() {
        return new SvcHdrsType();
    }

    /**
     * Create an instance of {@link DONLIMIMOSQUERYSYNC_Type }
     * 
     */
    public DONLIMIMOSQUERYSYNC_Type createDONLIMIMOSQUERYSYNC_Type() {
        return new DONLIMIMOSQUERYSYNC_Type();
    }

    /**
     * Create an instance of {@link SvcHdrType }
     * 
     */
    public SvcHdrType createSvcHdrType() {
        return new SvcHdrType();
    }

}
