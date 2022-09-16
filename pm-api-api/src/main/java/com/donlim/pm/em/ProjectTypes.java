package com.donlim.pm.em;

import com.changhong.sei.annotation.Remark;

/**
 * @author Carol
 * @date 2022/8/26 16:18
 * @description
 */
public enum  ProjectTypes {
    @Remark("KPI项目")
    KPI,
    @Remark("年度重点项目")
    FOCUS,
    @Remark("其他项目")
    OTHER;
}
