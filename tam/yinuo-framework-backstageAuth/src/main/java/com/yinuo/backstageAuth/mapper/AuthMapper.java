package com.yinuo.backstageAuth.mapper;

import com.inuol.entity.backstageAuth.Auth;
import com.inuol.entity.backstageAuth.User;
import com.inuol.entity.backstageAuth.AuthCriteria;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AuthMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    long countByExample(AuthCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int deleteByExample(AuthCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int insert(Auth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int insertSelective(Auth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    List<Auth> selectByExample(AuthCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    Auth selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int updateByExample(@Param("record") Auth record, @Param("example") AuthCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int updateByPrimaryKeySelective(Auth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_auth
     *
     * @mbg.generated Fri Jan 17 11:49:44 CST 2020
     */
    int updateByPrimaryKey(Auth record);

    /**
     * 查询顶级权限
     *
     * @return
     */
    List<Map<String, Object>> selectTopAuth();

    /**
     * 根据父级id查询当前角色的权限
     *
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> selectAuthByParentId(Map<String, Object> paramMap);


    User selectOne(String userName, String password);

    User selectUserByUserName(String userName);

}