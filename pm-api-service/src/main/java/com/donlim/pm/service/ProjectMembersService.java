package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.dao.ProjectMembersDao;
import com.donlim.pm.entity.ProjectMembers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 项目组成员(ProjectMembers)业务逻辑实现类
 *
 * @author sei
 * @since 2022-07-30 08:14:05
 */
@Service
public class ProjectMembersService extends BaseEntityService<ProjectMembers> {
    @Autowired
    private ProjectMembersDao dao;

    @Override
    protected BaseEntityDao<ProjectMembers> getDao() {
        return dao;
    }

}