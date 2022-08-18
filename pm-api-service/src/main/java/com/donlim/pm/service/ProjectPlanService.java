package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.dao.ProjectPlanDao;
import com.donlim.pm.entity.ProjectPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 项目计划表(ProjectPlan)业务逻辑实现类
 *
 * @author sei
 * @since 2022-08-18 16:42:06
 */
@Service
public class ProjectPlanService extends BaseEntityService<ProjectPlan> {
    @Autowired
    private ProjectPlanDao dao;

    @Override
    protected BaseEntityDao<ProjectPlan> getDao() {
        return dao;
    }

}
