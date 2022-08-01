package com.donlim.pm.entity;
import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 员工表(PmEmployee)实体类
 *
 * @author sei
 * @since 2022-07-27 17:17:10
 */
@Entity
@Table(name = "pm_employee")
@DynamicInsert
@DynamicUpdate
public class PmEmployee extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = -64872230874568801L;
    /**
     * EMPID
     */
    @Column(name = "emp_id")
    private Integer empId;
    /**
     * 工号
     */
    @Column(name = "code")
    private String code;
    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;
    /**
     * 部门id
     */
    @Column(name = "emp_dept_id")
    private String empDeptId;
    /**
     * 职务
     */
    @Column(name = "emp_zhiwu")
    private String empZhiwu;
    /**
     * 人员状态
     */
    @Column(name = "emp_state")
    private Integer empState;
    /**
     * 联系电话
     */
    @Column(name = "emp_lxdh")
    private String empLxdh;
    /**
     * 备注
     */
    @Column(name = "emp_remark")
    private String empRemark;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
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