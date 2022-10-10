package com.donlim.pm.flow;

import com.changhong.sei.core.dto.flow.FlowStatus;
import com.changhong.sei.core.limiter.support.lock.SeiLock;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.service.bo.OperateResultWithData;

import java.util.Objects;

/**
 * @author liusonglin
 * @version 1.0
 * @Desc
 * @date 2021/9/17 16:18
 */
public abstract class BaseFlowEntityService<T extends BaseFlowEntity> extends BaseEntityService<T> {

    /**
     * 变更流程状态
     * @param id
     * @param status
     * @return
     */
    @SeiLock(key = "'updateFlowStatus:' + #id")
    public OperateResultWithData<T> updateFlowStatus(String id, FlowStatus status) {
        try{
             T t = findOne(id);
            if (Objects.isNull(t)){
                LogUtil.info("变更流程状态，单据不存在，id:{0}",id);
                return OperateResultWithData.operationFailure("commons",id);
            }
            t.setFlowStatus(status);
            changeWithFlowStatus(t,status);
            getDao().save(t);
            return OperateResultWithData.operationSuccessWithData(t);
        }catch (Exception e) {
            LogUtil.error("变更流程状态异常", e);
            return OperateResultWithData.operationFailure("flow");
        }
    }

    protected void changeWithFlowStatus(T t, FlowStatus status){

    }

}
