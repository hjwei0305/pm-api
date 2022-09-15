package com.donlim.pm.dto.upload;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/9/14.
 */
@Data
public class PlanDTO {

    private String projectCode;
    private LocalDate planStartDate;
    /**
     * 计划结束日期
     */
    private LocalDate planEndDate;
    /**
     * 实际开始日期
     */
    private LocalDate actualStartDate;
    /**
     * 实际结束日期
     */
    private LocalDate actualEndDate;
    /**
     * 天数
     */
    private String schedureDays;
    /**
     * 序号
     */
    private String schedureNo;
    /**
     * 任务类型
     */
    private String workType;
    /**
     * 任务列表
     */
    private String workTodoList;
    /**
     * 负责人
     */
    private String workOnduty;
    /**
     * 协助人
     */
    private String workAssist;
    /**
     * 备注
     */
    private String remark;
}
