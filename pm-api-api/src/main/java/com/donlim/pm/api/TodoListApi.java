package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.changhong.sei.core.dto.ResultData;
import com.donlim.pm.dto.TodoListDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
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
}
