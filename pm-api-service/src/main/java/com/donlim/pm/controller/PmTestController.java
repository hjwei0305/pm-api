package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.PmTestApi;
import com.donlim.pm.dto.PmTestDto;
import com.donlim.pm.entity.PmTest;
import com.donlim.pm.service.PmTestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试(PmTest)控制类
 *
 * @author sei
 * @since 2022-07-23 09:19:29
 */
@RestController
@Api(value = "PmTestApi", tags = "测试服务")
@RequestMapping(path = PmTestApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class PmTestController extends BaseEntityController<PmTest, PmTestDto> implements PmTestApi {
    /**
     * 测试服务对象
     */
    @Autowired
    private PmTestService service;

    @Override
    public BaseEntityService<PmTest> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<PmTestDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }
}
