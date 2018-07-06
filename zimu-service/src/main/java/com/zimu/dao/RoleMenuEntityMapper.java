package com.zimu.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zimu.domain.entity.RoleMenuEntity;
import com.zimu.domain.entity.RoleMenuEntityExample;

public interface RoleMenuEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated
     */
    long countByExample(RoleMenuEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated
     */
    int deleteByExample(RoleMenuEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated
     */
    int insert(RoleMenuEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated
     */
    int insertSelective(RoleMenuEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated
     */
    List<RoleMenuEntity> selectByExample(RoleMenuEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated
     */
    RoleMenuEntity selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") RoleMenuEntity record, @Param("example") RoleMenuEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") RoleMenuEntity record, @Param("example") RoleMenuEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RoleMenuEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_menu
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RoleMenuEntity record);
}