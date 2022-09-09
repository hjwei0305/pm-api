package com.donlim.pm.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (PmLog)实体类
 *
 * @author sei
 * @since 2022-09-06 14:23:47
 */
@Entity
@Table(name = "pm_log")
@DynamicInsert
@DynamicUpdate
public class PmLog extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -84298899024966139L;
    /**
     * 项目id
     */
    @Column(name = "project_id")
    private String projectId;
    /**
     * 项目类型
     */
    @Column(name = "project_types")
    private String projectTypes;
    /**
     * 工号
     */
    @Column(name = "employee_code")
    private String employeeCode;
    /**
     * 姓名
     */
    @Column(name = "employee_name")
    private String employeeName;
    /**
     * 操作的内容
     */
    @Column(name = "content")
    private String content;

    @Column(name = "tenant_code")
    private String tenantCode;


    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectTypes() {
        return projectTypes;
    }

    public void setProjectTypes(String projectTypes) {
        this.projectTypes = projectTypes;
    }
}
