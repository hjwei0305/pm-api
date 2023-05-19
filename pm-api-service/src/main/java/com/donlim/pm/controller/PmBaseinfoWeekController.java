package com.donlim.pm.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.donlim.pm.api.PmBaseinfoWeekApi;
import com.donlim.pm.dto.PmBaseinfoDto;
import com.donlim.pm.dto.PmBaseinfoWeekDto;
import com.donlim.pm.dto.WeekReportDto;
import com.donlim.pm.dto.excel.WeekPlanReportExcelDto;
import com.donlim.pm.em.LogType;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.entity.PmBaseinfoWeek;
import com.donlim.pm.service.PmBaseinfoService;
import com.donlim.pm.service.PmBaseinfoWeekService;
import com.donlim.pm.service.PmLogService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 项目周计划(PmBaseinfoWeek)控制类
 *
 * @author sei
 * @since 2023-05-08 13:58:51
 */
@RestController
@Api(value = "PmBaseinfoWeekApi", tags = "项目周计划服务")
@RequestMapping(path = PmBaseinfoWeekApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class PmBaseinfoWeekController extends BaseEntityController<PmBaseinfoWeek, PmBaseinfoWeekDto> implements PmBaseinfoWeekApi {
    /**
     * 项目周计划服务对象
     */
    @Autowired
    private PmBaseinfoWeekService service;
    @Autowired
    private PmBaseinfoService pmBaseinfoService;
    @Autowired
    private PmLogService pmLogService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BaseEntityService<PmBaseinfoWeek> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<PmBaseinfoWeekDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public ResultData<PmBaseinfoWeekDto> save(PmBaseinfoWeekDto dto) {
        // 限制修改权限
        PmBaseinfo pmBaseinfo = pmBaseinfoService.findFirstByProperty("id", dto.getBaseinfoId());
        if(!pmBaseinfo.getMember().contains(ContextUtil.getUserName())){
            return ResultData.fail("非项目成员，没有修改权限");
        }
        // 字数判断
        if(StringUtils.isBlank(dto.getWeekPlan()) || dto.getWeekPlan().length() <5){
            return ResultData.fail("本周计划字数不能少于5个。");
        }
        if(StringUtils.isBlank(dto.getNextWeekPlan()) ||dto.getNextWeekPlan().length() <5){
            return ResultData.fail("下周计划字数不能少于5个。");
        }
        if(StringUtils.isBlank(dto.getWorkRisk())){
            return ResultData.fail("工作风险点不能为空。");
        }
        // 保存双周计划
        ResultData<PmBaseinfoWeekDto> result = super.save(dto);
        PmBaseinfoWeekDto resultDto = result.getData();
        // 保存到项目信息
        pmBaseinfo.setWeekPlan(resultDto.getWeekPlan());
        pmBaseinfo.setNextWeekPlan(resultDto.getNextWeekPlan());
        Instant instant = resultDto.getLastEditedDate().toInstant();
        pmBaseinfo.setWeekPlanUpdate(instant.atZone(ZoneId.systemDefault()).toLocalDate());
        pmBaseinfoService.save(pmBaseinfo);
        // 双周保存日志
        pmLogService.save(LogType.ModifyWeekPlan,modelMapper.map(pmBaseinfo, PmBaseinfoDto.class));
        // 新建点击保存 -- 判断有无附件进行绑定；非新建 -- 上传就绑定
        if (StringUtils.isEmpty(dto.getId()) && !CollectionUtils.isEmpty(dto.getAttachmentIdList())) {
            // 保存后数据
            resultDto.setAttachmentIdList(dto.getAttachmentIdList());
            service.bindFileList(resultDto);
        }
        return result;
    }

    @Override
    public ResultData<PmBaseinfoWeekDto> saveAttachList(PmBaseinfoWeekDto dto) throws IllegalAccessException {
        service.bindFileList(dto);
        return ResultDataUtil.success("执行成功");
    }

    @Override
    public ResultData getWeekReport(Search search) {
        return ResultData.success(service.getWeekReport(search));

    }

    @Override
    public ResultData<PmBaseinfoWeekDto> confirmFinishPlan(PmBaseinfoWeekDto dto) {
        PmBaseinfoWeek pmBaseinfoWeek = service.confirmFinishPlan(dto);
        if(!ObjectUtils.isEmpty(pmBaseinfoWeek)){
            return ResultData.success(convertToDto(pmBaseinfoWeek));
        }
        return ResultData.fail("id为【" + dto.getId() + "】的双周计划不存在，请联系管理员");
    }

    @Override
    public ResultData<List<WeekPlanReportExcelDto>> exportWeekPlanReport(Search search) {
        List<WeekReportDto> weekReportDTOS = service.getWeekReport(search);
        TypeMap<WeekReportDto, WeekPlanReportExcelDto> typeMap = dtoModelMapper.typeMap(WeekReportDto.class, WeekPlanReportExcelDto.class);
        List<WeekPlanReportExcelDto> excelList = weekReportDTOS.stream().map(typeMap::map).collect(Collectors.toList());
        return ResultData.success(excelList);
    }
}
