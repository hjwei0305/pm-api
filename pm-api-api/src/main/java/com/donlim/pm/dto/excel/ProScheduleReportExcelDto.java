package com.donlim.pm.dto.excel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 导出科室年度项目excel
 *
 * @author Carol
 * @since 2023-05-15 16:44:03
 */
@NoArgsConstructor
@Data
public class ProScheduleReportExcelDto {
    private static final long serialVersionUID = -47355896084772616L;
    /**
     * 项目类型
     */
    @ApiModelProperty(value = "项目类型")
    private String projectTypes;
    /**
     * 系统名称
     */
    @ApiModelProperty(value = "系统名称")
    private String sysName;
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String name;
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
     * 项目天数
     */
    @ApiModelProperty(value = "项目天数")
    private Long projectDays;
    /**
     * 项目启动
     */
    @ApiModelProperty(value = "项目启动")
    private String proStart;
    /**
     * 需求制作
     */
    @ApiModelProperty(value = "需求制作")
    private String requirement;
    /**
     * 程序开发
     */
    @ApiModelProperty(value = "程序开发")
    private String development;
    /**
     * 测试验证
     */
    @ApiModelProperty(value = "测试验证")
    private String test;
    /**
     * 应用推广
     */
    @ApiModelProperty(value = "应用推广")
    private String promote;
    /**
     * 项目验收
     */
    @ApiModelProperty(value = "项目验收")
    private String finish;
    /**
     * 项目完成率
     */
    @ApiModelProperty(value = "项目完成率")
    private BigDecimal completionRate;

}
