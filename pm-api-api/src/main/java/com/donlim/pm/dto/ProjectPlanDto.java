package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 项目计划表(ProjectPlan)DTO类
 *
 * @author sei
 * @since 2022-08-18 16:41:31
 */
@ApiModel(description = "项目计划表DTO")
public class ProjectPlanDto extends BaseEntityDto {
    private static final long serialVersionUID = -27235344758934297L;
    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    private String projectId;
    /**
     * 计划类型(0:主计划，1:前端计划，2:后端计划，3:实施计划)
     */
    @ApiModelProperty(value = "计划类型(0:主计划，1:前端计划，2:后端计划，3:实施计划)")
    private String planType;
    /**
     * 项目编码
     */
    @ApiModelProperty(value = "项目编码")
    private String projectCode;
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    /**
     * 计划开始日期
     */
    @ApiModelProperty(value = "计划开始日期")
    private Date planStartDate;
    /**
     * 计划结束日期
     */
    @ApiModelProperty(value = "计划结束日期")
    private Date planEndDate;
    /**
     * 实际开始日期
     */
    @ApiModelProperty(value = "实际开始日期")
    private Date actualStartDate;
    /**
     * 实际结束日期
     */
    @ApiModelProperty(value = "实际结束日期")
    private Date actualEndDate;
    /**
     * 天数
     */
    @ApiModelProperty(value = "天数")
    private String schedureDays;
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private String schedureNo;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String schedureStatus;
    /**
     * 任务类型
     */
    @ApiModelProperty(value = "任务类型")
    private String workType;
    /**
     * 任务列表
     */
    @ApiModelProperty(value = "任务列表")
    private String workTodoList;
    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人")
    private String workOnduty;
    /**
     * 协助人
     */
    @ApiModelProperty(value = "协助人")
    private String workAssist;
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


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
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

    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    public Date getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public Date getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(Date actualEndDate) {
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
