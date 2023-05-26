package com.donlim.pm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PmVisitStatisticsDTO {
    /**
     * 组织id
     */
    private String orgid;
    /**
     * 科室名称
     */
    private String name;
    /**
     * 访问次数
     */
    private Integer count;
    /**
     * 成员
     */
    private List<VistiEmployee> children;

    @Data
    @NoArgsConstructor
    public static class VistiEmployee {
        /**
         * 成员名称
         */
        private String employeeName;
        /**
         * 访问次数
         */
        private Integer count;
    }
}
