package com.donlim.pm.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 项目组成员(ProjectMembers)实体类
 *
 * @author sei
 * @since 2022-07-30 08:14:05
 */
@Entity
@Table(name = "project_members")
@DynamicInsert
@DynamicUpdate
public class ProjectMembers extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = 597490167306174465L;
    /**
     * 项目编码
     */
    @Column(name = "project_code")
    private String projectCode;
    /**
     * 工号
     */
    @Column(name = "code")
    private String code;
    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;
    /**
     * 所在小组
     */
    @Column(name = "organize_id")
    private String organizeId;
    /**
     * 项目组身份
     */
    @Column(name = "project_role")
    private String projectRole;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

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

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}