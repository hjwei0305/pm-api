package com.donlim.pm.flow;

import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.flow.FlowInvokeParams;
import com.changhong.sei.core.dto.flow.FlowStatus;
import com.changhong.sei.core.entity.ITenant;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.changhong.sei.core.utils.ResultDataUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author liusonglin
 * @version 1.0
 * @Desc
 * @date 2021/9/17 16:02
 */
public abstract class BaseFlowController<T extends BaseFlowEntity, D extends BaseEntityDto> extends BaseEntityController<T, D> {


    @Override
    public abstract BaseFlowEntityService<T> getService();

    /**
     * 获取条件POJO属性说明
     *
     * @param businessModelCode 业务实体代码
     * @param all               是否查询全部
     * @return 流程条件设置，key 代表 code 可以自定义，在 propertiesAndValues 返回中需要有相同的code和值的返回, value 代表描述
     */
    @GetMapping(path = "properties")
    @ApiOperation(value = "通过业务实体代码获取条件POJO属性说明", notes = "获取业务单据条件实体的属性定义")
    public ResultData<Map<String, String>> properties(@RequestParam("businessModelCode") String businessModelCode,
                                                      @RequestParam(value = "all", defaultValue = "false") Boolean all) {
        Map<String, String> map = new LinkedHashMap<>();
        addProperties(map);
        return ResultData.success(map);
    }

    protected void addProperties(Map<String, String> map){};

    /**
     * 获取条件POJO属性键值对
     *
     * @param businessModelCode 业务实体代码
     * @param id                单据id
     * @return POJO属性说明Map
     */
    @GetMapping(path = "propertiesAndValues")
    @ApiOperation(value = "通过业务实体代码,业务ID获取条件POJO属性键值对", notes = "获取业务单据条件实体的属值，包含工作流必要的属性值")
    public ResultData<Map<String, Object>> propertiesAndValues(@RequestParam("businessModelCode") String businessModelCode,
                                                               @RequestParam("id") String id,
                                                               @RequestParam("all") Boolean all) {
        // 获取业务单据
        if (StringUtils.isBlank(id)) {
            return ResultDataUtil.fail("00006", id, businessModelCode);
        }
        T t = this.getService().findOne(id);
        if (Objects.isNull(t)) {
            return ResultDataUtil.fail("00007", id, businessModelCode);
        }
        Map<String, Object> map = new LinkedHashMap<>();
        // 设置必要属性
        map.put("orgId", t.getOrganizationId());
        map.put("orgCode", t.getOrganizationCode());
        map.put("businessCode", getBusinessCode(t));
        map.put("name", getFlowName(t));
        map.put("workCaption", getWorkCaption(t));
        if(t instanceof ITenant){
            map.put("tenantCode", ((ITenant) t).getTenantCode());
        }

        // 设置条件属性
        Map<String, Object> result = getPropertyValue(t);
        if(Objects.nonNull(result)){
            map.putAll(result);
        }
        return ResultData.success(map);
    }

    public Map<String,Object> getPropertyValue(T t){
        return null;
    }

    /***
     * 流程获取工作摘要
     *
     * @param t 单据
     * @return 流程名称
     */
    protected abstract String getWorkCaption(T t);

    /***
     * 流程获取名称
     *
     * @param t 单据
     * @return 业务单据代码
     */
    protected abstract String getFlowName(T t);

    /***
     * 流程获取业务单据代码
     * @param t
     * @return
     */
    protected abstract String getBusinessCode(T t);


    /**
     * 获取条件POJO属性初始化值键值对
     *
     * @param businessModelCode 业务实体代码
     * @return 初始话流程属性，流程校验表达式是否正确使时调用，比如 code > 100 这种表达，
     * 需要返回一个 code:value值，value值不需要有明确意义，但是需要符合表达式的所判断的类型，比如数字，字符串类似
     */
    @GetMapping(path = "initPropertiesAndValues")
    @ApiOperation(value = "通过业务实体代码获取条件POJO属性初始化值键值对", notes = "获取业务单据条件实体的属性初始值")
    public ResultData<Map<String, Object>> initPropertiesAndValues(@RequestParam("businessModelCode") String businessModelCode) {
        Map<String, Object> map = new LinkedHashMap<>();
        addInitValue(map);
        return ResultData.success(map);
    }

    protected void addInitValue(Map<String, Object> map){};

    /**
     * 重置单据状态
     *
     * @param businessModelCode 业务实体代码
     * @param id                单据id
     * @param status            状态
     * @return 返回结果
     */
    @PostMapping(path = "resetState")
    @ApiOperation(value = "通过业务实体代码及单据ID重置业务单据流程状态", notes = "重置业务单据的流程处理状态")
    public ResultData<Boolean> resetState(@RequestParam("businessModelCode") String businessModelCode,
                                          @RequestParam("id") String id,
                                          @RequestParam("status") FlowStatus status) {
        OperateResultWithData<T> result = this.getService().updateFlowStatus(id, status);
        if(result.getSuccess()){
            return ResultData.success();
        }else{
            return ResultData.fail(result.getMessage());
        }

    }

    /**
     * 流程启动前检查事件
     *
     * @param invokeParams 流程参数
     * @return 处理结果
     */
    @PostMapping(path = "beforeStartFlow", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "流程启动前检查事件", notes = "流程启动前检查事件，检查事件不通过禁止启动")
    public ResultData<?> beforeStartFlow(@RequestBody FlowInvokeParams invokeParams) {
        String id = invokeParams.getId();
        if (StringUtils.isBlank(id)) {
            return ResultDataUtil.fail("bms00351");
        }
        T t = this.getService().findOne(id);
        if (Objects.isNull(t)) {
            return ResultDataUtil.fail("bms00352");
        }
        return ResultDataUtil.success("bms00018");
    }

    /**
     * 流程完成后检查事件
     *
     * @param invokeParams 流程参数
     * @return 处理结果
     */
    @PostMapping(path = "afterFinishFlow", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "流程完成后检查事件", notes = "流程完成后检查事件，检查事件不通过禁止提交")
    public ResultData<Void> afterFinishFlow(@RequestBody FlowInvokeParams invokeParams) {
        return ResultData.success();
    }

    /**
     * 流程启动后执行事件
     *
     * @param invokeParams 流程参数
     * @return 处理结果
     */
    @PostMapping(path = "afterStartFlow", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "流程启动后执行事件", notes = "流程启动后执行事件，检查事件不通过禁止提交")
    public ResultData<Void> afterStartFlow(FlowInvokeParams invokeParams) {
        return ResultData.success();
    }
}
