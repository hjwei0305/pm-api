package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.ProjectPlanApi;
import com.donlim.pm.dto.ProjectPlanDto;
import com.donlim.pm.entity.ProjectPlan;
import com.donlim.pm.service.ProjectPlanService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Override
    public BaseEntityService<ProjectPlan> getService() {
        return service;
    }

}
