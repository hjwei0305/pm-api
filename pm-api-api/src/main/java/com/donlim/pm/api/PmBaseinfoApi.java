package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.donlim.pm.dto.PmBaseinfoDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 基础资料(PmBaseinfo)API
 *
 * @author sei
 * @since 2022-07-28 08:56:30
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = PmBaseinfoApi.PATH)
public interface PmBaseinfoApi extends BaseEntityApi<PmBaseinfoDto>, FindByPageApi<PmBaseinfoDto> {
    String PATH = "pmBaseinfo";
}