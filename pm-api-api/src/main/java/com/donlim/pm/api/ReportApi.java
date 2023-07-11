package com.donlim.pm.api;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/9/15.
 */

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.pm.dto.PersonalBaseInfoReportDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * 基础资料(PmBaseinfo)API
 *
 * @author sei
 * @since 2022-07-28 08:56:30
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = ReportApi.PATH)
public interface ReportApi {
    String PATH = "report";
    /**
     * 分页查找欠料计划
     * @param search
     * @return
     */
    @PostMapping(path = "findPersonnelProjectStatistics",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查找欠料计划",notes = "查找欠料计划")
    ResultData<PageResult> findPersonnelProjectStatistics(@RequestBody Search search);

    /**
     * 无项目名单
     * @param search 查询条件
     */
    @PostMapping(path = "haveNoProjectList",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "无项目名单" ,notes = "无项目名单")
    ResultData<List<PersonalBaseInfoReportDto>> haveNoProjectList(@RequestBody Search search);
}
