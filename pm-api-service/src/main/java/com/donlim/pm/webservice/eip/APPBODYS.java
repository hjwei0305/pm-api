
package com.donlim.pm.webservice.eip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>APPBODYS complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
    "checkdate",
    "user01",
    "user02",
    "user03",
    "user04",
    "user05"
})
public class APPBODYS {

    @XmlElement(name = "RESULT")
    protected boolean result;
    @XmlElement(name = "CHECKDATE")
    protected String checkdate;
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

    public String getCHECKDATE() {
        return checkdate;
    }


    public void setCHECKDATE(String value) {
        this.checkdate = value;
    }
    /**
     * ��ȡresult���Ե�ֵ��
     *
     */
    public boolean isRESULT() {
        return result;
    }

    /**
     * ����result���Ե�ֵ��
     *
     */
    public void setRESULT(boolean value) {
        this.result = value;
    }

    /**
     * ��ȡuser01���Ե�ֵ��
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
     * ����user01���Ե�ֵ��
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
     * ��ȡuser02���Ե�ֵ��
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
     * ����user02���Ե�ֵ��
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
     * ��ȡuser03���Ե�ֵ��
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
     * ����user03���Ե�ֵ��
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
     * ��ȡuser04���Ե�ֵ��
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
     * ����user04���Ե�ֵ��
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
     * ��ȡuser05���Ե�ֵ��
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
     * ����user05���Ե�ֵ��
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
