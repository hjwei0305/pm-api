package com.donlim.pm.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.pm.entity.PmBaseinfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 基础资料(PmBaseinfo)数据库访问类
 *
 * @author sei
 * @since 2022-07-28 09:01:47
 */
@Repository
public interface PmBaseinfoDao extends BaseEntityDao<PmBaseinfo> {

    public List<PmBaseinfo>findAllByCodeIn(List<String>codeList);

}
