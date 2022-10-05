package com.donlim.pm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Author: chenzhiquan
 * @Date: 2022/8/11.
 */
@NoArgsConstructor
@Data
public class IppProjectDto {

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
         * proposalID
         */
        @JsonProperty("ProposalID")
        private String proposalID;
        /**
         * branchFlow
         */
        private Integer branchFlow;
        /**
         * proposalName
         */
        @JsonProperty("ProposalName")
        private String proposalName;
        /**
         * createDate
         */
        @JsonProperty("CeateDate")
        private String createDate;
        /**
         * createMan
         */
        private String createMan;
        /**
         * impactNumbers
         */
        private Object impactNumbers;
        /**
         * currentDescription
         */
        @JsonProperty("CurrentDescription")
        private String currentDescription;
        /**
         * demandDescription
         */
        @JsonProperty("DemandDescription")
        private String demandDescription;
        /**
         * improvedDescription
         */
        @JsonProperty("ImprovedDescription")
        private String improvedDescription;
        /**
         * promotionDescription
         */
        @JsonProperty("PromotionDescription")
        private String promotionDescription;
        /**
         * auditOpinion
         */
        private String auditOpinion;
        /**
         * auditMan
         */
        private String auditMan;
        /**
         * auditDate
         */
        private Object auditDate;
        /**
         * approvalOpinion
         */
        private String approvalOpinion;
        /**
         * approvalMan
         */
        private String approvalMan;
        /**
         * approvalDate
         */
        private Object approvalDate;
        /**
         * filteredOpinion
         */
        private String filteredOpinion;
        /**
         * filteredMan
         */
        private String filteredMan;
        /**
         * filteredDate
         */
        private String filteredDate;
        /**
         * visitOpinion
         */
        private String visitOpinion;
        /**
         * isAgree
         */
        private String isAgree;
        /**
         * billState
         */
        private Integer billState;
        /**
         * billStateDate
         */
        private String billStateDate;
        /**
         * mobile
         */
        private String mobile;
        /**
         * firstScore
         */
        private Object firstScore;
        /**
         * reviewScore
         */
        private Object reviewScore;
        /**
         * stageCode
         */
        private Object stageCode;
        /**
         * developmentEngineer
         */
        private String developmentEngineer;
        /**
         * feasibilityReport
         */
        private String feasibilityReport;
        /**
         * projectEngineer
         */
        private Object projectEngineer;
        /**
         * projectOrganization
         */
        private String projectOrganization;
        /**
         * demandReport
         */
        private String demandReport;
        /**
         * developmentPlan
         */
        private String developmentPlan;
        /**
         * competencyAssessment
         */
        private String competencyAssessment;
        /**
         * developDays
         */
        private Object developDays;
        /**
         * level
         */
        private String level;
        /**
         * department
         */
        private String department;
        /**
         * demandAttachment
         */
        private String demandAttachment;
        /**
         * ifSupport
         */
        private String ifSupport;
        /**
         * workflowID
         */
        private String workflowID;
        /**
         * departmentFullName
         */
        private Object departmentFullName;
        /**
         * zhiWei
         */
        private String zhiWei;
        /**
         * ifOverFiveDays
         */
        private Object ifOverFiveDays;
        /**
         * systemInvolvedInProposal
         */
        private String systemInvolvedInProposal;
        /**
         * proposalType
         */
        private String proposalType;
        /**
         * belongingBusinessModule
         */
        private String belongingBusinessModule;
        /**
         * teamDutyInstruction
         */
        private String teamDutyInstruction;
        /**
         * developmentWay
         */
        private Integer developmentWay;
        /**
         * judgeAbilityOpinion
         */
        private String judgeAbilityOpinion;
        /**
         * oneKeySetup
         */
        private String oneKeySetup;
        /**
         * planEngineer
         */
        private String planEngineer;
        /**
         * fZCOpinion
         */
        private String fZCOpinion;
        /**
         * areaID
         */
        private Integer areaID;
        /**
         * billType
         */
        private Integer billType;
        /**
         * demandType
         */
        private Integer demandType;
        /**
         * hopeDate
         */
        private String hopeDate;
        /**
         * bPRMan
         */
        private String bPRMan;
        /**
         * isChangeBPR
         */
        private Integer isChangeBPR;
        /**
         * testEngineer
         */
        private String testEngineer;
        /**
         * developmentStart
         */
        private Object developmentStart;
        /**
         * developmentEnd
         */
        private Object developmentEnd;
        /**
         * developmentFinish
         */
        private Object developmentFinish;
        /**
         * getProDate
         */
        private Object getProDate;
        /**
         * testStartDate
         */
        private Object testStartDate;
        /**
         * testEndDate
         */
        private Object testEndDate;
        /**
         * testFinishDate
         */
        private Object testFinishDate;
        /**
         * extendDate
         */
        private Object extendDate;
        /**
         * bPROpinion
         */
        private String bPROpinion;
        /**
         * developmentDays
         */
        private Integer developmentDays;
        /**
         * checkOpinion
         */
        private String checkOpinion;
        /**
         * projectEngineerOption
         */
        private String projectEngineerOption;
        /**
         * isProjectClear
         */
        private Integer isProjectClear;
        /**
         * clearRemark
         */
        private String clearRemark;
        /**
         * isPassRemand
         */
        private Integer isPassRemand;
        /**
         * projectAnalysis
         */
        private String projectAnalysis;
        /**
         * technologyAnalysis
         */
        private String technologyAnalysis;
        /**
         * projectManager
         */
        private String projectManager;
        /**
         * dDOpinion
         */
        private String dDOpinion;
        /**
         * systemInfo
         */
        private Integer systemInfo;
        /**
         * testReport
         */
        private String testReport;
        /**
         * isOperation
         */
        private Object isOperation;
        /**
         * sDDOpinion
         */
        private String sDDOpinion;
        /**
         * suggestDevelopmentWay
         */
        private Integer suggestDevelopmentWay;
        /**
         * suggestOneKeyUp
         */
        private Object suggestOneKeyUp;
        /**
         * sVisitOpinion
         */
        private String sVisitOpinion;
        /**
         * testResult
         */
        private Object testResult;
        /**
         * isProject
         */
        private Integer isProject;
        /**
         * planAcceptanceDate
         */
        private Object planAcceptanceDate;
        /**
         * createManWorkState
         */
        private Integer createManWorkState;
        /**
         * aduitManWorkState
         */
        private Integer aduitManWorkState;
        /**
         * remarks
         */
        private Object remarks;
        /**
         * isReview
         */
        private Object isReview;
        /**
         * proposalName2
         */
        private String proposalName2;
        /**
         * systemInfo2
         */
        private Object systemInfo2;
        /**
         * devlopmentMan
         */
        private String devlopmentMan;
        /**
         * internDate
         */
        private Object internDate;
        /**
         * reviewDate
         */
        private Object reviewDate;
        /**
         * frameReviewID
         */
        private String frameReviewID;
        /**
         * technicalReviewID
         */
        private String technicalReviewID;
        /**
         * oneKeyUpDate
         */
        private Object oneKeyUpDate;
        /**
         * projectDate
         */
        @JsonProperty("ProjectDate")
        private String projectDate;
        /**
         * isPlastic
         */
        private Integer isPlastic;
        /**
         * currentWFStep
         */
        private Integer currentWFStep;
        /**
         * currentWFStepName
         */
        private String currentWFStepName;
        /**
         * suggestDevelopmentWay2
         */
        private Integer suggestDevelopmentWay2;
        /**
         * savePersonnelDescription
         */
        private String savePersonnelDescription;
        /**
         * hardwareNeedDescription
         */
        @JsonProperty("hardwareNeedDescription")
        private String hardwareNeedDescription;
        /**
         * isOnlyImplement
         */
        private Integer isOnlyImplement;
        /**
         * isImplement
         */
        private Integer isImplement;
        /**
         * isDevelop
         */
        private Integer isDevelop;
        /**
         * isTest
         */
        private Integer isTest;
        /**
         * dueProjectStart
         */
        private Object dueProjectStart;
        /**
         * dueProjectEnd
         */
        private Object dueProjectEnd;
        /**
         * dueProjectPeriod
         */
        private Integer dueProjectPeriod;
        /**
         * isArbitration
         */
        private Integer isArbitration;
        /**
         * isInvestmentForm
         */
        private Object isInvestmentForm;
        /**
         * isSubmitForBuyer
         */
        private Object isSubmitForBuyer;
        /**
         * acceptanceCode
         */
        private String acceptanceCode;
        /**
         * acceptanceDate
         */
        private Object acceptanceDate;
        /**
         * planEngineerName
         */
        private String planEngineerName;
        /**
         * planEngineerDept
         */
        private String planEngineerDept;
        /**
         * planEngineerPost
         */
        private String planEngineerPost;
        /**
         * testOption
         */
        private String testOption;
        /**
         * isImplementerCreate
         */
        private Integer isImplementerCreate;
        /**
         * implementerProposalID
         */
        private String implementerProposalID;
        /**
         * isDemandPool
         */
        private Integer isDemandPool;
        /**
         * pMOpinion
         */
        private String pMOpinion;
        /**
         * sourceProposalID1
         */
        private String sourceProposalID1;
        /**
         * sourceProposalID2
         */
        private String sourceProposalID2;
        /**
         * sourceProposalID3
         */
        private String sourceProposalID3;
        /**
         * sourceProposalID4
         */
        private String sourceProposalID4;
        /**
         * sourceProposalID5
         */
        private String sourceProposalID5;
        /**
         * sourceProposalID6
         */
        private String sourceProposalID6;
        /**
         * sourceProposalID7
         */
        private String sourceProposalID7;
        /**
         * sourceProposalID8
         */
        private String sourceProposalID8;
        /**
         * sourceProposalID9
         */
        private String sourceProposalID9;
        /**
         * sourceProposalID10
         */
        private String sourceProposalID10;
        /**
         * accepter
         */
        private String accepter;
        /**
         * systemName
         */
        @JsonProperty("SystemName")
        private String systemName;
        /**
         * areaName
         */
        private String areaName;
        /**
         * demandTypeName
         */
        private String demandTypeName;
    }
}
