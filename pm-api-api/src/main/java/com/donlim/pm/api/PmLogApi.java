package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.pm.dto.PmLogDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
public interface PmLogApi extends BaseEntityApi<PmLogDto>, FindByPageApi<PmLogDto> {
    String PATH = "pmLog";

    /**
     * 科室 / 个人操作量统计
     * @param search 查询参数
     */
    @PostMapping(path = "findVisitStatistics", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询访问量", notes = "查询访问量")
    ResultData findVisitStatistics(@RequestBody Search search);


}
