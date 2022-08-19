package com.donlim.pm.entity;

import com.changhong.sei.core.entity.BaseAuditableEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (FileList)实体类
 *
 * @author sei
 * @since 2022-08-18 18:22:18
 */
@Entity
@Table(name = "file_list")
@DynamicInsert
@DynamicUpdate
@Data
public class FileList extends BaseAuditableEntity implements Serializable {
    private static final long serialVersionUID = 376937010933366871L;
    /**
     * 文件ID
     */
    @Column(name = "file_id")
    private Integer fileId;
    /**
     * 文件大小
     */
    @Column(name = "file_size")
    private Integer fileSize;
    /**
     * 文件名
     */
    @Column(name = "file_name")
    private String fileName;
    /**
     * 项目ID
     */
    @Column(name = "project_id")
    private String projectId;
    /**
     * 项目阶段
     */
    @Column(name = "stage")
    private String stage;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;



}
