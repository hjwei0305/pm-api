package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 流程配置(WfConfig)DTO类
 *
 * @author sei
 * @since 2022-07-30 08:21:19
 */
@ApiModel(description = "流程配置DTO")
public class WfConfigDto extends BaseEntityDto {
    private static final long serialVersionUID = -51801572107409220L;
    /**
     * 项目编码
     */
    @ApiModelProperty(value = "项目编码")
    private String code;
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String name;
    /**
     * 流程顺序
     */
    @ApiModelProperty(value = "流程顺序")
    private Integer wfNo;
    /**
     * 流程名称流程名称
     */
    @ApiModelProperty(value = "流程名称流程名称")
    private String wfName;
    /**
     * 节点顺序
     */
    @ApiModelProperty(value = "节点顺序")
    private Integer nodeNo;
    /**
     * 节点名称
     */
    @ApiModelProperty(value = "节点名称")
    private String nodeName;
    /**
     * 有无附件
     */
    @ApiModelProperty(value = "有无附件")
    private Integer nodeAttachement;
    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称")
    private String nodeAttachementName;
    /**
     * 节点启用
     */
    @ApiModelProperty(value = "节点启用")
    private String isFrozen;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


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

    public Integer getWfNo() {
        return wfNo;
    }

    public void setWfNo(Integer wfNo) {
        this.wfNo = wfNo;
    }

    public String getWfName() {
        return wfName;
    }

    public void setWfName(String wfName) {
        this.wfName = wfName;
    }

    public Integer getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(Integer nodeNo) {
        this.nodeNo = nodeNo;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getNodeAttachement() {
        return nodeAttachement;
    }

    public void setNodeAttachement(Integer nodeAttachement) {
        this.nodeAttachement = nodeAttachement;
    }

    public String getNodeAttachementName() {
        return nodeAttachementName;
    }

    public void setNodeAttachementName(String nodeAttachementName) {
        this.nodeAttachementName = nodeAttachementName;
    }

    public String getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(String isFrozen) {
        this.isFrozen = isFrozen;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}