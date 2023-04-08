package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.PmProjectOptionApi;
import com.donlim.pm.dto.PmProjectOptionDto;
import com.donlim.pm.entity.PmProjectOption;
import com.donlim.pm.service.PmProjectOptionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目流程配置(PmProjectOption)控制类
 *
 * @author sei
 * @since 2023-04-06 14:59:08
 */
@RestController
@Api(value = "PmProjectOptionApi", tags = "项目流程配置服务")
@RequestMapping(path = PmProjectOptionApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class PmProjectOptionController extends BaseEntityController<PmProjectOption, PmProjectOptionDto> implements PmProjectOptionApi {
    /**
     * 项目流程配置服务对象
     */
    @Autowired
    private PmProjectOptionService service;

    @Override
    public BaseEntityService<PmProjectOption> getService() {
        return service;
    }


    @Override
    public ResultData<PageResult<PmProjectOptionDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }
}
