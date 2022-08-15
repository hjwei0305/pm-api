package com.donlim.pm.em;

import com.changhong.sei.annotation.Remark;

/**
 * @Description:项目小节点状态枚举类
 * @Author: chenzhiquan
 * @Date: 2022/8/10.
 */
public enum  SmallNodeEnum {
    @Remark("创建项目")
    Start,
    @Remark("调研")
    Research,
    @Remark("启动会议")
    StartMeeting,
    @Remark("提案系统")
    SubmitSystem,
    @Remark("需求评审")
    RequireReview,
    @Remark("UI设计")
    UIDesigner,
    @Remark("UI评审")
    UIReview,
    @Remark("前端评审")
    WebReview,
    @Remark("后端评审")
    CodeReview,
    @Remark("缺陷系统")
    TestSystem,
    @Remark("测试结果")
    TestResult,
    @Remark("上线")
    Online,
    @Remark("监控")
    Monitor,
    @Remark("结束")
    Close,
    @Remark("验收")
    Acceptance,
    @Remark("项目完结")
    End
}
