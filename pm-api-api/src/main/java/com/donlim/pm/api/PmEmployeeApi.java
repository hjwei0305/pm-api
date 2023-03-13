package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.pm.dto.PmEmployeeDto;
import com.donlim.pm.dto.excel.PmEmployeeExcelDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


/**
 * (PmEmployee)API
 *
 * @author sei
 * @since 2022-08-10 16:44:03
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = PmEmployeeApi.PATH)
public interface PmEmployeeApi extends BaseEntityApi<PmEmployeeDto>, FindByPageApi<PmEmployeeDto> {
    String PATH = "pmEmployee";

    /**
     * 查找所有人员
     * @param search
     * @return
     */
    @PostMapping("findEmp")
    ResultData<List<PmEmployeeDto>> findEmp(@RequestBody Search search);

    /**
     * 导出人员一览表excel
     * @param search 查询条件
     */
    @PostMapping(path = "export",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "导出人员一览表" ,notes = "导出人员一览表")
    ResultData<List<PmEmployeeExcelDto>> export(@RequestBody Search search);

    /**
     * 同步人员
     * @param
     */
    @PostMapping(path = "synEmp",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "同步人员" ,notes = "同步人员")
    ResultData synEmp(@RequestBody Map<String, String> params);
}
