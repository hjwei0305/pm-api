package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 项目双周明细
 *
 * @author Carol
 * @since 2023-05-16 13:58:32
 */
@NoArgsConstructor
@Data
public class WeekReportDto extends BaseEntityDto {
    /**
     * 项目id
     */
    @ApiModelProperty(value = "双周计划id")
    private String weekId;
    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;
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
     * 上次修改时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_TIME_FORMAT)
    @ApiModelProperty(value = "上次修改时间")
    private LocalDateTime lastModifiedTime;
    /**
     * 完成当周计划
     */
    @ApiModelProperty(value = "完成当周计划")
    private Boolean finishPlan;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间")
    private Date lastEditedDate;


}
