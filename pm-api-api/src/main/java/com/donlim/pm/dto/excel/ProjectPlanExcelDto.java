package com.donlim.pm.dto.excel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;


@ApiModel(description = "计划表DTO")
public class ProjectPlanExcelDto {
    private static final long serialVersionUID = -27235344758934297L;
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private String schedureNo;
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
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String schedureStatus;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSchedureNo() {
        return schedureNo;
    }

    public void setSchedureNo(String schedureNo) {
        this.schedureNo = schedureNo;
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

    public String getSchedureStatus() {
        return schedureStatus;
    }

    public void setSchedureStatus(String schedureStatus) {
        this.schedureStatus = schedureStatus;
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
}
