package com.donlim.pm.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 项目流程配置(PmProjectOption)实体类
 *
 * @author sei
 * @since 2023-04-06 14:59:06
 */
@Entity
@Table(name = "pm_project_option")
@DynamicInsert
@DynamicUpdate
public class PmProjectOption extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = 629393090599701024L;
    /**
     * 配置名称
     */
    @Column(name = "pro_name")
    private String proName;
    /**
     * 流程配置
     */
    @Column(name = "pro_opt")
    private String proOpt;
    /**
     * 是否启用
     */
    @Column(name = "usable")
    private Boolean usable;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;


    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProOpt() {
        return proOpt;
    }

    public void setProOpt(String proOpt) {
        this.proOpt = proOpt;
    }

    public Boolean getUsable() {
        return usable;
    }

    public void setUsable(Boolean usable) {
        this.usable = usable;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
