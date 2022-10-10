package com.donlim.pm.flow;

import com.changhong.sei.core.dto.flow.FlowInvokeParams;
import com.google.common.base.Strings;

import java.util.Map;
import java.util.Objects;

/**
 * <strong>实现功能:</strong>
 * <p>业务流程操作工具类</p>
 *
 * @author Mr.Gw 龚旺
 * @version 1.0.1 2020-08-04 18:21
 */
public class FlowOpreateUtil {
    private static final String END_SIGN_KEY = "endSign";
    private static final String PROCESS_NOT_KEY = "opinion";
    /**
     * 是终止流程
     * @param params 业务流程调用服务方法的参数
     * @return 是终止流程
     */
    public static boolean isTerminate(FlowInvokeParams params){
        if (Objects.isNull(params) || Objects.isNull(params.getParams())){
            return false;
        }
        Map<String, String> map = params.getParams();
        if (map.containsKey(END_SIGN_KEY)){
            String endSign = map.get(END_SIGN_KEY);
            return "1".equals(endSign);
        }
        return false;
    }

    /**
     * 流程是正常完成的
     * @param params 业务流程调用服务方法的参数
     * @return 是正常完成
     */
    public static boolean isCompleted(FlowInvokeParams params){
        if (Objects.isNull(params) || Objects.isNull(params.getParams())){
            return false;
        }
        Map<String, String> map = params.getParams();
        if (map.containsKey(END_SIGN_KEY)){
            String endSign = map.get(END_SIGN_KEY);
            if(Strings.isNullOrEmpty(endSign)){
                return true;
            }
            return "0".equals(endSign);
        }
        return false;
    }
    /**
     * 获取处理意见
     * @param params 业务流程调用服务方法的参数
     * @return 处理意见
     */
    public static String getProcessNote(FlowInvokeParams params){
        String processNote = "";
        if (Objects.isNull(params) || Objects.isNull(params.getParams())){
            return processNote;
        }
        Map<String, String> map = params.getParams();
        if (map.containsKey(PROCESS_NOT_KEY)){
            return map.get(PROCESS_NOT_KEY);
        }
        return processNote;
    }
}
