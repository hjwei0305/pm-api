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

    private static final SvcHdrType svcHdr = new SvcHdrType();
    private static SvcHdrTypes svcHdrs = new SvcHdrTypes();
    private static final DONLIMESAGENCYNOTICEINFOSYNC086_Service service = new DONLIMESAGENCYNOTICEINFOSYNC086_Service();
    private static  final DONLIMESAGENCYNOTICEINFOSYNC086 sync=service.getDONLIMESAGENCYNOTICEINFOSYNC086SOAP();
    private static final String sourceId = "SPM";
    private static final String destinationId = "EIP";
    private static final String ipAddress = "127.0.0.1";
    private static String mailId="1234";
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
        addNoticeType.setSystemName("软件项目管理系统");
        addNoticeType.setSystemSort("A99");
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


    /**
     * 提案单号
     * @param code
     * @return 是否结案
     */
    public static boolean isFinish(String code) {
        APPHDR appHdr = new APPHDR();
        APPBODY appBody = new APPBODY();
        appBody.setPROJECTNO(code);
        SVCHDR svcHdr = new SVCHDR();
        CheckSearchSevice checkSearchSevice = new CheckSearchSevice();
        SVCBODYS svcbodys = checkSearchSevice.getDomino().synchecksearch(svcHdr, appHdr, appBody);
        if ("S".equals(svcbodys.getOSVCHDRS().getRCODE())) {
            return svcbodys.getOAPPBODYS().isRESULT();
        }
        return false;
    }

}
