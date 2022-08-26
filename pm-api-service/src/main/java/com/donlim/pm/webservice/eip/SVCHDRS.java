
package com.donlim.pm.webservice.eip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SVCHDRS complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="SVCHDRS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RCODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RDESC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ESBCODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SVCHDRS", propOrder = {
    "rcode",
    "rdesc",
    "esbcode"
})
public class SVCHDRS {

    @XmlElement(name = "RCODE", required = true)
    protected String rcode;
    @XmlElement(name = "RDESC", required = true)
    protected String rdesc;
    @XmlElement(name = "ESBCODE", required = true)
    protected String esbcode;

    /**
     * ��ȡrcode���Ե�ֵ��
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
     * ����rcode���Ե�ֵ��
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
     * ��ȡrdesc���Ե�ֵ��
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
     * ����rdesc���Ե�ֵ��
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
     * ��ȡesbcode���Ե�ֵ��
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
     * ����esbcode���Ե�ֵ��
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
