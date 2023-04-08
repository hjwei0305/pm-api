package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 项目流程配置(PmProjectOption)DTO类
 *
 * @author sei
 * @since 2023-04-06 15:00:17
 */
@ApiModel(description = "项目流程配置DTO")
public class PmProjectOptionDto extends BaseEntityDto {
    private static final long serialVersionUID = -67474999867197203L;
    /**
     * 配置名称
     */
    @ApiModelProperty(value = "配置名称")
    private String proName;
    /**
     * 流程配置
     */
    @ApiModelProperty(value = "流程配置")
    private String proOpt;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    private Boolean usable;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
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
