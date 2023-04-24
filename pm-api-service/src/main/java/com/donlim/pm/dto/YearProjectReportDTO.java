package com.donlim.pm.dto;

import com.changhong.sei.core.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @author Carol
 * @date 2023/4/21 13:49
 * @description
 */
@NoArgsConstructor
@Data
public class YearProjectReportDTO extends BaseEntity {

    /**
     * 科室名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 科室负责人
     */
    @Column(name = "manager")
    private String manager;
    /**
     * 结案数量
     */
    @Column(name = "finishNum")
    private Long finishNum;
    /**
     * 未结案数量
     */
    @Column(name = "notFinishNum")
    private Long notFinishNum;
    /**
     * 暂停数量
     */
    @Column(name = "pauseNum")
    private Long pauseNum;
    /**
     * 项目总数
     */
    @Column(name = "totalNum")
    private Long totalNum;

}
