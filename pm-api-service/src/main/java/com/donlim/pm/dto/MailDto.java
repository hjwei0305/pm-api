package com.donlim.pm.dto;

import lombok.Data;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/9/13.
 */
@Data
public class MailDto {

    private String account;

    private String address;

    private String createMan;

    private String mailBody;

    private LocalDate approvalDate;

    private String mailID;

    private String mailSubject;
    /**
     * 通知、待办
     */
    private String mailType;

    private String url;

}
