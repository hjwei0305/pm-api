package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 项目周计划(PmBaseinfoWeek)DTO类
 *
 * @author sei
 * @since 2023-05-08 13:58:32
 */
@ApiModel(description = "项目周计划DTO")
public class PmBaseinfoWeekDto extends BaseEntityDto {
    private static final long serialVersionUID = 358761161942159857L;
    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private String baseinfoId;
    /**
     * 周数
     */
    @ApiModelProperty(value = "周数")
    private String week;
    /**
     * 本周计划
     */
    @ApiModelProperty(value = "本周计划")
    private String weekPlan;
    /**
     * 下周计划
     */
    @ApiModelProperty(value = "下周计划")
    private String nextWeekPlan;
    /**
     * 工作风险
     */
    @ApiModelProperty(value = "工作风险")
    private String workRisk;
    /**
     * 上次修改时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_TIME_FORMAT)
    @ApiModelProperty(value = "上次修改时间")
    private LocalDateTime lastModifiedTime;
    /**
     * 完成当周计划
     */
    @ApiModelProperty(value = "完成当周计划")
    private Boolean finishPlan;
    /**
     * 租户代码
     */
    @ApiModelProperty(value = "租户代码")
    private String tenantCode;
    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间")
    private Date lastEditedDate;

    @ApiModelProperty(value = "文档ID")
    private List<String> attachmentIdList;

    @ApiModelProperty(value = "文档类别")
    private String fileType;

    @ApiModelProperty(value = "状态数据JSON")
    private String gfxJson;


    public String getBaseinfoId() {
        return baseinfoId;
    }

    public void setBaseinfoId(String baseinfoId) {
        this.baseinfoId = baseinfoId;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeekPlan() {
        return weekPlan;
    }

    public void setWeekPlan(String weekPlan) {
        this.weekPlan = weekPlan;
    }

    public String getNextWeekPlan() {
        return nextWeekPlan;
    }

    public void setNextWeekPlan(String nextWeekPlan) {
        this.nextWeekPlan = nextWeekPlan;
    }

    public String getWorkRisk() {
        return workRisk;
    }

    public void setWorkRisk(String workRisk) {
        this.workRisk = workRisk;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Boolean getFinishPlan() {
        return finishPlan;
    }

    public void setFinishPlan(Boolean finishPlan) {
        this.finishPlan = finishPlan;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public Date getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(Date lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    public List<String> getAttachmentIdList() {
        return attachmentIdList;
    }

    public void setAttachmentIdList(List<String> attachmentIdList) {
        this.attachmentIdList = attachmentIdList;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getGfxJson() {
        return gfxJson;
    }

    public void setGfxJson(String gfxJson) {
        this.gfxJson = gfxJson;
    }
}
