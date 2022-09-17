package com.donlim.pm.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.donlim.pm.api.TodoListApi;
import com.donlim.pm.dto.TodoListDto;
import com.donlim.pm.entity.PmEmployee;
import com.donlim.pm.entity.TodoList;
import com.donlim.pm.service.PmEmployeeService;
import com.donlim.pm.service.TodoListService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    @Autowired
    private PmEmployeeService pmEmployeeService;

    @Override
    public BaseEntityService<TodoList> getService() {
        return service;
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
        if(ondutyEmployee.isPresent()){
            dto.setOndutyCode(ondutyEmployee.get().getEmployeeCode());
        }
        if(submitEmployee.isPresent()){
            dto.setSubmitCode(submitEmployee.get().getEmployeeCode());
        }
        super.save(dto);
        // 保存结案且已发送过待办 删除EIP待办
//        if(dto.getOndutyCode().equals("376951")){
//            if(dto.getIsFinished() && dto.getIsSync().equals("1")){
//                MailDto mailDto=new MailDto();
//                mailDto.setMailType(dto.getType());
//                mailDto.setMailID(dto.getId());
//                mailDto.setAccount(dto.getOndutyCode());
//                mailDto.setType("DELETE");
//                mailDto.setUrl("https://sei.donlim.com/");
////            mailDto.setUrl("https://sei.donlim.com/api-gateway/sei-auth/sso/login?authType=xbDL&LoginType=SSO&ClientIP=127.0.0.1&tenant=DONLIM&userCode="+userName);
//                String mailTitle="";
//                mailDto.setMailSubject(mailTitle);
//                mailDto.setMailBody(mailTitle);
//                EipConnector.sendNotice(mailDto);
//            }
//        }
        return ResultData.success();
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
    public ResultData sendTodoListTask(Map<String, String> params) {
            LogUtil.bizLog("后台任务由【{}】执行完成！", ContextUtil.getSessionUser());
            service.SendEipTask();
            return ResultDataUtil.success("执行成功");
        }

}
