package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import lombok.Data;

/**
 * @Description:无项目人员统计
 * @Author: zhangxiaotong
 * @Date: 2023/7/11
 */
@Data
public class PersonalBaseInfoReportDto extends BaseEntityDto {
    private static final long serialVersionUID = -47355896084772612L;
    private String employeeCode;

    private String employeeName;
    /**
     * 未开始
     */
    private  Integer notStartedNum;
    /**
     * 进行中
     */
    private Integer processingNum;
    /**
     * 暂停
     */
    private Integer pauseNum;
    /**
     * 结案
     */
    private Integer finishNum;
    /**
     * 总项目数
     */
    private Integer projectTotalNum;


}
