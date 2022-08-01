package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Date;

/**
 * 基础资料(PmBaseinfo)DTO类
 *
 * @author sei
 * @since 2022-07-28 08:56:29
 */
@ApiModel(description = "基础资料DTO")
public class PmBaseinfoDto extends BaseEntityDto {
    private static final long serialVersionUID = 770347183022104499L;
    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 主导人
     */
    @ApiModelProperty(value = "主导人")
    private String projectMaster;
    /**
     * 当前阶段
     */
    @ApiModelProperty(value = "当前阶段")
    private String currentPeriod;
    /**
     * 主计划达成率
     */
    @ApiModelProperty(value = "主计划达成率")
    private String masterScheduleRate;
    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    private LocalDate startDate;
    //private Date startDate;
    /**
     * 计划结案日期
     */
    @ApiModelProperty(value = "计划结案日期")
    private LocalDate planFinishDate;
    /**
     * 实际结案日期
     */
    @ApiModelProperty(value = "实际结案日期")
    private LocalDate finalFinishDate;
    /**
     * 项目天数
     */
    @ApiModelProperty(value = "项目天数")
    private String projectDays;
    /**
     * 是否逾期
     */
    @ApiModelProperty(value = "是否逾期")
    private Integer isOverdue;
    /**
     * 逾期天数
     */
    @ApiModelProperty(value = "逾期天数")
    private String overedDays;
    /**
     * 参与人数
     */
    @ApiModelProperty(value = "参与人数")
    private String attendanceMemberrCount;
    /**
     * 提案日期
     */
    @ApiModelProperty(value = "提案日期")
    private LocalDate submissionDate;
    /**
     * 规划审批
     */
    @ApiModelProperty(value = "规划审批")
    private String planningApproval;
    /**
     * 现状描述
     */
    @ApiModelProperty(value = "现状描述")
    private String currentDescription;
    /**
     * 需求描述
     */
    @ApiModelProperty(value = "需求描述")
    private String requirementDescription;
    /**
     * 改善效益
     */
    @ApiModelProperty(value = "改善效益")
    private String improveBenefits;
    /**
     * 推广度
     */
    @ApiModelProperty(value = "推广度")
    private String promotionDegree;
    /**
     * 硬件要求
     */
    @ApiModelProperty(value = "硬件要求")
    private String hardwareRequirement;
    /**
     * 项目类型
     */
    @ApiModelProperty(value = "项目类型")
    private String projectTypes;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
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

    //public Date getStartDate() {return startDate;}
    public LocalDate getStartDate() {
        return startDate;
    }

    //public void setStartDate(Date startDate) {this.startDate = startDate;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}
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