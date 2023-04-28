package com.donlim.pm.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 个人月计划明细(PmPersonalMonthReportDetail)实体类
 *
 * @author sei
 * @since 2023-04-24 16:51:33
 */
@Entity
@Table(name = "pm_personal_month_report_detail")
@DynamicInsert
@DynamicUpdate
public class PmPersonalMonthReportDetail extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = 958427737950054296L;
    /**
     * 月计划id
     */
    @Column(name = "personal_month_report_id")
    private String personalMonthReportId;
    /**
     * 序号
     */
    @Column(name = "sche_no")
    private Integer scheNo;
    /**
     * 计划开始时间
     */
    @Column(name = "plan_start_date")
    private LocalDate planStartDate;
    /**
     * 计划结束时间
     */
    @Column(name = "plan_end_date")
    private LocalDate planEndDate;
    /**
     * 计划天数
     */
    @Column(name = "plan_days")
    private Integer planDays;
    /**
     * 实际开始时间
     */
    @Column(name = "actual_start_date")
    private LocalDate actualStartDate;
    /**
     * 实际结束时间
     */
    @Column(name = "actual_end_date")
    private LocalDate actualEndDate;
    /**
     * 实际天数
     */
    @Column(name = "schedure_days")
    private Integer schedureDays;
    /**
     * 项目名称
     */
    @Column(name = "project_name")
    private String projectName;
    /**
     * 主要任务/关键步骤
     */
    @Column(name = "work_todo_list")
    private String workTodoList;
    /**
     * 计划类别
     */
    @Column(name = "type")
    private String type;
    /**
     * 所占比例(%)
     */
    @Column(name = "work_houres_rate")
    private BigDecimal workHouresRate;
    /**
     * 工作小时(H)
     */
    @Column(name = "work_hours")
    private Integer workHours;
    /**
     * 协助人
     */
    @Column(name = "work_assist")
    private String workAssist;
    /**
     * 第一周进度
     */
    @Column(name = "first_week_situation")
    private String firstWeekSituation;
    /**
     * 第二周进度
     */
    @Column(name = "second_week_situation")
    private String secondWeekSituation;
    /**
     * 第三周进度
     */
    @Column(name = "third_week_situation")
    private String thirdWeekSituation;
    /**
     * 第四周进度
     */
    @Column(name = "fourth_week_situation")
    private String fourthWeekSituation;
    /**
     * 是否达成
     */
    @Column(name = "is_complete")
    private Integer complete;
    /**
     * 是否生成
     */
    @Column(name = "auto_generate")
    private Boolean autoGenerate;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public String getPersonalMonthReportId() {
        return personalMonthReportId;
    }

    public void setPersonalMonthReportId(String personalMonthReportId) {
        this.personalMonthReportId = personalMonthReportId;
    }

    public Integer getScheNo() {
        return scheNo;
    }

    public void setScheNo(Integer scheNo) {
        this.scheNo = scheNo;
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

    public Integer getSchedureDays() {
        return schedureDays;
    }

    public void setSchedureDays(Integer schedureDays) {
        this.schedureDays = schedureDays;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getWorkTodoList() {
        return workTodoList;
    }

    public void setWorkTodoList(String workTodoList) {
        this.workTodoList = workTodoList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getWorkHouresRate() {
        return workHouresRate;
    }

    public void setWorkHouresRate(BigDecimal workHouresRate) {
        this.workHouresRate = workHouresRate;
    }

    public Integer getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Integer workHours) {
        this.workHours = workHours;
    }

    public String getWorkAssist() {
        return workAssist;
    }

    public void setWorkAssist(String workAssist) {
        this.workAssist = workAssist;
    }

    public String getFirstWeekSituation() {
        return firstWeekSituation;
    }

    public void setFirstWeekSituation(String firstWeekSituation) {
        this.firstWeekSituation = firstWeekSituation;
    }

    public String getSecondWeekSituation() {
        return secondWeekSituation;
    }

    public void setSecondWeekSituation(String secondWeekSituation) {
        this.secondWeekSituation = secondWeekSituation;
    }

    public String getThirdWeekSituation() {
        return thirdWeekSituation;
    }

    public void setThirdWeekSituation(String thirdWeekSituation) {
        this.thirdWeekSituation = thirdWeekSituation;
    }

    public String getFourthWeekSituation() {
        return fourthWeekSituation;
    }

    public void setFourthWeekSituation(String fourthWeekSituation) {
        this.fourthWeekSituation = fourthWeekSituation;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public Boolean getAutoGenerate() {
        return autoGenerate;
    }

    public void setAutoGenerate(Boolean autoGenerate) {
        this.autoGenerate = autoGenerate;
    }

    public Integer getPlanDays() {
        return planDays;
    }

    public void setPlanDays(Integer planDays) {
        this.planDays = planDays;
    }
}
