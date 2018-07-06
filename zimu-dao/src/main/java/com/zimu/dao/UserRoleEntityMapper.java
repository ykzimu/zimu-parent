package com.zimu.dao;

import com.zimu.dao.entity.UserRoleEntity;
import com.zimu.dao.entity.UserRoleEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleEntityMapper {
    long countByExample(UserRoleEntityExample example);

    int deleteByExample(UserRoleEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRoleEntity record);

    int insertSelective(UserRoleEntity record);

    List<UserRoleEntity> selectByExample(UserRoleEntityExample example);

    UserRoleEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserRoleEntity record, @Param("example") UserRoleEntityExample example);

    int updateByExample(@Param("record") UserRoleEntity record, @Param("example") UserRoleEntityExample example);

    int updateByPrimaryKeySelective(UserRoleEntity record);

    int updateByPrimaryKey(UserRoleEntity record);
}