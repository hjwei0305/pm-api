
package com.donlim.pm.webservice.eipcenter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SvcHdrTypes complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SvcHdrTypes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RCODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RDESC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ESBCODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SvcHdrTypes", propOrder = {
    "rcode",
    "rdesc",
    "esbcode"
})
public class SvcHdrTypes {

    @XmlElement(name = "RCODE", required = true)
    protected String rcode;
    @XmlElement(name = "RDESC")
    protected String rdesc;
    @XmlElement(name = "ESBCODE")
    protected String esbcode;

    /**
     * 获取rcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRCODE() {
        return rcode;
    }

    /**
     * 设置rcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRCODE(String value) {
        this.rcode = value;
    }

    /**
     * 获取rdesc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRDESC() {
        return rdesc;
    }

    /**
     * 设置rdesc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRDESC(String value) {
        this.rdesc = value;
    }

    /**
     * 获取esbcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESBCODE() {
        return esbcode;
    }

    /**
     * 设置esbcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESBCODE(String value) {
        this.esbcode = value;
    }

}
