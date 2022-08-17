package com.donlim.pm.service;

import com.alibaba.fastjson.JSONObject;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.edm.sdk.DocumentManager;
import com.donlim.pm.connector.IppConnector;
import com.donlim.pm.dao.PmBaseinfoDao;
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

import javax.xml.soap.Node;
import java.time.LocalDate;
import java.util.HashMap;
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

    @Override
    protected BaseEntityDao<PmBaseinfo> getDao() {
        return dao;
    }


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
                documentManager.bindBusinessDocuments(dto.getId(), dto.getAttachmentIdList());
                //首字母转小写并加上Id
                String fieldName = dto.getFileType().substring(0, 1).toLowerCase() + dto.getFileType().substring(1) + "Id";
                //根据不同的类别记录附件的ID
                ReflectUtils.setFieldValue(byId.get(), fieldName, dto.getAttachmentIdList().get(0));
                save(byId.get());
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void syncIppInfo(LocalDate localDate) {
        List<PmBaseinfo> pmBaseinfoList = IppConnector.getPorjectInfo(localDate);
        List<String> codeList = pmBaseinfoList.stream().map(PmBaseinfo::getCode).collect(Collectors.toList());
        List<PmBaseinfo> allByCodeInList = dao.findAllByCodeIn(codeList);
        //更新的附上ID
        for (PmBaseinfo pmBaseinfo : pmBaseinfoList) {
            Optional<PmBaseinfo> first = allByCodeInList.stream().filter(a -> a.getCode().equals(pmBaseinfo.getCode())).findFirst();
            if (first.isPresent()) {
                pmBaseinfo.setId(first.get().getId());
            }
        }
        dao.save(pmBaseinfoList);
    }

    /**
     * @return
     */
    public void findById(String id) {
        Optional<PmBaseinfo> byId = dao.findById(id);
        if (byId.isPresent()) {
            PmBaseinfo pmBaseinfo = byId.get();
            ModelMapper dtoModelMapper = new ModelMapper();
            PmBaseinfoDto pmBaseinfoDto = dtoModelMapper.map(pmBaseinfo, PmBaseinfoDto.class);
            HashMap<String, String> map = new HashMap<>();
            boolean flag = true;
            //2.1调研
            if (!StringUtils.isEmpty(pmBaseinfoDto.getRequireDocId()) && !StringUtils.isEmpty(pmBaseinfoDto.getAcceptStandardDocId())) {
                map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Research), EnumUtils.getNodeTypeRemark(NodeType.Pass));
            } else {
                map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Research), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                flag = false;
            }
            //2.2启动会议
            if (flag) {
                if (!StringUtils.isEmpty(pmBaseinfoDto.getStartReportDocId())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.StartMeeting), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.StartMeeting), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
            }
            //2.3提案系统
            if (flag) {
                if (!StringUtils.isEmpty(pmBaseinfoDto.getUserRequireDocId())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.SubmitSystem), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.SubmitSystem), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
            }
            //2.4需求评审
            if (flag) {
                if (!StringUtils.isEmpty(pmBaseinfoDto.getRequireReview())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.RequireReview), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.RequireReview), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
            }
            //3.1ui设计
            if (flag) {
                if (!StringUtils.isEmpty(pmBaseinfoDto.getDesignerDocId()) && !StringUtils.isEmpty(pmBaseinfoDto.getCropDocId())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.UIDesigner), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.UIDesigner), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
            }
            //3.2ui评审
            if (flag) {
                if (!StringUtils.isEmpty(pmBaseinfoDto.getUiReview())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.UIReview), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.UIReview), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
            }
            //4.1前端开发 4.3后台开发
            if (flag) {
                if (!StringUtils.isEmpty(pmBaseinfoDto.getWebDevDocId())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.WebDev), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.WebDev), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
                if (!StringUtils.isEmpty(pmBaseinfoDto.getCodeDevDocId())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeDev), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeDev), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
            }
            //4.2前端评审 4.4后台评审
            if (flag) {
                if (!StringUtils.isEmpty(pmBaseinfoDto.getWebReview())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.WebReview), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.WebReview), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
                if (!StringUtils.isEmpty(pmBaseinfoDto.getCodeReview())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeReview), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.CodeReview), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }

            }

            //5.1缺陷系统
            if (flag) {
                if (!StringUtils.isEmpty(pmBaseinfoDto.getTestExampleDocId()) && !StringUtils.isEmpty(pmBaseinfoDto.getTestReportDocId())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.TestSystem), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.TestSystem), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
            }
            //5.2测试结果
            if (flag) {
                if (!StringUtils.isEmpty(pmBaseinfoDto.getTest())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Test), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Test), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
            }

            //6.1上线
            if (flag) {
                if (!StringUtils.isEmpty(pmBaseinfoDto.getSopDocId()) && !StringUtils.isEmpty(pmBaseinfoDto.getQuestionListDocId())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Online), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Online), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
            }
            //6.2监控
            if (flag) {
                if (!StringUtils.isEmpty(pmBaseinfoDto.getCheckListDocId())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Monitor), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Monitor), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
            }
            //7.1结案
            if (flag) {
                if (!StringUtils.isEmpty(pmBaseinfoDto.getCaseCloseReportDocId())&& !StringUtils.isEmpty(pmBaseinfoDto.getSatisfactionSurveyDocId())&& !StringUtils.isEmpty(pmBaseinfoDto.getPageCheckDocId())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Close), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Close), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
            }
            //7.2验收
            if (flag) {
                if (!StringUtils.isEmpty(pmBaseinfoDto.getAcceptOrderDocId()) && StringUtils.isEmpty(pmBaseinfoDto.getAccpetReprotDocId())) {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Acceptance), EnumUtils.getNodeTypeRemark(NodeType.Pass));
                } else {
                    map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.Acceptance), EnumUtils.getNodeTypeRemark(NodeType.NoPass));
                    flag = false;
                }
            }
            //7.3完结
            if (flag) {
                map.put(EnumUtils.getSmallNodeRemark(SmallNodeType.End), EnumUtils.getNodeTypeRemark(NodeType.Pass));
            }
            String gtfJson = JSONObject.toJSONString(map);
            pmBaseinfoDto.setGfxJson(gtfJson);
        }

    }


}
