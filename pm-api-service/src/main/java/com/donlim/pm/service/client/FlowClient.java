package com.donlim.pm.service.client;

import com.changhong.sei.core.dto.flow.FlowTask;
import com.changhong.sei.core.service.bo.ResponseData;
import com.donlim.pm.service.client.vo.StartFlowVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "flow-service", path = "defaultFlowBase")
public interface FlowClient {
    @ResponseBody
    @GetMapping(path = "startFlowNew")
    ResponseData startFlow(StartFlowVo startFlowVo);
}
