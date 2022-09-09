package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * (PmLog)DTO类
 *
 * @author sei
 * @since 2022-09-06 14:25:57
 */
@ApiModel(description = "DTO")
public class PmLogDto extends BaseEntityDto {
    private static final long serialVersionUID = -12905451610446700L;
    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private String projectId;
    /**
     * 项目类型
     */
    @ApiModelProperty(value = "项目类型")
    private String projectTypes;
    /**
     * 工号
     */
    @ApiModelProperty(value = "工号")
    private String employeeCode;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String employeeName;
    /**
     * 操作的内容
     */
    @ApiModelProperty(value = "操作的内容")
    private String content;


    private String tenantCode;

    @ApiModelProperty(value = "创建时间")
    protected Date createdDate;


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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectTypes() {
        return projectTypes;
    }

    public void setProjectTypes(String projectTypes) {
        this.projectTypes = projectTypes;
    }
}
