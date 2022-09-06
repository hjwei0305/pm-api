package com.donlim.pm.service;

import com.alibaba.fastjson.JSONObject;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.edm.dto.DocumentDto;
import com.changhong.sei.edm.sdk.DocumentManager;
import com.donlim.pm.connector.EipConnector;
import com.donlim.pm.connector.IppConnector;
import com.donlim.pm.constant.IppConstant;
import com.donlim.pm.dao.PmBaseinfoDao;
import com.donlim.pm.dao.ProjectPlanDao;
import com.donlim.pm.dto.IppProjectInfoDetails;
import com.donlim.pm.dto.PmBaseinfoDto;
import com.donlim.pm.em.NodeType;
import com.donlim.pm.em.SmallNodeType;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.util.EnumUtils;
import com.donlim.pm.util.ReflectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
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
        }
    }

    /**
     * 同步项目基础信息
     *
     * @param code 提案单号
     */
    @Transactional(rollbackFor = Exception.class)
    public PmBaseinfo syncIppInfo(String code) {
        List<PmBaseinfo> pmBaseinfoList = IppConnector.getPorjectInfo(code);
        if (pmBaseinfoList.size() > 0) {
            Optional<PmBaseinfo> byCode = dao.findByCode(code);
            if (byCode.isPresent()) {
                //已经存在
                return null;
            } else {
                // dao.save(pmBaseinfoList);
                return pmBaseinfoList.get(0);
            }
        } else {
            return null;
        }

    }

    /**
     * 定时更新项目状态
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateProjectInfo() {
        //更新尚未结案的项目状态
        List<PmBaseinfo> pmBaseinfoList = dao.findAllByStatus("0");
        for (PmBaseinfo pmBaseinfo:pmBaseinfoList){
            List<IppProjectInfoDetails.TableDTO> list = IppConnector.getPorjectInfoDetails(pmBaseinfo.getCode());
            for (IppProjectInfoDetails.TableDTO data :list){
                if(data.getDevelopWay().equals(IppConstant.UI)){
                    pmBaseinfo.setUiReview(true);
                }else if(data.getDevelopWay().equals(IppConstant.REQUIRE_REVIEW)){
                    pmBaseinfo.setRequireReview(true);
                }else if(data.getDevelopWay().equals(IppConstant.WEB_REVIEW)){
                    pmBaseinfo.setWebReview(true);
                }else if (data.getDevelopWay().equals(IppConstant.CODE_REVIEW)){
                    pmBaseinfo.setCodeReview(true);
                }
            }
            pmBaseinfo.setTest(IppConnector.getTestResult(pmBaseinfo.getCode()));
            pmBaseinfo.setStatus(EipConnector.isFinish(pmBaseinfo.getCode())?"1":"0");
        }
        save(pmBaseinfoList);
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
            String proOpt = pmBaseinfoDto.getProOpt();
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            boolean flag = true;
            //2.1调研
            // 1、包含该节点---判断是否通过  2、不包含节点 -- 直接跳过
            if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.Research))){
                if (!StringUtils.isEmpty(pmBaseinfoDto.getRequireDocId()) && !StringUtils.isEmpty(pmBaseinfoDto.getAcceptStandardDocId())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Research), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Research), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
            }
            //2.2启动会议
            if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.StartMeeting))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getStartReportDocId())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.StartMeeting), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.StartMeeting), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //2.3提案系统
            if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.SubmitSystem))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getUserRequireDocId())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.SubmitSystem), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.SubmitSystem), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //2.4需求评审
            if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.RequireReview))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getRequireReview())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.RequireReview), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.RequireReview), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //3.1ui设计
            if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.UIDesigner))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getDesignerDocId()) && !StringUtils.isEmpty(pmBaseinfoDto.getCropDocId())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.UIDesigner), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.UIDesigner), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //3.2ui评审
            if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.UIReview))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getUiReview())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.UIReview), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.UIReview), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //4.1前端开发 4.3后台开发 填写计划即当完成

                if (flag) {
                    if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.WebDev))) {
                        //获取后端开发计划表
                        if (projectPlanDao.countByProjectIdAndPlanType(pmBaseinfoDto.getId(), PROJECT_WEB_PLAN) > 0) {
                            map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.WebDev), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        } else {
                            map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.WebDev), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                            flag = false;
                        }
                    }
                    if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeDev))) {
                        //获取前端计划表
                        if (projectPlanDao.countByProjectIdAndPlanType(pmBaseinfoDto.getId(), PROJECT_DEV_PLAN) > 0) {
                            map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeDev), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        } else {
                            map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeDev), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                            flag = false;
                        }
                    }
                }

            //4.2前端评审 4.4后台评审

                if (flag) {
                    if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.WebReview))) {
                        if (!StringUtils.isEmpty(pmBaseinfoDto.getWebReview())) {
                            map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.WebReview), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        } else {
                            map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.WebReview), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                            flag = false;
                        }
                    }
                    if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeReview))) {
                        if (!StringUtils.isEmpty(pmBaseinfoDto.getCodeReview())) {
                            map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeReview), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                        } else {
                            map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeReview), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                            flag = false;
                        }
                    }
                }

            //5.1测试用例
            if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.TestSystem))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getTestExampleDocId()) && !StringUtils.isEmpty(pmBaseinfoDto.getTestReportDocId())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.TestSystem), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.TestSystem), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //5.2测试结果
            if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.Test))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getTest())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Test), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Test), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //6.1上线
            if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.Online))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getSopDocId()) && !StringUtils.isEmpty(pmBaseinfoDto.getQuestionListDocId())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Online), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Online), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //6.2监控
            if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.Monitor))) {
                if (flag) {
                    if (!StringUtils.isEmpty(pmBaseinfoDto.getCheckListDocId())) {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Monitor), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                    } else {
                        map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Monitor), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                        flag = false;
                    }
                }
            }
            //7.1结案
            if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.Close))) {
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
            if(proOpt.contains(EnumUtils.getSmallNodeRemark(SmallNodeType.Acceptance))) {
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
            }
            String gtfJson = JSONObject.toJSONString(map);
            pmBaseinfoDto.setGfxJson(gtfJson);
            return pmBaseinfoDto;
        }
        return null;
    }
}
