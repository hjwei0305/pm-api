package com.donlim.pm.service;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.dao.BaseTreeDao;
import com.changhong.sei.core.service.BaseTreeService;
import com.donlim.pm.connector.HRMSConnector;
import com.donlim.pm.dao.PmOrganizeDao;
import com.donlim.pm.dto.OrgDTO;
import com.donlim.pm.entity.PmOrganize;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * (PmOrganize)业务逻辑实现类
 *
 * @author sei
 * @since 2022-08-11 14:05:49
 */
@Service
public class PmOrganizeService extends BaseTreeService<PmOrganize> {
    @Autowired
    private PmOrganizeDao dao;

    @Override
    protected BaseTreeDao<PmOrganize> getDao() {
        return dao;
    }

    /**
     * 同步/更新组织信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void synOrg(){
        List<OrgDTO.DataDTO> orgList = HRMSConnector.getOrg();
        List<PmOrganize> allList = findAll();
        ArrayList<PmOrganize> saveList = new ArrayList<>();
        for (OrgDTO.DataDTO dataDTO : orgList) {
            // 对比信息
            List<PmOrganize> pmOrganizes = allList.stream()
                    .filter(org -> org.getCode().equals(dataDTO.getCode()))
                    .collect(Collectors.toList());
            if(pmOrganizes.size()>0){
                // 更新
                PmOrganize pmOrganize  = pmOrganizes.get(0);
                pmOrganize.setFrozen(false);
                saveList.add(pmOrganize);
            }else {
                // 新增
                PmOrganize pmOrganize = new PmOrganize();
                pmOrganize.setOrgid(dataDTO.getOrgid());
                pmOrganize.setCode(dataDTO.getCode());
                pmOrganize.setName(dataDTO.getOrgname());
                pmOrganize.setExtorgname(dataDTO.getExtorgname());
                pmOrganize.setSuperid(dataDTO.getSuperid());
                pmOrganize.setIdpath(dataDTO.getIdpath());
                pmOrganize.setFrozen(false);
                saveList.add(pmOrganize);
            }
        }
        for (PmOrganize org : allList) {
            if(saveList.stream().filter(o -> org.getCode().equals(o.getCode())).collect(Collectors.toList()).size() == 0 ){
                org.setFrozen(true);
                saveList.add(org);
            }
        }
        save(saveList);
        saveParentId();
    }

    private void saveParentId() {
        List<PmOrganize> allList = findAll();
        ArrayList<PmOrganize> saveList = new ArrayList<>();
        allList.stream().forEach(org -> {
            List<PmOrganize> parentOrg = allList.stream().filter(allOrg -> allOrg.getOrgid().equals(org.getSuperid())).collect(Collectors.toList());
            if(parentOrg.size() > 0){
                org.setParentId(parentOrg.get(0).getId());
                saveList.add(org);
            }
        });
        save(saveList);
    }

    /**
     * 获取组织机构树
     * @return
     */
    public PmOrganize findTree(){
        String tenantCode = ContextUtil.getTenantCode();
        List<PmOrganize> rootNodes = getAllRootNode();
        Optional<PmOrganize> first = rootNodes.stream()
                .filter(tempRoot -> StringUtils.equals(tenantCode, tempRoot.getTenantCode()))
                .sorted(Comparator.comparing(PmOrganize::getCode)).findFirst();
        if (first.isPresent()){
            PmOrganize pmOrganize = first.get();
            return getTree(pmOrganize.getId());
        }
        return null;
    }
}
