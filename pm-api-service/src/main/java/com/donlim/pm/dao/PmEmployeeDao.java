package com.donlim.pm.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import org.springframework.stereotype.Repository;
import com.donlim.pm.entity.PmEmployee;

/**
 * 员工表(PmEmployee)数据库访问类
 *
 * @author sei
 * @since 2022-07-27 17:17:10
 */
@Repository
public interface PmEmployeeDao extends BaseEntityDao<PmEmployee> {

}