package com.donlim.pm.connector;

import com.donlim.pm.webservice.eip.*;

/**
 * @Description:EIP系统对接
 * @Author: chenzhiquan
 * @Date: 2022/8/22.
 */
public class EipConnector {


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
