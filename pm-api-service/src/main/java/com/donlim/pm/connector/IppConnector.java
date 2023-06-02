package com.donlim.pm.connector;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.donlim.pm.dto.IppProjectDto;
import com.donlim.pm.dto.IppProjectInfoDetails;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.webservice.ipp.DONLIMIMOSQUERYSYNC;
import com.donlim.pm.webservice.ipp.DONLIMIMOSQUERYSYNC_Service;
import com.donlim.pm.webservice.ipp.SvcHdrType;
import com.donlim.pm.webservice.ipp.SvcHdrsType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:提案系统对接
 * @Author: chenzhiquan
 * @Date: 2022/8/12.
 */
public class IppConnector {
    private static final SvcHdrType svcHdr = new SvcHdrType();
    private static SvcHdrsType svcHdrs = new SvcHdrsType();
    private static final DONLIMIMOSQUERYSYNC_Service service = new DONLIMIMOSQUERYSYNC_Service();
    private static final DONLIMIMOSQUERYSYNC sync = service.getDONLIMIMOSQUERYSYNCSOAP();
    private static final String sourceId = "PM";
    private static final String destinationId = "IPP";
    private static final String type = "SELECT";
    private static final String ipAddress = "127.0.0.1";

    /**
     * 获取项目
     * @param code
     * @return
     */
    public static List<PmBaseinfo> getPorjectInfo(String code) {
        svcHdr.setBO("项目管理系统");
        svcHdr.setSOURCEID(sourceId);
        svcHdr.setDESTINATIONID(destinationId);
        svcHdr.setTYPE(type);
        svcHdr.setIPADDRESS(ipAddress);
        svcHdr.setNO("631");
        svcHdr.setPageIndex(1);
        svcHdr.setPageSize(1);
        svcHdr.setBodyJson("{\"ProposalID\":\"" +code + "\"}");
        try {
            svcHdrs = sync.donlimIMOSQUERYSYNC(svcHdr);
        } catch (Exception e) {
            e.toString();
        }
        List<PmBaseinfo>pmBaseinfoList=new ArrayList<>();
        if ("S".equals(svcHdrs.getRCODE())) {
            JSONObject result = JSONObject.parseObject("{\"table\":" + svcHdrs.getResultJson() + "}");
            IppProjectDto ippProjectDto = JSON.toJavaObject(result, IppProjectDto.class);
            if (ippProjectDto.getTable().size() > 0) {
                for (IppProjectDto.TableDTO ipp : ippProjectDto.getTable()) {
                    PmBaseinfo pmBaseinfo=new PmBaseinfo();
                    pmBaseinfo.setCode(ipp.getProposalID());
                    pmBaseinfo.setName(ipp.getProposalName());
                    pmBaseinfo.setSysName(ipp.getSystemName());
                    pmBaseinfo.setSubmissionDate(LocalDate.now());
                    pmBaseinfo.setCurrentDescription(ipp.getCurrentDescription());
                    pmBaseinfo.setRequirementDescription(ipp.getDemandDescription());
                    pmBaseinfo.setImproveBenefits(ipp.getImprovedDescription());
                    pmBaseinfo.setPromotionDegree(ipp.getPromotionDescription());
                    pmBaseinfo.setHardwareRequirement(ipp.getHardwareNeedDescription());
                    pmBaseinfo.setProjectTypes(ipp.getProposalType());
                    pmBaseinfo.setStatus("0");
                    pmBaseinfoList.add(pmBaseinfo);
                }
            }
        }
        return pmBaseinfoList;
    }
    /**
     * 获取项目
     * @param code
     * @return
     */
    public static List<IppProjectInfoDetails.TableDTO> getPorjectInfoDetails(String code) {
        svcHdr.setBO("项目管理系统");
        svcHdr.setSOURCEID(sourceId);
        svcHdr.setDESTINATIONID(destinationId);
        svcHdr.setTYPE(type);
        svcHdr.setIPADDRESS(ipAddress);
        svcHdr.setNO("633");
        svcHdr.setPageIndex(1);
        svcHdr.setPageSize(1);
        svcHdr.setBodyJson("{\"ProposalID\":\"" +code + "\"}");
        try {
            svcHdrs = sync.donlimIMOSQUERYSYNC(svcHdr);
        } catch (Exception e) {
            e.toString();
        }
        List<IppProjectInfoDetails.TableDTO>ippProjectInfoDetailList=new ArrayList<>();
        if ("S".equals(svcHdrs.getRCODE())) {
            JSONObject result = JSONObject.parseObject("{\"table\":" + svcHdrs.getResultJson() + "}");
            IppProjectInfoDetails ippProjectInfoDetails = JSON.toJavaObject(result, IppProjectInfoDetails.class);
            ippProjectInfoDetailList=  ippProjectInfoDetails.getTable();
        }
        return ippProjectInfoDetailList;
    }

    /**
     * 获取测试结果
     * @param code
     * @return
     */
    public static boolean getTestResult(String code) {
        svcHdr.setBO("项目管理系统");
        svcHdr.setSOURCEID(sourceId);
        svcHdr.setDESTINATIONID(destinationId);
        svcHdr.setTYPE(type);
        svcHdr.setIPADDRESS(ipAddress);
        svcHdr.setNO("633-01");
        svcHdr.setPageIndex(1);
        svcHdr.setPageSize(1);
        svcHdr.setBodyJson("{\"num\":\"" +code + "\"}");
        try {
            svcHdrs = sync.donlimIMOSQUERYSYNC(svcHdr);
        } catch (Exception e) {
            e.toString();
        }
        if ("S".equals(svcHdrs.getRCODE())) {
            JSONObject result = JSONObject.parseObject(svcHdrs.getResultJson().replace("[","").replace("]",""));
                if(result!=null && "通过".equals(result.getString("result"))){
                    return true;
                }
            }
        return false;
    }
}
