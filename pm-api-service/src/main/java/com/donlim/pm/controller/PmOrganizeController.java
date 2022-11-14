package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseTreeController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseTreeService;
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

import java.util.List;
import java.util.Objects;
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
        List<PmOrganize> classList = byFilter.stream().filter(a -> a.getIdpath().startsWith("1,265,266,12318")
                || a.getIdpath().startsWith("1,265,266,14090,1")
                || a.getIdpath().startsWith("1,265,266,14091,1"))
                .collect(Collectors.toList());
        return ResultData.success(classList);
    }
}
