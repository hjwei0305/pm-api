package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.TodoListApi;
import com.donlim.pm.dto.TodoListDto;
import com.donlim.pm.entity.TodoList;
import com.donlim.pm.service.TodoListService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代办事项(TodoList)控制类
 *
 * @author sei
 * @since 2022-07-30 08:17:40
 */
@RestController
@Api(value = "TodoListApi", tags = "代办事项服务")
@RequestMapping(path = TodoListApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoListController extends BaseEntityController<TodoList, TodoListDto> implements TodoListApi {
    /**
     * 代办事项服务对象
     */
    @Autowired
    private TodoListService service;

    @Override
    public BaseEntityService<TodoList> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<TodoListDto>> findByPage(Search search) {
        return convertToDtoPageResult(service.findByPage(search));
    }
}