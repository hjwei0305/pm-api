package com.donlim.pm.em;
import com.changhong.sei.annotation.Remark;

/**
 * @Description:上传文件类别枚举类
 * @Author: chenzhiquan
 * @Date: 2022/8/10.
 */
public enum FileType {
    @Remark("需求范围说明书")
    RequireDoc,
    @Remark("验收标准")
    AcceptStandardDoc,
    @Remark( "启动报告")
    StartReportDoc,
    @Remark( "用户需求说明书")
    UserRequireDoc,
    @Remark( "设计图")
    DesignerDoc,
    @Remark( "切图")
    CropDoc,

    @Remark( "测试用例")
    TestExampleDoc,

    @Remark( "测试报告")
    TestReportDoc,

    @Remark( "SOP")
    SopDoc,

    @Remark( "问题清单")
    QuestionListDoc,

    @Remark( "关健节点点检表")
    CheckListDoc,

    @Remark( "结案报告")
    CaseCloseReportDoc,

    @Remark( "满意度调查表")
    SatisfactionSurveyDoc,

    @Remark( "页面点检")
    PageCheckDoc,

    @Remark( "验收单")
    AcceptOrderDoc,

    @Remark( "验收报告")
    AccpetReprotDoc,

}
