package com.donlim.pm.controller;

import com.changhong.sei.basic.api.UserApi;
import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JwtTokenUtil;
import com.donlim.pm.connector.EipConnector;
import com.donlim.pm.connector.IppConnector;
import com.donlim.pm.dto.MailDto;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.service.PmBaseinfoService;
import com.donlim.pm.service.TodoListService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    private UserApi userApi;
    @Autowired
    private TodoListService service;

    @Test
    public void sayHello() {

        MailDto mailDto=new MailDto();
        mailDto.setAccount("377614");
        mailDto.setUrl("https://sei.donlim.com/#/user/login");
        mailDto.setMailID("1234");
        mailDto.setMailType("ADD");
        mailDto.setMailSubject("你有一个待办未处理，请登陆项目管理系统尽快处理！");
        EipConnector.sendNotice(mailDto);

      /*  Search search=new Search();
        ResultData<PageResult<PmBaseinfoDto>> byPage = pmBaseinfoController.findByPage(search);
        System.out.println(byPage.getData());*/

    }
      //  pmBaseinfoController.syncProjectInfo("E20220808002");
       // System.out.println( EnumUtils.getEnumItemRemark(LogType.class,LogType.ModifyCodePlan));
       /* String name = "程序员";
        ResultData<String> result = controller.sayHello(name);
        LOG.debug(JsonUtils.toJson(result));*/

  /*      HashMap<String,String> map=new HashMap<>();
        map.put("mm","aa");
        map.put("bb","kk");
        String aa =JSONObject.toJSONString(map);
        System.out.println(aa);*/
       // System.out.println(SmallNodeType.Start.name()); ;
      //  System.out.println(EnumUtils.getEnumItemRemark(SmallNodeType.class,SmallNodeType.Research));

      //  pmBaseinfoService.updateProjectInfo();
      //  APPHDR appHdr=new APPHDR();

       /* APPBODY appBody=new APPBODY();
        appBody.setPROJECTNO("E20211222011");
        SVCHDR svcHdr=new SVCHDR();
        ObjectFactory objectFactory=new ObjectFactory();
        CheckSearchSevice checkSearchSevice=new CheckSearchSevice();
        SVCBODYS svcbodys = checkSearchSevice.getDomino().synchecksearch(svcHdr, appHdr, appBody);
        if("S".equals(svcbodys.getOSVCHDRS().getRCODE())) {
            if (svcbodys.getOAPPBODYS().isRESULT()) {

            }
        }*/
     // IppConnector.getTestResult("E20211008003");
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
           service.SendEipTask();
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
