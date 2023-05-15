package com.donlim.pm.service;


import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.util.EnumUtils;
import com.donlim.pm.connector.HRMSConnector;
import com.donlim.pm.dao.PmEmployeeDao;
import com.donlim.pm.dto.EmployeeDTO;
import com.donlim.pm.em.EmpstatidEnum;
import com.donlim.pm.entity.PmEmployee;
import com.donlim.pm.entity.PmOrganize;
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
    @Autowired
    private PmOrganizeService pmOrganizeService;

    @Override
    protected BaseEntityDao<PmEmployee> getDao() {
        return dao;
    }

    /**
     * 同步/更新人员信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void synEmp(){
        // 组织要有租户代码
        List<PmOrganize> allOrgList = pmOrganizeService.findAll();
        List<EmployeeDTO.DataDTO> empList = HRMSConnector.getEmp();
        List<PmEmployee> allList = findAll();
        ArrayList<PmEmployee> saveList = new ArrayList<>();
        for (EmployeeDTO.DataDTO dataDTO : empList) {
            // 对比信息
            List<PmEmployee> pmEmployees = allList.stream()
                    .filter(emp -> emp.getEmployeeCode().equals(dataDTO.getEmployeeCode()))
                    .collect(Collectors.toList());
            List<PmOrganize> orgList = allOrgList.stream()
                    .filter(org -> org.getCode().equals(dataDTO.getOrgcode()))
                    .collect(Collectors.toList());
            if(pmEmployees.size()>0){
                // 更新
                PmEmployee pmEmployee = pmEmployees.get(0);
                pmEmployee.setGroupid(orgList.size() > 0 ? orgList.get(0).getId() : null);
                pmEmployee.setEmployeeName(dataDTO.getEmployeeName());
                pmEmployee.setOrgid(dataDTO.getOrgid());
                pmEmployee.setOrgcode(dataDTO.getOrgcode());
                pmEmployee.setOrgname(dataDTO.getOrgname());
                pmEmployee.setSpName(dataDTO.getSpName());
                pmEmployee.setTelephone(dataDTO.getTelphone());
                pmEmployee.setEmpstatid(EnumUtils.getEnum(EmpstatidEnum.class,Integer.valueOf(dataDTO.getEmpstatid())));
                pmEmployee.setIdpath(dataDTO.getIdpath());
                if(null != dataDTO.getLjdate() && !"0000-00-00 00:00:00".equals(dataDTO.getLjdate())){
                    pmEmployee.setLjdate(LocalDate.parse(dataDTO.getLjdate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }
                saveList.add(pmEmployee);
            }else {
                // 新增
                PmEmployee pmEmployee = new PmEmployee();
                pmEmployee.setGroupid(orgList.size() > 0 ? orgList.get(0).getId() : null);
                pmEmployee.setEmployeeCode(dataDTO.getEmployeeCode());
                pmEmployee.setEmployeeName(dataDTO.getEmployeeName());
                pmEmployee.setOrgid(dataDTO.getOrgid());
                pmEmployee.setOrgcode(dataDTO.getOrgcode());
                pmEmployee.setOrgname(dataDTO.getOrgname());
                pmEmployee.setSpName(dataDTO.getSpName());
                pmEmployee.setTelephone(dataDTO.getTelphone());
                pmEmployee.setEmpstatid(EnumUtils.getEnum(EmpstatidEnum.class,Integer.valueOf(dataDTO.getEmpstatid())));
                pmEmployee.setIdpath(dataDTO.getIdpath());
                if(null != dataDTO.getLjdate() && !"0000-00-00 00:00:00".equals(dataDTO.getLjdate())){
                    pmEmployee.setLjdate(LocalDate.parse(dataDTO.getLjdate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }
                saveList.add(pmEmployee);
            }
        }
        for (PmEmployee emp : allList) {
            if(saveList.stream().filter(o -> emp.getEmployeeCode().equals(o.getEmployeeCode())).collect(Collectors.toList()).size() == 0 ){
                if(!"381799".equals(emp.getEmployeeCode()) && !"795888".equals(emp.getEmployeeCode())
                        && !"381800".equals(emp.getEmployeeCode())){
                    emp.setEmpstatid(EmpstatidEnum.LEAVE);
                    saveList.add(emp);
                }
            }
        }
        save(saveList);
    }
}
