package com.donlim.pm.controller;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.FileListApi;
import com.donlim.pm.api.ReportApi;
import com.donlim.pm.dao.TodoListDao;
import com.donlim.pm.dto.FileListDto;
import com.donlim.pm.dto.PersonnelProjectStatisticsDto;
import com.donlim.pm.em.ProjectTypes;
import com.donlim.pm.entity.FileList;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.entity.PmEmployee;
import com.donlim.pm.entity.TodoList;
import com.donlim.pm.service.FileListService;
import com.donlim.pm.service.PmBaseinfoService;
import com.donlim.pm.service.PmEmployeeService;
import com.donlim.pm.service.TodoListService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * (FileList)控制类
 *
 * @author sei
 * @since 2022-08-18 18:22:18
 */
@RestController
@Api(value = "ReportApi", tags = "服务")
@RequestMapping(path = FileListApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController  implements ReportApi {
    /**
     * 服务对象
     */
    @Autowired
    PmEmployeeService pmEmployeeService;
    @Autowired
    PmBaseinfoService pmBaseinfoService;
    @Autowired
    TodoListDao dao;
    @Override
    public ResultData<PageResult> findPersonnelProjectStatistics(Search search) {
        //取出所有未完成待办
        List<TodoList> toDoList =  dao.findAllByIsCompleted(false).stream().collect(Collectors.toList());
        List<PersonnelProjectStatisticsDto> list=new ArrayList<>();
        for (PmEmployee pmEmployee : pmEmployeeService.findAll()) {
            int projectFocusNum=0;
            int projectNum=0;
            String projectNames="";
            PersonnelProjectStatisticsDto dto=new PersonnelProjectStatisticsDto();
            dto.setEmployeeCode(pmEmployee.getEmployeeCode());
            dto.setEmployeeName(pmEmployee.getEmployeeName());
            for (PmBaseinfo pmBaseinfo : pmBaseinfoService.findAll()) {
               if( pmBaseinfo.getMember()!=null && pmBaseinfo.getMember().contains(dto.getEmployeeName())){
                   projectNum++;
                   if(pmBaseinfo.getProjectTypes()!=null && pmBaseinfo.getProjectTypes().equals(ProjectTypes.FOCUS)){
                       projectFocusNum++;
                   }
                   projectNames+="["+pmBaseinfo.getName()+"],";
               }


            }
            long count = toDoList.stream().filter(a -> a.getOndutyName().equals(dto.getEmployeeName())).count();
            dto.setToDoNum(count);
            dto.setProjectFocusNum(projectFocusNum);
            dto.setProjectTotalNum(projectNum);
            dto.setProjectNames(projectNames);
            list.add(dto);
        }
        PageResult pageResult=new PageResult();
        pageResult.setRows(list);
       return ResultData.success(pageResult);
    }
}
