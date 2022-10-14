package com.donlim.pm.service;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.edm.sdk.DocumentManager;
import com.donlim.pm.connector.EipConnector;
import com.donlim.pm.dao.PmEmployeeDao;
import com.donlim.pm.dao.TodoListDao;
import com.donlim.pm.dto.MailDto;
import com.donlim.pm.dto.PmBaseinfoDto;
import com.donlim.pm.dto.TodoListDto;
import com.donlim.pm.entity.PmEmployee;
import com.donlim.pm.entity.TodoList;
import com.donlim.pm.flow.BaseFlowEntityService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
public class TodoListService extends BaseFlowEntityService<TodoList> {
    @Autowired
    private TodoListDao dao;
    @Autowired
    private PmEmployeeDao pmEmployeeDao;
    @Autowired
    private DocumentManager documentManager;

    @Override
    protected BaseEntityDao<TodoList> getDao() {
        return dao;
    }

    /**
     * 定时推送待办和通知
     */
    @Transactional(rollbackFor = Exception.class)
    public  void SendEipTask(){
        String userName = ContextUtil.getUserName();
        List<TodoList> list = dao.findAllByIsSyncEquals("0");
        for (TodoList todo:list){
//            if(todo.getOndutyCode() == null || !todo.getOndutyCode().equals("376951")){
//                continue;
//            }
            MailDto mailDto=new MailDto();
            mailDto.setMailType(todo.getType());
            mailDto.setMailID(todo.getId());
            mailDto.setAccount(todo.getOndutyCode());
            mailDto.setType("ADD");
            mailDto.setUrl("https://sei.donlim.com/");
//            mailDto.setUrl("https://sei.donlim.com/api-gateway/sei-auth/sso/login?authType=xbDL&LoginType=SSO&ClientIP=127.0.0.1&tenant=DONLIM&userCode="+userName);
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

    @Transactional(rollbackFor = Exception.class)
    public void bindFile(TodoListDto dto) {
        if (!StringUtils.isEmpty(dto.getId()) && !CollectionUtils.isEmpty(dto.getAttachmentIdList())) {
            Optional<TodoList> byId = dao.findById(dto.getId());
            if (byId.isPresent()) {
                documentManager.bindBusinessDocuments(dto.getId(), dto.getAttachmentIdList());
                save(byId.get());
            }
        }
    }
}
