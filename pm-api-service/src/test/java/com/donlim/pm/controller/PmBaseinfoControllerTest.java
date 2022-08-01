package com.donlim.pm.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JsonUtils;
import com.donlim.pm.dto.PmBaseinfoDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础资料(PmBaseinfo)单元测试类
 *
 * @author sei
 * @since 2022-07-28 09:35:42
 */
public class PmBaseinfoControllerTest extends BaseUnitTest {

    @Autowired
    private PmBaseinfoController controller;

    @Test
    public void findOne() {
        String id = "";
        ResultData<PmBaseinfoDto> resultData = controller.findOne(id);
        LOG.debug(JsonUtils.toJson(resultData));
        Assert.assertTrue(resultData.successful());
    }

}