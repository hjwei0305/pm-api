package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.connector.EipConnector;
import com.donlim.pm.dao.PmEmployeeDao;
import com.donlim.pm.dao.TodoListDao;
import com.donlim.pm.dto.MailDto;
import com.donlim.pm.dto.PmBaseinfoDto;
import com.donlim.pm.entity.PmEmployee;
import com.donlim.pm.entity.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    @Autowired
    private PmEmployeeDao pmEmployeeDao;

    @Override
    protected BaseEntityDao<TodoList> getDao() {
        return dao;
    }

    /**
     * 定时推送待办和通知
     */
    @Transactional(rollbackFor = Exception.class)
    public  void SendEipTask(){
        List<TodoList> list = dao.findAllByIsSyncEquals("0");
        for (TodoList todo:list){
            if(!todo.getOndutyCode().equals("377614")){
                continue;
            }
            MailDto mailDto=new MailDto();
            mailDto.setMailType(todo.getType());
            mailDto.setMailID(todo.getId());
            mailDto.setAccount(todo.getOndutyCode());
            mailDto.setUrl("https://sei.donlim.com/");
            String mailTitle="";

            if(todo.getType().equals("待办")){
                 mailTitle="DEAR"+todo.getOndutyName()+"您在[软件项目管理系统]有一个待办事项，请及时处理";
            }else if(todo.getType().equals("通知")){
                mailTitle="DEAR"+todo.getOndutyName()+"您在[软件项目管理系统]有一个通知，请及时查看";
                mailDto.setMailBody(todo.getTodoList());
            }
            mailDto.setMailSubject(mailTitle);
            if(EipConnector.sendNotice(mailDto)){
                todo.setIsSync("1");
            }
        }
        save(list);

    }
    @Transactional(rollbackFor = Exception.class)
    public void addNotice(PmBaseinfoDto dto){
        List<TodoList> noticeList = dao.findAllByProjectCodeAndType(dto.getCode(), "通知");
        List<String> memberNoticeList = noticeList.stream().map(a -> a.getOndutyName()).collect(Collectors.toList());
        List<TodoList>saveList=new ArrayList<>();
        for (String member : dto.getMember().split(",")) {
            if(!memberNoticeList.contains(member)){
                TodoList todoList=new TodoList();
                Optional<PmEmployee> allByEmployeeName = pmEmployeeDao.findAllByEmployeeName(member);
                if(allByEmployeeName.isPresent()){
                    todoList.setOndutyCode(allByEmployeeName.get().getEmployeeCode());
                    todoList.setProjectCode(dto.getCode());
                    todoList.setProjectName(dto.getName());
                    todoList.setOndutyName(member);
                    todoList.setType("通知");
                    todoList.setTodoList("您已经加入["+dto.getName()+"]项目组！");
                    saveList.add(todoList);
                }
            }
        }
        save(saveList);
    }
}
