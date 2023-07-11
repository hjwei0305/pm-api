package com.donlim.pm.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.donlim.pm.api.ReportApi;
import com.donlim.pm.dao.PmEmployeeDao;
import com.donlim.pm.dao.TodoListDao;
import com.donlim.pm.dto.PersonalBaseInfoReportDto;
import com.donlim.pm.dto.PersonnelProjectStatisticsDto;
import com.donlim.pm.em.EmpstatidEnum;
import com.donlim.pm.em.ProjectProgress;
import com.donlim.pm.em.ProjectTypes;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.entity.PmEmployee;
import com.donlim.pm.entity.TodoList;
import com.donlim.pm.service.PmBaseinfoService;
import com.donlim.pm.service.PmEmployeeService;
import com.donlim.pm.util.EnumUtils;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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
    PmEmployeeDao pmEmployeeDao;
    @Autowired
    TodoListDao dao;
    @Override
    public ResultData<PageResult> findPersonnelProjectStatistics(Search search) {
        //取出所有未完成待办
        List<TodoList> toDoList =  dao.findAllByIsCompleted(false).stream().collect(Collectors.toList());
        List<PersonnelProjectStatisticsDto> list=new ArrayList<>();

        List<SearchFilter> filters = new ArrayList<>();
        SearchFilter searchFilter = new SearchFilter("employeeCode","380287", SearchFilter.Operator.NE);
        SearchFilter searchFilter2 = new SearchFilter("empstatid", EmpstatidEnum.LEAVE, SearchFilter.Operator.NE);
        SearchFilter searchFilter3 = new SearchFilter("employeeCode","274735", SearchFilter.Operator.NE);
        SearchFilter searchFilter4 = new SearchFilter("employeeCode","043660", SearchFilter.Operator.NE);
        filters.add(searchFilter);
        filters.add(searchFilter2);
        filters.add(searchFilter3);
        filters.add(searchFilter4);
        search.setFilters(filters);
        List<PmEmployee> byFilters = pmEmployeeService.findByFilters(search);
        for (PmEmployee pmEmployee : byFilters) {
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

    @Override
    public ResultData<List<PersonalBaseInfoReportDto>> haveNoProjectList(Search search) {
        List<PersonalBaseInfoReportDto> list = new ArrayList<>();
        List<SearchFilter> filters = new ArrayList<>();
        SearchFilter searchFilter = new SearchFilter("employeeCode","380287", SearchFilter.Operator.NE);
        SearchFilter searchFilter2 = new SearchFilter("empstatid", EmpstatidEnum.LEAVE, SearchFilter.Operator.NE);
        SearchFilter searchFilter3 = new SearchFilter("employeeCode","274735", SearchFilter.Operator.NE);
        SearchFilter searchFilter4 = new SearchFilter("employeeCode","043660", SearchFilter.Operator.NE);
        filters.add(searchFilter);
        filters.add(searchFilter2);
        filters.add(searchFilter3);
        filters.add(searchFilter4);
        Search empSearch = new Search();
        empSearch.setFilters(filters);
        List<PmEmployee> byFilters = pmEmployeeService.findByFilters(empSearch);
        for (PmEmployee pmEmployee : byFilters) {
            int notStartedNum = 0;
            int pauseNum = 0;
            int finishNum = 0;
            int projectTotalNum = 0;
            PersonalBaseInfoReportDto dto = new PersonalBaseInfoReportDto();
            for (PmBaseinfo pmBaseinfo : pmBaseinfoService.findByFilters(search)) {
                String enumItemRemark = EnumUtils.getEnumItemRemark(ProjectProgress.class, ProjectProgress.FINISH);
                if( pmBaseinfo.getLeader()!=null && pmBaseinfo.getLeader().contains(pmEmployee.getEmployeeName())){
                    projectTotalNum++;
                    if(pmBaseinfo.getIsPause()){
                        pauseNum++;
                    }
                    if(pmBaseinfo.getStartDate().isAfter(LocalDate.now())){
                        notStartedNum++;
                    }
                    if(!StringUtils.isEmpty(pmBaseinfo.getCurrentPeriod()) && pmBaseinfo.getCurrentPeriod().equals(enumItemRemark)){
                        finishNum++;
                    }
                }
            }
            dto.setEmployeeCode(pmEmployee.getEmployeeCode());
            dto.setEmployeeName(pmEmployee.getEmployeeName());
            dto.setNotStartedNum(notStartedNum);
            dto.setPauseNum(pauseNum);
            dto.setFinishNum(finishNum);
            dto.setProjectTotalNum(projectTotalNum);
            dto.setProcessingNum(projectTotalNum-notStartedNum-pauseNum-finishNum);
            list.add(dto);
        }
        // 按进行中项目升序
        List<PersonalBaseInfoReportDto> sortList = list.stream()
                .sorted(Comparator.comparingInt(PersonalBaseInfoReportDto::getProjectTotalNum))
                .sorted(Comparator.comparingInt(PersonalBaseInfoReportDto::getProcessingNum))
                .collect(Collectors.toList());
        return ResultData.success(sortList);
    }
}
