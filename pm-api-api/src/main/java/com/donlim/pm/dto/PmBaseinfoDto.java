package com.donlim.pm.dto;

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
public class PmBaseinfoDto extends BaseEntityDto {
    private static final long serialVersionUID = 417621879606687300L;

    private String pmProjectOptionProName;
    private String pmProjectOptionProOpt;
    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 系统名称
     */
    @ApiModelProperty(name = "系统名称")
    private String sysName;
    /**
     * 组织编码
     */
    @ApiModelProperty(name = "组织编码")
    private String orgcode;
    /**
     * 组织名称
     */
    @ApiModelProperty(name = "组织名称")
    private String orgname;
    /**
     * 组织全称
     */
    @ApiModelProperty(name = "组织全称")
    private String extorgname;
    /**
     * 主导人
     */
    @ApiModelProperty(value = "主导人")
    private String projectMaster;
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
     * 日期是否改过
     */
    @ApiModelProperty(value = "日期是否改过")
    private Boolean dateModified;
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
     * 参与人数
     */
    @ApiModelProperty(value = "参与人数")
    private Integer attendanceMemberrCount;
    /**
     * 提案日期
     */
    @ApiModelProperty(value = "提案日期")
    private LocalDate submissionDate;
    /**
     * 规划审批
     */
    @ApiModelProperty(value = "规划审批")
    private String planningApproval;
    /**
     * 现状描述
     */
    @ApiModelProperty(value = "现状描述")
    private String currentDescription;
    /**
     * 需求描述
     */
    @ApiModelProperty(value = "需求描述")
    private String requirementDescription;
    /**
     * 改善效益
     */
    @ApiModelProperty(value = "改善效益")
    private String improveBenefits;
    /**
     * 推广度
     */
    @ApiModelProperty(value = "推广度")
    private String promotionDegree;
    /**
     * 硬件要求
     */
    @ApiModelProperty(value = "硬件要求")
    private String hardwareRequirement;
    /**
     * 项目类型
     */
    @ApiModelProperty(value = "项目类型")
    private String projectTypes;
    /**
     * 状态
     */
    @ApiModelProperty(value = "项目状态0为进行中、1为结束")
    private String status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 项目大点节
     */
    @ApiModelProperty(value = "项目大点节")
    @JsonSerialize(using = EnumJsonSerializer.class)
    private BigNodeType bigNode;
    /**
     * 项目小节点
     */
    @ApiModelProperty(value = "项目小节点")
    @JsonSerialize(using = EnumJsonSerializer.class)
    private SmallNodeType smallNode;
    /**
     * 需求范围说明书id
     */
    @ApiModelProperty(value = "需求范围说明书id")
    private String requireDocId;
    /**
     * 验收标准id
     */
    @ApiModelProperty(value = "验收标准id")
    private String acceptStandardDocId;
    /**
     * 启动报告id
     */
    @ApiModelProperty(value = "启动报告id")
    private String startReportDocId;
    /**
     * 用户需求说明书id
     */
    @ApiModelProperty(value = "用户需求说明书id")
    private String userRequireDocId;
    /**
     * 设计图id
     */
    @ApiModelProperty(value = "设计图id")
    private String designerDocId;
    /**
     * 切图id
     */
    @ApiModelProperty(value = "切图id")
    private String cropDocId;
    /**
     * 测试用例id
     */
    @ApiModelProperty(value = "测试用例id")
    private String testExampleDocId;
    /**
     * 测试报告id
     */
    @ApiModelProperty(value = "测试报告id")
    private String testReportDocId;
    /**
     * SOP
     */
    @ApiModelProperty(value = "SOP")
    private String sopDocId;
    /**
     * 问题清单id
     */
    @ApiModelProperty(value = "问题清单id")
    private String questionListDocId;
    /**
     * 关健节点点检表id
     */
    @ApiModelProperty(value = "关健节点点检表id")
    private String checkListDocId;
    /**
     * 结案报告id
     */
    @ApiModelProperty(value = "结案报告id")
    private String caseCloseReportDocId;
    /**
     * 满意度调查表id
     */
    @ApiModelProperty(value = "满意度调查表id")
    private String satisfactionSurveyDocId;
    /**
     * 页面点检id
     */
    @ApiModelProperty(value = "页面点检id")
    private String pageCheckDocId;
    /**
     * 验收单id
     */
    @ApiModelProperty(value = "验收单id")
    private String acceptOrderDocId;
    /**
     * 验收报告id
     */
    @ApiModelProperty(value = "验收报告id")
    private String accpetReprotDocId;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    @ApiModelProperty(value = "文档ID")
    private List<String> attachmentIdList;

    @ApiModelProperty(value = "文档类别")
    private String fileType;

    @ApiModelProperty(value = "状态数据JSON")
    private String gfxJson;

    /**
     * UI评审
     */
    @ApiModelProperty(value = "UI评审")
    private Boolean uiReview;
    /**
     * 代码评审
     */
    @ApiModelProperty(value = "代码评审")
    private Boolean codeReview;
    /**
     * 前端评审
     */
    @ApiModelProperty(value = "前端评审")
    private Boolean webReview;
    /**
     * 后端开发文档id
     */
    @ApiModelProperty(value = "后端开发文档id")
    private String codeDevDocId;
    /**
     * 前端开发文档id
     */
    @ApiModelProperty(value = "前端开发文档id")
    private String webDevDocId;
    /**
     * 测试结果
     */
    @ApiModelProperty(value = "测试结果")
    private Boolean test;
    /**
     * 需求评审
     */
    @ApiModelProperty(value = "需求评审")
    private Boolean requireReview;
    @ApiModelProperty(value = "主导人")
    private String leader;
    @ApiModelProperty(value = "协助人")
    private String assist;
    @ApiModelProperty(value = "设计人员")
    private String  designer;
    @ApiModelProperty(value = "开发人员")
    private String  developer;
    @ApiModelProperty(value = "实施人员")
    private String implementer;
    /**
     * 流程配置
     */
    @ApiModelProperty(value = "流程配置")
    private String proOpt;
    /**
     * 流程配置id
     */
    @ApiModelProperty(value = "流程配置id")
    private String proOptId;
    @ApiModelProperty(value = "项目成员")
    private String member;
    @ApiModelProperty(value = "本周计划")
    private String weekPlan;
    @ApiModelProperty(value = "下周计划")
    private String nextWeekPlan;
    @ApiModelProperty(value = "工作风险")
    private String workRisk;
    @ApiModelProperty(value = "双周计划更新时间")
    private LocalDate weekPlanUpdate;
    @ApiModelProperty(value = "是否暂停")
    private Boolean isPause;
}
