package com.donlim.pm.service;


import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.donlim.pm.connector.HRMSConnector;
import com.donlim.pm.dao.PmEmployeeDao;
import com.donlim.pm.dto.EmployeeDTO;
import com.donlim.pm.entity.PmEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * (PmEmployee)业务逻辑实现类
 *
 * @author sei
 * @since 2022-08-10 16:44:57
 */
@Service
public class PmEmployeeService extends BaseEntityService<PmEmployee> {
    @Autowired
    private PmEmployeeDao dao;

    @Override
    protected BaseEntityDao<PmEmployee> getDao() {
        return dao;
    }

    /**
     * 同步/更新人员信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void synEmp(){
        List<EmployeeDTO.DataDTO> empList = HRMSConnector.getEmp();
        List<PmEmployee> allList = findAll();
        ArrayList<PmEmployee> saveList = new ArrayList<>();
        for (EmployeeDTO.DataDTO dataDTO : empList) {
            // 对比信息
            List<PmEmployee> pmEmployees = allList.stream()
                    .filter(emp -> emp.getEmployeeCode().equals(dataDTO.getEmployeeCode()))
                    .collect(Collectors.toList());
            if(pmEmployees.size()>0){
                // 更新
                PmEmployee pmEmployee = pmEmployees.get(0);
                pmEmployee.setEmployeeName(dataDTO.getEmployeeName());
                pmEmployee.setOrgid(dataDTO.getOrgid());
                pmEmployee.setOrgcode(dataDTO.getOrgcode());
                pmEmployee.setOrgname(dataDTO.getOrgname());
                pmEmployee.setSpName(dataDTO.getSpName());
                pmEmployee.setTelephone(dataDTO.getTelphone());
                pmEmployee.setEmpstatid(dataDTO.getEmpstatid());
                pmEmployee.setIdpath(dataDTO.getIdpath());
                if(null != dataDTO.getLjdate() && !"0000-00-00 00:00:00".equals(dataDTO.getLjdate())){
                    pmEmployee.setLjdate(LocalDate.parse(dataDTO.getLjdate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }
                saveList.add(pmEmployee);
            }else {
                // 新增
                PmEmployee pmEmployee = new PmEmployee();
                pmEmployee.setEmployeeCode(dataDTO.getEmployeeCode());
                pmEmployee.setEmployeeName(dataDTO.getEmployeeName());
                pmEmployee.setOrgid(dataDTO.getOrgid());
                pmEmployee.setOrgcode(dataDTO.getOrgcode());
                pmEmployee.setOrgname(dataDTO.getOrgname());
                pmEmployee.setSpName(dataDTO.getSpName());
                pmEmployee.setTelephone(dataDTO.getTelphone());
                pmEmployee.setEmpstatid(dataDTO.getEmpstatid());
                pmEmployee.setIdpath(dataDTO.getIdpath());
                if(null != dataDTO.getLjdate() && !"0000-00-00 00:00:00".equals(dataDTO.getLjdate())){
                    pmEmployee.setLjdate(LocalDate.parse(dataDTO.getLjdate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }
                saveList.add(pmEmployee);
            }
        }
        save(saveList);
    }
}
