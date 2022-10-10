package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.pm.dto.TodoListDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * 代办事项(TodoList)API
 *
 * @author sei
 * @since 2022-07-30 08:16:53
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = TodoListApi.PATH)
public interface TodoListApi extends BaseEntityApi<TodoListDto>, FindByPageApi<TodoListDto> {
    String PATH = "todoList";

    /**
     * 定时更新项目状态
     * @param params
     * @return
     */
    @PostMapping(path = "sendTodoListTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "定时推送待办通知", notes = "定时推送待办通知")
    ResultData sendTodoListTask(@RequestBody Map<String, String> params);

    /**
     * 根据项目编码查找待办
     * @param
     * @return
     */
    @PostMapping(path = "projFindByPage", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查找项目待办", notes = "查找项目待办")
    ResultData<PageResult<TodoListDto>> projFindByPage(@RequestBody Search search);

    @PostMapping(path = "projFindByPage2", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查找项目待办清单", notes = "查找项目待办清单")
    ResultData<PageResult<TodoListDto>> projFindByPage2(@RequestBody Search search);

    /**
     * 导出
     *
     */
    @PostMapping(path = "exportDept" , consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "待办列表清单导出" , notes = "待办列表清单导出")
    void exportDept(@RequestBody Search search , HttpServletResponse response) throws IOException;
}
