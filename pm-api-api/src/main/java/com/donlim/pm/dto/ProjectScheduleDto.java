package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.Date;

/**
 * 项目计划表(ProjectSchedule)DTO类
 *
 * @author sei
 * @since 2022-07-30 08:15:41
 */
@ApiModel(description = "项目计划表DTO")
public class ProjectScheduleDto extends BaseEntityDto {
    private static final long serialVersionUID = 186233698562693807L;
    /**
     * 计划类型
     */
    @ApiModelProperty(value = "计划类型")
    private String schedureType;
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
    private LocalDate planStartDate;
    /**
     * 计划结束日期
     */
    @ApiModelProperty(value = "计划结束日期")
    private LocalDate planEndDate;
    /**
     * 实际开始日期
     */
    @ApiModelProperty(value = "实际开始日期")
    private LocalDate actualStartDate;
    /**
     * 实际结束日期
     */
    @ApiModelProperty(value = "实际结束日期")
    private LocalDate actualEndDate;
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