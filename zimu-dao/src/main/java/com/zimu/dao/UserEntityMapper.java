package com.zimu.dao;

import com.zimu.dao.entity.UserEntity;
import com.zimu.dao.entity.UserEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserEntityMapper {
    long countByExample(UserEntityExample example);

    int deleteByExample(UserEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    List<UserEntity> selectByExample(UserEntityExample example);

    UserEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserEntity record, @Param("example") UserEntityExample example);

    int updateByExample(@Param("record") UserEntity record, @Param("example") UserEntityExample example);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);
}