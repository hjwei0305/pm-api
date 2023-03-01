package com.donlim.pm.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.flow.Executor;
import com.changhong.sei.core.dto.flow.FlowInvokeParams;
import com.changhong.sei.core.dto.flow.FlowStatus;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.donlim.pm.api.TodoListApi;
import com.donlim.pm.connector.EipConnector;
import com.donlim.pm.dto.MailDto;
import com.donlim.pm.dto.TodoListDto;
import com.donlim.pm.dto.excel.TodoListExcelDto;
import com.donlim.pm.entity.PmEmployee;
import com.donlim.pm.entity.TodoList;
import com.donlim.pm.flow.BaseFlowController;
import com.donlim.pm.flow.BaseFlowEntityService;
import com.donlim.pm.service.PmEmployeeService;
import com.donlim.pm.service.TodoListService;
import com.donlim.pm.webservice.eipcenter.SvcHdrTypes;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 代办事项(TodoList)控制类
 *
 * @author sei
 * @since 2022-07-30 08:17:40
 */
@RestController
@Api(value = "TodoListApi", tags = "代办事项服务")
@RequestMapping(path = TodoListApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoListController extends BaseFlowController<TodoList, TodoListDto> implements TodoListApi {
    /**
     * 代办事项服务对象
     */
    @Autowired
    private TodoListService service;
    @Autowired
    private PmEmployeeService pmEmployeeService;

    @Override
    public BaseFlowEntityService<TodoList> getService() {
        return service;
    }

    @Override
    protected void addProperties(Map<String, String> map) {
        map.put("type", "待办类型");
        map.put("idpath", "是否运维部门");
    }

    @Override
    public Map<String, Object> getPropertyValue(TodoList entity) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("type", entity.getType());
        map.put("idpath", "0");
        // 返回责任人idpath是否运维部门
        List<PmEmployee> empList = pmEmployeeService.findAll();
        String ondutyCode = entity.getOndutyCode();
        List<PmEmployee> pmEmployees = empList.stream().filter(emp -> emp.getEmployeeCode().equals(ondutyCode)).collect(Collectors.toList());
        PmEmployee pmEmployee = new PmEmployee();
        if(pmEmployees.size() > 0){
            pmEmployee = pmEmployees.get(0);
        }
        if (pmEmployee.getIdpath().startsWith("1,265,266,12318,") || pmEmployee.getIdpath().startsWith("1,265,18038,18042,")){
            map.put("idpath", "1");
        }
        return map;
    }

    @Override
    protected void addInitValue(Map<String, Object> map) {
        map.put("type", "待办清单");
        map.put("idpath", "0");
    }

    @Override
    protected String getWorkCaption(TodoList todoList) {
        return "项目管理系统待办清单审批：" +todoList.getTodoList();
    }

    @Override
    protected String getFlowName(TodoList todoList) {
        return "项目管理系统待办清单审批流程：" +todoList.getTodoList();
    }

    @Override
    protected String getBusinessCode(TodoList todoList) {
        return todoList.getTodoList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultData<TodoListDto> saveUserId(TodoListDto dto){
        TodoList list = service.findOne(dto.getId());
        list.setConfirmedby1(dto.getConfirmedby1());
        TodoListDto dto1 = dtoModelMapper.map(list,TodoListDto.class);
        ResultData<TodoListDto> result = super.save(dto1);
        return result;
    }

    @Override
    public ResultData<TodoListDto> bindFile(TodoListDto dto) {
        service.bindFile(dto);
        return ResultData.success();
    }

    @Override
    public ResultData<String> deleteEipTodo(TodoListDto dto) {
        SvcHdrTypes flag = EipConnector.deleteEipMall(dto.getProjectCode());
        return ResultData.success(flag.getESBCODE()+","+flag.getRCODE()+","+flag.getRDESC());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<TodoListDto> save(TodoListDto dto){
        // 保存工号
        List<PmEmployee> employeeList = pmEmployeeService.findAll();
        Optional<PmEmployee> ondutyEmployee = employeeList.stream()
                .filter(pmEmployee -> pmEmployee.getEmployeeName().equals(dto.getOndutyName()))
                .findFirst();
        Optional<PmEmployee> submitEmployee = employeeList.stream()
                .filter(pmEmployee -> pmEmployee.getEmployeeName().equals(dto.getSubmitName()))
                .findFirst();
        Optional<PmEmployee> advisor = employeeList.stream()
                .filter(pmEmployee -> pmEmployee.getEmployeeName().equals(dto.getAdvisor()))
                .findFirst();
        Optional<PmEmployee> assist = employeeList.stream()
                .filter(pmEmployee -> pmEmployee.getEmployeeName().equals(dto.getAssistName()))
                .findFirst();
        if(ondutyEmployee.isPresent()){
            dto.setOndutyCode(ondutyEmployee.get().getEmployeeCode());
        }
        if(submitEmployee.isPresent()){
            dto.setSubmitCode(submitEmployee.get().getEmployeeCode());
        }
        if(advisor.isPresent()){
            dto.setAdvisorCode(advisor.get().getEmployeeCode());
        }
        if(assist.isPresent()){
            dto.setAssistCode(assist.get().getEmployeeCode());
        }
        //检查type=待办清单,防止重复提交流程
        if(dto.getType().equals("待办清单") && dto.getId() != null){
            TodoList checkFlowStatus = service.findOne(dto.getId());
            if(checkFlowStatus.getFlowStatus() != null){
                if(dto.getFlowStatus() == null || (checkFlowStatus.getFlowStatus().equals(FlowStatus.INPROCESS) && dto.getFlowStatus().equals(FlowStatus.INIT))){
                    return ResultData.fail("单据已提交，请勿重复！");
                }
            }
        }
        ResultData<TodoListDto> saveResultData = super.save(dto);
        // 保存结案且已发送过待办 删除EIP待办
//        if(dto.getOndutyCode().equals("376951")){
            if(dto.getId() != null && dto.getIsFinished() && dto.getIsSync().equals("1")){
                MailDto mailDto=new MailDto();
                mailDto.setMailType(dto.getType());
                mailDto.setMailID(dto.getId());
                mailDto.setAccount(dto.getOndutyCode());
                mailDto.setType("DELETE");
                mailDto.setUrl("https://sei.donlim.com/");
//            mailDto.setUrl("https://sei.donlim.com/api-gateway/sei-auth/sso/login?authType=xbDL&LoginType=SSO&ClientIP=127.0.0.1&tenant=DONLIM&userCode="+userName);
                String mailTitle="";
                mailDto.setMailSubject(mailTitle);
                mailDto.setMailBody(mailTitle);
                EipConnector.sendNotice(mailDto);
            }
//        }
        return saveResultData;
    }

    @Override
    public ResultData<PageResult<TodoListDto>> findByPage(Search search) {
        // 主页查找个人通知、待办
        String userName = ContextUtil.getUserName();
        List<SearchFilter> filtersList = search.getFilters();
        SearchFilter searchFilter = new SearchFilter("ondutyName",userName, SearchFilter.Operator.EQ);
        filtersList.add(searchFilter);
        search.setFilters(filtersList);
        if(search.getFilters() == null){
            return ResultData.success();
        }
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public ResultData<PageResult<TodoListDto>> projFindByPage(Search search) {
        List<SearchFilter> filtersList = search.getFilters();
        SearchFilter searchFilter = new SearchFilter("type","待办", SearchFilter.Operator.EQ);
        filtersList.add(searchFilter);
        search.setFilters(filtersList);
        if(search.getFilters() == null){
            return ResultData.success();
        }
        return convertToDtoPageResult(service.findByPage(search));
    }
    @Override
    public ResultData<PageResult<TodoListDto>> projFindByPage2(Search search) {
        List<SearchFilter> filtersList = search.getFilters();
        SearchFilter searchFilter = new SearchFilter("type","待办清单", SearchFilter.Operator.EQ);
        filtersList.add(searchFilter);
        search.setFilters(filtersList);
        if(search.getFilters() == null){
            return ResultData.success();
        }
        return convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public ResultData<List<TodoListExcelDto>> export(Search search, HttpServletResponse response) throws IOException {
        List<TodoListExcelDto> exportList = service.export(search);
        return ResultData.success(exportList);
    }

    @Override
    public ResultData<List<Executor>> appointConfirm(FlowInvokeParams invokeParams) {
        TodoList todoList = service.findOne(invokeParams.getId());
        List<Executor> executors = new ArrayList<>();
        Executor executor = new Executor();
        executor.setId(todoList.getConfirmedby1());
        executor.setCode(todoList.getOndutyCode());
        executor.setName(todoList.getOndutyName());
        executors.add(executor);
        return ResultData.success(executors);
    }

    @Override
    public ResultData<List<Executor>> appointAccept(FlowInvokeParams invokeParams) {
        List<Executor> executors = new ArrayList<>();
        Executor executor = new Executor();
        TodoList todoList = service.findOne(invokeParams.getId());
        List<PmEmployee> empList = pmEmployeeService.findAll();
        String ondutyCode = todoList.getOndutyCode();
        List<PmEmployee> pmEmployees = empList.stream().filter(emp -> emp.getEmployeeCode().equals(ondutyCode)).collect(Collectors.toList());
        PmEmployee pmEmployee = new PmEmployee();
        if(pmEmployees.size() > 0){
            pmEmployee = pmEmployees.get(0);
        }
        if(pmEmployee.getIdpath().startsWith("1,265,266,12318,") || pmEmployee.getIdpath().startsWith("1,265,18038,18042,")){
            // 运维
            executor.setId("56760230-237A-11ED-B087-34C93D8809B5");
            executor.setCode("299100");
            executor.setName("吴碧华");
            executors.add(executor);
        }else if(pmEmployee.getIdpath().startsWith("1,265,266,14090,") || pmEmployee.getIdpath().startsWith("1,265,266,14091,")
            || pmEmployee.getIdpath().startsWith("1,265,266,12016,")
                || pmEmployee.getIdpath().startsWith("1,265,18038,18039,") || pmEmployee.getIdpath().startsWith("1,265,18038,18040,")
                || pmEmployee.getIdpath().startsWith("1,265,18038,18041,") || pmEmployee.getIdpath().startsWith("1,265,18038,18043,")
        ){
            // 开发、项目
            executor.setId("56F2FA70-237A-11ED-B087-34C93D8809B5");
            executor.setCode("237267");
            executor.setName("卢彩霞");
            executors.add(executor);
//            Executor executor2 = new Executor();
//            executor2.setId("1DE14F77-2442-11ED-B32F-34C93D8809B5");
//            executor2.setCode("380825");
//            executor2.setName("吴婷婷");
//            executors.add(executor2);
        }
        return ResultData.success(executors);
    }



    @Override
    public ResultData toConfirm(FlowInvokeParams invokeParams) {
//        if(invokeParams.getAgree() == true){
        // 普通任务只能执行下一步触发，不需要agree
            TodoList list = service.findOne(invokeParams.getId());
            list.setConfirm1Status(true);
            TodoListDto dto = dtoModelMapper.map(list,TodoListDto.class);
            super.save(dto);
//        }
        return ResultData.success();
    }

    @Override
    public ResultData returnConfirm(FlowInvokeParams invokeParams) {
        TodoList list = service.findOne(invokeParams.getId());
        list.setConfirm1Status(false);
        TodoListDto dto = dtoModelMapper.map(list,TodoListDto.class);
        super.save(dto);
        return ResultData.success();
    }

    @Override
    public ResultData sendTodoListTask(Map<String, String> params) {
        LogUtil.bizLog("后台任务由【{}】执行完成！", ContextUtil.getSessionUser());
        service.SendEipTask();
        return ResultDataUtil.success("执行成功");
    }

    @Override
    public ResultData calcOverdueDay(Map<String, String> params) {
        LogUtil.bizLog("后台任务由【{}】执行完成！", ContextUtil.getSessionUser());
        service.calcOverdueDay();
        return ResultDataUtil.success("执行成功");
    }

}
