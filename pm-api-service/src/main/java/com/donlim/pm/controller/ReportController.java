package com.donlim.pm.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.donlim.pm.api.ReportApi;
import com.donlim.pm.dao.TodoListDao;
import com.donlim.pm.dto.PersonnelProjectStatisticsDto;
import com.donlim.pm.em.ProjectTypes;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.entity.PmEmployee;
import com.donlim.pm.entity.TodoList;
import com.donlim.pm.service.PmBaseinfoService;
import com.donlim.pm.service.PmEmployeeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (FileList)控制类
 *
 * @author sei
 * @since 2022-08-18 18:22:18
 */
@RestController
@Api(value = "ReportApi", tags = "服务")
@RequestMapping(path = ReportApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
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
            long count = toDoList.stream().filter(a ->a.getOndutyName()!=null && a.getOndutyName().equals(dto.getEmployeeName())).count();
            dto.setToDoNum(count);
            dto.setProjectFocusNum(projectFocusNum);
            dto.setProjectTotalNum(projectNum);
            dto.setProjectNames(projectNames);
            list.add(dto);
        }
        PageResult pageResult=new PageResult();
        // 按总项目数排序
        List<PersonnelProjectStatisticsDto> sortList = list.stream()
                .sorted(Comparator.comparingInt(PersonnelProjectStatisticsDto::getProjectTotalNum).reversed())
                .collect(Collectors.toList());
        pageResult.setRows(sortList);
       return ResultData.success(pageResult);
    }
}
