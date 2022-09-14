package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.dao.TodoListDao;
import com.donlim.pm.entity.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 代办事项(TodoList)业务逻辑实现类
 *
 * @author sei
 * @since 2022-07-30 08:17:40
 */
@Service
public class TodoListService extends BaseEntityService<TodoList> {
    @Autowired
    private TodoListDao dao;

    @Override
    protected BaseEntityDao<TodoList> getDao() {
        return dao;
    }


    public  void SendEipTask(){

    }
}
