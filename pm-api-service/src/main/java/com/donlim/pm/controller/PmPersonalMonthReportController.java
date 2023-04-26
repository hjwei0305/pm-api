package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.PmPersonalMonthReportApi;
import com.donlim.pm.dto.PmPersonalMonthReportDto;
import com.donlim.pm.entity.PmPersonalMonthReport;
import com.donlim.pm.service.PmPersonalMonthReportService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 个人月计划(PmPersonalMonthReport)控制类
 *
 * @author sei
 * @since 2023-04-24 14:00:02
 */
@RestController
@Api(value = "PmPersonalMonthReportApi", tags = "个人月计划服务")
@RequestMapping(path = PmPersonalMonthReportApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class PmPersonalMonthReportController extends BaseEntityController<PmPersonalMonthReport, PmPersonalMonthReportDto> implements PmPersonalMonthReportApi {
    /**
     * 个人月计划服务对象
     */
    @Autowired
    private PmPersonalMonthReportService service;

    @Override
    public BaseEntityService<PmPersonalMonthReport> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<PmPersonalMonthReportDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }

}
