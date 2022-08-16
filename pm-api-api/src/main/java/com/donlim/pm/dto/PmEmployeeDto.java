package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * (PmEmployee)DTO类
 *
 * @author sei
 * @since 2022-08-10 16:44:03
 */
@ApiModel(description = "DTO")
public class PmEmployeeDto extends BaseEntityDto {
    private static final long serialVersionUID = -47355896084772616L;


    private String employeeCode;


    private String employeeName;


    private Integer groupid;


    private Integer orgid;


    private String orgcode;


    private String orgname;


    private String spName;


    private String telephone;


    private String empstatid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate ljdate;


    private String empRemark;


    private String tenantCode;

    private String idpath;


    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmpstatid() {
        return empstatid;
    }

    public void setEmpstatid(String empstatid) {
        this.empstatid = empstatid;
    }

    public LocalDate getLjdate() {
        return ljdate;
    }

    public void setLjdate(LocalDate ljdate) {
        this.ljdate = ljdate;
    }

    public String getEmpRemark() {
        return empRemark;
    }

    public void setEmpRemark(String empRemark) {
        this.empRemark = empRemark;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getIdpath() {
        return idpath;
    }

    public void setIdpath(String idpath) {
        this.idpath = idpath;
    }
}
