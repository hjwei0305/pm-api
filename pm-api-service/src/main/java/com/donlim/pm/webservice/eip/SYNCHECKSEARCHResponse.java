
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
 *         &lt;element name="SYNCHECKSEARCHReturn" type="{urn:DefaultNamespace}SVCBODYS"/>
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
    "synchecksearchReturn"
})
@XmlRootElement(name = "SYNCHECKSEARCHResponse")
public class SYNCHECKSEARCHResponse {

    @XmlElement(name = "SYNCHECKSEARCHReturn", required = true)
    protected SVCBODYS synchecksearchReturn;

    /**
     * ��ȡsynchecksearchReturn���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link SVCBODYS }
     *
     */
    public SVCBODYS getSYNCHECKSEARCHReturn() {
        return synchecksearchReturn;
    }

    /**
     * ����synchecksearchReturn���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link SVCBODYS }
     *
     */
    public void setSYNCHECKSEARCHReturn(SVCBODYS value) {
        this.synchecksearchReturn = value;
    }

}
