package com.donlim.pm.dto.excel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * 提案清单(PmBaseinfo)DTO类
 *
 * @author sei
 * @since 2022-08-10 15:49:26
 */
@ApiModel(description = "提案清单DTO")
@Data
public class PmBaseinfoExcelDto {
    private static final long serialVersionUID = 417621879606687300L;
    /**
     * 系统名称
     */
    @ApiModelProperty(name = "系统名称")
    private String sysName;
    /**
     * 提案名称
     */
    @ApiModelProperty(value = "提案名称")
    private String name;
    @ApiModelProperty(value = "主导人")
    private String leader;
    @ApiModelProperty(value = "当前进度")
    private String masterScheduleRate;
    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    private LocalDate startDate;
    /**
     * 计划结案日期
     */
    @ApiModelProperty(value = "计划结案日期")
    private LocalDate planFinishDate;
    /**
     * 人天
     */
    @ApiModelProperty(value = "人天")
    private BigDecimal personDay;
    /**
     * 本周计划
     */
    @ApiModelProperty(value = "本周计划")
    private String weekPlan;
    /**
     * 下周计划
     */
    @ApiModelProperty(value = "下周计划")
    private String nextWeekPlan;
    /**
     * 工作风险
     */
    @ApiModelProperty(value = "工作风险")
    private String workRisk;
    /**
     * 双周计划更新时间
     */
    @ApiModelProperty(value = "周计划更新时间")
    private LocalDate weekPlanUpdate;
    /**
     * 是否逾期
     */
    @ApiModelProperty(value = "是否逾期")
    private Boolean isOverdue;
    /**
     * 逾期天数
     */
    @ApiModelProperty(value = "逾期天数")
    private Integer overedDays;
    /**
     * 是否提前
     */
    @ApiModelProperty(value = "是否提前")
    private Boolean isAdvance;
    /**
     * 提前天数
     */
    @ApiModelProperty(value = "提前天数")
    private Long advanceDays;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "项目类型")
    private String projectTypes;
    /**
     * 编码
     */
    @ApiModelProperty(value = "项目编码")
    private String code;
    @ApiModelProperty(name = "科室名称")
    private String orgname;

    /**
     * 当前阶段
     */
    @ApiModelProperty(value = "当前阶段")
    private String currentPeriod;
    /**
     * 实际结案日期
     */
    @ApiModelProperty(value = "实际结案日期")
    private LocalDate finalFinishDate;
    /**
     * 项目天数
     */
    @ApiModelProperty(value = "项目天数")
    private Integer projectDays;

    /**
     * 是否暂停
     */
    @ApiModelProperty(value = "是否暂停")
    private Boolean isPause;
}
