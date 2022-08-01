package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.donlim.pm.dto.WfConfigDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 流程配置(WfConfig)API
 *
 * @author sei
 * @since 2022-07-30 08:21:19
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = WfConfigApi.PATH)
public interface WfConfigApi extends BaseEntityApi<WfConfigDto> {
    String PATH = "wfConfig";
}