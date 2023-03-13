package com.donlim.pm.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.controller.BaseTreeController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseTreeService;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.donlim.pm.api.PmOrganizeApi;
import com.donlim.pm.dto.PmOrganizeDto;
import com.donlim.pm.dto.excel.PmOrganizeExcelDto;
import com.donlim.pm.entity.PmOrganize;
import com.donlim.pm.service.PmOrganizeService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;


/**
 * (PmOrganize)控制类
 *
 * @author sei
 * @since 2022-08-11 14:05:49
 */
@RestController
@Api(value = "PmOrganizeApi", tags = "服务")
@RequestMapping(path = PmOrganizeApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class PmOrganizeController extends BaseTreeController<PmOrganize, PmOrganizeDto> implements PmOrganizeApi {
    /**
     * 服务对象
     */
    @Autowired
    private PmOrganizeService service;

    @Override
    public BaseTreeService<PmOrganize> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<PmOrganizeDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public ResultData<PmOrganizeDto> findTree() {
        return ResultData.success(convertToDto(service.findTree()));
    }

    @Override
    public ResultData<List<PmOrganizeExcelDto>> export(Search search) {
        List<PmOrganize> allOrders = service.findByFilters(search);
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<PmOrganize, PmOrganizeExcelDto> typeMap = modelMapper.typeMap(PmOrganize.class, PmOrganizeExcelDto.class);
        List<PmOrganizeExcelDto> collect = allOrders.stream().map(typeMap::map).collect(Collectors.toList());
        collect.stream().forEach(c ->{
            if(Objects.nonNull(c.getFrozen())){
                c.setFrozen(c.getFrozen().equals("1") ? "是" : "否");
            }
        });
        return ResultData.success(collect);


    }

    @Override
    public ResultData findClass() {
        List<PmOrganize> byFilter = service.findByFilters(new Search());
        Map<String, List<PmOrganize>> OrgMap = byFilter.stream().filter(a ->
                a.getIdpath().startsWith("1,265,266,12318") // 信息化管理中心/系统运维管理部
                        || a.getIdpath().startsWith("1,265,266,14090,1") // 信息化管理中心/系统项目管理部
                        || a.getIdpath().startsWith("1,265,266,14091,1") // 信息化管理中心/系统开发管理部
                        || a.getIdpath().startsWith("1,265,18038,18042,") // 数字化管理中心/系统运维管理部
                        || a.getIdpath().startsWith("1,265,18038,18040,1") // 数字化管理中心/系统开发管理部
                        || a.getIdpath().startsWith("1,265,18038,18043,1") // 联席项目系统推广管理部/运营策略科
                        || a.getIdpath().startsWith("1,265,18038,18041,1") // 技术与数据研究部/架构策略科
                        || a.getIdpath().startsWith("1,265,18038,18039,1") // 系统项目管理部/配件智造科
        ).collect(Collectors.groupingBy(PmOrganize::getName));
        ArrayList<PmOrganize> resultList = new ArrayList<>();
        for(String name : OrgMap.keySet()){
            resultList.add(OrgMap.get(name).get(0));
        }
        return ResultData.success(resultList);
    }

    @Override
    public ResultData synOrg(Map<String, String> params) {
        LogUtil.bizLog("后台任务由【{}】执行完成！", ContextUtil.getSessionUser());
        service.synOrg();
        return ResultDataUtil.success("执行成功");
    }
}
