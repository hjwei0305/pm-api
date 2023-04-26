package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.pm.dto.PmPersonalMonthReportDetailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;


/**
 * 个人月计划明细(PmPersonalMonthReportDetail)API
 *
 * @author sei
 * @since 2023-04-24 16:51:10
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = PmPersonalMonthReportDetailApi.PATH)
public interface PmPersonalMonthReportDetailApi extends BaseEntityApi<PmPersonalMonthReportDetailDto>, FindByPageApi<PmPersonalMonthReportDetailDto> {
    String PATH = "pmPersonalMonthReportDetail";

    /**
     * 根据月度计划id查询计划明细
     * @param search
     * @return
     */
    @PostMapping("findBypersonalMonthReportId")
    ResultData findBypersonalMonthReportId(@RequestBody Search search);

    /**
     * 生成月度计划
     * @param search
     * @return
     */
    @PostMapping("createPersonalMonthPlan")
    ResultData createPersonalMonthPlan(@RequestBody Search search);

    /**
     * 保存个人月度计划
     * @param
     * @return
     */
    @PostMapping("monthPlanSaveBatch")
    ResultData monthPlanSaveBatch(@RequestBody List<PmPersonalMonthReportDetailDto> monthReportDetailDtoList);
}
