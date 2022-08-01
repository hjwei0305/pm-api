package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.changhong.sei.core.api.FindByPageApi;
import com.donlim.pm.dto.TodoListDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

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
}