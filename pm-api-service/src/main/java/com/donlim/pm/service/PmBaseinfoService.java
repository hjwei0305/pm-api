package com.donlim.pm.service;

import com.alibaba.fastjson.JSONObject;
import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.edm.dto.DocumentDto;
import com.changhong.sei.edm.sdk.DocumentManager;
import com.donlim.pm.connector.EipConnector;
import com.donlim.pm.connector.IppConnector;
import com.donlim.pm.constant.IppConstant;
import com.donlim.pm.dao.PmBaseinfoDao;
import com.donlim.pm.dao.ProjectPlanDao;
import com.donlim.pm.dto.*;
import com.donlim.pm.em.*;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.entity.PmOrganize;
import com.donlim.pm.entity.ProjectPlan;
import com.donlim.pm.util.EnumUtils;
import com.donlim.pm.util.ReflectUtils;
import com.donlim.pm.webservice.eip.APPBODYS;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 基础资料(PmBaseinfo)业务逻辑实现类
 *
 * @author sei
 * @since 2022-07-28 09:01:47
 */
@Service
public class PmBaseinfoService extends BaseEntityService<PmBaseinfo> {
    @Autowired
    private PmBaseinfoDao dao;
    @Autowired
    private DocumentManager documentManager;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private ModelMapper dtoModelMapper;
    @Autowired
    private PmOrganizeService pmOrganizeService;

    @Override
    protected BaseEntityDao<PmBaseinfo> getDao() {
        return dao;
    }


    /**
     * 主计划
     */
    private static final String PROJECT_MASTER_PLAN = "0";
    /**
     * 后端开发计划
     */
    private static final String PROJECT_DEV_PLAN = "1";
    /**
     * 前端开发计划
     */
    private static final String PROJECT_WEB_PLAN = "2";
    /**
     * 实施计划
     */
    private static final String PROJECT_IMPL_PLAN = "3";
    /**
     * UI计划
     */
    private static final String UI_PLAN = "4";

    /**
     * 绑定上传附件关系
     *
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void bindFile(PmBaseinfoDto dto) {
        if (!StringUtils.isEmpty(dto.getId()) && !CollectionUtils.isEmpty(dto.getAttachmentIdList())) {
            Optional<PmBaseinfo> byId = dao.findById(dto.getId());
            if (byId.isPresent() && EnumUtils.isIncludeFileTypeEnum(dto.getFileType())) {
                List<String> idList = documentManager.getEntityDocumentInfos(dto.getId()).getData().stream().map(DocumentDto::getDocId).collect(Collectors.toList());
                idList.add(dto.getAttachmentIdList().get(0));
                documentManager.bindBusinessDocuments(dto.getId(), idList);
                //首字母转小写并加上Id
                String fieldName = dto.getFileType().substring(0, 1).toLowerCase() + dto.getFileType().substring(1) + "Id";
                //根据不同的类别记录附件的ID
                ReflectUtils.setFieldValue(byId.get(), fieldName, dto.getAttachmentIdList().get(0));
                documentManager.bindBusinessDocuments(dto.getAttachmentIdList().get(0), dto.getAttachmentIdList());
                save(byId.get());
            }
        } else if (!StringUtils.isEmpty(dto.getId()) && CollectionUtils.isEmpty(dto.getAttachmentIdList())) {
            // 清除附件绑定
            Optional<PmBaseinfo> byId = dao.findById(dto.getId());
            if (byId.isPresent() && EnumUtils.isIncludeFileTypeEnum(dto.getFileType())) {
                String fieldName = dto.getFileType().substring(0, 1).toLowerCase() + dto.getFileType().substring(1) + "Id";
                // 字段内容清除
                ReflectUtils.setFieldValue(byId.get(), fieldName, null);
                save(byId.get());
            }
        }
    }

    /**
     * 绑定上传附件列表
     *
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void bindFileList(PmBaseinfoDto dto) {
        List<com.changhong.sei.util.EnumUtils.EnumEntity> enumDataList = EnumUtils.getEnumDataList(FileType.class);
        if (!StringUtils.isEmpty(dto.getId()) && !CollectionUtils.isEmpty(dto.getAttachmentIdList())) {
            Optional<PmBaseinfo> byId = dao.findById(dto.getId());
            if (byId.isPresent()) {
                for (com.changhong.sei.util.EnumUtils.EnumEntity enumEntity : enumDataList) {
                    String fieldName = enumEntity.getName().substring(0, 1).toLowerCase() + enumEntity.getName().substring(1) + "Id";
                    if (!ObjectUtils.isEmpty(ReflectUtils.getFieldValue(byId.get(), fieldName)) &&
                            !dto.getAttachmentIdList().contains(ReflectUtils.getFieldValue(byId.get(), fieldName))) {
                        ReflectUtils.setFieldValue(byId.get(), fieldName, null);
                    }
                }
                documentManager.bindBusinessDocuments(dto.getId(), dto.getAttachmentIdList());
                save(byId.get());
            }
        } else if (!StringUtils.isEmpty(dto.getId()) && CollectionUtils.isEmpty(dto.getAttachmentIdList())) {
            // 附件list为空，清空绑定
            documentManager.deleteBusinessInfos(dto.getId());
            // 清除附件绑定
            Optional<PmBaseinfo> byId = dao.findById(dto.getId());
            if (byId.isPresent()) {
                for (com.changhong.sei.util.EnumUtils.EnumEntity enumEntity : enumDataList) {
                    String fieldName = enumEntity.getName().substring(0, 1).toLowerCase() + enumEntity.getName().substring(1) + "Id";
                    ReflectUtils.setFieldValue(byId.get(), fieldName, null);
                }
                save(byId.get());
            }
        }
    }

    /**
     * 同步项目基础信息
     *
     * @param code 提案单号
     */
    @Transactional(rollbackFor = Exception.class)
    public PmBaseinfo syncIppInfo(String code) {
        Optional<PmBaseinfo> byCode = dao.findByCode(code);
        if (byCode.isPresent()) {
            //已经存在
            return byCode.get();
        } else {
            List<PmBaseinfo> pmBaseinfoList = IppConnector.getPorjectInfo(code);
            if (pmBaseinfoList.size() > 0) {
                // dao.save(pmBaseinfoList);
                return pmBaseinfoList.get(0);
            } else {
                return null;
            }
        }
    }

    /**
     * 定时更新项目状态
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateProjectInfo() {
        //更新尚未结案的项目状态
        //List<PmBaseinfo> pmBaseinfoList = dao.findAllByStatus("0").stream().collect(Collectors.toList());
//        List<PmBaseinfo> pmBaseinfoList = dao.findAll().stream().filter(p -> p.getCode().equals("E20230426002")).collect(Collectors.toList());
        List<PmBaseinfo> pmBaseinfoList = dao.findAll().stream().collect(Collectors.toList());
        for (PmBaseinfo pmBaseinfo : pmBaseinfoList) {
            if (!StringUtils.isEmpty(pmBaseinfo.getCode())) {
                List<IppProjectInfoDetails.TableDTO> list = IppConnector.getPorjectInfoDetails(pmBaseinfo.getCode());
                for (IppProjectInfoDetails.TableDTO data : list) {
                    if (data.getDevelopWay().equals(IppConstant.UI)) {
                        pmBaseinfo.setUiReview(true);
                    } else if (data.getDevelopWay().equals(IppConstant.REQUIRE_REVIEW)) {
                        pmBaseinfo.setRequireReview(true);
                    } else if (data.getDevelopWay().equals(IppConstant.WEB_REVIEW)) {
                        pmBaseinfo.setWebReview(true);
                    } else if (data.getDevelopWay().equals(IppConstant.CODE_REVIEW)) {
                        pmBaseinfo.setCodeReview(true);
                    }
                }
                if (ObjectUtils.isEmpty(pmBaseinfo.getTest()) || pmBaseinfo.getTest() == false) {
                    pmBaseinfo.setTest(IppConnector.getTestResult(pmBaseinfo.getCode()));
                }
                if (pmBaseinfo.getStatus().equals("0")) {
                    APPBODYS finish = EipConnector.isFinish(pmBaseinfo.getCode().replaceAll(" ", ""));
                    if (!ObjectUtils.isEmpty(finish) && !StringUtils.isBlank(finish.getCHECKDATE())) {
                        pmBaseinfo.setStatus(finish.isRESULT() ? "1" : "0");
                        if (finish.getCHECKDATE().contains("/")) {
                            finish.setCHECKDATE(finish.getCHECKDATE().replaceAll("/", "-"));
                        }
                        if (finish.getCHECKDATE().split("-")[1].length() == 1) {
                            finish.setCHECKDATE(finish.getCHECKDATE().substring(0, 5) + "0" + finish.getCHECKDATE().substring(5));
                        }
                        if (finish.getCHECKDATE().split("-")[2].length() == 1) {
                            finish.setCHECKDATE(finish.getCHECKDATE().substring(0, 8) + "0" + finish.getCHECKDATE().substring(8));
                        }
                        pmBaseinfo.setFinalFinishDate(LocalDate.parse(finish.getCHECKDATE(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    }
                }
            }
            pmBaseinfo.setCurrentPeriod(findByIdForSchedule(pmBaseinfo.getId()).getCurrentPeriod());
            //计算主计划达成率
//            List<ProjectPlan> planList = projectPlanDao.getAllByProjectIdAndPlanType(pmBaseinfo.getId(), PROJECT_MASTER_PLAN);
            // 除主计划外，其他计划表
            List<ProjectPlan> otherPlanList = projectPlanDao.getAllByProjectIdAndPlanTypeNot(pmBaseinfo.getId(), PROJECT_MASTER_PLAN);
            int finishNum = 0;
            // 是否逾期 \ 提前
            long overTimeDay=0;
            long advanceDay=0;
            //计算是否逾期，逾期天数
            if (pmBaseinfo.getFinalFinishDate() != null && pmBaseinfo.getPlanFinishDate() != null) {
                if (pmBaseinfo.getFinalFinishDate().isAfter(pmBaseinfo.getPlanFinishDate())) {
                    if (LocalDate.now().isAfter(pmBaseinfo.getFinalFinishDate())) {
                        overTimeDay = pmBaseinfo.getFinalFinishDate().toEpochDay() - pmBaseinfo.getPlanFinishDate().toEpochDay();
                    }
                } else {
                    advanceDay = pmBaseinfo.getPlanFinishDate().toEpochDay() - pmBaseinfo.getFinalFinishDate().toEpochDay();
                }
            }
            if(pmBaseinfo.getPlanFinishDate() != null &&  pmBaseinfo.getFinalFinishDate() == null && LocalDate.now().isAfter(pmBaseinfo.getPlanFinishDate())){
                overTimeDay =LocalDate.now().toEpochDay()- pmBaseinfo.getPlanFinishDate().toEpochDay();
            }
            pmBaseinfo.setOveredDays(overTimeDay);
            pmBaseinfo.setAdvanceDays(advanceDay);
            if(overTimeDay>0){
                pmBaseinfo.setIsOverdue(true);
            }else{
                pmBaseinfo.setIsOverdue(false);
            }
            if(advanceDay>0){
                pmBaseinfo.setIsAdvance(true);
            }else{
                pmBaseinfo.setIsAdvance(false);
            }

            for (ProjectPlan projectPlan : otherPlanList) {
                    // 按主计划序号1计算是否逾期
//                    if (projectPlan.getSchedureNo().equals("1")) {
//                        if (projectPlan.getPlanEndDate() != null) {
//                            long daydiff = LocalDate.now().toEpochDay() - projectPlan.getPlanEndDate().toEpochDay();
//                            if (daydiff > 0) {
//                                pmBaseinfo.setIsOverdue(true);
//                                pmBaseinfo.setOveredDays(daydiff);
//                            } else {
//                                pmBaseinfo.setIsOverdue(false);
//                            }
//                        }
//                    }
                if (projectPlan.getActualEndDate() != null) {
                    finishNum++;
                }
            }
            String scheduleRatePercent = "0%";
            // 规定计划总数 > 6
            if (otherPlanList.size() >=7) {
                scheduleRatePercent = Math.round(finishNum * 100 / otherPlanList.size()) + "%";
            }
            pmBaseinfo.setMasterScheduleRate(scheduleRatePercent);
            save(pmBaseinfoList);
        }
    }

    /**
     * 获取项目详细信息供进度图使用
     *
     * @param id 项目ID
     * @return
     */
    public PmBaseinfoDto findByIdForSchedule(String id) {
        Optional<PmBaseinfo> byId = dao.findById(id);
        if (byId.isPresent()) {
            PmBaseinfo pmBaseinfo = byId.get();
            ModelMapper dtoModelMapper = new ModelMapper();
            PmBaseinfoDto pmBaseinfoDto = dtoModelMapper.map(pmBaseinfo, PmBaseinfoDto.class);
//            String proOpt = pmBaseinfoDto.getProOpt();
            // 改配置流程
            String proOpt = Optional.ofNullable(pmBaseinfoDto.getPmProjectOptionProOpt()).orElse(pmBaseinfoDto.getProOpt());
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            boolean flag = true;
            //2.1调研
            // 1、包含该节点---判断是否通过  2、不包含节点 -- 直接跳过
            if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.Research))) {
                if (!StringUtils.isEmpty(pmBaseinfoDto.getRequireDocId()) && !StringUtils.isEmpty(pmBaseinfoDto.getAcceptStandardDocId())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Research), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                    pmBaseinfoDto.setCurrentPeriod("需求分析");
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Research), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
            }
            //2.2启动会议
            if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.StartMeeting))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getStartReportDocId())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.StartMeeting), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        pmBaseinfoDto.setCurrentPeriod("需求分析");
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.StartMeeting), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //2.3提案系统
            if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.SubmitSystem))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getUserRequireDocId())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.SubmitSystem), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        pmBaseinfoDto.setCurrentPeriod("需求分析");
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.SubmitSystem), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //2.4需求评审
            if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.RequireReview))) {
                if (flag) {
                    if (!ObjectUtils.isEmpty(pmBaseinfoDto.getRequireReview())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.RequireReview), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        pmBaseinfoDto.setCurrentPeriod("需求分析");
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.RequireReview), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //3.1ui设计
            if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.UIDesigner))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getDesignerDocId()) && !StringUtils.isEmpty(pmBaseinfoDto.getCropDocId())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.UIDesigner), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        pmBaseinfoDto.setCurrentPeriod("UI设计");
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.UIDesigner), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //3.2ui评审
            if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.UIReview))) {
                if (flag) {
                    if (!ObjectUtils.isEmpty(pmBaseinfoDto.getUiReview())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.UIReview), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        pmBaseinfoDto.setCurrentPeriod("UI设计");
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.UIReview), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //4.1前端开发 4.3后台开发 填写计划即当完成

            if (flag) {
                if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.WebDev))) {
                    //获取后端开发计划表
                    if (projectPlanDao.countByProjectIdAndPlanType(pmBaseinfoDto.getId(), PROJECT_WEB_PLAN) > 0) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.WebDev), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        pmBaseinfoDto.setCurrentPeriod("系统开发");
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.WebDev), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
                if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeDev))) {
                    //获取前端计划表
                    if (projectPlanDao.countByProjectIdAndPlanType(pmBaseinfoDto.getId(), PROJECT_DEV_PLAN) > 0) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeDev), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        pmBaseinfoDto.setCurrentPeriod("系统开发");
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeDev), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }

            //4.2前端评审 4.4后台评审

            if (flag) {
                if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.WebReview))) {
                    if (!ObjectUtils.isEmpty(pmBaseinfoDto.getWebReview())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.WebReview), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        pmBaseinfoDto.setCurrentPeriod("系统开发");
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.WebReview), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
                if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeReview))) {
                    if (!ObjectUtils.isEmpty(pmBaseinfoDto.getCodeReview())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeReview), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        pmBaseinfoDto.setCurrentPeriod("系统开发");
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeReview), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }

            //5.1测试用例
            if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.TestSystem))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getTestExampleDocId()) && !StringUtils.isEmpty(pmBaseinfoDto.getTestReportDocId())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.TestSystem), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        pmBaseinfoDto.setCurrentPeriod("测试");
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.TestSystem), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //5.2测试结果
            if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.Test))) {
                if (flag) {
                    if (!ObjectUtils.isEmpty(pmBaseinfoDto.getTest())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Test), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        pmBaseinfoDto.setCurrentPeriod("测试");
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Test), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //6.1上线
            if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.Online))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getSopDocId()) && !StringUtils.isEmpty(pmBaseinfoDto.getQuestionListDocId())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Online), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        pmBaseinfoDto.setCurrentPeriod("上线实施");
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Online), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //6.2监控
            if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.Monitor))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getCheckListDocId())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Monitor), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        pmBaseinfoDto.setCurrentPeriod("上线实施");
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Monitor), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //7.1结案
            if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.Close))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getCaseCloseReportDocId()) && !StringUtils.isEmpty(pmBaseinfoDto.getSatisfactionSurveyDocId()) && !StringUtils.isEmpty(pmBaseinfoDto.getPageCheckDocId())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Close), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Close), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //7.2验收
            if (proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.Acceptance))) {
                if (flag) {
                    if (pmBaseinfoDto.getStatus().equals("1")) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Acceptance), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Acceptance), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //7.3完结
            if (flag) {
                map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.End), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                pmBaseinfoDto.setCurrentPeriod("项目结案");
            }
            String gtfJson = JSONObject.toJSONString(map);
            pmBaseinfoDto.setGfxJson(gtfJson);

            return pmBaseinfoDto;
        }
        return null;
    }

    /**
     * 获取个人的项目概况
     *
     * @return
     */
    public ProjectInfoDto getProjectInfo(Search search) {
        //  List<SearchFilter> filters = search.getFilters();
        // SearchFilter searchFilter = new SearchFilter("startDate","2023-01-01", SearchFilter.Operator.GE);
      //  search.addFilter(new SearchFilter("orgname","运营策略科", SearchFilter.Operator.EQ));
        // filters.add(searchFilter);
        //   search.setFilters(filters);
        String userName = ContextUtil.getUserName();
        ProjectInfoDto projectInfoDto = new ProjectInfoDto();
        //未开始数
        int notStartedNum = 0;
        //进行中数
        int processingNum = 0;
        // 结案数
        int finishNum = 0;
        // 暂停数
        int pauseNum = 0;
        /*
        //提前完成数
        int advanceFinishNum = 0;
        */
        //即将逾期项目数
        int preOverTimeNum=0;
        //逾期数
        int overTimeNum = 0;
        //提前天数
        long advanceDay = 0;
        //逾期天数
        long overTimeDay=0;
        List<PmBaseinfo> PmBaseinfoList = dao.findByFilters(search);
        for (PmBaseinfo pmBaseinfo : PmBaseinfoList) {
            // 暂停项目，其他不计入
            if(pmBaseinfo.getIsPause()){
                pauseNum++;
                continue;
            }
            if (!ObjectUtils.isEmpty(pmBaseinfo.getStartDate()) && LocalDate.now().isBefore(pmBaseinfo.getStartDate())) {
                notStartedNum++;
            }
            if (!ObjectUtils.isEmpty(pmBaseinfo.getStartDate()) && ObjectUtils.isEmpty(pmBaseinfo.getFinalFinishDate())  && LocalDate.now().isAfter(pmBaseinfo.getStartDate())) {
                processingNum++;
            }
            if (!ObjectUtils.isEmpty(pmBaseinfo.getFinalFinishDate())) {
                finishNum++;
            }
            /*
            if (pmBaseinfo.getFinalFinishDate() != null && pmBaseinfo.getPlanFinishDate() != null && pmBaseinfo.getFinalFinishDate().isBefore(pmBaseinfo.getPlanFinishDate())) {
                advanceFinishNum++;
            }
            */
            if (pmBaseinfo.getFinalFinishDate() != null && pmBaseinfo.getPlanFinishDate() != null && pmBaseinfo.getFinalFinishDate().isAfter(pmBaseinfo.getPlanFinishDate())) {
                // 已结案延期
                overTimeNum++;
            } else if (pmBaseinfo.getFinalFinishDate() == null && pmBaseinfo.getPlanFinishDate() != null && LocalDate.now().isAfter(pmBaseinfo.getPlanFinishDate())) {
                // 未结案延期
                overTimeNum++;
            }
            if (pmBaseinfo.getFinalFinishDate() != null && pmBaseinfo.getPlanFinishDate() != null) {
                if(pmBaseinfo.getFinalFinishDate().isAfter(pmBaseinfo.getPlanFinishDate())){
                    if(LocalDate.now().isAfter(pmBaseinfo.getFinalFinishDate())){
                        overTimeDay +=pmBaseinfo.getFinalFinishDate().toEpochDay()- pmBaseinfo.getPlanFinishDate().toEpochDay();
                    }
                }else{
                    advanceDay+=pmBaseinfo.getPlanFinishDate().toEpochDay()- pmBaseinfo.getFinalFinishDate().toEpochDay();
                }

            }
            if(pmBaseinfo.getPlanFinishDate() != null &&  pmBaseinfo.getFinalFinishDate() == null && LocalDate.now().isAfter(pmBaseinfo.getPlanFinishDate())){
                overTimeDay +=LocalDate.now().toEpochDay()- pmBaseinfo.getPlanFinishDate().toEpochDay();
            }
            if(pmBaseinfo.getPlanFinishDate() != null &&  pmBaseinfo.getFinalFinishDate() == null && LocalDate.now().isBefore(pmBaseinfo.getPlanFinishDate())){
               if(pmBaseinfo.getPlanFinishDate().toEpochDay()-LocalDate.now().toEpochDay()<7){
                   preOverTimeNum ++;
               }
            }


        }
        projectInfoDto.setNotStartedNum(notStartedNum);
        projectInfoDto.setProcessingNum(processingNum);
        projectInfoDto.setSumNum(PmBaseinfoList.size());
        projectInfoDto.setFinishNum(finishNum);
        projectInfoDto.setPauseNum(pauseNum);
//        projectInfoDto.setAdvanceFinishNum(advanceFinishNum);
        projectInfoDto.setOverTimeNum(overTimeNum);
        projectInfoDto.setAdvanceDay(advanceDay);
        projectInfoDto.setOverTimeDay(overTimeDay);
        projectInfoDto.setPreOverTimeNum(preOverTimeNum);
        return projectInfoDto;
    }

    private String CurrentPeriod(PmBaseinfo pmBaseinfo) {
        String currentPeriod = "未开始";
        findByIdForSchedule(pmBaseinfo.getId());
        // 1、验收阶段
        if (null != pmBaseinfo.getStatus() && pmBaseinfo.getStatus().equals("1")) {

        } else if (null != pmBaseinfo.getTest() && pmBaseinfo.getTest()) {
            // 2、测试结果

        } else if ((null != pmBaseinfo.getCodeReview() && pmBaseinfo.getTest()) || (null != pmBaseinfo.getWebReview() && pmBaseinfo.getWebReview())) {
            // 3、前后端

        } else if (null != pmBaseinfo.getUiReview() && pmBaseinfo.getUiReview()) {
            // 4、UI设计

        } else if (null != pmBaseinfo.getRequireReview() && pmBaseinfo.getRequireReview()) {
            // 4、需求规划

        }
        return currentPeriod;
    }

    /**
     * 项目进度表统计
     * @param search
     * @return
     */
    public List<ProScheduleReportDTO> getProScheduleReport(Search search) {
        List<ProScheduleReportDTO> proScheduleReportDTOS = new ArrayList<>();
        List<PmBaseinfo> proScheduleReport = findByFilters(search);
        for (PmBaseinfo pmBaseinfo : proScheduleReport) {
            ProScheduleReportDTO reportDto = dtoModelMapper.map(pmBaseinfo, ProScheduleReportDTO.class);
            // 项目类型转换
            if(StringUtils.isNotBlank(reportDto.getProjectTypes())){
                String enumItemRemark = EnumUtils.getEnumItemRemark(ProjectTypes.class, Integer.valueOf(reportDto.getProjectTypes()));
                reportDto.setProjectTypes(enumItemRemark);
            }
            // 判断项目进度
            reportDto.setProStart("1");
            String proOpt = null;
            if(StringUtils.isBlank(pmBaseinfo.getProOptId())){
                if(StringUtils.isBlank(pmBaseinfo.getProOpt())){
                    // 没有配置任何流程
                    reportDto.setRequirement("/");
                    reportDto.setDevelopment("/");
                    reportDto.setTest("/");
                    reportDto.setPromote("/");
                    reportDto.setFinish("/");
                    reportDto.setCompletionRate(BigDecimal.valueOf(100));
                }else {
                    proOpt = pmBaseinfo.getProOpt();
                    dealWithProgress(proOpt,pmBaseinfo,reportDto);
                }
            }else {
                proOpt = pmBaseinfo.getPmProjectOption().getProOpt();
                dealWithProgress(proOpt,pmBaseinfo,reportDto);
            }
            proScheduleReportDTOS.add(reportDto);
        }
        // 合计列
        ProScheduleReportDTO sumDTO = new ProScheduleReportDTO();
        List<Integer> requireMentList = proScheduleReportDTOS.stream().filter(a -> !a.getRequirement().equals("/")).map(a -> Integer.valueOf(a.getRequirement())).collect(Collectors.toList());
        List<Integer> developList = proScheduleReportDTOS.stream().filter(a -> !a.getDevelopment().equals("/")).map(a -> Integer.valueOf(a.getDevelopment())).collect(Collectors.toList());
        List<Integer> testList = proScheduleReportDTOS.stream().filter(a -> !a.getTest().equals("/")).map(a -> Integer.valueOf(a.getTest())).collect(Collectors.toList());
        List<Integer> promoteList = proScheduleReportDTOS.stream().filter(a -> !a.getPromote().equals("/")).map(a -> Integer.valueOf(a.getPromote())).collect(Collectors.toList());
        List<Integer> finishList = proScheduleReportDTOS.stream().filter(a -> !a.getFinish().equals("/")).map(a -> Integer.valueOf(a.getFinish())).collect(Collectors.toList());
        BigDecimal req = requireMentList.size() == 0 ? BigDecimal.ZERO :BigDecimal.valueOf(requireMentList.stream().filter(a -> a == 1).count()).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(requireMentList.size()), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal dev = developList.size() == 0 ? BigDecimal.ZERO : BigDecimal.valueOf(developList.stream().filter(a -> a == 1).count()).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(developList.size()), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal tes = testList.size() == 0 ? BigDecimal.ZERO : BigDecimal.valueOf(testList.stream().filter(a -> a == 1).count()).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(testList.size()), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal pro = promoteList.size() == 0 ? BigDecimal.ZERO : BigDecimal.valueOf(promoteList.stream().filter(a -> a == 1).count()).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(promoteList.size()), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal fin = finishList.size() == 0 ? BigDecimal.ZERO : BigDecimal.valueOf(finishList.stream().filter(a -> a == 1).count()).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(finishList.size()), 2, BigDecimal.ROUND_HALF_UP);
        sumDTO.setId("sum");
        sumDTO.setProStart("100%");
        sumDTO.setRequirement(req + "%");
        sumDTO.setDevelopment(dev + "%");
        sumDTO.setTest(tes + "%");
        sumDTO.setPromote(pro + "%");
        sumDTO.setFinish(fin + "%");
        sumDTO.setCompletionRate((fin.add(pro).add(tes).add(dev).add(req).add(BigDecimal.valueOf(100))).divide(BigDecimal.valueOf(6),2,BigDecimal.ROUND_HALF_UP));
        proScheduleReportDTOS.add(sumDTO);
        return proScheduleReportDTOS;
    }

    /**
     * 项目进度表：处理项目进度显示
     * @param proOpt 流程配置
     * @param pmBaseinfo 项目信息
     * @param reportDto 返回dto
     */
    private void dealWithProgress(String proOpt,PmBaseinfo pmBaseinfo,ProScheduleReportDTO reportDto){
        Map<String, List<String>> proOptMap = Arrays.asList(proOpt.split(",")).stream().collect(Collectors.groupingBy(a -> a.substring(0,1)));
        ProjectProgress anEnum = null;
        if(StringUtils.isNotBlank(pmBaseinfo.getCurrentPeriod())){
            anEnum = EnumUtils.getEnumByRemark(ProjectProgress.class, "UI设计".equals(pmBaseinfo.getCurrentPeriod()) ? "需求分析" : pmBaseinfo.getCurrentPeriod());
        }
        // 项目提案默认完成
        BigDecimal finishNum = BigDecimal.ONE;
        BigDecimal allNum = BigDecimal.ONE;

        for (int i = 2;i <= 7 ;i++) {
            if(!proOptMap.containsKey(String.valueOf(i))){
                switch (i){
                    case 2:
                        reportDto.setRequirement("/");
                        break;
                    case 3:
                        if(StringUtils.isBlank(reportDto.getRequirement())){
                            reportDto.setRequirement("/");
                        }
                        break;
                    case 4:
                        reportDto.setDevelopment("/");
                        break;
                    case 5:
                        reportDto.setTest("/");
                        break;
                    case 6:
                        reportDto.setPromote("/");
                        break;
                    case 7:
                        reportDto.setFinish("/");
                        break;
                }
            }else {
                String result;
                if(ObjectUtils.isEmpty(anEnum)){
                    result = "0";
                }else {
                    result = anEnum.ordinal()+1 >= i ? "1" : "0";
                    allNum = allNum.add(BigDecimal.ONE);
                    if("1".equals(result)){
                        finishNum = finishNum.add(BigDecimal.ONE);
                    }
                }
                switch (i){
                    case 2:
                        reportDto.setRequirement(result);
                        break;
                    case 3:
                        if(StringUtils.isBlank(reportDto.getRequirement())){
                            reportDto.setRequirement(result);
                        }
                        break;
                    case 4:
                        reportDto.setDevelopment(result);
                        break;
                    case 5:
                        reportDto.setTest(result);
                        break;
                    case 6:
                        reportDto.setPromote(result);
                        break;
                    case 7:
                        reportDto.setFinish(result);
                        break;
                }
            }
        }
        reportDto.setCompletionRate(finishNum.multiply(BigDecimal.valueOf(100)).divide(allNum,2,BigDecimal.ROUND_HALF_UP) );
    }

    /**
     * 项目进度表统计
     * @param search
     * @return
     */
    public List<YearProjectReportDTO> getYearProjectReport(Search search) {
        List<SearchFilter> filters = search.getFilters();
        // 处理科室
        List<PmOrganize> orgList = pmOrganizeService.getChildrenNodesByName("数字化管理中心").stream()
                .filter(a -> !a.getFrozen() &&  ("系统运维管理部".equals(a.getName()) || a.getNodeLevel() == 3))
                .collect(Collectors.toList());
        List<YearProjectReportDTO> resultList = new ArrayList<>();
        for (SearchFilter filter : filters) {
            if("orgname".equals(filter.getFieldName())){
                orgList = orgList.stream().filter(a -> (filter.getValue()).equals(a.getName()))
                        .collect(Collectors.toList());
            }
        }
        List<PmBaseinfo> baseinfoList = findByFilters(search);
        for (PmOrganize pmOrganize : orgList) {
            List<PmBaseinfo> pmBaseinfoList = baseinfoList.stream()
                    .filter(info -> pmOrganize.getName().equals(info.getOrgname()))
                    .collect(Collectors.toList());
            // 科室名称、科室负责人
            YearProjectReportDTO yearProjectReportDTO = dtoModelMapper.map(pmOrganize, YearProjectReportDTO.class);
            // 结案数量
            long finishNum = pmBaseinfoList.stream()
                    .filter(a -> "项目结案".equals(a.getCurrentPeriod()) && pmOrganize.getName().equals(a.getOrgname()))
                    .count();
            // 暂停数量
            long pauseNum = pmBaseinfoList.stream().filter(a -> a.getIsPause() == true).count();
            // 项目总数
            long totalNum = pmBaseinfoList.size();
            // 未结案数量
            long notFinish = totalNum - pauseNum - finishNum;
            long notFinishNum = notFinish >= 0 ? notFinish : 0;
            yearProjectReportDTO.setFinishNum(finishNum);
            yearProjectReportDTO.setNotFinishNum(notFinishNum);
            yearProjectReportDTO.setPauseNum(pauseNum);
            yearProjectReportDTO.setTotalNum(totalNum);
            resultList.add(yearProjectReportDTO);
        }
        return resultList;
    }
}
