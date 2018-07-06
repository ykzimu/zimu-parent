package com.zimu.dao;

import com.zimu.dao.entity.RoleMenuEntity;
import com.zimu.dao.entity.RoleMenuEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuEntityMapper {
    long countByExample(RoleMenuEntityExample example);

    int deleteByExample(RoleMenuEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoleMenuEntity record);

    int insertSelective(RoleMenuEntity record);

    List<RoleMenuEntity> selectByExample(RoleMenuEntityExample example);

    RoleMenuEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoleMenuEntity record, @Param("example") RoleMenuEntityExample example);

    int updateByExample(@Param("record") RoleMenuEntity record, @Param("example") RoleMenuEntityExample example);

    int updateByPrimaryKeySelective(RoleMenuEntity record);

    int updateByPrimaryKey(RoleMenuEntity record);
}