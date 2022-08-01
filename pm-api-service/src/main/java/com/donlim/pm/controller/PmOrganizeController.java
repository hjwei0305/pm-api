package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.PmOrganizeApi;
import com.donlim.pm.dto.PmOrganizeDto;
import com.donlim.pm.entity.PmOrganize;
import com.donlim.pm.service.PmOrganizeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门(PmOrganize)控制类
 *
 * @author sei
 * @since 2022-07-30 08:12:25
 */
@RestController
@Api(value = "PmOrganizeApi", tags = "部门服务")
@RequestMapping(path = PmOrganizeApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class PmOrganizeController extends BaseEntityController<PmOrganize, PmOrganizeDto> implements PmOrganizeApi {
    /**
     * 部门服务对象
     */
    @Autowired
    private PmOrganizeService service;

    @Override
    public BaseEntityService<PmOrganize> getService() {
        return service;
    }

}