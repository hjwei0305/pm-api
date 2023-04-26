package com.donlim.pm.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.pm.entity.PmPersonalMonthReport;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * 个人月计划(PmPersonalMonthReport)数据库访问类
 *
 * @author sei
 * @since 2023-04-24 14:00:01
 */
@Repository
public interface PmPersonalMonthReportDao extends BaseEntityDao<PmPersonalMonthReport> {

    Optional<PmPersonalMonthReport> getFirstByEmployeeNameAndYm(String employeeName, String ym);


}
