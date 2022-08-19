package com.donlim.pm.api;

import com.changhong.sei.core.api.BaseEntityApi;
import com.donlim.pm.dto.FileListDto;
import org.springframework.cloud.openfeign.FeignClient;

import javax.validation.Valid;

/**
 * (FileList)API
 *
 * @author sei
 * @since 2022-08-18 18:22:50
 * TODO @FeignClient(name = "请修改为项目服务名")
 */
@Valid
@FeignClient(name = "pm-api", path = FileListApi.PATH)
public interface FileListApi extends BaseEntityApi<FileListDto> {
    String PATH = "fileList";
}
