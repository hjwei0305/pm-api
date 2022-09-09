package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.donlim.pm.dto.PmBaseinfoDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

/**
 * 基础资料(PmBaseinfo)API
 *
 * @author sei
 * @since 2022-07-28 08:56:30
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = PmBaseinfoApi.PATH)
public interface PmBaseinfoApi extends BaseEntityApi<PmBaseinfoDto>, FindByPageApi<PmBaseinfoDto> {
    String PATH = "pmBaseinfo";


    @GetMapping(path = "findByIdForSchedule")
    @ApiOperation(value = "获取一个业务实体供进度图使用", notes = "通过Id获取一个业务实体")
    ResultData<PmBaseinfoDto> findByIdForSchedule(@RequestParam("id") String id);

    /**
     * 定时更新项目状态
     * @param params
     * @return
     */
    @PostMapping(path = "updateProjectInfoTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "定时更新项目信息", notes = "定时更新项目信息")
    ResultData updateProjectInfoTask(@RequestBody Map<String, String> params);


    /**
     * 定时更新项目状态
     * @param code
     * @return
     */
    @PostMapping(path = "syncProjectInfo")
    @ApiOperation(value = "根据提案单号获取项目信息", notes = "根据提案单号获取项目信息")
    ResultData syncProjectInfo(@RequestParam("code") String code);

    /**
     * 保存项目信息
     * @param dto
     * @return
     * @throws IllegalAccessException
     */
    @PostMapping(path = "saveBaseInfo")
    @ApiOperation(value = "保存项目信息", notes = "保存项目信息")
    ResultData<PmBaseinfoDto> saveBaseInfo(@RequestBody PmBaseinfoDto dto) throws IllegalAccessException;

    /**
     * 绑定附件列表
     * @param dto
     * @return
     * @throws IllegalAccessException
     */
    @PostMapping(path = "saveAttachList")
    @ApiOperation(value = "绑定附件列表", notes = "绑定附件列表")
    ResultData<PmBaseinfoDto> saveAttachList(@RequestBody PmBaseinfoDto dto) throws IllegalAccessException;

    /**
     * 获取各阶段项目数量
     * @param
     * @return
     * @throws IllegalAccessException
     */
    @PostMapping(path = "getProjectInfo")
    @ApiOperation(value = "获取各阶段项目数量", notes = "获取各阶段项目数量")
    ResultData getProjectInfo() throws IllegalAccessException;

}
