package com.yinuo.backstageAuth.mapper;

import com.inuol.entity.backstageAuth.Organization;
import com.inuol.entity.backstageAuth.OrganizationCriteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrganizationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_organization
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    long countByExample(OrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_organization
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    int deleteByExample(OrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_organization
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_organization
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    int insert(Organization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_organization
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    int insertSelective(Organization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_organization
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    List<Organization> selectByExample(OrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_organization
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    Organization selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_organization
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    int updateByExampleSelective(@Param("record") Organization record, @Param("example") OrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_organization
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    int updateByExample(@Param("record") Organization record, @Param("example") OrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_organization
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    int updateByPrimaryKeySelective(Organization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_organization
     *
     * @mbg.generated Tue Jan 21 17:05:32 CST 2020
     */
    int updateByPrimaryKey(Organization record);


    /**
     * 查询顶级组织
     * @return
     */
    List<Map<String, Object>> selectTopRog();

    /**
     * 查询所有组织，除去顶级
     * @return
     */
    List<Map<String, Object>> selectAllOrgExceptTop();

    /**
     * 查询下级组织
     * @return
     */
    List<Map<String, Object>> selectOrgByParentId(@Param("parentId") String parentId);

    /**
     * 判断是否存在下级组织
     * @return
     */
    int selectOrgCountByParentId(Long id);

}