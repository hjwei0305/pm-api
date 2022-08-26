
package com.donlim.pm.webservice.eip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>APPBODYS complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="APPBODYS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RESULT" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="USER01" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USER02" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USER03" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USER04" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USER05" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "APPBODYS", propOrder = {
    "result",
    "user01",
    "user02",
    "user03",
    "user04",
    "user05"
})
public class APPBODYS {

    @XmlElement(name = "RESULT")
    protected boolean result;
    @XmlElement(name = "USER01", required = true)
    protected String user01;
    @XmlElement(name = "USER02", required = true)
    protected String user02;
    @XmlElement(name = "USER03", required = true)
    protected String user03;
    @XmlElement(name = "USER04", required = true)
    protected String user04;
    @XmlElement(name = "USER05", required = true)
    protected String user05;

    /**
     * 获取result属性的值。
     * 
     */
    public boolean isRESULT() {
        return result;
    }

    /**
     * 设置result属性的值。
     * 
     */
    public void setRESULT(boolean value) {
        this.result = value;
    }

    /**
     * 获取user01属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSER01() {
        return user01;
    }

    /**
     * 设置user01属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSER01(String value) {
        this.user01 = value;
    }

    /**
     * 获取user02属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSER02() {
        return user02;
    }

    /**
     * 设置user02属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSER02(String value) {
        this.user02 = value;
    }

    /**
     * 获取user03属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSER03() {
        return user03;
    }

    /**
     * 设置user03属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSER03(String value) {
        this.user03 = value;
    }

    /**
     * 获取user04属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSER04() {
        return user04;
    }

    /**
     * 设置user04属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSER04(String value) {
        this.user04 = value;
    }

    /**
     * 获取user05属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSER05() {
        return user05;
    }

    /**
     * 设置user05属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSER05(String value) {
        this.user05 = value;
    }

}
