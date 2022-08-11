package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.edm.sdk.DocumentManager;
import com.donlim.pm.dao.PmBaseinfoDao;
import com.donlim.pm.dto.PmBaseinfoDto;
import com.donlim.pm.em.FileTypeEnum;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.util.EnumUtil;
import com.donlim.pm.util.ReflectUtils;
import com.google.gson.internal.reflect.ReflectionAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;


/**
 * 基础资料(PmBaseinfo)业务逻辑实现类
 *
 * @author sei
 * @since 2022-07-28 09:01:47
 */
@Service
public class PmBaseinfoService extends BaseEntityService<PmBaseinfo> {
    @Autowired
    private PmBaseinfoDao dao;
    @Autowired
    private DocumentManager documentManager;

    @Override
    protected BaseEntityDao<PmBaseinfo> getDao() {
        return dao;
    }


    /**
     * 绑定上传附件关系
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void  bindFile(PmBaseinfoDto dto){
        if(!StringUtils.isEmpty(dto.getId())&& !CollectionUtils.isEmpty(dto.getAttachmentIdList())){
            Optional<PmBaseinfo> byId = dao.findById(dto.getId());
            if(byId.isPresent() && EnumUtil.isIncludeFileTypeEnum(dto.getFileType())){
                documentManager.bindBusinessDocuments(dto.getId(),dto.getAttachmentIdList());
                //首字母转小写并加上Id
                String fieldName=dto.getFileType().substring(0, 1).toLowerCase() + dto.getFileType().substring(1)+"Id";
                //根据不同的类别记录附件的ID
                ReflectUtils.setFieldValue(byId.get(),fieldName,dto.getAttachmentIdList().get(0));
                save(byId.get());
            }
        }
    }


}
