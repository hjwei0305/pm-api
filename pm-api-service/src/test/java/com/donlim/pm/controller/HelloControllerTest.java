package com.donlim.pm.controller;

import com.alibaba.fastjson.JSONObject;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.util.EnumUtils;
import com.donlim.pm.em.SmallNodeType;
import com.donlim.pm.service.PmBaseinfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * 实现功能: Hello 单元测试
 */
public class HelloControllerTest extends BaseUnitTest {
    @Autowired
    private HelloController controller;
    @Autowired
    private PmBaseinfoService pmBaseinfoService;
    @Test
    public void sayHello() {
       /* String name = "程序员";
        ResultData<String> result = controller.sayHello(name);
        LOG.debug(JsonUtils.toJson(result));*/

  /*      HashMap<String,String> map=new HashMap<>();
        map.put("mm","aa");
        map.put("bb","kk");
        String aa =JSONObject.toJSONString(map);
        System.out.println(aa);*/
        System.out.println(SmallNodeType.Start.name()); ;
        System.out.println(EnumUtils.getEnumItemRemark(SmallNodeType.class,SmallNodeType.Research));


    }
}
