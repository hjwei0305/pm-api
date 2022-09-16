package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.dao.ProjectPlanDao;
import com.donlim.pm.entity.ProjectPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
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
            save(plansList);
        }else {
            throw new Exception("导入数据不能为空");
        }
    }

}
