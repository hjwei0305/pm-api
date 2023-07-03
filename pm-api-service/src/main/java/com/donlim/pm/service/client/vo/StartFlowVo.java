package com.donlim.pm.service.client.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class StartFlowVo implements Serializable {

    private String businessModelCode;

    private String businessKey;

    private String opinion;

    private String typeId;

    private String flowDefKey;

    private String taskList;

    private String anonymousNodeId;

}