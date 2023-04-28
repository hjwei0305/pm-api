package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * 个人月计划(PmPersonalMonthReport)DTO类
 *
 * @author sei
 * @since 2023-04-24 13:59:22
 */
@ApiModel(description = "个人月计划DTO")
public class PmPersonalMonthReportDto extends BaseEntityDto {
    private static final long serialVersionUID = -62241190974847475L;
    /**
     * 月份
     */
    @ApiModelProperty(value = "月份")
    private String ym;
    /**
     * 工号
     */
    @ApiModelProperty(value = "工号")
    private String employeeCode;
    /**
     * 名字
     */
    @ApiModelProperty(value = "名字")
    private String employeeName;
    /**
     * 科室名称
     */
    @ApiModelProperty(value = "科室名称")
    private String groupName;
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
     * 计划总项
     */
    @ApiModelProperty(value = "计划总项")
    private Integer totalNum;
    /**
     * 完成项
     */
    @ApiModelProperty(value = "完成项")
    private Integer finishNum;
    /**
     * 逾期项
     */
    @ApiModelProperty(value = "逾期项")
    private Integer overtimeNum;
    /**
     * 计划工作小时
     */
    @ApiModelProperty(value = "计划工作小时")
    private Integer planHours;
    /**
     * 计划占比
     */
    @ApiModelProperty(value = "计划占比")
    private BigDecimal planHoursRate;
    /**
     * 实际达成率
     */
    @ApiModelProperty(value = "实际达成率")
    private BigDecimal compeletionRate;
    /**
     * 实际占比
     */
    @ApiModelProperty(value = "实际占比")
    private BigDecimal workHouresRate;
    /**
     * 工作小时
     */
    @ApiModelProperty(value = "工作小时")
    private Integer workHours;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date lastEditedDate;


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

    public Date getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(Date lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    public Integer getPlanHours() {
        return planHours;
    }

    public void setPlanHours(Integer planHours) {
        this.planHours = planHours;
    }

    public BigDecimal getPlanHoursRate() {
        return planHoursRate;
    }

    public void setPlanHoursRate(BigDecimal planHoursRate) {
        this.planHoursRate = planHoursRate;
    }
}
