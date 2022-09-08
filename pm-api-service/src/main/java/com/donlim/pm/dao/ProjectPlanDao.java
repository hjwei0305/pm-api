package com.donlim.pm.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.pm.entity.ProjectPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目计划表(ProjectPlan)数据库访问类
 *
 * @author sei
 * @since 2022-08-18 16:42:06
 */
@Repository
public interface ProjectPlanDao extends BaseEntityDao<ProjectPlan> {
    Long countByProjectIdAndPlanType(String id,String type);

    /**
     * 取出所有主计划的第一项
     * @param planType
     * @param schedureNo
     * @return
     */
    List<ProjectPlan> getAllByPlanTypeAndSchedureNo(String planType,String schedureNo);
}
