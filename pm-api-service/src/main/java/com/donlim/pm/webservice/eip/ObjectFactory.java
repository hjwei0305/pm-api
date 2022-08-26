
package com.donlim.pm.webservice.eip;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.donlim.pm.webservice.eip package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.donlim.pm.webservice.eip
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SYNCHECKSEARCH }
     * 
     */
    public SYNCHECKSEARCH createSYNCHECKSEARCH() {
        return new SYNCHECKSEARCH();
    }

    /**
     * Create an instance of {@link SVCHDR }
     * 
     */
    public SVCHDR createSVCHDR() {
        return new SVCHDR();
    }

    /**
     * Create an instance of {@link APPHDR }
     * 
     */
    public APPHDR createAPPHDR() {
        return new APPHDR();
    }

    /**
     * Create an instance of {@link APPBODY }
     * 
     */
    public APPBODY createAPPBODY() {
        return new APPBODY();
    }

    /**
     * Create an instance of {@link SYNCHECKSEARCHResponse }
     * 
     */
    public SYNCHECKSEARCHResponse createSYNCHECKSEARCHResponse() {
        return new SYNCHECKSEARCHResponse();
    }

    /**
     * Create an instance of {@link SVCBODYS }
     * 
     */
    public SVCBODYS createSVCBODYS() {
        return new SVCBODYS();
    }

    /**
     * Create an instance of {@link APPBODYS }
     * 
     */
    public APPBODYS createAPPBODYS() {
        return new APPBODYS();
    }

    /**
     * Create an instance of {@link SVCHDRS }
     * 
     */
    public SVCHDRS createSVCHDRS() {
        return new SVCHDRS();
    }

    /**
     * Create an instance of {@link APPHDRS }
     * 
     */
    public APPHDRS createAPPHDRS() {
        return new APPHDRS();
    }

}
