package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.dao.PmOrganizeDao;
import com.donlim.pm.entity.PmOrganize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 部门(PmOrganize)业务逻辑实现类
 *
 * @author sei
 * @since 2022-07-30 08:12:25
 */
@Service
public class PmOrganizeService extends BaseEntityService<PmOrganize> {
    @Autowired
    private PmOrganizeDao dao;

    @Override
    protected BaseEntityDao<PmOrganize> getDao() {
        return dao;
    }

}