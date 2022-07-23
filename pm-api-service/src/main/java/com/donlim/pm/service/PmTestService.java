package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.dao.PmTestDao;
import com.donlim.pm.entity.PmTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 测试(PmTest)业务逻辑实现类
 *
 * @author sei
 * @since 2022-07-23 09:19:29
 */
@Service
public class PmTestService extends BaseEntityService<PmTest> {
    @Autowired
    private PmTestDao dao;

    @Override
    protected BaseEntityDao<PmTest> getDao() {
        return dao;
    }

}
