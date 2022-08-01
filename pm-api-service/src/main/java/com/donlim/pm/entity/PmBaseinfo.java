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
 * 基础资料(PmBaseinfo)实体类
 *
 * @author sei
 * @since 2022-07-28 09:01:47
 */
@Entity
@Table(name = "pm_baseinfo")
@DynamicInsert
@DynamicUpdate
public class PmBaseinfo extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = 999284241070392357L;
    /**
     * 编码
     */
    @Column(name = "code")
    private String code;
    /**
     * 名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 主导人
     */
    @Column(name = "project_master")
    private String projectMaster;
    /**
     * 当前阶段
     */
    @Column(name = "current_period")
    private String currentPeriod;
    /**
     * 主计划达成率
     */
    @Column(name = "master_schedule_rate")
    private String masterScheduleRate;
    /**
     * 开始日期
     */
    @Column(name = "start_date")
    private LocalDate startDate;
    /**
     * 计划结案日期
     */
    @Column(name = "plan_finish_date")
    private LocalDate planFinishDate;
    /**
     * 实际结案日期
     */
    @Column(name = "final_finish_date")
    private LocalDate finalFinishDate;
    /**
     * 项目天数
     */
    @Column(name = "project_days")
    private String projectDays;
    /**
     * 是否逾期
     */
    @Column(name = "is_overdue")
    private Integer isOverdue;
    /**
     * 逾期天数
     */
    @Column(name = "overed_days")
    private String overedDays;
    /**
     * 参与人数
     */
    @Column(name = "attendance_memberr_count")
    private String attendanceMemberrCount;
    /**
     * 提案日期
     */
    @Column(name = "submission_date")
    private LocalDate submissionDate;
    /**
     * 规划审批
     */
    @Column(name = "planning_approval")
    private String planningApproval;
    /**
     * 现状描述
     */
    @Column(name = "current_description")
    private String currentDescription;
    /**
     * 需求描述
     */
    @Column(name = "requirement_description")
    private String requirementDescription;
    /**
     * 改善效益
     */
    @Column(name = "improve_benefits")
    private String improveBenefits;
    /**
     * 推广度
     */
    @Column(name = "promotion_degree")
    private String promotionDegree;
    /**
     * 硬件要求
     */
    @Column(name = "hardware_requirement")
    private String hardwareRequirement;
    /**
     * 项目类型
     */
    @Column(name = "project_types")
    private String projectTypes;
    /**
     * 状态
     */
    @Column(name = "status")
    private String status;
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

    public String getProjectMaster() {
        return projectMaster;
    }

    public void setProjectMaster(String projectMaster) {
        this.projectMaster = projectMaster;
    }

    public String getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(String currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public String getMasterScheduleRate() {
        return masterScheduleRate;
    }

    public void setMasterScheduleRate(String masterScheduleRate) {
        this.masterScheduleRate = masterScheduleRate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getPlanFinishDate() {
        return planFinishDate;
    }

    public void setPlanFinishDate(LocalDate planFinishDate) {
        this.planFinishDate = planFinishDate;
    }

    public LocalDate getFinalFinishDate() {
        return finalFinishDate;
    }

    public void setFinalFinishDate(LocalDate finalFinishDate) {
        this.finalFinishDate = finalFinishDate;
    }

    public String getProjectDays() {
        return projectDays;
    }

    public void setProjectDays(String projectDays) {
        this.projectDays = projectDays;
    }

    public Integer getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(Integer isOverdue) {
        this.isOverdue = isOverdue;
    }

    public String getOveredDays() {
        return overedDays;
    }

    public void setOveredDays(String overedDays) {
        this.overedDays = overedDays;
    }

    public String getAttendanceMemberrCount() {
        return attendanceMemberrCount;
    }

    public void setAttendanceMemberrCount(String attendanceMemberrCount) {
        this.attendanceMemberrCount = attendanceMemberrCount;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getPlanningApproval() {
        return planningApproval;
    }

    public void setPlanningApproval(String planningApproval) {
        this.planningApproval = planningApproval;
    }

    public String getCurrentDescription() {
        return currentDescription;
    }

    public void setCurrentDescription(String currentDescription) {
        this.currentDescription = currentDescription;
    }

    public String getRequirementDescription() {
        return requirementDescription;
    }

    public void setRequirementDescription(String requirementDescription) {
        this.requirementDescription = requirementDescription;
    }

    public String getImproveBenefits() {
        return improveBenefits;
    }

    public void setImproveBenefits(String improveBenefits) {
        this.improveBenefits = improveBenefits;
    }

    public String getPromotionDegree() {
        return promotionDegree;
    }

    public void setPromotionDegree(String promotionDegree) {
        this.promotionDegree = promotionDegree;
    }

    public String getHardwareRequirement() {
        return hardwareRequirement;
    }

    public void setHardwareRequirement(String hardwareRequirement) {
        this.hardwareRequirement = hardwareRequirement;
    }

    public String getProjectTypes() {
        return projectTypes;
    }

    public void setProjectTypes(String projectTypes) {
        this.projectTypes = projectTypes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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