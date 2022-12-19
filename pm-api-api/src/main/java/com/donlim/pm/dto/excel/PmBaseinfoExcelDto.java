package com.donlim.pm.dto.excel;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.core.dto.serializer.EnumJsonSerializer;
import com.donlim.pm.em.BigNodeType;
import com.donlim.pm.em.SmallNodeType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


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
     * 编码
     */
    @ApiModelProperty(value = "项目编码")
    private String code;
    /**
     * 名称
     */
    @ApiModelProperty(value = "项目名称")
    private String name;
    /**
     * 系统名称
     */
    @ApiModelProperty(name = "系统名称")
    private String sysName;

    @ApiModelProperty(name = "组织名称")
    private String orgname;

    /**
     * 当前阶段
     */
    @ApiModelProperty(value = "当前阶段")
    private String currentPeriod;
    /**
     * 主计划达成率
     */
    @ApiModelProperty(value = "主计划达成率")
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
     * 是否逾期
     */
    @ApiModelProperty(value = "是否逾期")
    private Boolean isOverdue;
    /**
     * 逾期天数
     */
    @ApiModelProperty(value = "逾期天数")
    private Integer overedDays;
    @ApiModelProperty(value = "项目类型")
    private String projectTypes;
    @ApiModelProperty(value = "主导人")
    private String leader;
    @ApiModelProperty(value = "项目成员")
    private String member;



}
