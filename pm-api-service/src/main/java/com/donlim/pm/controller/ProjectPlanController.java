package com.donlim.pm.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.ColsAndSearch;
import com.donlim.pm.api.ProjectPlanApi;
import com.donlim.pm.dto.PmBaseinfoDto;
import com.donlim.pm.dto.ProjectPlanDto;
import com.donlim.pm.em.LogType;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.entity.ProjectPlan;
import com.donlim.pm.service.PmBaseinfoService;
import com.donlim.pm.service.PmLogService;
import com.donlim.pm.service.ProjectPlanService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 项目计划表(ProjectPlan)控制类
 *
 * @author sei
 * @since 2022-08-18 16:42:06
 */
@RestController
@Api(value = "ProjectPlanApi", tags = "项目计划表服务")
@RequestMapping(path = ProjectPlanApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectPlanController extends BaseEntityController<ProjectPlan, ProjectPlanDto> implements ProjectPlanApi {
    /**
     * 项目计划表服务对象
     */
    @Autowired
    private ProjectPlanService service;
    @Autowired
    private PmLogService pmLogService;
    @Autowired
    private PmBaseinfoService pmBaseinfoService;

    @Override
    public BaseEntityService<ProjectPlan> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<ProjectPlanDto>> findByPage(Search search) {
        PageResult pageResult=new PageResult();
        pageResult.setRows(service.findByFilters(search));
        return convertToDtoPageResult(pageResult);
    }

    @Override
    public ResultData<String> saveBatch(List<ProjectPlanDto> projectPlanDtos) {
        List<ProjectPlanDto> projectPlanDtosNew = new ArrayList<>();
        if (projectPlanDtos.size() > 0) {
            // 序号排序
            projectPlanDtosNew = service.sortNo(projectPlanDtos);

            String projectId = projectPlanDtos.get(0).getProjectId();
            List<String> projectList = new ArrayList<>();
            projectList.add(projectId);
            List<PmBaseinfo> pmBaseinfoList = pmBaseinfoService.findByIds(projectList);
            PmBaseinfo pmBaseinfo = pmBaseinfoList.get(0);
            PmBaseinfoDto pmBaseinfoDto = dtoModelMapper.map(pmBaseinfo, PmBaseinfoDto.class);
            String planType = projectPlanDtos.get(0).getPlanType();
            //为没有序号的初始化序号
            int no=1;
            for (ProjectPlanDto projectPlanDto : projectPlanDtos) {
                if(StringUtils.isEmpty(projectPlanDto.getSchedureNo())){
                    projectPlanDto.setSchedureNo(no+"");
                    no++;
                }
            }
            if (pmBaseinfo.getLeader().contains(ContextUtil.getUserName()) && planType.equals("0")) {
                pmLogService.save(LogType.ModifyMasterPlan,pmBaseinfoDto);
                //拿出序号1用来计算计划周期
                Optional<ProjectPlanDto> first = projectPlanDtos.stream().filter(a ->a.getSchedureNo().trim().equals("1")).findFirst();
                if(first.isPresent()){
//                    pmBaseinfo.setStartDate(first.get().getActualStartDate());
//                    pmBaseinfo.setPlanFinishDate(first.get().getPlanEndDate());
//                    pmBaseinfo.setFinalFinishDate(first.get().getActualEndDate());
                    if(first.get().getPlanStartDate()==null || first.get().getPlanEndDate()==null){
                        return ResultData.fail("序号["+first.get().getSchedureNo()+"]行的[计划开始时间]或[计划结束时间]未填写！！！");
                    }
//                    pmBaseinfo.setProjectDays(first.get().getPlanEndDate().toEpochDay()-first.get().getPlanStartDate().toEpochDay());
                    pmBaseinfoService.save(pmBaseinfo);
                }else{
                    return ResultData.fail("主计划序号[1]必填！！！");
                }
            } else if (pmBaseinfo.getDeveloper().contains(ContextUtil.getUserName()) && planType.equals("1")) {
                pmLogService.save(LogType.ModifyCodePlan,pmBaseinfoDto);
            } else if (pmBaseinfo.getDeveloper().contains(ContextUtil.getUserName()) && planType.equals("2")) {
                pmLogService.save(LogType.ModifyFrontPlan,pmBaseinfoDto);
            } else if (pmBaseinfo.getImplementer().contains(ContextUtil.getUserName()) && planType.equals("3")) {
                pmLogService.save(LogType.ModifyImplPlan,pmBaseinfoDto);
            }else{
                return ResultData.fail("当前用户["+ContextUtil.getUserName()+"],你没有权限操作，请联系项目负责人添加！！！");
            }
        }

        projectPlanDtosNew.stream().parallel().forEach(p -> save(p));
        return ResultData.success("保存成功");
    }

    @Override
    public ResultData<String> uploadMasterPlan(List<ProjectPlanDto> list) throws Exception {
        List<ProjectPlan> plansList = new ArrayList<>();
        for(ProjectPlanDto dto:list){
            ProjectPlan entity = entityModelMapper.map(dto,ProjectPlan.class);
            plansList.add(entity);
        }
        service.uploadMasterPlan(plansList);
        return ResultData.success();
    }

    @Override
    public void export(ColsAndSearch search, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("计划表", "utf-8").replace("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            List<List<Object>> exportData = service.findByFiltersForExport(search, search.getCols());
            List<List<String>> exportHeader = ColumnUtils.easyExcelHeaderWithDynamicColsGenerator(new String[]{
                    "任务类型", "主要任务/关键步骤", "计划开始日期", "计划完成日期", "实际开始日期", "实际完成日期", "实际天数", "开发状态", "执行人", "协助人", "备注"
            }, search.getCols());
            EasyExcel.write(response.getOutputStream()).head(exportHeader).autoCloseStream(false).sheet("sheet1").doWrite(exportData);
        }catch (Exception e){
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(JSON.toJSONString(ResultData.fail("导出Excel失败,"+e.getMessage())));
        }
    }
}
