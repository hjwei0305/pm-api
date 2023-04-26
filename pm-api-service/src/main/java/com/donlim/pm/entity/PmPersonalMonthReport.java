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
 * 个人月计划(PmPersonalMonthReport)实体类
 *
 * @author sei
 * @since 2023-04-24 14:00:01
 */
@Entity
@Table(name = "pm_personal_month_report")
@DynamicInsert
@DynamicUpdate
public class PmPersonalMonthReport extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -10054380066950389L;
    /**
     * 月份
     */
    @Column(name = "ym")
    private String ym;
    /**
     * 工号
     */
    @Column(name = "employee_code")
    private String employeeCode;
    /**
     * 名字
     */
    @Column(name = "employee_name")
    private String employeeName;
    /**
     * 科室名称
     */
    @Column(name = "group_name")
    private String groupName;
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
     * 计划总项
     */
    @Column(name = "total_num")
    private Integer totalNum;
    /**
     * 完成项
     */
    @Column(name = "finish_num")
    private Integer finishNum;
    /**
     * 逾期项
     */
    @Column(name = "overtime_num")
    private Integer overtimeNum;
    /**
     * 达成率
     */
    @Column(name = "compeletion_rate")
    private BigDecimal compeletionRate;
    /**
     * 所占比例
     */
    @Column(name = "work_houres_rate")
    private BigDecimal workHouresRate;
    /**
     * 工作小时
     */
    @Column(name = "work_hours")
    private Integer workHours;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Integer finishNum) {
        this.finishNum = finishNum;
    }

    public Integer getOvertimeNum() {
        return overtimeNum;
    }

    public void setOvertimeNum(Integer overtimeNum) {
        this.overtimeNum = overtimeNum;
    }

    public Integer getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Integer workHours) {
        this.workHours = workHours;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
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

    public BigDecimal getCompeletionRate() {
        return compeletionRate;
    }

    public void setCompeletionRate(BigDecimal compeletionRate) {
        this.compeletionRate = compeletionRate;
    }

    public BigDecimal getWorkHouresRate() {
        return workHouresRate;
    }

    public void setWorkHouresRate(BigDecimal workHouresRate) {
        this.workHouresRate = workHouresRate;
    }
}
