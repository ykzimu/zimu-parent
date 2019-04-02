package com.zimu.dao;

import com.zimu.domain.entity.UserGroupEntity;
import com.zimu.domain.entity.UserGroupEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserGroupEntityMapper {
    long countByExample(UserGroupEntityExample example);

    int deleteByExample(UserGroupEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserGroupEntity record);

    int insertSelective(UserGroupEntity record);

    List<UserGroupEntity> selectByExample(UserGroupEntityExample example);

    UserGroupEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserGroupEntity record, @Param("example") UserGroupEntityExample example);

    int updateByExample(@Param("record") UserGroupEntity record, @Param("example") UserGroupEntityExample example);

    int updateByPrimaryKeySelective(UserGroupEntity record);

    int updateByPrimaryKey(UserGroupEntity record);
}