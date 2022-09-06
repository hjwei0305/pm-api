package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.donlim.pm.dto.PmLogDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * (PmLog)API
 *
 * @author sei
 * @since 2022-09-06 14:25:57
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = PmLogApi.PATH)
public interface PmLogApi extends BaseEntityApi<PmLogDto> {
    String PATH = "pmLog";
}
