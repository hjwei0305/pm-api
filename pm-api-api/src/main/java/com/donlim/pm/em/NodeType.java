package com.donlim.pm.em;

import com.changhong.sei.annotation.Remark;

/**
 * @Description:用于前端显示类别
 * @Author: chenzhiquan
 * @Date: 2022/8/17.
 */
public enum NodeType {

    @Remark(value = "0",comments = "未通过")
    NoPass,
    @Remark(value = "1",comments = "通过")
    Pass,
    @Remark(value = "1",comments = "未到达")
    None,
}
