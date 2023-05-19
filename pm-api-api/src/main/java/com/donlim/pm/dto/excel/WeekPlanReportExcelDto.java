package com.donlim.pm.dto.excel;

import com.changhong.sei.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 双周明细导出excel
 *
 * @author Carol
 * @since 2023-05-19 16:44:03
 */
@NoArgsConstructor
@Data
public class WeekPlanReportExcelDto {
    private static final long serialVersionUID = -47355896084772616L;
    /**
     * 名称
     */
    @ApiModelProperty(value = "提案名称")
    private String name;
    /**
     * 系统名称
     */
    @ApiModelProperty(name = "系统名称")
    private String sysName;
    /**
     * 组织名称
     */
    @ApiModelProperty(name = "组织名称")
    private String orgname;
    /**
     * 项目类型
     */
    @ApiModelProperty(name = "项目类型")
    private String projectTypes;
    /**
     * 主导人
     */
    @ApiModelProperty(value = "主导人")
    private String leader;
    /**
     * 当前阶段
     */
    @ApiModelProperty(value = "当前阶段")
    private String currentPeriod;
    /**
     * 是否暂停
     */
    @ApiModelProperty(value = "是否暂停")
    private Boolean isPause;
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

    // 以上来自项目信息，以下来自双周计划
    /**
     * 完成当周计划
     */
    @ApiModelProperty(value = "完成当周计划")
    private Boolean finishPlan;
    /**
     * 上次修改时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_TIME_FORMAT)
    @ApiModelProperty(value = "上次修改时间")
    private LocalDateTime lastModifiedTime;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间")
    private Date lastEditedDate;

}
