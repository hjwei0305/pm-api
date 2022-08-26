
package com.donlim.pm.webservice.eip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SVCHDR complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SVCHDR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SOURCEID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DESTINATIONID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IPADDRESS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SVCHDR", propOrder = {
    "sourceid",
    "destinationid",
    "type",
    "ipaddress",
    "bo"
})
public class SVCHDR {

    @XmlElement(name = "SOURCEID", required = true)
    protected String sourceid;
    @XmlElement(name = "DESTINATIONID", required = true)
    protected String destinationid;
    @XmlElement(name = "TYPE", required = true)
    protected String type;
    @XmlElement(name = "IPADDRESS", required = true)
    protected String ipaddress;
    @XmlElement(name = "BO", required = true)
    protected String bo;

    /**
     * 获取sourceid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOURCEID() {
        return sourceid;
    }

    /**
     * 设置sourceid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOURCEID(String value) {
        this.sourceid = value;
    }

    /**
     * 获取destinationid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDESTINATIONID() {
        return destinationid;
    }

    /**
     * 设置destinationid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDESTINATIONID(String value) {
        this.destinationid = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTYPE() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTYPE(String value) {
        this.type = value;
    }

    /**
     * 获取ipaddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPADDRESS() {
        return ipaddress;
    }

    /**
     * 设置ipaddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPADDRESS(String value) {
        this.ipaddress = value;
    }

    /**
     * 获取bo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBO() {
        return bo;
    }

    /**
     * 设置bo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBO(String value) {
        this.bo = value;
    }

}
