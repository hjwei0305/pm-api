package com.donlim.pm.dto;

import com.changhong.sei.core.dto.BaseEntityDto;
import lombok.Data;

/**
 * @Description:项目人员统计
 * @Author: chenzhiquan
 * @Date: 2022/9/15.
 */
@Data
public class PersonnelProjectStatisticsDto extends BaseEntityDto {
    private static final long serialVersionUID = -47355896084772612L;
    private String employeeCode;

    private String employeeName;
    /**
     * 总项目数
     */
    private Integer projectTotalNum;
    /**
     * 重点项目数
     */
    private Integer projectFocusNum;
    /**
     * 项目名称集合
     */
    private String projectNames;
    /**
     * 待办数
     */
    private Long toDoNum;

}
