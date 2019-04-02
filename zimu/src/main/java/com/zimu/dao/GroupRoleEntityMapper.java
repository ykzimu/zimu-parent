package com.zimu.dao;

import com.zimu.domain.entity.GroupRoleEntity;
import com.zimu.domain.entity.GroupRoleEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupRoleEntityMapper {
    long countByExample(GroupRoleEntityExample example);

    int deleteByExample(GroupRoleEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupRoleEntity record);

    int insertSelective(GroupRoleEntity record);

    List<GroupRoleEntity> selectByExample(GroupRoleEntityExample example);

    GroupRoleEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupRoleEntity record, @Param("example") GroupRoleEntityExample example);

    int updateByExample(@Param("record") GroupRoleEntity record, @Param("example") GroupRoleEntityExample example);

    int updateByPrimaryKeySelective(GroupRoleEntity record);

    int updateByPrimaryKey(GroupRoleEntity record);
}