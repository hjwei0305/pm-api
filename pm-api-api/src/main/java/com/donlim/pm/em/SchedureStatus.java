package com.donlim.pm.em;

import com.changhong.sei.annotation.Remark;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2023/3/21
 */
public enum SchedureStatus {
    @Remark("未开始")
    NOTSTART,
    @Remark("进行中")
    ONGOING,
    @Remark("完成")
    COMPELETE,
}
