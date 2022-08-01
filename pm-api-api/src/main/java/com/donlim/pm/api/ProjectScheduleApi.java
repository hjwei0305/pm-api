package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.donlim.pm.dto.ProjectScheduleDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 项目计划表(ProjectSchedule)API
 *
 * @author sei
 * @since 2022-07-30 08:15:41
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = ProjectScheduleApi.PATH)
public interface ProjectScheduleApi extends BaseEntityApi<ProjectScheduleDto> {
    String PATH = "projectSchedule";
}