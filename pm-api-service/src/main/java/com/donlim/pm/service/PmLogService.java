package com.donlim.pm.service;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.dao.PmLogDao;
import com.donlim.pm.dto.PmBaseinfoDto;
import com.donlim.pm.dto.PmVisitStatisticsDTO;
import com.donlim.pm.em.EmpstatidEnum;
import com.donlim.pm.em.LogType;
import com.donlim.pm.em.OperationType;
import com.donlim.pm.entity.PmEmployee;
import com.donlim.pm.entity.PmLog;
import com.donlim.pm.entity.PmOrganize;
import com.donlim.pm.util.DateUtils;
import com.donlim.pm.util.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * (PmLog)业务逻辑实现类
 *
 * @author sei
 * @since 2022-09-06 14:23:47
 */
@Service
public class PmLogService extends BaseEntityService<PmLog> {
    @Autowired
    private PmLogDao dao;
    @Autowired
    private PmOrganizeService pmOrganizeService;
    @Autowired
    private PmEmployeeService pmEmployeeService;

    @Override
    protected BaseEntityDao<PmLog> getDao() {
        return dao;
    }

    /**
     * 保存日志
     * @param logType  日志类型
     */
    public void save(LogType logType, PmBaseinfoDto dto){
        PmLog pmLog=new PmLog();
        pmLog.setEmployeeCode(ContextUtil.getUserAccount());
        pmLog.setEmployeeName(ContextUtil.getUserName());
        pmLog.setContent(EnumUtils.getEnumItemRemark(LogType.class,logType));
        pmLog.setProjectId(dto.getId());
        pmLog.setProjectTypes(dto.getProjectTypes());
        dao.save(pmLog);
    }

    public void save(OperationType operationType){
        PmLog pmLog=new PmLog();
        pmLog.setEmployeeCode(ContextUtil.getUserAccount());
        pmLog.setEmployeeName(ContextUtil.getUserName());
        pmLog.setContent(EnumUtils.getEnumItemRemark(OperationType.class,operationType));
        dao.save(pmLog);
    }

    /**
     * 科室 / 个人操作量统计
     * @param search 查询参数
     * @return List<PmVisitStatisticsDTO>
     */
    public List<PmVisitStatisticsDTO> findVisitStatistics(Search search) {
        ArrayList<PmVisitStatisticsDTO> resultList = new ArrayList<>();
        List<SearchFilter> filters = search.getFilters();
        Map<String, List<PmLog>> logMap = new HashMap<>();
        if(CollectionUtils.isEmpty(filters)){
            logMap = findAll().stream().collect(Collectors.groupingBy(PmLog::getEmployeeName));
        }
        // 人员根据orgcode分组
        SearchFilter searchFilter1 = new SearchFilter("employeeCode","380287", SearchFilter.Operator.NE);
        SearchFilter searchFilter2 = new SearchFilter("empstatid", EmpstatidEnum.LEAVE, SearchFilter.Operator.NE);
        List<SearchFilter> empFilters = new ArrayList<>();
        empFilters.add(searchFilter1);
        empFilters.add(searchFilter2);
        Map<String, List<PmEmployee>> empMap = pmEmployeeService.findByFilters(new Search().setFilters(empFilters))
                .stream().collect(Collectors.groupingBy(PmEmployee::getOrgcode));
        // 处理科室
        List<PmOrganize> orgList = pmOrganizeService.getChildrenNodesByName("数字化管理中心").stream()
                .filter(a -> !a.getFrozen() &&  ("系统运维管理部".equals(a.getName()) || a.getNodeLevel() == 3))
                .collect(Collectors.toList());
        for (SearchFilter filter : filters) {
            // 处理月份
            if("month".equals(filter.getFieldName())){
                String endStr = LocalDate.parse(filter.getValue().toString() + "-01").plusMonths(1).format(DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_FORMAT));
                Date endDate = DateUtils.parseDate(endStr, DateUtils.DEFAULT_DATE_FORMAT);
                Date startDate = DateUtils.parseDate(filter.getValue().toString() + "-01", DateUtils.DEFAULT_DATE_FORMAT);
                logMap = findAll().stream().filter(log -> log.getCreatedDate().after(startDate) && log.getCreatedDate().before(endDate))
                        .collect(Collectors.groupingBy(PmLog::getEmployeeName));
            }
        }
        for (PmOrganize pmOrganize : orgList) {
            PmVisitStatisticsDTO pmVisitStatisticsDTO = new PmVisitStatisticsDTO();
            List<PmEmployee> pmEmployees = empMap.get(pmOrganize.getCode());
            String[] split = pmOrganize.getManager().split(",");
            ArrayList<PmVisitStatisticsDTO.VistiEmployee> visitEmpList = new ArrayList<>();
            int count = 0;
            for (PmEmployee pmEmployee : pmEmployees) {
                count = dealData(logMap, visitEmpList, count, pmEmployee.getEmployeeName());
            }
            for (String manage : split) {
                if(!pmEmployees.stream().map(PmEmployee::getEmployeeName).collect(Collectors.toList()).contains(manage)){
                    count = dealData(logMap, visitEmpList, count, manage);
                }
            }
            visitEmpList.sort(Comparator.comparing(PmVisitStatisticsDTO.VistiEmployee::getCount).reversed());
            pmVisitStatisticsDTO.setName(pmOrganize.getName());
            pmVisitStatisticsDTO.setOrgid(pmOrganize.getId());
            pmVisitStatisticsDTO.setChildren(visitEmpList);
            pmVisitStatisticsDTO.setCount(count);
            resultList.add(pmVisitStatisticsDTO);
        }
        resultList.sort(Comparator.comparing(PmVisitStatisticsDTO::getCount).reversed());
        return resultList;
    }

    /**
     * 处理数据
     * @param logMap 日志map
     * @param visitEmpList 人员list
     * @param count 总数
     * @param employeeName 人员名称
     */
    private int dealData(Map<String, List<PmLog>> logMap, ArrayList<PmVisitStatisticsDTO.VistiEmployee> visitEmpList, int count, String employeeName) {
        PmVisitStatisticsDTO.VistiEmployee vistiEmployee = new PmVisitStatisticsDTO.VistiEmployee();
        vistiEmployee.setEmployeeName(employeeName);
        if(logMap.containsKey(employeeName)){
            vistiEmployee.setCount(logMap.get(employeeName).size());
        }else {
            vistiEmployee.setCount(0);
        }
        count += vistiEmployee.getCount();
        visitEmpList.add(vistiEmployee);
        return count;
    }
}
