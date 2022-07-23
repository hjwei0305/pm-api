package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.donlim.pm.dto.PmTestDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 测试(PmTest)API
 *
 * @author sei
 * @since 2022-07-23 09:20:11
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = PmTestApi.PATH)
public interface PmTestApi extends BaseEntityApi<PmTestDto>, FindByPageApi<PmTestDto> {
    String PATH = "pmTest";
}
