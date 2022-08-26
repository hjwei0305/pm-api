
package com.donlim.pm.webservice.eip;

import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OSVCHDR" type="{urn:DefaultNamespace}SVCHDR"/>
 *         &lt;element name="OAPPHDR" type="{urn:DefaultNamespace}APPHDR"/>
 *         &lt;element name="OAPPBODY" type="{urn:DefaultNamespace}APPBODY"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "osvchdr",
    "oapphdr",
    "oappbody"
})
@XmlRootElement(name = "SYNCHECKSEARCH")
public class SYNCHECKSEARCH {

    @XmlElement(name = "OSVCHDR", required = true)
    protected SVCHDR osvchdr;
    @XmlElement(name = "OAPPHDR", required = true)
    protected APPHDR oapphdr;
    @XmlElement(name = "OAPPBODY", required = true)
    protected APPBODY oappbody;

    /**
     * ��ȡosvchdr���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link SVCHDR }
     *
     */
    public SVCHDR getOSVCHDR() {
        return osvchdr;
    }

    /**
     * ����osvchdr���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link SVCHDR }
     *
     */
    public void setOSVCHDR(SVCHDR value) {
        this.osvchdr = value;
    }

    /**
     * ��ȡoapphdr���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link APPHDR }
     *
     */
    public APPHDR getOAPPHDR() {
        return oapphdr;
    }

    /**
     * ����oapphdr���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link APPHDR }
     *
     */
    public void setOAPPHDR(APPHDR value) {
        this.oapphdr = value;
    }

    /**
     * ��ȡoappbody���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link APPBODY }
     *
     */
    public APPBODY getOAPPBODY() {
        return oappbody;
    }

    /**
     * ����oappbody���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link APPBODY }
     *
     */
    public void setOAPPBODY(APPBODY value) {
        this.oappbody = value;
    }

}
