package com.donlim.pm.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/9/13.
 */
@Data
public class MailDto {

    private String account;
    /**
     * ADD,UPDATE,DELETE
     */
    private String type;

    //private String address;

    //private String createMan;

    private String mailBody;

    //private LocalDate approvalDate;

    private String mailID;

    private String mailSubject;
    /**
     * 通知、待办
     */
    private String mailType;

    private String url;

}
