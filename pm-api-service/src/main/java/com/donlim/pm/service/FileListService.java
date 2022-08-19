package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.dao.FileListDao;
import com.donlim.pm.entity.FileList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * (FileList)业务逻辑实现类
 *
 * @author sei
 * @since 2022-08-18 18:22:18
 */
@Service
public class FileListService extends BaseEntityService<FileList> {
    @Autowired
    private FileListDao dao;

    @Override
    protected BaseEntityDao<FileList> getDao() {
        return dao;
    }

}
