package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.dao.ProjectScheduleDao;
import com.donlim.pm.entity.ProjectSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 项目计划表(ProjectSchedule)业务逻辑实现类
 *
 * @author sei
 * @since 2022-07-30 08:16:11
 */
@Service
public class ProjectScheduleService extends BaseEntityService<ProjectSchedule> {
    @Autowired
    private ProjectScheduleDao dao;

    @Override
    protected BaseEntityDao<ProjectSchedule> getDao() {
        return dao;
    }

}