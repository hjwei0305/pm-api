package com.donlim.pm.dto.excel;

import io.swagger.annotations.ApiModelProperty;

/**
 * 导出人员一览表excel
 *
 * @author sei
 * @since 2022-08-10 16:44:03
 */
public class PmEmployeeExcelDto {
    private static final long serialVersionUID = -47355896084772616L;

    @ApiModelProperty(value = "工号")
    private String employeeCode;

    @ApiModelProperty(value = "姓名")
    private String employeeName;

    @ApiModelProperty(value = "职务")
    private String spName;

    @ApiModelProperty(value = "所在小组")
    private String pmOrganizeGroupName;

    @ApiModelProperty(value = "所在部门")
    private String orgname;

    @ApiModelProperty(value = "联系电话")
    private String telephone;

    @ApiModelProperty(value = "在职状态")
    private String empstatid;

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

    public String getPmOrganizeGroupName() {
        return pmOrganizeGroupName;
    }

    public void setPmOrganizeGroupName(String pmOrganizeGroupName) {
        this.pmOrganizeGroupName = pmOrganizeGroupName;
    }
}
