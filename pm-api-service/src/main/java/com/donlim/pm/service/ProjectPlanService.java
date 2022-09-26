package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.dao.ProjectPlanDao;
import com.donlim.pm.dto.ProjectPlanDto;
import com.donlim.pm.entity.ProjectPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
                    .stream()
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
        int order = 1;
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
        return projectPlanDtosNew;
    }
}
