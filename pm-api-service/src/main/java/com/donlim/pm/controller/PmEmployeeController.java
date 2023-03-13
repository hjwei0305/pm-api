package com.donlim.pm.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.changhong.sei.util.EnumUtils;
import com.donlim.pm.api.PmEmployeeApi;
import com.donlim.pm.dto.PmEmployeeDto;
import com.donlim.pm.dto.excel.PmEmployeeExcelDto;
import com.donlim.pm.em.EmpstatidEnum;
import com.donlim.pm.entity.PmEmployee;
import com.donlim.pm.service.PmEmployeeService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * (PmEmployee)控制类
 *
 * @author sei
 * @since 2022-08-10 16:44:57
 */
@RestController
@Api(value = "PmEmployeeApi", tags = "服务")
@RequestMapping(path = PmEmployeeApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class PmEmployeeController extends BaseEntityController<PmEmployee, PmEmployeeDto> implements PmEmployeeApi {
    /**
     * 服务对象
     */
    @Autowired
    private PmEmployeeService service;

    @Override
    public BaseEntityService<PmEmployee> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<PmEmployeeDto>> findByPage(Search search) {
        List<SearchFilter> filters = search.getFilters();
        SearchFilter searchFilter = new SearchFilter("employeeCode","380287", SearchFilter.Operator.NE);
        SearchFilter searchFilter2 = new SearchFilter("empstatid",EmpstatidEnum.LEAVE, SearchFilter.Operator.NE);
        filters.add(searchFilter);
        filters.add(searchFilter2);
        search.setFilters(filters);
        return convertToDtoPageResult(service.findByPage(search));
    }


    @Override
    public ResultData<List<PmEmployeeDto>> findEmp(Search search) {
        SearchFilter searchFilter1 = new SearchFilter("employeeCode","380287", SearchFilter.Operator.NE);
        SearchFilter searchFilter2 = new SearchFilter("empstatid",EmpstatidEnum.LEAVE, SearchFilter.Operator.NE);
        List<SearchFilter> filters = search.getFilters();
        filters.add(searchFilter1);
        filters.add(searchFilter2);
        return ResultData.success(convertToDtos(service.findByFilters(new Search().setFilters(filters))));

    }

    @Override
    public ResultData<List<PmEmployeeExcelDto>> export(Search search) {
        List<PmEmployee> allOrders = service.findByFilters(search);
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<PmEmployee, PmEmployeeExcelDto> typeMap = modelMapper.typeMap(PmEmployee.class, PmEmployeeExcelDto.class);
        List<PmEmployeeExcelDto> collect = allOrders.stream().map(typeMap::map).collect(Collectors.toList());
        collect.stream().forEach(c ->{
            if(Objects.nonNull(c.getEmpstatid())){
                c.setEmpstatid(EnumUtils.getEnumItemRemark(EmpstatidEnum.class,EnumUtils.getEnum(EmpstatidEnum.class,c.getEmpstatid())));
            }
        });
        return ResultData.success(collect);
    }

    @Override
    public ResultData synEmp(Map<String, String> params) {
        LogUtil.bizLog("后台任务由【{}】执行完成！", ContextUtil.getSessionUser());
        service.synEmp();
        return ResultDataUtil.success("执行成功");
    }
}
