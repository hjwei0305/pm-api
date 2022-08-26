
package com.donlim.pm.webservice.eip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SVCBODYS complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="SVCBODYS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OSVCHDRS" type="{urn:DefaultNamespace}SVCHDRS"/>
 *         &lt;element name="OAPPHDRS" type="{urn:DefaultNamespace}APPHDRS"/>
 *         &lt;element name="OAPPBODYS" type="{urn:DefaultNamespace}APPBODYS"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SVCBODYS", propOrder = {
    "osvchdrs",
    "oapphdrs",
    "oappbodys"
})
public class SVCBODYS {

    @XmlElement(name = "OSVCHDRS", required = true, nillable = true)
    protected SVCHDRS osvchdrs;
    @XmlElement(name = "OAPPHDRS", required = true, nillable = true)
    protected APPHDRS oapphdrs;
    @XmlElement(name = "OAPPBODYS", required = true, nillable = true)
    protected APPBODYS oappbodys;

    /**
     * ��ȡosvchdrs���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link SVCHDRS }
     *     
     */
    public SVCHDRS getOSVCHDRS() {
        return osvchdrs;
    }

    /**
     * ����osvchdrs���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link SVCHDRS }
     *     
     */
    public void setOSVCHDRS(SVCHDRS value) {
        this.osvchdrs = value;
    }

    /**
     * ��ȡoapphdrs���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link APPHDRS }
     *     
     */
    public APPHDRS getOAPPHDRS() {
        return oapphdrs;
    }

    /**
     * ����oapphdrs���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link APPHDRS }
     *     
     */
    public void setOAPPHDRS(APPHDRS value) {
        this.oapphdrs = value;
    }

    /**
     * ��ȡoappbodys���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link APPBODYS }
     *     
     */
    public APPBODYS getOAPPBODYS() {
        return oappbodys;
    }

    /**
     * ����oappbodys���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link APPBODYS }
     *     
     */
    public void setOAPPBODYS(APPBODYS value) {
        this.oappbodys = value;
    }

}
