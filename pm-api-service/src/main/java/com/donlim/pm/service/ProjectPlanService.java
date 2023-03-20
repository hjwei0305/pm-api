package com.donlim.pm.service;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.api.ColsAndSearch;
import com.donlim.pm.dao.ProjectPlanDao;
import com.donlim.pm.dto.ProjectPlanDto;
import com.donlim.pm.entity.ProjectPlan;
import com.donlim.pm.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 项目计划表(ProjectPlan)业务逻辑实现类
 *
 * @author sei
 * @since 2022-08-18 16:42:06
 */
@Service
public class ProjectPlanService extends BaseEntityService<ProjectPlan> {
    @Autowired
    private ProjectPlanDao dao;

    @Override
    protected BaseEntityDao<ProjectPlan> getDao() {
        return dao;
    }


    /**
     * 上传计划
     *
     * @param plansList
     * @throws IOException
     */
    @Transactional(rollbackFor = Exception.class)
    public void uploadMasterPlan(List<ProjectPlan> plansList) throws Exception {
        if(plansList.size() > 0){
            ProjectPlan projectPlan = plansList.get(0);
            List<String> idCollect = dao.getAllByProjectIdAndPlanType(projectPlan.getProjectId(), projectPlan.getPlanType())
                    .stream().filter(c->c.getCreatorAccount().equals(ContextUtil.getUserAccount()))
                    .map(ProjectPlan::getId)
                    .collect(Collectors.toList());
            // 清除项目相关计划
            delete(idCollect);
            int order = 1;
            for (ProjectPlan plan : plansList) {
                plan.setOrderNo(order);
                order++;
            }
            save(plansList);
        }else {
            throw new Exception("导入数据不能为空");
        }
    }

    /**
     * 计划序号排序
     * @param projectPlanDtos
     * @return
     */
    public List<ProjectPlanDto> sortNo(List<ProjectPlanDto> projectPlanDtos) {
        List<ProjectPlanDto> projectPlanDtosNew = new ArrayList<>();
        boolean flag = true;
        // 网页保存排序
        Map<String, List<ProjectPlanDto>> orderMap = projectPlanDtos.stream()
                .sorted(Comparator.comparing(ProjectPlanDto::getSchedureNo))
                .collect(Collectors.groupingBy(item -> item.getSchedureNo().substring(0, item.getSchedureNo().indexOf(".") != -1 ? item.getSchedureNo().indexOf(".") : item.getSchedureNo().length())));
        Set<String> strings = orderMap.keySet();
        List<Integer> noList = new ArrayList<>();
        boolean numFlag = true;
        int order = 1;
        // 判断序号纯数字或包含"."
        for (ProjectPlanDto projectPlanDto : projectPlanDtos) {
            if(projectPlanDto.getSchedureNo().contains(".")){
                numFlag = false;
                break;
            }
        }
        if(numFlag){
            for (String string : strings) {
                noList.add(Integer.valueOf(string));
            }
            noList = noList.stream().sorted().collect(Collectors.toList());
            for (Integer no : noList) {
                List<ProjectPlanDto> orderList = orderMap.get(no.toString());
                for (ProjectPlanDto dto : orderList) {
                    dto.setOrderNo(order);
                    order++;
                }
                projectPlanDtosNew.addAll(orderList);
            }
        }else {
            for (String key : strings) {
                List<ProjectPlanDto> orderList = orderMap.get(key);
                for (ProjectPlanDto dto : orderList) {
                    if(dto.getSchedureNo().indexOf(".") == -1){
                        dto.setOrderNo(0);
                    }else {
                        String sub = dto.getSchedureNo().substring(dto.getSchedureNo().indexOf(".")+1);
                        if(sub.indexOf(".") == -1){
                            dto.setOrderNo(Integer.valueOf(dto.getSchedureNo().substring(dto.getSchedureNo().indexOf(".")+1)));
                        }else {
                            flag = false;
                        }
                    }
                }
                if(flag){
                    orderList.sort(Comparator.comparingInt(ProjectPlanDto::getOrderNo));
                    for (ProjectPlanDto dto : orderList) {
                        dto.setOrderNo(order);
                        order++;
                    }
                    projectPlanDtosNew.addAll(orderList);
                    orderList.clear();
                }else {
                    flag = true;
                    Map<String, List<ProjectPlanDto>> secondMap = orderList.stream()
                            .collect(Collectors.groupingBy(item ->
                                    item.getSchedureNo().substring(item.getSchedureNo().indexOf(".") + 1 ,
                                            item.getSchedureNo().substring(item.getSchedureNo().indexOf(".") + 1).indexOf(".") != -1 ? item.getSchedureNo().substring(item.getSchedureNo().indexOf(".") + 1).indexOf(".") + item.getSchedureNo().indexOf(".") + 1 : item.getSchedureNo().length())));
                    Set<String> keySet = secondMap.keySet();
                    for (String s : keySet) {
                        orderList = secondMap.get(s);
                        for (ProjectPlanDto dto : orderList) {
                            if(dto.getSchedureNo().substring(dto.getSchedureNo().indexOf(".") + 1 ).indexOf(".") == -1){
                                dto.setOrderNo(0);
                            }else {
                                String sub = dto.getSchedureNo().substring(dto.getSchedureNo().indexOf(".")+1);
                                dto.setOrderNo(Integer.valueOf(sub.substring(sub.indexOf(".")+1)));
                            }
                        }
                        orderList.sort(Comparator.comparingInt(ProjectPlanDto::getOrderNo));
                        for (ProjectPlanDto dto : orderList) {
                            dto.setOrderNo(order);
                            order++;
                        }
                        projectPlanDtosNew.addAll(orderList);
                        orderList.clear();
                    }
                }
            }
        }
        return projectPlanDtosNew;
    }
    /**
     * 导出excel数据
     */
    @Transactional(rollbackFor = Exception.class)
    public List<List<Object>> findByFiltersForExport(ColsAndSearch search, Integer cols){
        List<ProjectPlan> byFilters = this.findByFilters(search);
        LocalDate now = LocalDate.now();
        LocalDate end = now.plusDays(cols);
        List<List<Object>> result = new ArrayList<>();
        for (ProjectPlan plan : byFilters) {
            List<Object> row = new ArrayList<>();
            row.add(plan.getWorkType());
            row.add(plan.getWorkTodoList());
            row.add(DateUtils.LocalDateToString(plan.getPlanStartDate()));
            row.add(DateUtils.LocalDateToString(plan.getPlanEndDate()));
            row.add(DateUtils.LocalDateToString(plan.getActualStartDate()));
            row.add(DateUtils.LocalDateToString(plan.getActualEndDate()));
            row.add(plan.getSchedureDays());
            row.add(plan.getSchedureStatus());
            row.add(plan.getWorkOnduty());
            row.add(plan.getWorkAssist());
            row.add(plan.getRemark());
//            List<ApsPurchasePlanDetail> purchasePlanDetails = plan.getPurchasePlanDetails();
//            for (ApsPurchasePlanDetail detail : purchasePlanDetails) {
//                LocalDate planDate = detail.getPlanDate();
//                if (planDate.isAfter(now) && planDate.isBefore(end)){
//                    row.add(detail.getPlanQty());
//                }
//            }
            result.add(row);
        }
        return result;
    }

    /**
     * 判断主要任务/关键步骤>=5字符
     * @param projectPlanDtos
     * @return
     */
    public ResultData limtWorkTodoList(List<ProjectPlanDto> projectPlanDtos) {
        String errSchedureNo = "";
        List<ProjectPlanDto> errList = projectPlanDtos.stream()
                .filter(p -> null == p.getWorkTodoList() || p.getWorkTodoList().length() < 5).collect(Collectors.toList());
        for (ProjectPlanDto err : errList) {
            errSchedureNo += err.getSchedureNo() + ",";
        }
        if(!"".equals(errSchedureNo)){
            return ResultData.fail("序号："+ errSchedureNo.substring(0,errSchedureNo.length() - 1) +"，主要任务/关键步骤不可低于5个字符！！！");
        }
        return null;
    }

}
