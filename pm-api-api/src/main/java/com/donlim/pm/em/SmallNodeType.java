package com.donlim.pm.em;

import com.changhong.sei.annotation.Remark;

/**
 * @Description:项目小节点状态枚举类
 * @Author: chenzhiquan
 * @Date: 2022/8/10.
 */
public enum SmallNodeType {
    @Remark(value = "1.1",comments = "创建项目")
    Start,
    @Remark(value = "2.1",comments = "调研")
    Research,
    @Remark(value = "2.2",comments = "启动会议")
    StartMeeting,
    @Remark(value = "2.3",comments = "提案系统")
    SubmitSystem,
    @Remark(value = "2.4",comments = "需求评审")
    RequireReview,
    @Remark(value = "3.1",comments = "UI设计")
    UIDesigner,
    @Remark(value = "3.2",comments = "UI评审")
    UIReview,
    @Remark(value = "4.1",comments = "前端开发")
    WebDev,
    @Remark(value = "4.2",comments = "前端评审")
    WebReview,
    @Remark(value = "4.3",comments = "后端开发")
    CodeDev,
    @Remark(value = "4.4",comments = "后端评审")
    CodeReview,
    @Remark(value = "5.1",comments = "缺陷系统")
    TestSystem,
    @Remark(value = "5.2",comments = "测试结果")
    Test,
    @Remark(value = "6.1",comments = "上线")
    Online,
    @Remark(value = "6.2",comments = "监控")
    Monitor,
    @Remark(value = "7.1",comments = "结案")
    Close,
    @Remark(value = "7.2",comments = "验收")
    Acceptance,
    @Remark(value = "7.3",comments = "项目完结")
    End
}
