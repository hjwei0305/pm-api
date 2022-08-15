package com.donlim.pm.constant;

/**
 * @author Carol
 * @date 2022/8/10 15:24
 * @description
 */
public class HRMSConstant {
    public static final String HRMSURL = "http://192.168.117.132";
    // 获取人员信息
    public static final String GET_EMP = "/dlhr/hr/api/getEmpData.co";
    // 获取组织信息
    public static final String GET_ORG = "/dlhr/hr/api/getOrgData.co";
    // 信息化idpath
    public static final String IT_IDPATH = "1,265,266,";

//    {"language1":"招募待入职","statvalue":"0"},{"language1":"实习","statvalue":"1"},{"language1":"试用","statvalue":"2"},{"language1":"考察","statvalue":"3"},{"language1":"正式","statvalue":"4"},{"language1":"借出","statvalue":"5"},{"language1":"待入职","statvalue":"6"},{"language1":"实习试用","statvalue":"7"},{"language1":"见习期","statvalue":"8"},{"language1":"待离职","statvalue":"11"},{"language1":"离职","statvalue":"12"},{"language1":"黑名单","statvalue":"13"}
    public static final String PRACTICE = "1";
    public static final String TRY = "2";
    public static final String INSPECT = "3";
    public static final String OFFICIAL = "4";
    public static final String LEAVE = "12";
}
