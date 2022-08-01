package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.donlim.pm.dto.PmOrganizeDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 部门(PmOrganize)API
 *
 * @author sei
 * @since 2022-07-30 08:11:36
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = PmOrganizeApi.PATH)
public interface PmOrganizeApi extends BaseEntityApi<PmOrganizeDto> {
    String PATH = "pmOrganize";
}