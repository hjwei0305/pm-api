package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.dao.WfConfigDao;
import com.donlim.pm.entity.WfConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 流程配置(WfConfig)业务逻辑实现类
 *
 * @author sei
 * @since 2022-07-30 08:21:57
 */
@Service
public class WfConfigService extends BaseEntityService<WfConfig> {
    @Autowired
    private WfConfigDao dao;

    @Override
    protected BaseEntityDao<WfConfig> getDao() {
        return dao;
    }

}