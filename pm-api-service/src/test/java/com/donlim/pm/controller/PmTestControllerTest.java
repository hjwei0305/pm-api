package com.donlim.pm.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JsonUtils;
import com.donlim.pm.dto.PmTestDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试(PmTest)单元测试类
 *
 * @author sei
 * @since 2022-07-23 09:19:29
 */
public class PmTestControllerTest extends BaseUnitTest {

    @Autowired
    private PmTestController controller;

    @Test
    public void findOne() {
        String id = "";
        ResultData<PmTestDto> resultData = controller.findOne(id);
        LOG.debug(JsonUtils.toJson(resultData));
        Assert.assertTrue(resultData.successful());
    }

}
