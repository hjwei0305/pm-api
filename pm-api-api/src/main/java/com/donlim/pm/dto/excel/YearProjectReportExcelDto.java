package com.donlim.pm.dto.excel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 导出科室年度项目excel
 *
 * @author Carol
 * @since 2023-05-15 16:44:03
 */
@NoArgsConstructor
@Data
public class YearProjectReportExcelDto {
    private static final long serialVersionUID = -47355896084772616L;
    /**
     * 科室名称
     */
    @ApiModelProperty(value = "科室名称")
    private String name;
    /**
     * 科室负责人
     */
    @ApiModelProperty(value = "科室负责人")
    private String manager;
    /**
     * 结案数量
     */
    @ApiModelProperty(value = "结案数量")
    private Long finishNum;
    /**
     * 未结案数量
     */
    @ApiModelProperty(value = "未结案数量")
    private Long notFinishNum;
    /**
     * 暂停数量
     */
    @ApiModelProperty(value = "暂停数量")
    private Long pauseNum;
    /**
     * 项目总数
     */
    @ApiModelProperty(value = "项目总数")
    private Long totalNum;

}
