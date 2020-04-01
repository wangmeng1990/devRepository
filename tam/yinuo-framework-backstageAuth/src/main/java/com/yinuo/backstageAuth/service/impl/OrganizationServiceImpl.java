package com.yinuo.backstageAuth.service.impl;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.inuol.entity.backstageAuth.Organization;
import com.yinuo.backstageAuth.mapper.OrganizationMapper;
import com.yinuo.common.common.HttpResult;
import com.inuol.vo.backstageAuth.OrganizationVo;
import com.yinuo.backstageAuth.service.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Capejor
 * @date 2020-01-09 14:30
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public HttpResult insertOrganization(OrganizationVo vo) {
        Organization organization = new Organization();
        BeanUtils.copyProperties(vo, organization);
        organization.setId(IdWorker.getId());
        organization.setCreateTime(new Date());
        organization.setCreator(1L);
        return HttpResult.retCol(organizationMapper.insertSelective(organization));
    }

    @Override
    public HttpResult updateOrganization(OrganizationVo vo) {
        Organization organization = new Organization();
        BeanUtils.copyProperties(vo, organization);
        organization.setUpdateTime(new Date());
        return HttpResult.retCol(organizationMapper.updateByPrimaryKeySelective(organization));
    }

    @Override
    public HttpResult selectOrgTree() {
        List<Map<String, Object>> childList = new ArrayList<>();
        //查询顶级
        List<Map<String, Object>> topList = organizationMapper.selectTopRog();
        if (topList != null && topList.size() > 0) {
            for (Map<String, Object> topMap : topList) {
                Map<String, Object> childMap = new HashMap<>();
                // 查询当前部门下的员工数
                //int count = organizationMapper.selectOrgCountByParentId((String) topMap.get("id"));
                childMap.put("id", topMap.get("id"));
                childMap.put("label", topMap.get("orgName"));
                //childMap.put("label", topMap.get("orgName") + "(" + count + "人)");
                childMap.put("parentId", topMap.get("parentId"));
                setOrgTree(childMap);
                childList.add(childMap);
            }
        }
        return HttpResult.success(childList);
    }

    //递归
    public void setOrgTree(Map<String, Object> map) {
        List<Map<String, Object>> childList = new ArrayList<>();
        map.put("children", childList);
        String parentId = map.get("id").toString();
        List<Map<String, Object>> orgList = organizationMapper.selectOrgByParentId(parentId);
        if (orgList != null && orgList.size() > 0) {
            for (Map<String, Object> OrgMap : orgList) {
                Map<String, Object> childMap = new HashMap<>();
                // 查询当前部门下的用户数
                //int count = organizationMapper.selectOrgCountByParentId((String) deptMap.get("id"));
                childMap.put("id", OrgMap.get("id"));
                childMap.put("label", OrgMap.get("orgName"));
                //childMap.put("label", OrgMap.get("orgName") + "(" + count + "人)");
                childMap.put("parentId", OrgMap.get("parentId"));
                setOrgTree(childMap);
                childList.add(childMap);
            }
        }
    }

    @Override
    public HttpResult selectOrgByParentId(String id) {
        if("-1".equals(id)){
            return HttpResult.success(organizationMapper.selectAllOrgExceptTop());
        }else {
            return HttpResult.success(organizationMapper.selectOrgByParentId(id));
        }
    }

    @Override
    public HttpResult deleteOneById(Long id) {
        Organization organization = new Organization();
        organization.setId(id);
        organization.setState("0");
        return HttpResult.retCol(organizationMapper.updateByPrimaryKey(organization));
    }

    @Override
    public int selectOrgCountByParentId(Long id) {
        return organizationMapper.selectOrgCountByParentId(id);
    }


}
