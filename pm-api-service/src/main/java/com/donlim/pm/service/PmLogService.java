package com.donlim.pm.service;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.dao.PmLogDao;
import com.donlim.pm.dto.PmBaseinfoDto;
import com.donlim.pm.em.LogType;
import com.donlim.pm.entity.PmLog;
import com.donlim.pm.util.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * (PmLog)业务逻辑实现类
 *
 * @author sei
 * @since 2022-09-06 14:23:47
 */
@Service
public class PmLogService extends BaseEntityService<PmLog> {
    @Autowired
    private PmLogDao dao;

    @Override
    protected BaseEntityDao<PmLog> getDao() {
        return dao;
    }

    /**
     * 保存日志
     * @param logType  日志类型
     */
    public void save(LogType logType, PmBaseinfoDto dto){
        PmLog pmLog=new PmLog();
        pmLog.setEmployeeCode(ContextUtil.getUserAccount());
        pmLog.setEmployeeName(ContextUtil.getUserName());
        pmLog.setContent(EnumUtils.getEnumItemRemark(LogType.class,logType));
        pmLog.setProjectId(dto.getId());
        pmLog.setProjectTypes(dto.getProjectTypes());
        dao.save(pmLog);
    }

}
