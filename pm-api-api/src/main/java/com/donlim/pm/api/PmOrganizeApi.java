package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseTreeApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.pm.dto.PmOrganizeDto;
import com.donlim.pm.dto.excel.PmOrganizeExcelDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


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

    /**
     * 导出组织一览表excel
     * @param search 查询条件
     */
    @PostMapping(path = "export",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "导出组织一览表" ,notes = "导出组织一览表")
    ResultData<List<PmOrganizeExcelDto>> export(@RequestBody Search search);

    /**
     * 查找科室
     * @return
     */
    @PostMapping(path = "findClass")
    @ApiOperation(value = "查找科室" ,notes = "查找科室")
    ResultData findClass();

    /**
     * 同步组织
     * @param
     */
    @PostMapping(path = "synOrg",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "同步组织" ,notes = "同步组织")
    ResultData synOrg(@RequestBody Map<String, String> params);
}
