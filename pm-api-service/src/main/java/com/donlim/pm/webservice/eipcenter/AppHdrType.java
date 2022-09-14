
package com.donlim.pm.webservice.eipcenter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AppHdrType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AppHdrType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="User01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppHdrType", propOrder = {
    "user01",
    "user02",
    "user03",
    "user04",
    "user05"
})
public class AppHdrType {

    @XmlElement(name = "User01")
    protected String user01;
    @XmlElement(name = "User02")
    protected String user02;
    @XmlElement(name = "User03")
    protected String user03;
    @XmlElement(name = "User04")
    protected String user04;
    @XmlElement(name = "User05")
    protected String user05;

    /**
     * 获取user01属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser01() {
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
    public void setUser01(String value) {
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
    public String getUser02() {
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
    public void setUser02(String value) {
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
    public String getUser03() {
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
    public void setUser03(String value) {
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
    public String getUser04() {
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
    public void setUser04(String value) {
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
    public String getUser05() {
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
    public void setUser05(String value) {
        this.user05 = value;
    }

}
