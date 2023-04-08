package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.donlim.pm.dto.PmProjectOptionDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;


/**
 * 项目流程配置(PmProjectOption)API
 *
 * @author sei
 * @since 2023-04-06 15:00:17
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = PmProjectOptionApi.PATH)
public interface PmProjectOptionApi extends BaseEntityApi<PmProjectOptionDto>, FindByPageApi<PmProjectOptionDto> {
    String PATH = "pmProjectOption";
}
