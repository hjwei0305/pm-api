package com.donlim.pm.flow;

import com.changhong.sei.core.dto.flow.FlowStatus;
import com.changhong.sei.core.entity.BaseAuditableEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author liusonglin
 * @version 1.0
 * @Desc
 * @date 2021/9/17 16:19
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public class BaseFlowEntity extends BaseAuditableEntity {

    @Column(length = 36, name = "flow_status")
    private FlowStatus flowStatus;

    @Column(name = "organization_code")
    private String organizationCode;

    @Column(name = "organization_id")
    private String organizationId;

    /**
     * 组织机构名称
     */
    @Column(name = "organization_name")
    private String organizationName;


    public FlowStatus getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(FlowStatus flowStatus) {
        this.flowStatus = flowStatus;
    }


    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
