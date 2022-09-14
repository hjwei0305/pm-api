package com.donlim.pm.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JsonUtils;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.donlim.pm.connector.IppConnector;
import com.donlim.pm.dto.PmBaseinfoDto;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.service.PmBaseinfoService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

/**
 * 基础资料(PmBaseinfo)单元测试类
 *
 * @author sei
 * @since 2022-07-28 09:35:42
 */
public class PmBaseinfoControllerTest extends BaseUnitTest {

    @Autowired
    private PmBaseinfoController controller;
@Autowired
private PmBaseinfoService pmBaseinfoService;
@Autowired PmBaseinfoController pmBaseinfoController;
    @Test
    public void findOne() {
/*        String id = "";
        ResultData<PmBaseinfoDto> resultData = controller.findOne(id);
        LOG.debug(JsonUtils.toJson(resultData));
        Assert.assertTrue(resultData.successful());*/
        //List<PmBaseinfo> porjectInfo = IppConnector.getPorjectInfo();
       // ResultData resultData = pmBaseinfoController.syncProjectInfo("E20220408004");
     //   resultData.getData();
      //  ResultData<PmBaseinfoDto> byIdForSchedule = pmBaseinfoController.findByIdForSchedule("235C2B6B-1AE1-11ED-BF87-34C93D8809B5");
      //  System.out.println(byIdForSchedule.getData());
      //  pmBaseinfoService.updateProjectInfo();
        pmBaseinfoService.updateProjectInfo();

    }

}
