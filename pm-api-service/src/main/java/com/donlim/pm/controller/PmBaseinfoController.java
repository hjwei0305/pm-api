package com.donlim.pm.controller;

import com.changhong.sei.basic.api.UserApi;
import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchOrder;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.changhong.sei.edm.sdk.DocumentManager;
import com.donlim.pm.api.PmBaseinfoApi;
import com.donlim.pm.dto.PmBaseinfoDto;
import com.donlim.pm.dto.excel.PmBaseinfoExcelDto;
import com.donlim.pm.em.LogType;
import com.donlim.pm.em.ProjectTypes;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.entity.ProjectPlan;
import com.donlim.pm.service.PmBaseinfoService;
import com.donlim.pm.service.PmLogService;
import com.donlim.pm.service.ProjectPlanService;
import com.donlim.pm.service.TodoListService;
import com.donlim.pm.util.EnumUtils;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 基础资料(PmBaseinfo)控制类
 *
 * @author sei
 * @since 2022-07-28 09:01:47
 */
@RestController
@Api(value = "PmBaseinfoApi", tags = "基础资料服务")
@RequestMapping(path = PmBaseinfoApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class PmBaseinfoController extends BaseEntityController<PmBaseinfo, PmBaseinfoDto> implements PmBaseinfoApi {
    /**
     * 基础资料服务对象
     */
    @Autowired
    private PmBaseinfoService service;
    @Autowired
    private PmLogService pmLogService;
    @Autowired
    private DocumentManager documentManager;
    @Autowired
    private UserApi userApi;
    @Autowired
    private TodoListService todoListService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Override
    public BaseEntityService<PmBaseinfo> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<PmBaseinfoDto>> findByPage(Search search) {
        //非管理员只显示自己的项目
        boolean isAdmin=false;
      /*  ResultData<List<FeatureRoleDto>> featureRolesByAccount = userApi.getFeatureRolesByAccount(ContextUtil.getUserAccount());
        if (featureRolesByAccount.getSuccess()) {
          for(FeatureRoleDto roleDto:featureRolesByAccount.getData()){
             if(roleDto.getCode().equals("DONLIM-XB-PM-ADMIN")){
                 isAdmin=true;
             }
          }
        }
        PageResult<PmBaseinfo> byPage = service.findByPage(search);
        byPage.getRows().stream().forEach(info -> {
            if (StringUtils.isNotEmpty(info.getProjectTypes())) {
                String enumItemRemark = EnumUtils.getEnumItemRemark(ProjectTypes.class, Integer.valueOf(info.getProjectTypes()));
                info.setProjectTypes(enumItemRemark);
            }
        });*/
        SearchOrder searchOrder=new SearchOrder("overedDays", SearchOrder.Direction.ASC);
        List<SearchOrder>searchOrderList=new ArrayList<>();
        searchOrderList.add(searchOrder);
        search.setSortOrders(searchOrderList);
      //  search.addFilter(new SearchFilter("orgname","运营策略科", SearchFilter.Operator.EQ));
        PageResult<PmBaseinfo> byPage = service.findByPage(search);
        byPage.getRows().stream().forEach(info -> {
            long overTimeDay=0;
            long advanceDay=0;
            //计算是否逾期，逾期天数
            if (info.getFinalFinishDate() != null && info.getPlanFinishDate() != null) {
                if(info.getFinalFinishDate().isAfter(info.getPlanFinishDate())){
                    if(LocalDate.now().isAfter(info.getFinalFinishDate())){
                        overTimeDay =info.getFinalFinishDate().toEpochDay()- info.getPlanFinishDate().toEpochDay();
                    }
                }else{
                    advanceDay =info.getPlanFinishDate().toEpochDay()- info.getFinalFinishDate().toEpochDay();
                }

            }
            if(info.getPlanFinishDate() != null &&  info.getFinalFinishDate() == null && LocalDate.now().isAfter(info.getPlanFinishDate())){
                overTimeDay =LocalDate.now().toEpochDay()- info.getPlanFinishDate().toEpochDay();
            }
            info.setOveredDays(overTimeDay);
            info.setAdvanceDays(advanceDay);
            if(overTimeDay>0){
                info.setIsOverdue(true);
            }else{
                info.setIsOverdue(false);
            }
            if(advanceDay>0){
                info.setIsAdvance(true);
            }else{
                info.setIsAdvance(false);
            }
            if (StringUtils.isNotEmpty(info.getProjectTypes())) {
                String enumItemRemark = EnumUtils.getEnumItemRemark(ProjectTypes.class, Integer.valueOf(info.getProjectTypes()));
                info.setProjectTypes(enumItemRemark);
                List<ProjectPlan> projectPlanList = projectPlanService.findListByProperty("projectId", info.getId());
                Map<String, List<ProjectPlan>> doingList = projectPlanList.stream()
                        .filter(p -> null != p.getSchedureStatus() && p.getSchedureStatus().equals("进行中"))
                        .collect(Collectors.groupingBy(ProjectPlan::getPlanType));
                String progress = "";
                for (Map.Entry<String, List<ProjectPlan>> entry : doingList.entrySet()) {
                    Integer key = Integer.valueOf(entry.getKey());
                    switch (key){
                        case 0: progress += "主计划：";
                            break;
                        case 1: progress += "后端计划：";
                            break;
                        case 2: progress += "前端计划：";
                            break;
                        case 3: progress += "实施计划：";
                            break;
                        case 4: progress += "UI计划：";
                            break;
                    }
                    int index = 1;
                    for (ProjectPlan projectPlan : entry.getValue()) {
                        progress += projectPlan.getWorkTodoList();
                        if(index < entry.getValue().size()){
                            progress += "，";
                        }
                        if(index == entry.getValue().size()){
                            progress += "；";
                        }
                        index++;
                    }
                }
                info.setRemark(progress);
            }
        });
        if(true || ContextUtil.getUserAccount().equals("admin")){
            return convertToDtoPageResult(byPage);
        }else{
            List<PmBaseinfo>newRows=new ArrayList<>();
            ArrayList<PmBaseinfo> rows = byPage.getRows();
            for(PmBaseinfo pmBaseinfo :rows){
                if(pmBaseinfo.getLeader().contains(ContextUtil.getUserName())
                        ||pmBaseinfo.getAssist().contains(ContextUtil.getUserName())
                        ||pmBaseinfo.getDesigner().contains(ContextUtil.getUserName())
                        ||pmBaseinfo.getDeveloper().contains(ContextUtil.getUserName())
                        ||pmBaseinfo.getImplementer().contains(ContextUtil.getUserName())){
                    newRows.add(pmBaseinfo);
                }
            }
            byPage.setRows(newRows);
            return convertToDtoPageResult(byPage);

        }
     /*   PageResult<PmBaseinfo> byPage = service.findByPage(search);
        byPage.getRows().stream().forEach(info -> {
            if (StringUtils.isNotEmpty(info.getProjectTypes())) {
                String enumItemRemark = EnumUtils.getEnumItemRemark(ProjectTypes.class, Integer.valueOf(info.getProjectTypes()));
                info.setProjectTypes(enumItemRemark);
            }
            // 1、验收阶段
            if (null != info.getStatus() && info.getStatus().equals("1")) {
                info.setCurrentPeriod("验收");
                info.setMasterScheduleRate("100%");
            } else if (null != info.getTest() && info.getTest()) {
                // 2、测试结果
                info.setCurrentPeriod("测试");
                info.setMasterScheduleRate("80%");
            } else if ((null != info.getCodeReview() && info.getTest()) || (null != info.getWebReview() && info.getWebReview())) {
                // 3、前后端
                info.setCurrentPeriod("开发");
                info.setMasterScheduleRate("60%");
            } else if (null != info.getUiReview() && info.getUiReview()) {
                // 4、UI设计
                info.setCurrentPeriod("设计");
                info.setMasterScheduleRate("40%");
            } else if (null != info.getRequireReview() && info.getRequireReview()) {
                // 4、需求规划
                info.setCurrentPeriod("规划");
                info.setMasterScheduleRate("20%");
            } else {
                info.setCurrentPeriod("未开始");
                info.setMasterScheduleRate("0%");
            }


        });
        return convertToDtoPageResult(service.findByPage(search));*/
    }

    @Override
    public ResultData<PmBaseinfoDto> save(PmBaseinfoDto dto) {
        service.bindFile(dto);
        return ResultData.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<PmBaseinfoDto> saveBaseInfo(PmBaseinfoDto dto) throws IllegalAccessException {
        PmBaseinfoDto pmBaseinfoDto;
        if (StringUtils.isNotEmpty(dto.getId())) {
            // 查找原数据
            PmBaseinfo originInfo = service.findFirstByProperty("id", dto.getId());
            //只有主导人或管理员才能修改
            if (StringUtils.isNotEmpty(dto.getLeader()) && (originInfo.getLeader().contains(ContextUtil.getUserName()) || ContextUtil.getUserName().equals("admin"))) {

            } else {
                return ResultData.fail("只有主导人才可以修改信息");
            }
            PmBaseinfo pmBaseinfo = service.findOne(dto.getId());
//            pmBaseinfoDto = convertToDto(pmBaseinfo);
            pmBaseinfoDto = dto;
            pmBaseinfoDto.setId(pmBaseinfo.getId());
        } else {
            pmBaseinfoDto = dto;
            pmBaseinfoDto.setId(null);
        }
        if(!ObjectUtils.isEmpty(pmBaseinfoDto.getWeekPlanUpdate()) && pmBaseinfoDto.getWeekPlanUpdate().compareTo(LocalDate.now()) >= 0){
            if(StringUtils.isBlank(pmBaseinfoDto.getWeekPlan()) || pmBaseinfoDto.getWeekPlan().length() <5){
                return ResultData.fail("本周计划字数不能少于5个。");
            }
            if(StringUtils.isBlank(pmBaseinfoDto.getNextWeekPlan()) ||pmBaseinfoDto.getNextWeekPlan().length() <5){
                return ResultData.fail("下周计划字数不能少于5个。");
            }
            if(StringUtils.isBlank(pmBaseinfoDto.getWorkRisk())){
                return ResultData.fail("工作风险点不能为空。");
            }
            pmLogService.save(LogType.ModifyWeekPlan,pmBaseinfoDto);
        }
        if(StringUtils.isEmpty(pmBaseinfoDto.getName())){
            return ResultData.fail("项目名称为空，不能保存。");
        }
        if(StringUtils.isEmpty(pmBaseinfoDto.getProjectTypes())){
            return ResultData.fail("项目类型为空，不能保存。");
        }
        if(StringUtils.isEmpty(pmBaseinfoDto.getLeader())){
            return ResultData.fail("项目主导人为空，不能保存。");
        }
        if(StringUtils.isEmpty(pmBaseinfoDto.getSysName())){
            return ResultData.fail("系统名称为空，不能保存。");
        }
        if(dto.getStartDate() == null || dto.getPlanFinishDate() == null){
            return ResultData.fail("项目开始或计划结案日期为空，不能保存。");
        }
        pmBaseinfoDto.setProjectTypes(dto.getProjectTypes());
        pmBaseinfoDto.setCurrentPeriod(dto.getCurrentPeriod());
        pmBaseinfoDto.setLeader(dto.getLeader());
        pmBaseinfoDto.setAssist(dto.getAssist());
        pmBaseinfoDto.setDesigner(dto.getDesigner());
        pmBaseinfoDto.setImplementer(dto.getImplementer());
        pmBaseinfoDto.setDeveloper(dto.getDeveloper());
        pmBaseinfoDto.setAttendanceMemberrCount(dto.getAttendanceMemberrCount());
        pmBaseinfoDto.setProOpt(dto.getProOpt());
        pmBaseinfoDto.setStartDate(dto.getStartDate());
        pmBaseinfoDto.setPlanFinishDate(dto.getPlanFinishDate());
        pmBaseinfoDto.setFinalFinishDate(dto.getFinalFinishDate());
        pmBaseinfoDto.setStatus(null != dto.getStatus() ? dto.getStatus() : "0");
        if(!StringUtils.isBlank(dto.getOrgname())){
            pmBaseinfoDto.setOrgname(dto.getOrgname());
            pmBaseinfoDto.setOrgcode(dto.getOrgcode());
            pmBaseinfoDto.setExtorgname(dto.getExtorgname());
        }else{
            return ResultData.fail("科室名称为空，不能保存。");
        }
        if(dto.getFinalFinishDate() != null && dto.getStartDate() != null){
            pmBaseinfoDto.setProjectDays((int)(dto.getFinalFinishDate().toEpochDay()-dto.getStartDate().toEpochDay()));
        }else {
            pmBaseinfoDto.setProjectDays(null);
        }
        String member="";
        String memberStr=pmBaseinfoDto.getLeader()+","+pmBaseinfoDto.getAssist()+","+pmBaseinfoDto.getDesigner()
                +","+pmBaseinfoDto.getDeveloper()+","+pmBaseinfoDto.getImplementer();
        //去重
        String[] members = memberStr.split(",");
        for(String str :members){
            if(!member.contains(str)){
                member+=str+",";
            }
        }
        pmBaseinfoDto.setMember(member.substring(0,member.length()-1));
        // 新建项目取项目id
        ResultData<PmBaseinfoDto> result = super.save(pmBaseinfoDto);
        dto.setId(result.getData().getId());
        pmLogService.save(LogType.ModifyProjectInfo,dto);
        todoListService.addNotice(pmBaseinfoDto);
        return result;
    }

    @Override
    public ResultData<PmBaseinfoDto> saveAttachList(PmBaseinfoDto dto) throws IllegalAccessException {
        service.bindFileList(dto);
        return ResultDataUtil.success("执行成功");
    }

    @Override
    public ResultData getProjectInfo(Search search)  {
        return ResultData.success(service.getProjectInfo(search));
    }

    @Override
    public ResultData findPageByUserName() {
        String userName = ContextUtil.getUserName();
        List<PmBaseinfo> allProj = service.findAll();
        ArrayList<PmBaseinfo> newRows = new ArrayList<>();
        allProj.stream().forEach(info -> {
            if (info.getLeader().contains(userName) || info.getAssist().contains(userName)|| info.getDesigner().contains(userName)
                    || info.getImplementer().contains(userName) || info.getDeveloper().contains(userName)) {
                if (StringUtils.isNotEmpty(info.getProjectTypes())) {
                    String enumItemRemark = EnumUtils.getEnumItemRemark(ProjectTypes.class, Integer.valueOf(info.getProjectTypes()));
                    info.setProjectTypes(enumItemRemark);
                }
                newRows.add(info);
            }
        });
        PageResult<PmBaseinfo> byPage = service.findByPage(new Search());
        byPage.setRows(newRows);
        return convertToDtoPageResult(byPage);
    }

    @Override
    public ResultData<List<PmBaseinfoExcelDto>> export(Search search) {
        List<PmBaseinfo> pmBaseinfoList = service.findByFilters(search);

        pmBaseinfoList.stream().forEach(info ->{
            List<ProjectPlan> projectPlanList = projectPlanService.findListByProperty("projectId", info.getId());
            Map<String, List<ProjectPlan>> doingList = projectPlanList.stream()
                    .filter(p -> null != p.getSchedureStatus() && p.getSchedureStatus().equals("进行中"))
                    .collect(Collectors.groupingBy(ProjectPlan::getPlanType));
            String progress = "";
            for (Map.Entry<String, List<ProjectPlan>> entry : doingList.entrySet()) {
                Integer key = Integer.valueOf(entry.getKey());
                switch (key){
                    case 0: progress += "主计划：";
                        break;
                    case 1: progress += "前端计划：";
                        break;
                    case 2: progress += "后端计划：";
                        break;
                    case 3: progress += "实施计划：";
                        break;
                    case 4: progress += "UI计划：";
                        break;
                }
                int index = 1;
                for (ProjectPlan projectPlan : entry.getValue()) {
                    progress += projectPlan.getWorkTodoList();
                    if(index < entry.getValue().size()){
                        progress += "，";
                    }
                    if(index == entry.getValue().size()){
                        progress += "；";
                    }
                    index++;
                }
            }
            info.setRemark(progress);
        });
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<PmBaseinfo, PmBaseinfoExcelDto> typeMap = modelMapper.typeMap(PmBaseinfo.class, PmBaseinfoExcelDto.class);
        List<PmBaseinfoExcelDto> collect = pmBaseinfoList.stream().map(typeMap::map).collect(Collectors.toList());
        return ResultData.success(collect);
    }

    @Override
    public ResultData<PmBaseinfoDto> findByIdForSchedule(String id) {
        return ResultData.success(service.findByIdForSchedule(id));
    }

    @Override
    public ResultData updateProjectInfoTask(Map<String, String> params) {
        LogUtil.bizLog("后台任务由【{}】执行完成！", ContextUtil.getSessionUser());
        service.updateProjectInfo();
        return ResultDataUtil.success("执行成功");
    }

    @Override
    public ResultData syncProjectInfo(String code) {
        PmBaseinfo pmBaseinfo = service.syncIppInfo(code);
        if (pmBaseinfo != null) {
            if (pmBaseinfo.getId() != null) {
                return ResultData.fail("该单号数据已经建档，请联系管理员。");
            }else if(pmBaseinfo.getRemark() != null){
                return ResultData.fail(pmBaseinfo.getRemark());
            }else {
                return ResultData.success(convertToDto(pmBaseinfo));
            }
        } else {
            return ResultData.fail("同步失败！！！该单号数据不存在。");
        }

    }

}
