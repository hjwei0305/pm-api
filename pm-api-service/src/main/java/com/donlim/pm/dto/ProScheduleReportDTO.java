package com.donlim.pm.dto;

import com.changhong.sei.core.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Carol
 * @date 2023/4/18 13:49
 * @description
 */
@NoArgsConstructor
@Data
public class ProScheduleReportDTO extends BaseEntity {

    /**
     * 项目类型
     */
    @Column(name = "project_types")
    private String projectTypes;
    /**
     * 项目名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 开始日期
     */
    @Column(name = "start_date")
    private LocalDate startDate;
    /**
     * 计划结案日期
     */
    @Column(name = "plan_finish_date")
    private LocalDate planFinishDate;
    /**
     * 项目天数
     */
    @Column(name = "project_days")
    private Long projectDays;
    /**
     * 项目启动
     */
    private String proStart;
    /**
     * 需求制作
     */
    private String requirement;
    /**
     * 程序开发
     */
    private String development;
    /**
     * 测试验证
     */
    private String test;
    /**
     * 应用推广
     */
    private String promote;
    /**
     * 项目验收
     */
    private String finish;
    /**
     * 项目完成率
     */
    private BigDecimal completionRate;

}
