package com.donlim.pm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/8/22.
 */
@NoArgsConstructor
@Data
public class IppProjectInfoDetails {

    /**
     * table
     */
    private List<TableDTO> table;

    /**
     * TableDTO
     */
    @NoArgsConstructor
    @Data
    public static class TableDTO {
        /**
         * reviewFormID
         */
        private String reviewFormID;
        /**
         * userCode
         */
        private String userCode;
        /**
         * userName
         */
        private String userName;
        /**
         * developWay
         */
        @JsonProperty("DevelopWay")
        private String developWay;
        /**
         * reviewWay
         */
        @JsonProperty("ReviewWay")
        private String reviewWay;
        /**
         * language
         */
        private String language;
        /**
         * reivewMonth
         */
        private String reivewMonth;
        /**
         * systemID
         */
        private Integer systemID;
        /**
         * systemName
         */
        private String systemName;
        /**
         * proposalID
         */
        private String proposalID;
        /**
         * proposalName
         */
        private String proposalName;
        /**
         * isPass
         */
        private Integer isPass;
    }
}
