package com.donlim.pm.dto;

import lombok.Data;

/**
 * @Description:项目统计
 * @Author: chenzhiquan
 * @Date: 2022/9/8.
 */
@Data
public class ProjectInfoDto {
    /**
     * 未开始
     */
    private  Integer notStartedNum;
    /**
     * 进行中
     */
    private Integer processingNum;
    /**
     * 在线
     */
    private Integer onLineNum;
    /**
     * 提前完成
     */
    private Integer advanceFinishNum;
    /**
     * 逾期
     */
    private Integer overTimeNum;



}
