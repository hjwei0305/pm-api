package com.donlim.pm.service;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.flow.FlowStatus;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.edm.sdk.DocumentManager;
import com.donlim.pm.connector.EipConnector;
import com.donlim.pm.dao.PmEmployeeDao;
import com.donlim.pm.dao.TodoListDao;
import com.donlim.pm.dto.MailDto;
import com.donlim.pm.dto.PmBaseinfoDto;
import com.donlim.pm.dto.ProjectInfoDto;
import com.donlim.pm.dto.TodoListDto;
import com.donlim.pm.dto.excel.TodoListExcelDto;
import com.donlim.pm.entity.PmEmployee;
import com.donlim.pm.entity.TodoList;
import com.donlim.pm.flow.BaseFlowEntityService;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
//            mailDto.setUrl("https://sei.donlim.com/");
//            mailDto.setUrl("https://seiprod.donlim.com/api-gateway/sei-auth/sso/login?authType=xbDL&LoginType=SSO&ClientIP=127.0.0.1&tenant=DONLIM&userCode="+ todo.getOndutyCode());
            mailDto.setUrl("https://seiprod.donlim.com/api-gateway/sei-auth/sso/login?authType=xbAG&LoginType=SSO&ClientIP=127.0.0.1&tenant=DONLIM&code="+ todo.getOndutyCode());

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
        if (!StringUtils.isEmpty(dto.getId())) {
            Optional<TodoList> byId = dao.findById(dto.getId());
            if (byId.isPresent()) {
                documentManager.bindBusinessDocuments(dto.getId(), dto.getAttachmentIdList());
                TodoList todoList = byId.get();
                if(dto.getAttachmentIdList().size() > 0){
                    todoList.setIsUpload("1");
                }else {
                    todoList.setIsUpload("0");
                }
                save(todoList);
            }
        }
    }

    public List<TodoListExcelDto> export(Search search) {
        List<SearchFilter> filters = search.getFilters();
        filters.add(new SearchFilter("type","待办清单", SearchFilter.Operator.EQ));
        search.setFilters(filters);
        List<TodoList> allOrders = findByFilters(search);
        ModelMapper dtoModelMapper = new ModelMapper();
        TypeMap<TodoList, TodoListExcelDto> typeMap = dtoModelMapper.typeMap(TodoList.class, TodoListExcelDto.class);
        List<TodoListExcelDto> collect = allOrders.stream().map(typeMap::map).collect(Collectors.toList());
        collect.stream().forEach(c ->{
            if(Objects.nonNull(c.getFlowStatus())){
                if(c.getFlowStatus().equals("INIT")){
                    c.setFlowStatus("起草");
                }else if(c.getFlowStatus().equals("INPROCESS")){
                    c.setFlowStatus("流程中");
                }else{
                    c.setFlowStatus("已完成");
                }
            }else {
                c.setFlowStatus("起草");
            }
        });
        return collect;
    }

    @Transactional(rollbackFor = Exception.class)
    public void calcOverdueDay() {
        List<TodoList> list = dao.findAllByType("待办清单");
        for (TodoList todoList : list) {
            long day = 0L;
            if(todoList.getClosingStatus() != null && todoList.getClosingStatus().equals("合格")){
                long completeDay = todoList.getCompletionDate().toEpochDay();
                day = todoList.getConfirmationTime().toEpochDay();
                long overDay = (day - completeDay) > 0 ? (day - completeDay) : 0;
                todoList.setOveredDays(overDay);
            }else{
                long nowDay = LocalDate.now().toEpochDay();
                day = todoList.getCompletionDate().toEpochDay();
                long overDay = (nowDay - day) > 0 ? (nowDay - day) : 0;
                todoList.setOveredDays(overDay);
            }
        }
    }

    public ProjectInfoDto projFindByPage2Summary(Search search) {
        ProjectInfoDto projectInfoDto = new ProjectInfoDto();
        //未开始数
        int notStartedNum = 0;
        // 进行中
        int processingNum = 0;
        // 结案
        int finishNum = 0;
        // 逾期
        int overTimeNum = 0;
        // 即将逾期项目数
        int preOverTimeNum = 0;
        //提前天数
        long advanceDay = 0;
        //逾期天数
        long overTimeDay = 0;
        List<SearchFilter> filtersList = search.getFilters();
        SearchFilter searchFilter = new SearchFilter("type", "待办清单", SearchFilter.Operator.EQ);
        filtersList.add(searchFilter);
        search.setFilters(filtersList);
        List<TodoList> todoList = dao.findByFilters(search);
        for (TodoList todo : todoList) {
            if (todo.getFlowStatus() == null) {
                todo.setFlowStatus(FlowStatus.INIT);
            }
            if (todo.getFlowStatus().equals(FlowStatus.INIT)) {
                notStartedNum++;
            }
            if (todo.getFlowStatus().equals(FlowStatus.INPROCESS)) {
                processingNum++;
            }
            if (todo.getFlowStatus().equals(FlowStatus.COMPLETED)) {
                finishNum++;
            }
            if (todo.getFlowStatus().equals(FlowStatus.INPROCESS) && todo.getCompletionDate().toEpochDay() - LocalDate.now().toEpochDay() < 7) {
                preOverTimeNum++;
            }
            if (!todo.getFlowStatus().equals(FlowStatus.INIT) && todo.getOveredDays() != null && todo.getOveredDays() > 0) {
                overTimeNum++;
                overTimeDay += todo.getOveredDays();
            }
            if (todo.getFlowStatus().equals(FlowStatus.COMPLETED) && todo.getConfirmationTime() != null && todo.getConfirmationTime().isBefore(todo.getCompletionDate())) {
                advanceDay += todo.getCompletionDate().toEpochDay() - todo.getConfirmationTime().toEpochDay();
            }
        }
        projectInfoDto.setProcessingNum(processingNum);
        projectInfoDto.setNotStartedNum(notStartedNum);
        projectInfoDto.setSumNum(todoList.size());
        projectInfoDto.setFinishNum(finishNum);
        projectInfoDto.setPreOverTimeNum(preOverTimeNum);
        projectInfoDto.setOverTimeNum(overTimeNum);
        projectInfoDto.setOverTimeDay(overTimeDay);
        projectInfoDto.setAdvanceDay(advanceDay);
        return projectInfoDto;
    }
}
