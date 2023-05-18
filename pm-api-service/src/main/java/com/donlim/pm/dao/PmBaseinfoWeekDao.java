package com.donlim.pm.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.pm.entity.PmBaseinfoWeek;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * 项目周计划(PmBaseinfoWeek)数据库访问类
 *
 * @author sei
 * @since 2023-05-08 13:58:50
 */
@Repository
public interface PmBaseinfoWeekDao extends BaseEntityDao<PmBaseinfoWeek> {

    /**
     * 更新周计划是否完成，不修改lastEditedDate
     * @param id
     * @param finishPlan
     */
    @Query("UPDATE PmBaseinfoWeek set finishPlan = :finishPlan WHERE id = :id")
    @Modifying
    public void updateFinishPlan(String id, Boolean finishPlan);
}
