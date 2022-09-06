package com.donlim.pm.em;

import com.changhong.sei.annotation.Remark;

/**
 * @Description:日志类型
 * @Author: chenzhiquan
 * @Date: 2022/9/6.
 */
public enum  LogType {
    @Remark("修改主计划")
    ModifyMasterPlan,
    @Remark("修改前端计划")
    ModifyFrontPlan,
    @Remark("修改后端计划")
    ModifyCodePlan,
    @Remark("修改实施计划")
    ModifyImplPlan

}
