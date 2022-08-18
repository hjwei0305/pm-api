package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.donlim.pm.dto.ProjectPlanDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * 项目计划表(ProjectPlan)API
 *
 * @author sei
 * @since 2022-08-18 16:41:31
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = ProjectPlanApi.PATH)
public interface ProjectPlanApi extends BaseEntityApi<ProjectPlanDto> {
    String PATH = "projectPlan";
}
