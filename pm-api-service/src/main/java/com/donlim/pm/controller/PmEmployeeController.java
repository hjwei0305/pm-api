package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.ResultData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.donlim.pm.api.PmEmployeeApi;
import com.donlim.pm.dto.PmEmployeeDto;
import com.donlim.pm.entity.PmEmployee;
import com.donlim.pm.service.PmEmployeeService;

/**
 * 员工表(PmEmployee)控制类
 *
 * @author sei
 * @since 2022-07-27 17:17:11
 */
@RestController
@Api(value = "PmEmployeeApi", tags = "员工表服务")
@RequestMapping(path = PmEmployeeApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class PmEmployeeController extends BaseEntityController<PmEmployee, PmEmployeeDto> implements PmEmployeeApi {
    /**
     * 员工表服务对象
     */
    @Autowired
    private PmEmployeeService service;

    @Override
    public BaseEntityService<PmEmployee> getService() {
        return service;
    }

}