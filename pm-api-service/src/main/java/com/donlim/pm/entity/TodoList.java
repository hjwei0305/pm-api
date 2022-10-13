package com.donlim.pm.entity;

import com.donlim.pm.flow.BaseFlowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
/**
 * 代办事项(TodoList)实体类
 *
 * @author sei
 * @since 2022-07-30 08:17:40
 */
@Entity
@Table(name = "todo_list")
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper=true)
@Data
public class TodoList extends BaseFlowEntity implements Serializable {
    private static final long serialVersionUID = 693716441330182047L;
    /**
     * 项目编码
     */
    @Column(name = "project_code")
    private String projectCode;
    /**
     * 项目名称
     */
    @Column(name = "project_name")
    private String projectName;
    /**
     * 代办事项
     */
    @Column(name = "todo_list")
    private String todoList;
    /**
     * 责任人工号
     */
    @Column(name = "onduty_code")
    private String ondutyCode;
    /**
     * 责任人姓名
     */
    @Column(name = "onduty_name")
    private String ondutyName;
    /**
     * 提出人工号
     */
    @Column(name = "submit_code")
    private String submitCode;
    /**
     * 提出人姓名
     */
    @Column(name = "submit_name")
    private String submitName;
    /**
     * 提出时间
     */
    @Column(name = "submit_date")
    private LocalDate submitDate;
    /**
     * 结案时间
     */
    @Column(name = "end_date")
    private LocalDate endDate;
    /**
     * 是否完成
     */
    @Column(name = "is_completed")
    private Boolean isCompleted;
    /**
     * 是否结案
     */
    @Column(name = "is_finished")
    private Boolean isFinished;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 租户代码
     */
    @Column(name = "tenant_code")
    private String tenantCode;
    /**
     * 是否同步
     */
    @Column(name="is_sync")
    private String isSync;
    /**
     * 类型：待办，通知
     */
    @Column(name = "type")
    private String type;
    /**
     * 要求完成日期
     */
    @Column(name = "completion_date")
    private LocalDate completionDate;
    /**
     * 确认人(确认阶段)
     */
    @Column(name = "confirmedby1")
    private String confirmedby1;
    /**
     * 确认阶段状态
     */
    @Column(name = "confirm1_status")
    private Boolean confirm1Status;
    /**
     * 建议状态confirm1_status
     */
    @Column(name = "proposal_status")
    private String proposalStatus;
    /**
     * 完成情况
     */
    @Column(name = "completion")
    private String completion;
    /**
     * 确认人(验证阶段)
     */
    @Column(name = "confirmedby2")
    private String confirmedby2;
    /**
     * 确认时间
     */
    @Column(name = "confirmation_time")
    private LocalDate confirmationTime;
    /**
     * 结案状态
     */
    @Column(name = "closing_status")
    private String closingStatus;
    /**
     * 单据状态
     */
    @Column(name = "document_status")
    private String documentStatus;
    /**
     * 来源
     */
    @Column(name = "source")
    private String source;
}
