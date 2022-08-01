package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.donlim.pm.entity.PmEmployee;
import com.donlim.pm.dao.PmEmployeeDao;


/**
 * 员工表(PmEmployee)业务逻辑实现类
 *
 * @author sei
 * @since 2022-07-27 17:17:10
 */
@Service
public class PmEmployeeService extends BaseEntityService<PmEmployee> {
    @Autowired
    private PmEmployeeDao dao;

    @Override
    protected BaseEntityDao<PmEmployee> getDao() {
        return dao;
    }

}