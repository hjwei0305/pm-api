package com.donlim.pm.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.ProjectPlanApi;
import com.donlim.pm.dto.PmBaseinfoDto;
import com.donlim.pm.dto.ProjectPlanDto;
import com.donlim.pm.dto.upload.PlanDTO;
import com.donlim.pm.em.LogType;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.entity.ProjectPlan;
import com.donlim.pm.service.PmBaseinfoService;
import com.donlim.pm.service.PmLogService;
import com.donlim.pm.service.ProjectPlanService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public ResultData<String> saveBatch(List<ProjectPlanDto> projectPlanDtos) {
        if (projectPlanDtos.size() > 0) {
            String projectId = projectPlanDtos.get(0).getProjectId();
            List<String> projectList = new ArrayList<>();
            projectList.add(projectId);
            List<PmBaseinfo> pmBaseinfoList = pmBaseinfoService.findByIds(projectList);
            PmBaseinfo pmBaseinfo = pmBaseinfoList.get(0);
            PmBaseinfoDto pmBaseinfoDto = dtoModelMapper.map(pmBaseinfo, PmBaseinfoDto.class);
            String planType = projectPlanDtos.get(0).getPlanType();
            if (pmBaseinfo.getLeader().contains(ContextUtil.getUserName()) && planType.equals("0")) {
                pmLogService.save(LogType.ModifyMasterPlan,pmBaseinfoDto);
                //拿出序号1用来计算计划周期
                Optional<ProjectPlanDto> first = projectPlanDtos.stream().filter(a -> a.getSchedureNo().equals("1")).findFirst();
                if(first.isPresent()){
                    pmBaseinfo.setStartDate(first.get().getActualStartDate());
                    pmBaseinfo.setPlanFinishDate(first.get().getPlanEndDate());
                    pmBaseinfo.setFinalFinishDate(first.get().getActualEndDate());
                    pmBaseinfo.setProjectDays(first.get().getPlanEndDate().toEpochDay()-first.get().getPlanStartDate().toEpochDay());
                    pmBaseinfoService.save(pmBaseinfo);
                }else{
                    return ResultData.fail("序号1必填！！！");
                }
            } else if (pmBaseinfo.getDeveloper().contains(ContextUtil.getUserName()) && planType.equals("1")) {
                pmLogService.save(LogType.ModifyFrontPlan,pmBaseinfoDto);
            } else if (pmBaseinfo.getDeveloper().contains(ContextUtil.getUserName()) && planType.equals("2")) {
                pmLogService.save(LogType.ModifyCodePlan,pmBaseinfoDto);
            } else if (pmBaseinfo.getImplementer().contains(ContextUtil.getUserName()) && planType.equals("3")) {
                pmLogService.save(LogType.ModifyImplPlan,pmBaseinfoDto);
            }else{
                return ResultData.fail("当前用户["+ContextUtil.getUserName()+"],你没有权限操作，请联系项目负责人添加！！！");
            }
        }
        projectPlanDtos.stream().parallel().forEach(p -> save(p));
        return ResultData.success("保存成功");
    }

    @Override
    public ResultData<String> uploadMasterPlan(List<PlanDTO> list) throws IOException {
        service.uploadMasterPlan(list);
        return ResultData.success();
    }
}
