package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.ProjectMembersApi;
import com.donlim.pm.dto.ProjectMembersDto;
import com.donlim.pm.entity.ProjectMembers;
import com.donlim.pm.service.ProjectMembersService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目组成员(ProjectMembers)控制类
 *
 * @author sei
 * @since 2022-07-30 08:14:05
 */
@RestController
@Api(value = "ProjectMembersApi", tags = "项目组成员服务")
@RequestMapping(path = ProjectMembersApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectMembersController extends BaseEntityController<ProjectMembers, ProjectMembersDto> implements ProjectMembersApi {
    /**
     * 项目组成员服务对象
     */
    @Autowired
    private ProjectMembersService service;

    @Override
    public BaseEntityService<ProjectMembers> getService() {
        return service;
    }

}