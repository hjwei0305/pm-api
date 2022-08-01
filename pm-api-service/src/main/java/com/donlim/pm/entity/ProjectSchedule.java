package com.donlim.pm.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;

/**
 * 项目计划表(ProjectSchedule)实体类
 *
 * @author sei
 * @since 2022-07-30 08:16:10
 */
@Entity
@Table(name = "project_schedule")
@DynamicInsert
@DynamicUpdate
public class ProjectSchedule extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -21686520281561050L;
    /**
     * 计划类型
     */
    @Column(name = "schedure_type")
    private String schedureType;
    /**
     * 项目编码
     */
    @Column(name = "project_code")
    private String projectCode;
    /**
     * 项目名称
     */
    @Column(name = "project_name")
    private String projectName;
    /**
     * 计划开始日期
     */
    @Column(name = "plan_start_date")
    private LocalDate planStartDate;
    /**
     * 计划结束日期
     */
    @Column(name = "plan_end_date")
    private LocalDate planEndDate;
    /**
     * 实际开始日期
     */
    @Column(name = "actual_start_date")
    private LocalDate actualStartDate;
    /**
     * 实际结束日期
     */
    @Column(name = "actual_end_date")
    private LocalDate actualEndDate;
    /**
     * 天数
     */
    @Column(name = "schedure_days")
    private String schedureDays;
    /**
     * 序号
     */
    @Column(name = "schedure_no")
    private String schedureNo;
    /**
     * 状态
     */
    @Column(name = "schedure_status")
    private String schedureStatus;
    /**
     * 任务类型
     */
    @Column(name = "work_type")
    private String workType;
    /**
     * 任务列表
     */
    @Column(name = "work_todo_list")
    private String workTodoList;
    /**
     * 负责人
     */
    @Column(name = "work_onduty")
    private String workOnduty;
    /**
     * 协助人
     */
    @Column(name = "work_assist")
    private String workAssist;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public String getSchedureType() {
        return schedureType;
    }

    public void setSchedureType(String schedureType) {
        this.schedureType = schedureType;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(LocalDate planStartDate) {
        this.planStartDate = planStartDate;
    }

    public LocalDate getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(LocalDate planEndDate) {
        this.planEndDate = planEndDate;
    }

    public LocalDate getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(LocalDate actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(LocalDate actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public String getSchedureDays() {
        return schedureDays;
    }

    public void setSchedureDays(String schedureDays) {
        this.schedureDays = schedureDays;
    }

    public String getSchedureNo() {
        return schedureNo;
    }

    public void setSchedureNo(String schedureNo) {
        this.schedureNo = schedureNo;
    }

    public String getSchedureStatus() {
        return schedureStatus;
    }

    public void setSchedureStatus(String schedureStatus) {
        this.schedureStatus = schedureStatus;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkTodoList() {
        return workTodoList;
    }

    public void setWorkTodoList(String workTodoList) {
        this.workTodoList = workTodoList;
    }

    public String getWorkOnduty() {
        return workOnduty;
    }

    public void setWorkOnduty(String workOnduty) {
        this.workOnduty = workOnduty;
    }

    public String getWorkAssist() {
        return workAssist;
    }

    public void setWorkAssist(String workAssist) {
        this.workAssist = workAssist;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}