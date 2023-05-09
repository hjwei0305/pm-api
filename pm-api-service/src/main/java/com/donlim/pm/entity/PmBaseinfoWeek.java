package com.donlim.pm.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 项目周计划(PmBaseinfoWeek)实体类
 *
 * @author sei
 * @since 2023-05-08 13:58:50
 */
@Entity
@Table(name = "pm_baseinfo_week")
@DynamicInsert
@DynamicUpdate
public class PmBaseinfoWeek extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = 414284500716402403L;
    /**
     * 项目id
     */
    @Column(name = "baseinfo_id")
    private String baseinfoId;
    /**
     * 周数
     */
    @Column(name = "week")
    private String week;
    /**
     * 本周计划
     */
    @Column(name = "week_plan")
    private String weekPlan;
    /**
     * 下周计划
     */
    @Column(name = "next_week_plan")
    private String nextWeekPlan;
    /**
     * 工作风险
     */
    @Column(name = "work_risk")
    private String workRisk;
    /**
     * 上次修改时间
     */
    @Column(name = "last_modified_time")
    private LocalDateTime lastModifiedTime;
    /**
     * 完成当周计划
     */
    @Column(name = "finish_plan")
    private Boolean finishPlan;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public String getBaseinfoId() {
        return baseinfoId;
    }

    public void setBaseinfoId(String baseinfoId) {
        this.baseinfoId = baseinfoId;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeekPlan() {
        return weekPlan;
    }

    public void setWeekPlan(String weekPlan) {
        this.weekPlan = weekPlan;
    }

    public String getNextWeekPlan() {
        return nextWeekPlan;
    }

    public void setNextWeekPlan(String nextWeekPlan) {
        this.nextWeekPlan = nextWeekPlan;
    }

    public String getWorkRisk() {
        return workRisk;
    }

    public void setWorkRisk(String workRisk) {
        this.workRisk = workRisk;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Boolean getFinishPlan() {
        return finishPlan;
    }

    public void setFinishPlan(Boolean finishPlan) {
        this.finishPlan = finishPlan;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
