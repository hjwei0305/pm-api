package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 员工表(PmEmployee)DTO类
 *
 * @author sei
 * @since 2022-07-27 17:16:06
 */
@ApiModel(description = "员工表DTO")
public class PmEmployeeDto extends BaseEntityDto {
    private static final long serialVersionUID = -26136492049020062L;
    /**
     * EMPID
     */
    @ApiModelProperty(value = "EMPID")
    private Integer empId;
    /**
     * 工号
     */
    @ApiModelProperty(value = "工号")
    private String code;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    private String empDeptId;
    /**
     * 职务
     */
    @ApiModelProperty(value = "职务")
    private String empZhiwu;
    /**
     * 人员状态
     */
    @ApiModelProperty(value = "人员状态")
    private Integer empState;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String empLxdh;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String empRemark;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpDeptId() {
        return empDeptId;
    }

    public void setEmpDeptId(String empDeptId) {
        this.empDeptId = empDeptId;
    }

    public String getEmpZhiwu() {
        return empZhiwu;
    }

    public void setEmpZhiwu(String empZhiwu) {
        this.empZhiwu = empZhiwu;
    }

    public Integer getEmpState() {
        return empState;
    }

    public void setEmpState(Integer empState) {
        this.empState = empState;
    }

    public String getEmpLxdh() {
        return empLxdh;
    }

    public void setEmpLxdh(String empLxdh) {
        this.empLxdh = empLxdh;
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

}