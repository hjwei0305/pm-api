package com.donlim.pm.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 流程配置(WfConfig)实体类
 *
 * @author sei
 * @since 2022-07-30 08:21:56
 */
@Entity
@Table(name = "wf_config")
@DynamicInsert
@DynamicUpdate
public class WfConfig extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = 295610028697446252L;
    /**
     * 项目编码
     */
    @Column(name = "code")
    private String code;
    /**
     * 项目名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 流程顺序
     */
    @Column(name = "wf_no")
    private Integer wfNo;
    /**
     * 流程名称流程名称
     */
    @Column(name = "wf_name")
    private String wfName;
    /**
     * 节点顺序
     */
    @Column(name = "node_no")
    private Integer nodeNo;
    /**
     * 节点名称
     */
    @Column(name = "node_name")
    private String nodeName;
    /**
     * 有无附件
     */
    @Column(name = "node_attachement")
    private Integer nodeAttachement;
    /**
     * 附件名称
     */
    @Column(name = "node_attachement_name")
    private String nodeAttachementName;
    /**
     * 节点启用
     */
    @Column(name = "is_frozen")
    private String isFrozen;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWfNo() {
        return wfNo;
    }

    public void setWfNo(Integer wfNo) {
        this.wfNo = wfNo;
    }

    public String getWfName() {
        return wfName;
    }

    public void setWfName(String wfName) {
        this.wfName = wfName;
    }

    public Integer getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(Integer nodeNo) {
        this.nodeNo = nodeNo;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getNodeAttachement() {
        return nodeAttachement;
    }

    public void setNodeAttachement(Integer nodeAttachement) {
        this.nodeAttachement = nodeAttachement;
    }

    public String getNodeAttachementName() {
        return nodeAttachementName;
    }

    public void setNodeAttachementName(String nodeAttachementName) {
        this.nodeAttachementName = nodeAttachementName;
    }

    public String getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(String isFrozen) {
        this.isFrozen = isFrozen;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}