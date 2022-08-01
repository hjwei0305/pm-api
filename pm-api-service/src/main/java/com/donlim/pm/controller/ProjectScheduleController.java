package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.ProjectScheduleApi;
import com.donlim.pm.dto.ProjectScheduleDto;
import com.donlim.pm.entity.ProjectSchedule;
import com.donlim.pm.service.ProjectScheduleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目计划表(ProjectSchedule)控制类
 *
 * @author sei
 * @since 2022-07-30 08:16:11
 */
@RestController
@Api(value = "ProjectScheduleApi", tags = "项目计划表服务")
@RequestMapping(path = ProjectScheduleApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectScheduleController extends BaseEntityController<ProjectSchedule, ProjectScheduleDto> implements ProjectScheduleApi {
    /**
     * 项目计划表服务对象
     */
    @Autowired
    private ProjectScheduleService service;

    @Override
    public BaseEntityService<ProjectSchedule> getService() {
        return service;
    }

}