package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.PmPersonalMonthReportDetailApi;
import com.donlim.pm.dto.PmPersonalMonthReportDetailDto;
import com.donlim.pm.em.OperationType;
import com.donlim.pm.entity.PmPersonalMonthReportDetail;
import com.donlim.pm.service.PmLogService;
import com.donlim.pm.service.PmPersonalMonthReportDetailService;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 个人月计划明细(PmPersonalMonthReportDetail)控制类
 *
 * @author sei
 * @since 2023-04-24 16:51:33
 */
@RestController
@Api(value = "PmPersonalMonthReportDetailApi", tags = "个人月计划明细服务")
@RequestMapping(path = PmPersonalMonthReportDetailApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class PmPersonalMonthReportDetailController extends BaseEntityController<PmPersonalMonthReportDetail, PmPersonalMonthReportDetailDto> implements PmPersonalMonthReportDetailApi {
    /**
     * 个人月计划明细服务对象
     */
    @Autowired
    private PmPersonalMonthReportDetailService service;
    @Autowired
    private PmLogService pmLogService;

    @Override
    public BaseEntityService<PmPersonalMonthReportDetail> getService() {
        return service;
    }

    @Override
    public ResultData findBypersonalMonthReportId(Search search) {
        return ResultData.success(service.findByFilters(search));
    }

    @Override
    public ResultData createPersonalMonthPlan(Search search) {
        List<SearchFilter> filters = search.getFilters();
        String ym = null;
        for (SearchFilter filter : filters) {
            if("month".equals(filter.getFieldName())){
                ym = (String) filter.getValue();
            }
        }
        if(StringUtils.isNotBlank(ym)){
            List<PmPersonalMonthReportDetail> personalMonthPlan = service.createPersonalMonthPlan(ym);
            return ResultData.success(personalMonthPlan);
        }else {
            return ResultData.fail("请选择生成月份");
        }
    }

    @Override
    public ResultData monthPlanSaveBatch(List<PmPersonalMonthReportDetailDto> monthReportDetailDtoList) {
        List<PmPersonalMonthReportDetail> pmPersonalMonthReportDetails = service.monthPlanSaveBatch(monthReportDetailDtoList);
        if(CollectionUtils.isNotEmpty(pmPersonalMonthReportDetails)){
            pmLogService.save(OperationType.SavePersonalMonthReport);
            return ResultData.success(pmPersonalMonthReportDetails);
        }
        return ResultData.fail("保存内容或月份不能为空");
    }

    @Override
    public ResultData<PageResult<PmPersonalMonthReportDetailDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }
}
