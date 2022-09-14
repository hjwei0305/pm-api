package com.donlim.pm.dao;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.donlim.pm.entity.TodoList;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 代办事项(TodoList)数据库访问类
 *
 * @author sei
 * @since 2022-07-30 08:17:40
 */
@Repository
public interface TodoListDao extends BaseEntityDao<TodoList> {
    /**
     * 取出未同步的待办和通知
     * @param sync
     * @return
     */
    List<TodoList>findAllByIsSyncEquals(String sync);

    List<TodoList>findAllByProjectCodeAndType(String code,String type);
}
