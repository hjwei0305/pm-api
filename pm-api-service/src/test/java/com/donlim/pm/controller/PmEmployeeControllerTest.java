package com.donlim.pm.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JsonUtils;
import com.donlim.pm.dto.PmEmployeeDto;
import com.donlim.pm.service.PmEmployeeService;
import com.donlim.pm.service.PmOrganizeService;
import com.donlim.pm.service.TodoListService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 员工表(PmEmployee)单元测试类
 *
 * @author sei
 * @since 2022-07-27 17:37:35
 */
public class PmEmployeeControllerTest extends BaseUnitTest {

    @Autowired
    private PmEmployeeController controller;
    @Autowired
    private PmEmployeeService pmEmployeeService;
    @Autowired
    private PmOrganizeService pmOrganizeService;
    @Autowired
    private TodoListService todoListService;

    @Test
    public void findOne() {
        String id = "";
        ResultData<PmEmployeeDto> resultData = controller.findOne(id);
        LOG.debug(JsonUtils.toJson(resultData));
        Assert.assertTrue(resultData.successful());
    }

    @Test
    public void test() {
        // 同步时org要有tentcode
        pmEmployeeService.synEmp();
        System.out.println(1);
    }

    @Test
    public void test1() {
        // 同步时org要有tentcode
        pmOrganizeService.synOrg();
        System.out.println(1);
    }

    @Test
    public void test2() {
        todoListService.calcOverdueDay();

    }

}
