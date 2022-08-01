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
 * 代办事项(TodoList)实体类
 *
 * @author sei
 * @since 2022-07-30 08:17:40
 */
@Entity
@Table(name = "todo_list")
@DynamicInsert
@DynamicUpdate
public class TodoList extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = 693716441330182047L;
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
     * 代办事项
     */
    @Column(name = "todo_list")
    private String todoList;
    /**
     * 责任人工号
     */
    @Column(name = "onduty_code")
    private String ondutyCode;
    /**
     * 责任人姓名
     */
    @Column(name = "onduty_name")
    private String ondutyName;
    /**
     * 提出人工号
     */
    @Column(name = "submit_code")
    private String submitCode;
    /**
     * 提出人姓名
     */
    @Column(name = "submit_name")
    private String submitName;
    /**
     * 提出时间
     */
    @Column(name = "submit_date")
    private LocalDate submitDate;
    /**
     * 结案时间
     */
    @Column(name = "end_date")
    private LocalDate endDate;
    /**
     * 是否完成
     */
    @Column(name = "is_completed")
    private String isCompleted;
    /**
     * 是否结案
     */
    @Column(name = "is_finished")
    private String isFinished;
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

    public String getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(String isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
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