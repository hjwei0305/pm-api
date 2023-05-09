package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.edm.sdk.DocumentManager;
import com.donlim.pm.dao.PmBaseinfoWeekDao;
import com.donlim.pm.dto.PmBaseinfoWeekDto;
import com.donlim.pm.entity.PmBaseinfoWeek;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Optional;


/**
 * 项目周计划(PmBaseinfoWeek)业务逻辑实现类
 *
 * @author sei
 * @since 2023-05-08 13:58:50
 */
@Service
public class PmBaseinfoWeekService extends BaseEntityService<PmBaseinfoWeek> {
    @Autowired
    private PmBaseinfoWeekDao dao;
    @Autowired
    private DocumentManager documentManager;

    @Override
    protected BaseEntityDao<PmBaseinfoWeek> getDao() {
        return dao;
    }

    /**
     * 绑定上传双周计划附件列表
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void bindFileList(PmBaseinfoWeekDto dto) {
        // 新建 -- id为空时，不做绑定
        if (!StringUtils.isEmpty(dto.getId()) && !CollectionUtils.isEmpty(dto.getAttachmentIdList())) {
            Optional<PmBaseinfoWeek> byId = dao.findById(dto.getId());
            if (byId.isPresent()) {
                // 实体id绑定附件
                documentManager.bindBusinessDocuments(dto.getId(), dto.getAttachmentIdList());
            }
        } else if (!StringUtils.isEmpty(dto.getId()) && CollectionUtils.isEmpty(dto.getAttachmentIdList())) {
            // 附件list为空，清空绑定
            documentManager.deleteBusinessInfos(dto.getId());
        }
    }
}
