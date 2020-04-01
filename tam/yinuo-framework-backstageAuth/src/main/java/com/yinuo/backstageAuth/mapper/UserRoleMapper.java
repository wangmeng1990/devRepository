package com.yinuo.backstageAuth.mapper;

import com.inuol.entity.backstageAuth.UserRole;
import com.inuol.entity.backstageAuth.UserRoleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    long countByExample(UserRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int deleteByExample(UserRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int insert(UserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int insertSelective(UserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    List<UserRole> selectByExample(UserRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    UserRole selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int updateByPrimaryKeySelective(UserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int updateByPrimaryKey(UserRole record);

    /**
     * 根据用户id 删除用户角色
     * @param id
     * @return
     */
    int deleteByUserId(Long id);
}