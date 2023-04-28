package com.donlim.pm.connector;

import com.alibaba.fastjson.JSONObject;
import com.donlim.pm.constant.HRMSConstant;
import com.donlim.pm.dto.EmployeeDTO;
import com.donlim.pm.dto.OrgDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Carol
 * @date 2022/8/10 14:50
 * @description
 */
@Component
public class HRMSConnector {
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static ResponseEntity<String> result;

    /**
     * 错误提示
     */
    public static final String ERROR = "HRMS服务器响应超时!";
    public static String url = null;

    /**
     * 人员信息
     *
     * @param
     * @return
     */
    public static List<EmployeeDTO.DataDTO> getEmp() {
        REST_TEMPLATE.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        url = HRMSConstant.HRMSURL + HRMSConstant.GET_EMP;
        try {
            result = REST_TEMPLATE.getForEntity(url, String.class);
        } catch (Exception e) {
            throw (e);
        }
        List<EmployeeDTO> empList = JSONObject.parseArray("[" + result.getBody() + "]", EmployeeDTO.class);
        return empList.get(0).getData().stream()
                .filter(emp -> emp.getIdpath().startsWith(HRMSConstant.IT_IDPATH) ||
                        emp.getIdpath().startsWith(HRMSConstant.DT_IDPATH))
                .collect(Collectors.toList());
    }

    /**
     * 组织信息
     *
     * @param
     * @return
     */
    public static List<OrgDTO.DataDTO> getOrg() {
        REST_TEMPLATE.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        url = HRMSConstant.HRMSURL + HRMSConstant.GET_ORG;
        try {
            result = REST_TEMPLATE.getForEntity(url, String.class);
        } catch (Exception e) {
            throw (e);
        }
        List<OrgDTO> orgList = JSONObject.parseArray("[" + result.getBody() + "]", OrgDTO.class);
        return orgList.get(0).getData().stream()
                .filter(org -> org.getIdpath().startsWith(HRMSConstant.IT_IDPATH) ||
                        org.getIdpath().startsWith(HRMSConstant.DT_IDPATH) || org.getIdpath().equals(HRMSConstant.CENTRE_IDPATH))
                .collect(Collectors.toList());
    }

    /**
     * 处理组织不在中心人员，手动添加到指定科室（后续加优化）
     * @return
     */
    public static List<EmployeeDTO.DataDTO> getOneEmp() {
        REST_TEMPLATE.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        url = HRMSConstant.HRMSURL + HRMSConstant.GET_EMP;
        try {
            result = REST_TEMPLATE.getForEntity(url, String.class);
        } catch (Exception e) {
            throw (e);
        }
        List<EmployeeDTO> empList = JSONObject.parseArray("[" + result.getBody() + "]", EmployeeDTO.class);
        return empList.get(0).getData().stream()
                .filter(emp -> "381800".equals(emp.getEmployeeCode()) || "795888".equals(emp.getEmployeeCode()))
                .collect(Collectors.toList());
    }

}
