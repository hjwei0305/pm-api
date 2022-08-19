package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.FileListApi;
import com.donlim.pm.dto.FileListDto;
import com.donlim.pm.entity.FileList;
import com.donlim.pm.service.FileListService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (FileList)控制类
 *
 * @author sei
 * @since 2022-08-18 18:22:18
 */
@RestController
@Api(value = "FileListApi", tags = "服务")
@RequestMapping(path = FileListApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class FileListController extends BaseEntityController<FileList, FileListDto> implements FileListApi {
    /**
     * 服务对象
     */
    @Autowired
    private FileListService service;

    @Override
    public BaseEntityService<FileList> getService() {
        return service;
    }

}
