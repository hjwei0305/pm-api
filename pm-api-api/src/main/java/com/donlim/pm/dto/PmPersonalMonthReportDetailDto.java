package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * 个人月计划明细(PmPersonalMonthReportDetail)DTO类
 *
 * @author sei
 * @since 2023-04-24 16:51:09
 */
@ApiModel(description = "个人月计划明细DTO")
public class PmPersonalMonthReportDetailDto extends BaseEntityDto {
    private static final long serialVersionUID = -82989734187986994L;
    /**
     * 月计划id
     */
    @ApiModelProperty(value = "月份")
    private String ym;
    /**
     * 月计划id
     */
    @ApiModelProperty(value = "月计划id")
    private String personalMonthReportId;
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer scheNo;
    /**
     * 计划开始时间
     */
    @ApiModelProperty(value = "计划开始时间")
    private LocalDate planStartDate;
    /**
     * 计划结束时间
     */
    @ApiModelProperty(value = "计划结束时间")
    private LocalDate planEndDate;
    /**
     * 实际开始时间
     */
    @ApiModelProperty(value = "实际开始时间")
    private LocalDate actualStartDate;
    /**
     * 实际结束时间
     */
    @ApiModelProperty(value = "实际结束时间")
    private LocalDate actualEndDate;
    /**
     * 实际天数
     */
    @ApiModelProperty(value = "实际天数")
    private Integer schedureDays;
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    /**
     * 主要任务/关键步骤
     */
    @ApiModelProperty(value = "主要任务/关键步骤")
    private String workTodoList;
    /**
     * 计划类别
     */
    @ApiModelProperty(value = "计划类别")
    private String type;
    /**
     * 所占比例(%)
     */
    @ApiModelProperty(value = "所占比例(%)")
    private BigDecimal workHouresRate;
    /**
     * 工作小时(H)
     */
    @ApiModelProperty(value = "工作小时(H)")
    private Integer workHours;
    /**
     * 协助人
     */
    @ApiModelProperty(value = "协助人")
    private String workAssist;
    /**
     * 第一周进度
     */
    @ApiModelProperty(value = "第一周进度")
    private String firstWeekSituation;
    /**
     * 第二周进度
     */
    @ApiModelProperty(value = "第二周进度")
    private String secondWeekSituation;
    /**
     * 第三周进度
     */
    @ApiModelProperty(value = "第三周进度")
    private String thirdWeekSituation;
    /**
     * 第四周进度
     */
    @ApiModelProperty(value = "第四周进度")
    private String fourthWeekSituation;
    /**
     * 是否达成
     */
    @ApiModelProperty(value = "是否达成")
    private Integer complete;
    /**
     * 是否达成
     */
    @ApiModelProperty(value = "是否生成")
    private Boolean autoGenerate;
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

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
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

    public BigDecimal getWorkHouresRate() {
        return workHouresRate;
    }

    public void setWorkHouresRate(BigDecimal workHouresRate) {
        this.workHouresRate = workHouresRate;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public Date getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(Date lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    public Boolean getAutoGenerate() {
        return autoGenerate;
    }

    public void setAutoGenerate(Boolean autoGenerate) {
        this.autoGenerate = autoGenerate;
    }
}
