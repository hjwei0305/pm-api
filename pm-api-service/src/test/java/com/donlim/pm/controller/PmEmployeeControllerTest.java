package com.donlim.pm.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JsonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.donlim.pm.dto.PmEmployeeDto;


/**
 * 员工表(PmEmployee)单元测试类
 *
 * @author sei
 * @since 2022-07-27 17:37:35
 */
public class PmEmployeeControllerTest extends BaseUnitTest {

    @Autowired
    private PmEmployeeController controller;

    @Test
    public void findOne() {
        String id = "";
        ResultData<PmEmployeeDto> resultData = controller.findOne(id);
        LOG.debug(JsonUtils.toJson(resultData));
        Assert.assertTrue(resultData.successful());
    }

}