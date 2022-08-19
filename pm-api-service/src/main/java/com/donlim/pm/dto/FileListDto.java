package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * (FileList)DTO类
 *
 * @author sei
 * @since 2022-08-18 18:22:49
 */
@ApiModel(description = "DTO")
public class FileListDto extends BaseEntityDto {
    private static final long serialVersionUID = 362425528485570767L;
    /**
     * 文件ID
     */
    @ApiModelProperty(value = "文件ID")
    private Integer fileId;
    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    private Integer fileSize;
    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名")
    private String fileName;
    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    private String projectId;
    /**
     * 项目阶段
     */
    @ApiModelProperty(value = "项目阶段")
    private String stage;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;


    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

}
