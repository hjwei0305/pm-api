package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.donlim.pm.dto.PmBaseinfoWeekDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;



/**
 * 项目周计划(PmBaseinfoWeek)API
 *
 * @author sei
 * @since 2023-05-08 13:58:32
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = PmBaseinfoWeekApi.PATH)
public interface PmBaseinfoWeekApi extends BaseEntityApi<PmBaseinfoWeekDto>, FindByPageApi<PmBaseinfoWeekDto> {
    String PATH = "pmBaseinfoWeek";
    /**
     * 绑定附件列表
     * @param dto
     * @return
     * @throws IllegalAccessException
     */
    @PostMapping(path = "saveAttachList")
    @ApiOperation(value = "绑定附件列表", notes = "绑定附件列表")
    ResultData<PmBaseinfoWeekDto> saveAttachList(@RequestBody PmBaseinfoWeekDto dto) throws IllegalAccessException;
}
