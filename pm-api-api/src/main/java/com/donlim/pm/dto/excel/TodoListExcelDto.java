package com.donlim.pm.dto.excel;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

/**
 * 待办清单明细excel
 *
 * @author Lem
 * @since 2022-11-01 14:44:03
 */
public class TodoListExcelDto {
    private static final long serialVersionUID = -47355896084772616L;
    @ApiModelProperty(value = "提出人")
    private String advisor;
    @ApiModelProperty(value = "提出日期")
    private LocalDate submitDate;

    @ApiModelProperty(value = "起草人")
    private String submitName;

    @ApiModelProperty(value = "待办事项")
    private String todoList;

    @ApiModelProperty(value = "责任人")
    private String ondutyName;

    @ApiModelProperty(value = "科室")
    private String orgname;

    @ApiModelProperty(value = "要求完成日期")
    private LocalDate completionDate;

    @ApiModelProperty(value = "确认人")
    private String ondutyName1;

    @ApiModelProperty(value = "最新确认时间")
    private LocalDate confir1Time;

    @ApiModelProperty(value = "最新进度说明")
    private String newestProgress;

    @ApiModelProperty(value = "建议状态")
    private String proposalStatus;

    @ApiModelProperty(value = "当前完成比率（%）")
    private String completion;

    @ApiModelProperty(value = "验证人")
    private String confirmedby2;

    @ApiModelProperty(value = "验证时间")
    private LocalDate confirmationTime;

    @ApiModelProperty(value = "结案状态")
    private String closingStatus;

    @ApiModelProperty(value = "单据状态")
    private String flowStatus;

    @ApiModelProperty(value = "备注")
    private String remark;

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }

    public String getSubmitName() {
        return submitName;
    }

    public void setSubmitName(String submitName) {
        this.submitName = submitName;
    }

    public String getTodoList() {
        return todoList;
    }

    public void setTodoList(String todoList) {
        this.todoList = todoList;
    }

    public String getOndutyName() {
        return ondutyName;
    }

    public void setOndutyName(String ondutyName) {
        this.ondutyName = ondutyName;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public String getOndutyName1() {
        return ondutyName1;
    }

    public void setOndutyName1(String ondutyName1) {
        this.ondutyName1 = ondutyName1;
    }

    public LocalDate getConfir1Time() {
        return confir1Time;
    }

    public void setConfir1Time(LocalDate confir1Time) {
        this.confir1Time = confir1Time;
    }

    public String getNewestProgress() {
        return newestProgress;
    }

    public void setNewestProgress(String newestProgress) {
        this.newestProgress = newestProgress;
    }

    public String getProposalStatus() {
        return proposalStatus;
    }

    public void setProposalStatus(String proposalStatus) {
        this.proposalStatus = proposalStatus;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public String getConfirmedby2() {
        return confirmedby2;
    }

    public void setConfirmedby2(String confirmedby2) {
        this.confirmedby2 = confirmedby2;
    }

    public LocalDate getConfirmationTime() {
        return confirmationTime;
    }

    public void setConfirmationTime(LocalDate confirmationTime) {
        this.confirmationTime = confirmationTime;
    }

    public String getClosingStatus() {
        return closingStatus;
    }

    public void setClosingStatus(String closingStatus) {
        this.closingStatus = closingStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }
}
