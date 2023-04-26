package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.dao.PmPersonalMonthReportDao;
import com.donlim.pm.entity.PmPersonalMonthReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 个人月计划(PmPersonalMonthReport)业务逻辑实现类
 *
 * @author sei
 * @since 2023-04-24 14:00:01
 */
@Service
public class PmPersonalMonthReportService extends BaseEntityService<PmPersonalMonthReport> {
    @Autowired
    private PmPersonalMonthReportDao dao;


    @Override
    protected BaseEntityDao<PmPersonalMonthReport> getDao() {
        return dao;
    }


}
