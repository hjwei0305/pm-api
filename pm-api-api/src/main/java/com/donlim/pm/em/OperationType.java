package com.donlim.pm.em;

import com.changhong.sei.annotation.Remark;

/**
 * @Description:操作类型（用于统计访问/使用量）
 * @Author: Carol
 * @Date: 2023/5/25.
 */
public enum OperationType {
    // 人员管理
    @Remark("编辑组织信息")
    ModifyOrgInfo,
    // 项目清单
    @Remark("上传项目进度附件")
    UploadProjFile,
    // 待办明细
    @Remark("待办保存")
    SaveToDoList,
    // 项目进度表
    @Remark("项目进度导出")
    ExportProSchedule,
    // 个人月度计划
    @Remark("保存个人月度计划")
    SavePersonalMonthReport,
    // 科室年度项目
    @Remark("导出科室年度项目")
    ExportYearProjReport,
    // 双周计划明细
    @Remark("导出双周计划明细")
    ExportWeekPlanReport,
    @Remark("验证双周完成")
    ConfirmWeekPlan,

}
