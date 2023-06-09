package com.donlim.pm.controller;

import com.changhong.sei.basic.dto.EmployeeDto;
import com.changhong.sei.basic.dto.SysUserDto;
import com.changhong.sei.basic.dto.search.EmployeeQuickQueryParam;
import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageInfo;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JwtTokenUtil;
import com.donlim.pm.connector.IppConnector;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.service.PmBaseinfoService;
import com.donlim.pm.service.TodoListService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.changhong.sei.basic.api.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 实现功能: Hello 单元测试
 */
public class HelloControllerTest extends BaseUnitTest {
    @Autowired
    private HelloController controller;
    @Autowired
    private PmBaseinfoService pmBaseinfoService;
    @Autowired
    private  PmBaseinfoController pmBaseinfoController;
    @Autowired
    private TodoListService service;
    @Autowired
    private SysUserApi sysUserApi;

    @Test
    public void sayHello() {
        ResultData<SysUserDto> byEmployeeCode = sysUserApi.findByEmployeeCode("377614");
        if(byEmployeeCode.successful()){

            SysUserDto data = byEmployeeCode.getData();
            System.out.println(data.getEmployeeCode());
        }
                
    }

       @Test
       public void a(){
           Map<String, Object> claims = new HashMap<>();
           String tenant = "test";
           String userId = "用户id";
           String account = "用户账号";
           String userName = "用户姓名";
           claims.put("tenant", tenant);
           claims.put("userId", "B0FB4370-0BBB-11ED-BD40-0242AC140011");
           claims.put("account", "376951");
           claims.put("userName", "张晓橦");
           claims.put("authorityPolicy","NormalUser");

           JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
           jwtTokenUtil.setJwtExpiration(2880000);
           String t = jwtTokenUtil.generateToken(account, UUID.randomUUID().toString(), claims);
           // 将token放入header中  x-authorization
           System.out.println("Token: " + t);
           account = jwtTokenUtil.getSubjectFromToken(t);
           System.out.println(account);
       }

       @Test
        public void b(){
         LocalDate start=  LocalDate.of(2023,3,23);
           LocalDate end=  LocalDate.of(2022,3,25);


           //service.SendEipTask();
       }

       @Test
        public void c(){
           IppConnector.getPorjectInfo("E20220415005");
       }

        @Test
        public void d(){
            List<PmBaseinfo> all = pmBaseinfoService.findAll();
            for (PmBaseinfo pmBaseinfo : all) {
                List<PmBaseinfo> porjectInfo = IppConnector.getPorjectInfo(pmBaseinfo.getCode());
                if(porjectInfo.size()>0){
                    pmBaseinfo.setSysName(porjectInfo.get(0).getSysName());
                }
            }
            pmBaseinfoService.save(all);
        }
        @Test
        public void w(){
            String userName = ContextUtil.getUserName();
            System.out.println(userName);

        }
}
