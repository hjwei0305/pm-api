package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.donlim.pm.dto.PmPersonalMonthReportDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;


/**
 * 个人月计划(PmPersonalMonthReport)API
 *
 * @author sei
 * @since 2023-04-24 13:59:23
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = PmPersonalMonthReportApi.PATH)
public interface PmPersonalMonthReportApi extends BaseEntityApi<PmPersonalMonthReportDto>, FindByPageApi<PmPersonalMonthReportDto> {
    String PATH = "pmPersonalMonthReport";


}
