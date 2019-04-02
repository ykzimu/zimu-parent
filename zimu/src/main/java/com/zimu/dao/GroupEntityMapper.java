package com.zimu.dao;

import com.zimu.domain.entity.GroupEntity;
import com.zimu.domain.entity.GroupEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupEntityMapper {
    long countByExample(GroupEntityExample example);

    int deleteByExample(GroupEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupEntity record);

    int insertSelective(GroupEntity record);

    List<GroupEntity> selectByExample(GroupEntityExample example);

    GroupEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupEntity record, @Param("example") GroupEntityExample example);

    int updateByExample(@Param("record") GroupEntity record, @Param("example") GroupEntityExample example);

    int updateByPrimaryKeySelective(GroupEntity record);

    int updateByPrimaryKey(GroupEntity record);
}