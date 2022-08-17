package com.donlim.pm.entity;

import com.changhong.sei.core.dto.TreeEntity;
import com.changhong.sei.core.entity.BaseAuditableEntity;
import com.changhong.sei.core.entity.IFrozen;
import com.changhong.sei.core.entity.ITenant;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * (PmOrganize)实体类
 *
 * @author sei
 * @since 2022-08-11 14:05:48
 */
@Entity
@Table(name = "pm_organize")
@DynamicInsert
@DynamicUpdate
public class PmOrganize extends BaseAuditableEntity implements Serializable , IFrozen, ITenant, TreeEntity<PmOrganize> {
    private static final long serialVersionUID = -21862674675858290L;

    @Column(name = "orgid")
    private Integer orgid;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "extorgname")
    private String extorgname;

    @Column(name = "manager")
    private String manager;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "superid")
    private Integer superid;

    @Column(name = "node_level")
    private Integer nodeLevel;

    @Column(name = "idpath")
    private String idpath;

    @Column(name = "code_path")
    private String codePath;

    @Column(name = "name_path")
    private String namePath;

    @Column(name = "category")
    private String category;

    @Column(name = "member_count")
    private Integer memberCount;

    @Column(name = "remark")
    private String remark;

    @Column(name = "tenant_code")
    private String tenantCode;
    /**
     * 冻结
     */
    @Column(name = "frozen")
    private Boolean frozen;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "rank")
    private Integer rank;

    /**
     * 子节点列表
     */
    @Transient
    private List<PmOrganize> children;


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
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getNodeLevel() {
        return nodeLevel;
    }

    @Override
    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
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

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public Integer getRank() {
        return rank;
    }

    @Override
    public List<PmOrganize> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<PmOrganize> children) {
        this.children = children;
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

    @Override
    public String getTenantCode() {
        return tenantCode;
    }
    @Override
    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    @Override
    public Boolean getFrozen() {
        return frozen;
    }

    @Override
    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
