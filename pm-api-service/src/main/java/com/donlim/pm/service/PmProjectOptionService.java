package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.dao.PmProjectOptionDao;
import com.donlim.pm.entity.PmProjectOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 项目流程配置(PmProjectOption)业务逻辑实现类
 *
 * @author sei
 * @since 2023-04-06 14:59:07
 */
@Service
public class PmProjectOptionService extends BaseEntityService<PmProjectOption> {
    @Autowired
    private PmProjectOptionDao dao;

    @Override
    protected BaseEntityDao<PmProjectOption> getDao() {
        return dao;
    }

}
