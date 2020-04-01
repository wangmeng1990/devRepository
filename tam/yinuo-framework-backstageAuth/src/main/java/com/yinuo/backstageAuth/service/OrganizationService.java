package com.yinuo.backstageAuth.service;

import com.yinuo.common.common.HttpResult;
import com.inuol.vo.backstageAuth.OrganizationVo;

/**
 * @author Capejor
 * @date 2020-01-09 14:30
 */
public interface OrganizationService {

    HttpResult insertOrganization(OrganizationVo vo);

    HttpResult updateOrganization(OrganizationVo vo);

    HttpResult selectOrgTree();

   // Result selectAllOrgExceptTop();

    HttpResult selectOrgByParentId(String id);

    HttpResult deleteOneById(Long id);

    int selectOrgCountByParentId(Long id);


}
