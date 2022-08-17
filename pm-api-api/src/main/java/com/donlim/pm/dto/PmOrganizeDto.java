package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.core.dto.TreeEntity;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * (PmOrganize)DTO类
 *
 * @author sei
 * @since 2022-08-11 14:06:11
 */
@ApiModel(description = "DTO")
public class PmOrganizeDto extends BaseEntityDto implements TreeEntity<PmOrganizeDto> {
    private static final long serialVersionUID = -69258574758473277L;


    private Integer orgid;


    private String code;


    private String name;


    private String extorgname;


    private String manager;

    private String groupName;

    private Integer superid;


    private Integer nodeLevel;


    private String idpath;


    private String codePath;


    private String namePath;


    private String category;


    private Integer memberCount;


    private String remark;


    private Boolean frozen;

    private List<PmOrganizeDto> children;


    private String tenantCode;

    private String parentId;

    private Integer rank;

    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    public String getExtorgname() {
        return extorgname;
    }

    public void setExtorgname(String extorgname) {
        this.extorgname = extorgname;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Integer getSuperid() {
        return superid;
    }

    public void setSuperid(Integer superid) {
        this.superid = superid;
    }

    public String getIdpath() {
        return idpath;
    }

    public void setIdpath(String idpath) {
        this.idpath = idpath;
    }
    @Override
    public String getCodePath() {
        return codePath;
    }
    @Override
    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }
    @Override
    public String getNamePath() {
        return namePath;
    }
    @Override
    public void setNamePath(String namePath) {
        this.namePath = namePath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }
    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public Integer getNodeLevel() {
        return nodeLevel;
    }
    @Override
    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public Boolean getFrozen() {
        return frozen;
    }

    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public List<PmOrganizeDto> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<PmOrganizeDto> children) {
        this.children = children;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
