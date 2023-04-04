package com.donlim.pm.connector;

import com.donlim.pm.dto.MailDto;
import com.donlim.pm.webservice.eip.*;
import com.donlim.pm.webservice.eipcenter.*;

import javax.xml.ws.Holder;


/**
 * @Description:EIP系统对接
 * @Author: chenzhiquan
 * @Date: 2022/8/22.
 */
public class EipConnector {

    public static final SvcHdrType svcHdr = new SvcHdrType();
    public static final AppHdrType appHdr = new AppHdrType();
    public static final AppBodyType appBody = new AppBodyType();
    public static final Holder<SvcHdrTypes> svcHdrs = new Holder<>();
    public static final Holder<AppHdrTypes> appHdrs = new Holder<>();
    public static final Holder<AppBodyTypes> appBodys = new Holder<>();
    public static final DONLIMESAGENCYNOTICEINFOSYNC086_Service service = new DONLIMESAGENCYNOTICEINFOSYNC086_Service();
    public static final DONLIMESAGENCYNOTICEINFOSYNC086 sync = service.getDONLIMESAGENCYNOTICEINFOSYNC086SOAP();
    public static final AddNoticeType notice = new AddNoticeType();
    public static final String sourceId = "SEIPROD";
    public static final String destinationId = "EIP";
    public static final String ipAddress = "127.0.0.1";
    public static final String bo = "待办通知信息同步";
    public static final String systemName = "软件项目管理系统";
    public static final String systemSort = "IMPMS";
    public static final String mailType = "待办";
    public  static boolean sendNotice(MailDto dto){
        //type ADD,UPDATE,DELETE
        svcHdr.setBO("项目管理系统");
        svcHdr.setSOURCEID(sourceId);
        svcHdr.setDESTINATIONID(destinationId);
        svcHdr.setTYPE(dto.getType());
        svcHdr.setIPADDRESS(ipAddress);
        AppHdrType appHdrType=new AppHdrType();
        AppBodyType appBodyType=new AppBodyType();
        AddNoticeType addNoticeType=new AddNoticeType();
        addNoticeType.setAccount(dto.getAccount());
        addNoticeType.setMailID(dto.getMailID());
        addNoticeType.setMailType(dto.getMailType());
        addNoticeType.setMailBody(dto.getMailBody());
        addNoticeType.setMailSubject(dto.getMailSubject());
        addNoticeType.setSystemName(systemName);
        addNoticeType.setSystemSort(systemSort);
        addNoticeType.setUrl(dto.getUrl());
        appBodyType.setAddNotice(addNoticeType);
        Holder<SvcHdrTypes> svcHdrTypesHolder=new Holder<>();
        Holder<AppHdrTypes> appHdrTypesHolder=new Holder<>();
        Holder<AppBodyTypes> appBodyTypesHolder=new Holder<>();
        sync.donlimESAGENCYNOTICEINFOSYNC086(svcHdr,appHdrType,appBodyType,svcHdrTypesHolder,appHdrTypesHolder,appBodyTypesHolder);
        if("Y".equals(svcHdrTypesHolder.value.getRCODE())){
            return true;
        }else{
            return false;
        }
    }

    public static SvcHdrTypes deleteEipMall(String mailId){
        svcHdr.setSOURCEID(sourceId);
        svcHdr.setDESTINATIONID(destinationId);
        svcHdr.setTYPE("DELETE");
        svcHdr.setBO(bo);
        svcHdr.setIPADDRESS(ipAddress);
        notice.setMailID(mailId);
        notice.setMailType(mailType);
        notice.setSystemName(systemName);
        notice.setSystemSort(systemSort);
        appBody.setAddNotice(notice);
        sync.donlimESAGENCYNOTICEINFOSYNC086(svcHdr, appHdr, appBody,svcHdrs,appHdrs,appBodys);
        return svcHdrs.value;
    }


    /**
     * 提案单号
     * @param code
     * @return 是否结案
     */
    public static APPBODYS isFinish(String code) {
        APPHDR appHdr = new APPHDR();
        APPBODY appBody = new APPBODY();
        appBody.setPROJECTNO(code);
        SVCHDR svcHdr = new SVCHDR();
        CheckSearchSevice checkSearchSevice = new CheckSearchSevice();
        SVCBODYS svcbodys = checkSearchSevice.getDomino().synchecksearch(svcHdr, appHdr, appBody);
        if ("S".equals(svcbodys.getOSVCHDRS().getRCODE())) {
            return svcbodys.getOAPPBODYS();
        }
        return null;
    }

}
