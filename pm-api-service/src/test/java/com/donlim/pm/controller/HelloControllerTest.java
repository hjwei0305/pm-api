package com.donlim.pm.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JsonUtils;
import com.donlim.pm.controller.HelloController;
import com.donlim.pm.dto.PmBaseinfoDto;
import com.donlim.pm.em.BigNodeEnum;
import com.donlim.pm.em.FileTypeEnum;
import com.donlim.pm.service.PmBaseinfoService;
import com.donlim.pm.util.EnumUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sound.midi.Soundbank;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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

    }
}
