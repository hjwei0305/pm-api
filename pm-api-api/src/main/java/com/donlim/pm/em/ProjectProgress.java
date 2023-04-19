package com.donlim.pm.em;

import com.changhong.sei.annotation.Remark;

/**
 * @author Carol
 * @date 2023/4/18 16:18
 * @description
 */
public enum ProjectProgress {
    @Remark("未启动")
    PRONOTSTART,
    @Remark("项目启动")
    PROSTART,
    @Remark("需求分析")
    REQUIREMENT,
    @Remark("系统开发")
    DEVELOPMENT,
    @Remark("测试")
    TEST,
    @Remark("上线实施")
    PROMOTE,
    @Remark("项目结案")
    FINISH;
}
