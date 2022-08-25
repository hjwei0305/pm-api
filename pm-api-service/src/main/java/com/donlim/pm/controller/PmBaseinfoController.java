package com.donlim.pm.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.utils.ResultDataUtil;
import com.changhong.sei.edm.sdk.DocumentManager;
import com.donlim.pm.api.PmBaseinfoApi;
import com.donlim.pm.dto.PmBaseinfoDto;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.service.PmBaseinfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 基础资料(PmBaseinfo)控制类
 *
 * @author sei
 * @since 2022-07-28 09:01:47
 */
@RestController
@Api(value = "PmBaseinfoApi", tags = "基础资料服务")
@RequestMapping(path = PmBaseinfoApi.PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class PmBaseinfoController extends BaseEntityController<PmBaseinfo, PmBaseinfoDto> implements PmBaseinfoApi {
    /**
     * 基础资料服务对象
     */
    @Autowired
    private PmBaseinfoService service;
    @Autowired
    private DocumentManager documentManager;
    @Override
    public BaseEntityService<PmBaseinfo> getService() {
        return service;
    }

    @Override
    public ResultData<PageResult<PmBaseinfoDto>> findByPage(Search search) {
        return  convertToDtoPageResult(service.findByPage(search));
    }

    @Override
    public ResultData<PmBaseinfoDto> save(PmBaseinfoDto dto) {
        service.bindFile(dto);
        return ResultData.success();
    }

    @Override
    public ResultData<PmBaseinfoDto> saveBaseInfo(PmBaseinfoDto dto) {
        return super.save(dto);
    }

    @Override
    public ResultData<PmBaseinfoDto> findByIdForSchedule(String id) {
      return  ResultData.success(service.findByIdForSchedule(id)) ;
    }

    @Override
    public ResultData updateProjectInfoTask(Map<String, String> params) {
        LogUtil.bizLog("后台任务由【{}】执行完成！", ContextUtil.getSessionUser());
        service.updateProjectInfo();
        return ResultDataUtil.success("执行成功");
    }

    @Override
    public ResultData syncProjectInfo(String code) {
        PmBaseinfo pmBaseinfo = service.syncIppInfo(code);
        if(pmBaseinfo!=null){
            return ResultData.success(convertToDto(pmBaseinfo));
        }else{
            return ResultData.fail("同步失败！！！该单号数据不存在或已经建档。");
        }

    }

}
