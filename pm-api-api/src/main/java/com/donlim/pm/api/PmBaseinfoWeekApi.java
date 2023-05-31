package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.pm.dto.PmBaseinfoWeekDto;
import com.donlim.pm.dto.excel.WeekPlanReportExcelDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;


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

    /**
     * 双周计划明细主表查询
     * @param search 查询条件
     */
    @PostMapping(path = "getWeekReport",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "双周计划明细主表查询" ,notes = "双周计划明细主表查询")
    ResultData getWeekReport(@RequestBody Search search);

    /**
     * 双周计划明细子表查询
     * @param search 查询条件
     */
    @PostMapping(path = "getWeekReportDetail",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "双周计划明细子表查询" ,notes = "双周计划明细主表查询")
    ResultData getWeekReportDetail(@RequestBody Search search);

    /**
     * 验证双周计划是否完成
     * @param dto
     */
    @PostMapping(path = "confirmFinishPlan",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "验证双周计划是否完成" ,notes = "验证双周计划是否完成")
    ResultData<PmBaseinfoWeekDto> confirmFinishPlan(@RequestBody PmBaseinfoWeekDto dto);

    /**
     * 双周明细导出excel
     * @param search 查询条件
     */
    @PostMapping(path = "exportWeekPlanReport",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "双周明细导出excel" ,notes = "双周明细导出excel")
    ResultData<List<WeekPlanReportExcelDto>> exportWeekPlanReport(@RequestBody Search search);

}
