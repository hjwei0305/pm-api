package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.core.dto.flow.FlowStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 代办事项(TodoList)DTO类
 *
 * @author sei
 * @since 2022-07-30 08:16:53
 */
@ApiModel(description = "代办事项DTO")
@EqualsAndHashCode(callSuper=true)
@Data
public class TodoListDto extends BaseEntityDto {
    private static final long serialVersionUID = 839294833465213816L;
    /**
     * 项目编码
     */
    @ApiModelProperty(value = "项目编码")
    private String projectCode;
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    /**
     * 代办事项
     */
    @ApiModelProperty(value = "代办事项")
    private String todoList;
    /**
     * 责任人工号
     */
    @ApiModelProperty(value = "责任人工号")
    private String ondutyCode;
    /**
     * 责任人姓名
     */
    @ApiModelProperty(value = "责任人姓名")
    private String ondutyName;
    /**
     * 提出人工号
     */
    @ApiModelProperty(value = "提出人工号")
    private String submitCode;
    /**
     * 提出人姓名
     */
    @ApiModelProperty(value = "提出人姓名")
    private String submitName;
    /**
     * 提出时间
     */
    @ApiModelProperty(value = "提出时间")
    private LocalDate submitDate;
    /**
     * 结案时间
     */
    @ApiModelProperty(value = "结案时间")
    private LocalDate endDate;
    /**
     * 是否完成
     */
    @ApiModelProperty(value = "是否完成")
    private Boolean isCompleted;
    /**
     * 是否结案
     */
    @ApiModelProperty(value = "是否结案")
    private Boolean isFinished;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;
    /**
     * 是否同步
     */
    @ApiModelProperty(value = "是否同步")
    private String isSync;
    /**
     * 类型：待办，通知
     */
    @ApiModelProperty(value = "类型：待办，通知")
    private String type;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createdDate;
    /**
     * 要求完成日期
     */
    @ApiModelProperty(value = "要求完成日期")
    private LocalDate completionDate;
    /**
     * 确认人(确认阶段)
     */
    @ApiModelProperty(value = "确认人(确认阶段)")
    private String confirmedby1;
    /**
     * 建议状态
     */
    @ApiModelProperty(value = "建议状态")
    private String proposalStatus;
    /**
     * 完成情况
     */
    @ApiModelProperty(value = "完成情况")
    private String completion;
    /**
     * 确认人(验证阶段)
     */
    @ApiModelProperty(value = "确认人(验证阶段)")
    private String confirmedby2;
    /**
     * 确认时间
     */
    @ApiModelProperty(value = "确认时间")
    private LocalDate confirmationTime;
    /**
     * 结案状态
     */
    @ApiModelProperty(value = "结案状态")
    private String closingStatus;
    /**
     * 单据状态
     */
    @ApiModelProperty(value = "单据状态")
    private String documentStatus;
    /**
     * 来源
     */
    @ApiModelProperty(value = "来源")
    private String source;
    /**
     * 审批状态
     */
    @ApiModelProperty(value = "审批状态")
    private FlowStatus flowStatus;


}
