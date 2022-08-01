package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.donlim.pm.dto.ProjectMembersDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 项目组成员(ProjectMembers)API
 *
 * @author sei
 * @since 2022-07-30 08:13:39
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = ProjectMembersApi.PATH)
public interface ProjectMembersApi extends BaseEntityApi<ProjectMembersDto> {
    String PATH = "projectMembers";
}