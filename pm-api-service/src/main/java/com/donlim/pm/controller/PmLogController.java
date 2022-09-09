package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.PmLogApi;
import com.donlim.pm.dto.PmLogDto;
import com.donlim.pm.entity.PmLog;
import com.donlim.pm.service.PmLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (PmLog)控制类
 *
 * @author sei
 * @since 2022-09-06 14:23:48
 */
@RestController
@Api(value = "PmLogApi", tags = "服务")
@RequestMapping(path = PmLogApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class PmLogController extends BaseEntityController<PmLog, PmLogDto> implements PmLogApi {
    /**
     * 服务对象
     */
    @Autowired
    private PmLogService service;

    @Override
    public BaseEntityService<PmLog> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<PmLogDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }
}
