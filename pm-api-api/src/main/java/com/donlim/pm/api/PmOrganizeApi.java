package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseTreeApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.donlim.pm.dto.PmOrganizeDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;


/**
 * (PmOrganize)API
 *
 * @author sei
 * @since 2022-08-11 14:06:12
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = PmOrganizeApi.PATH)
public interface PmOrganizeApi extends BaseTreeApi<PmOrganizeDto>, FindByPageApi<PmOrganizeDto> {
    String PATH = "pmOrganize";

    /**
     * 获取组织结构树
     * @return 组织结构树
     */
    @GetMapping(path = "findOrgTree")
    @ApiOperation(value = "获取组织机构树",notes = "获取组织机构树")
    ResultData<PmOrganizeDto> findTree();
}
