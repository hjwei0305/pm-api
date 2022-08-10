package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

/**
 * 代办事项(TodoList)DTO类
 *
 * @author sei
 * @since 2022-07-30 08:16:53
 */
@ApiModel(description = "代办事项DTO")
public class TodoListDto extends BaseEntityDto {
    private static final long serialVersionUID = 839294833465213816L;
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
     * 代办事项
     */
    @ApiModelProperty(value = "代办事项")
    private String todoList;
    /**
     * 责任人工号
     */
    @ApiModelProperty(value = "责任人工号")
    private String ondutyCode;
    /**
     * 责任人姓名
     */
    @ApiModelProperty(value = "责任人姓名")
    private String ondutyName;
    /**
     * 提出人工号
     */
    @ApiModelProperty(value = "提出人工号")
    private String submitCode;
    /**
     * 提出人姓名
     */
    @ApiModelProperty(value = "提出人姓名")
    private String submitName;
    /**
     * 提出时间
     */
    @ApiModelProperty(value = "提出时间")
    private LocalDate submitDate;
    /**
     * 结案时间
     */
    @ApiModelProperty(value = "结案时间")
    private LocalDate endDate;
    /**
     * 是否完成
     */
    @ApiModelProperty(value = "是否完成")
    private Boolean isCompleted;
    /**
     * 是否结案
     */
    @ApiModelProperty(value = "是否结案")
    private Boolean isFinished;
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

    public String getTodoList() {
        return todoList;
    }

    public void setTodoList(String todoList) {
        this.todoList = todoList;
    }

    public String getOndutyCode() {
        return ondutyCode;
    }

    public void setOndutyCode(String ondutyCode) {
        this.ondutyCode = ondutyCode;
    }

    public String getOndutyName() {
        return ondutyName;
    }

    public void setOndutyName(String ondutyName) {
        this.ondutyName = ondutyName;
    }

    public String getSubmitCode() {
        return submitCode;
    }

    public void setSubmitCode(String submitCode) {
        this.submitCode = submitCode;
    }

    public String getSubmitName() {
        return submitName;
    }

    public void setSubmitName(String submitName) {
        this.submitName = submitName;
    }

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
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
