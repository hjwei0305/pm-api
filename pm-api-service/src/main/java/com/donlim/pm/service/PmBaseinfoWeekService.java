package com.donlim.pm.service;

import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.edm.sdk.DocumentManager;
import com.donlim.pm.dao.PmBaseinfoWeekDao;
import com.donlim.pm.dto.PmBaseinfoWeekDto;
import com.donlim.pm.dto.WeekReportDto;
import com.donlim.pm.em.ProjectTypes;
import com.donlim.pm.entity.PmBaseinfo;
import com.donlim.pm.entity.PmBaseinfoWeek;
import com.donlim.pm.util.EnumUtils;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * 项目周计划(PmBaseinfoWeek)业务逻辑实现类
 *
 * @author sei
 * @since 2023-05-08 13:58:50
 */
@Service
public class PmBaseinfoWeekService extends BaseEntityService<PmBaseinfoWeek> {
    @Autowired
    private PmBaseinfoWeekDao dao;
    @Autowired
    private DocumentManager documentManager;
    @Autowired
    private PmBaseinfoService pmBaseinfoService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    protected BaseEntityDao<PmBaseinfoWeek> getDao() {
        return dao;
    }

    /**
     * 绑定上传双周计划附件列表
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void bindFileList(PmBaseinfoWeekDto dto) {
        // 新建 -- id为空时，不做绑定
        if (!StringUtils.isEmpty(dto.getId()) && !CollectionUtils.isEmpty(dto.getAttachmentIdList())) {
            Optional<PmBaseinfoWeek> byId = dao.findById(dto.getId());
            if (byId.isPresent()) {
                // 实体id绑定附件
                documentManager.bindBusinessDocuments(dto.getId(), dto.getAttachmentIdList());
            }
        } else if (!StringUtils.isEmpty(dto.getId()) && CollectionUtils.isEmpty(dto.getAttachmentIdList())) {
            // 附件list为空，清空绑定
            documentManager.deleteBusinessInfos(dto.getId());
        }
    }

    public List<WeekReportDto> getWeekReport(Search search) {
        List<PmBaseinfo> pmBaseinfoList = pmBaseinfoService.findByFilters(search);
        TypeMap<PmBaseinfo, WeekReportDto> typeMap = modelMapper.typeMap(PmBaseinfo.class, WeekReportDto.class);
        List<WeekReportDto> weekReportDtoList = pmBaseinfoList.stream().map(typeMap::map).collect(Collectors.toList());
        // 项目类型转换
        for (WeekReportDto weekReportDto : weekReportDtoList) {
            if(StringUtils.isNotEmpty(weekReportDto.getProjectTypes())){
                String enumItemRemark = EnumUtils.getEnumItemRemark(ProjectTypes.class, Integer.valueOf(weekReportDto.getProjectTypes()));
                weekReportDto.setProjectTypes(enumItemRemark);
            }
        }
        // 双周计划根据项目id分组
        Map<String, List<PmBaseinfoWeek>> weekMap = findAll().stream()
                .sorted(Comparator.comparing(PmBaseinfoWeek::getCreatedDate,Comparator.reverseOrder()))
                .collect(Collectors.groupingBy(PmBaseinfoWeek::getBaseinfoId));
        weekReportDtoList.forEach(week -> {
            if(weekMap.keySet().contains(week.getId())){
                // 项目所有周计划
                List<PmBaseinfoWeek> pmBaseinfoWeeks = weekMap.get(week.getId());
                if(!CollectionUtils.isEmpty(pmBaseinfoWeeks)){
                    PmBaseinfoWeek pmBaseinfoWeek = pmBaseinfoWeeks.get(0);
                    week.setWeekId(pmBaseinfoWeek.getId());
                    week.setLastEditedDate(pmBaseinfoWeek.getLastEditedDate());
                    week.setLastModifiedTime(pmBaseinfoWeek.getLastModifiedTime());
                    week.setFinishPlan(pmBaseinfoWeek.getFinishPlan());
                }
            }else{
                // 未有新的双周计划，更新时间 null
                week.setLastEditedDate(null);
                week.setLastModifiedTime(null);
                week.setFinishPlan(null);
            }
        });
        return weekReportDtoList;
    }

    @Transactional(rollbackFor = Exception.class)
    public PmBaseinfoWeek confirmFinishPlan(PmBaseinfoWeekDto dto) {
        PmBaseinfoWeek pmBaseinfoWeek = findByProperty("id", dto.getId());
        if(!ObjectUtils.isEmpty(pmBaseinfoWeek)){
            pmBaseinfoWeek.setFinishPlan(dto.getFinishPlan());
            dao.updateFinishPlan(dto.getId(), dto.getFinishPlan());
            return pmBaseinfoWeek;
        }
        return null;
    }
}
