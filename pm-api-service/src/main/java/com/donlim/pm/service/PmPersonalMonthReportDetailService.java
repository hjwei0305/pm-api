package com.donlim.pm.service;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dao.BaseEntityDao;
import com.changhong.sei.core.dto.BaseEntityDto;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.changhong.sei.exception.SeiException;
import com.donlim.pm.dao.PmPersonalMonthReportDao;
import com.donlim.pm.dao.PmPersonalMonthReportDetailDao;
import com.donlim.pm.dao.ProjectPlanDao;
import com.donlim.pm.dto.PmPersonalMonthReportDetailDto;
import com.donlim.pm.entity.PmEmployee;
import com.donlim.pm.entity.PmPersonalMonthReport;
import com.donlim.pm.entity.PmPersonalMonthReportDetail;
import com.donlim.pm.entity.ProjectPlan;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 个人月计划明细(PmPersonalMonthReportDetail)业务逻辑实现类
 *
 * @author sei
 * @since 2023-04-24 16:51:33
 */
@Service
public class PmPersonalMonthReportDetailService extends BaseEntityService<PmPersonalMonthReportDetail> {
    @Autowired
    private PmPersonalMonthReportDetailDao dao;
    @Autowired
    private ProjectPlanDao projectPlanDao;
    @Autowired
    private PmPersonalMonthReportDao pmPersonalMonthReportDao;
    @Autowired
    private PmPersonalMonthReportService pmPersonalMonthReportService;
    @Autowired
    private PmEmployeeService pmEmployeeService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    protected BaseEntityDao<PmPersonalMonthReportDetail> getDao() {
        return dao;
    }

    /**
     * 手动获取项目计划（除主计划）
     * @param ym
     * @return
     * @throws SeiException
     */
    public List<PmPersonalMonthReportDetail> createPersonalMonthPlan(String ym) throws SeiException {
        ArrayList<PmPersonalMonthReportDetail> resultList = new ArrayList<>();
        // 姓名
        String userName = ContextUtil.getUserName();
//        userName = "张晓橦";
//        //是否已有重复数据
//        Optional<PmPersonalMonthReport> personalMonthReport = pmPersonalMonthReportDao.getFirstByEmployeeNameAndYm(userName, ym);
//        if(personalMonthReport.isPresent()){
//            throw new SeiException("已有该月计划！请别重复生成！");
//        }
        Calendar instance = Calendar.getInstance();
        String[] split = ym.split("-");
        // 第一天
        String firstDay = ym + "-01";
        // 最后一天
        instance.set(Integer.valueOf(split[0]),Integer.valueOf(split[1]),1);
        instance.add(Calendar.DATE,-1);
        String lastDay = new SimpleDateFormat("yyyy-MM-dd").format(instance.getTime());
        LocalDate firstLocalDate = LocalDate.parse(firstDay, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate lastLocalDate = LocalDate.parse(lastDay, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<ProjectPlan> allPlan = projectPlanDao.getAllByWorkOndutyContainsAndPlanTypeNotAndPlanStartDateIsNotNullAndPlanEndDateIsNotNull(userName, "0");
        List<ProjectPlan> collect = allPlan.stream()
                .filter(a -> a.getPlanStartDate().compareTo(lastLocalDate) <= 0 && a.getPlanEndDate().compareTo(firstLocalDate) >= 0)
                .sorted(Comparator.comparing(ProjectPlan::getProjectId))
                .collect(Collectors.toList());
        int no = 1;
        for (ProjectPlan projectPlan : collect) {
            PmPersonalMonthReportDetail entity = modelMapper.map(projectPlan, PmPersonalMonthReportDetail.class);
            entity.setId(null);
            entity.setProjectName(projectPlan.getPmBaseinfo().getName());
            entity.setType("B");
//            entity.setWorkHours(ObjectUtils.isEmpty(entity.getSchedureDays()) ? 0 : entity.getSchedureDays()*8);
            entity.setScheNo(no++);
            entity.setAutoGenerate(true);
            entity.setComplete(2);
            entity.setRealWorkHours(0);
            entity.setRealWorkHoursRate(BigDecimal.ZERO);
//            entity.setPlanDays((int)(entity.getPlanEndDate().toEpochDay() - (entity.getPlanStartDate().toEpochDay())) + 1);
            resultList.add(entity);
        }
        // 计算工作占比
//        BigDecimal sumHours = resultList.stream().map(a -> BigDecimal.valueOf(a.getWorkHours())).reduce(BigDecimal.ZERO, BigDecimal::add);
//        for (PmPersonalMonthReportDetail detail : resultList) {
//            detail.setWorkHouresRate(BigDecimal.valueOf(detail.getWorkHours()).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(188),2,BigDecimal.ROUND_HALF_UP));
//        }

        return resultList;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<PmPersonalMonthReportDetail> monthPlanSaveBatch(List<PmPersonalMonthReportDetailDto> monthReportDetailDtoList) {
        ArrayList<PmPersonalMonthReportDetail> detailList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(monthReportDetailDtoList) && StringUtils.isNotBlank(monthReportDetailDtoList.get(0).getYm())){
            String userName = ContextUtil.getUserName();
            String userAccount = ContextUtil.getUserAccount();
//            userName = "张晓橦";
//            userAccount = "376951";
            // 计划id非空，从详情进入修改
            if(StringUtils.isNotBlank(monthReportDetailDtoList.get(0).getPersonalMonthReportId())){
                PmPersonalMonthReport personalMonthReport = pmPersonalMonthReportService.findByProperty("id", monthReportDetailDtoList.get(0).getPersonalMonthReportId());
                if(!personalMonthReport.getEmployeeName().equals(userName)){
                    throw new SeiException("不能修改他人计划！");
                }
                dealPersonalMonthReport(personalMonthReport,monthReportDetailDtoList);
                pmPersonalMonthReportService.save(personalMonthReport);
                // 去除删除的明细
                List<String> oldIds = findByFilter(new SearchFilter("personalMonthReportId", personalMonthReport.getId(), SearchFilter.Operator.EQ)).stream().map(PmPersonalMonthReportDetail::getId).collect(Collectors.toList());
                oldIds.removeAll(monthReportDetailDtoList.stream().map(BaseEntityDto::getId).collect(Collectors.toList()));
                delete(oldIds);
                for (PmPersonalMonthReportDetailDto detailDto : monthReportDetailDtoList) {
                    detailDto.setPersonalMonthReportId(personalMonthReport.getId());
                    detailList.add(modelMapper.map(detailDto,PmPersonalMonthReportDetail.class));
                }
            }else {
                // 计划id为空，判断是否重复创建
                Optional<PmPersonalMonthReport> personalMonthReportOptional = pmPersonalMonthReportDao.getFirstByEmployeeNameAndYm(userName, monthReportDetailDtoList.get(0).getYm());
                if(personalMonthReportOptional.isPresent()){
                    PmPersonalMonthReport pmPersonalMonthReport = personalMonthReportOptional.get();
                    dealPersonalMonthReport(pmPersonalMonthReport,monthReportDetailDtoList);
                    pmPersonalMonthReportService.save(pmPersonalMonthReport);
                    // 明细覆盖
                    List<String> oldIds = findByFilter(new SearchFilter("personalMonthReportId", pmPersonalMonthReport.getId(), SearchFilter.Operator.EQ)).stream().map(PmPersonalMonthReportDetail::getId).collect(Collectors.toList());
                    delete(oldIds);
                    for (PmPersonalMonthReportDetailDto detailDto : monthReportDetailDtoList) {
                        detailDto.setPersonalMonthReportId(pmPersonalMonthReport.getId());
                        detailList.add(modelMapper.map(detailDto,PmPersonalMonthReportDetail.class));
                    }
                }else {
                    PmPersonalMonthReport personalMonthReport = new PmPersonalMonthReport();
                    PmEmployee employeeCode = pmEmployeeService.findByProperty("employeeCode", userAccount);
                    personalMonthReport.setEmployeeCode(userAccount);
                    personalMonthReport.setEmployeeName(userName);
                    personalMonthReport.setGroupName(employeeCode.getOrgname());
                    personalMonthReport.setYm(monthReportDetailDtoList.get(0).getYm());
                    dealPersonalMonthReport(personalMonthReport,monthReportDetailDtoList);
                    OperateResultWithData<PmPersonalMonthReport> saveResult = pmPersonalMonthReportService.save(personalMonthReport);
                    PmPersonalMonthReport saveMonthReport = saveResult.getData();
                    for (PmPersonalMonthReportDetailDto detailDto : monthReportDetailDtoList) {
                        detailDto.setPersonalMonthReportId(saveMonthReport.getId());
                        detailList.add(modelMapper.map(detailDto,PmPersonalMonthReportDetail.class));
                    }
                }
            }
            save(detailList);
            detailList.sort(Comparator.comparingInt(PmPersonalMonthReportDetail::getScheNo));
            return detailList;
        }
        return null;
    }

    /**
     * 计算主表数据
     * @param personalMonthReport
     * @param monthReportDetailDtoList
     */
    private void dealPersonalMonthReport(PmPersonalMonthReport personalMonthReport, List<PmPersonalMonthReportDetailDto> monthReportDetailDtoList) {
        personalMonthReport.setTotalNum(monthReportDetailDtoList.size());
        // complete ：1 完成 2 未完成（计算逾期） 3 空白状态（不计算逾期）
        personalMonthReport.setFinishNum((int)monthReportDetailDtoList.stream().filter(a -> 1 == a.getComplete()).map(PmPersonalMonthReportDetailDto::getComplete).count());
        personalMonthReport.setOvertimeNum((int)monthReportDetailDtoList.stream().filter(a -> 0 == a.getComplete()).map(PmPersonalMonthReportDetailDto::getComplete).count());
        personalMonthReport.setCompeletionRate(
                BigDecimal.valueOf(personalMonthReport.getFinishNum())
                        .multiply(BigDecimal.valueOf(100))
                        .divide(BigDecimal.valueOf(personalMonthReport.getTotalNum()),2,BigDecimal.ROUND_HALF_UP));
        // 实际工时、占比（改用手工数据）
        personalMonthReport.setWorkHours(monthReportDetailDtoList.stream().filter(a -> !ObjectUtils.isEmpty(a.getRealWorkHours())).map(a -> BigDecimal.valueOf(a.getRealWorkHours())).reduce(BigDecimal.ZERO,BigDecimal::add).intValue());
        personalMonthReport.setWorkHouresRate(
                BigDecimal.valueOf(personalMonthReport.getWorkHours())
                        .multiply(BigDecimal.valueOf(100))
                        .divide(BigDecimal.valueOf(188),0,BigDecimal.ROUND_HALF_UP));
//        // 实际工时、占比
//        personalMonthReport.setWorkHours(monthReportDetailDtoList.stream().map(a -> BigDecimal.valueOf(a.getWorkHours())).reduce(BigDecimal.ZERO,BigDecimal::add).intValue());
//        personalMonthReport.setWorkHouresRate(
//                BigDecimal.valueOf(personalMonthReport.getWorkHours())
//                        .multiply(BigDecimal.valueOf(100))
//                        .divide(BigDecimal.valueOf(188),0,BigDecimal.ROUND_HALF_UP));
//        // 计划工时、占比
//        personalMonthReport.setPlanHours(monthReportDetailDtoList.stream().map(a -> BigDecimal.valueOf(a.getPlanDays())).reduce(BigDecimal.ZERO,BigDecimal::add).intValue() * 8);
//        personalMonthReport.setPlanHoursRate(
//                BigDecimal.valueOf(personalMonthReport.getPlanHours())
//                        .multiply(BigDecimal.valueOf(100))
//                        .divide(BigDecimal.valueOf(188),0,BigDecimal.ROUND_HALF_UP));
    }
}
