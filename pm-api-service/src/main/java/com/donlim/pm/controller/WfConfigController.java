package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.WfConfigApi;
import com.donlim.pm.dto.WfConfigDto;
import com.donlim.pm.entity.WfConfig;
import com.donlim.pm.service.WfConfigService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流程配置(WfConfig)控制类
 *
 * @author sei
 * @since 2022-07-30 08:21:57
 */
@RestController
@Api(value = "WfConfigApi", tags = "流程配置服务")
@RequestMapping(path = WfConfigApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class WfConfigController extends BaseEntityController<WfConfig, WfConfigDto> implements WfConfigApi {
    /**
     * 流程配置服务对象
     */
    @Autowired
    private WfConfigService service;

    @Override
    public BaseEntityService<WfConfig> getService() {
        return service;
    }

}