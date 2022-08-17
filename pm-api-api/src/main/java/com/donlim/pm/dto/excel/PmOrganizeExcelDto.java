package com.donlim.pm.dto.excel;

import io.swagger.annotations.ApiModelProperty;

/**
 * 导出组织一览表excel
 *
 * @author sei
 * @since 2022-08-17 16:44:03
 */
public class PmOrganizeExcelDto {
    private static final long serialVersionUID = -47355896084772616L;

    @ApiModelProperty(value = "单位名称")
    private String extorgname;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "小组名称")
    private String groupName;

    @ApiModelProperty(value = "组长")
    private String manager;

    @ApiModelProperty(value = "小组成员数量")
    private String memberCount;

    @ApiModelProperty(value = "是否冻结")
    private String frozen;

    public String getExtorgname() {
        return extorgname;
    }

    public void setExtorgname(String extorgname) {
        this.extorgname = extorgname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(String memberCount) {
        this.memberCount = memberCount;
    }

    public String getFrozen() {
        return frozen;
    }

    public void setFrozen(String frozen) {
        this.frozen = frozen;
    }
}
