package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import org.springframework.cloud.openfeign.FeignClient;
import com.changhong.sei.core.api.FindByPageApi;
import javax.validation.Valid;
import com.donlim.pm.dto.PmEmployeeDto;

/**
 * 员工表(PmEmployee)API
 *
 * @author sei
 * @since 2022-07-27 17:16:07
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = PmEmployeeApi.PATH)
public interface PmEmployeeApi extends BaseEntityApi<PmEmployeeDto> {
    String PATH = "pmEmployee";
}