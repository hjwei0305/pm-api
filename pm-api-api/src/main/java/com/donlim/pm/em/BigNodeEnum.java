package com.donlim.pm.em;

import com.changhong.sei.annotation.Remark;

/**
 * @Description:项目小节点状态枚举类
 * @Author: chenzhiquan
 * @Date: 2022/8/10.
 */
public enum BigNodeEnum {

    @Remark("项目提案")
    Start,
    @Remark("需求分析")
    RequireAnalysis,
    @Remark("UI")
    UI,
    @Remark("开发")
    Develop,
    @Remark("测试")
    Test,
    @Remark("上线")
    Online,
    @Remark("结案")
    End

}
