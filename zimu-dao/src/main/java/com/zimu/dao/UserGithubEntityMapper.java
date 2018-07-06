package com.zimu.dao;

import com.zimu.dao.entity.UserGithubEntity;
import com.zimu.dao.entity.UserGithubEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserGithubEntityMapper {
    long countByExample(UserGithubEntityExample example);

    int deleteByExample(UserGithubEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserGithubEntity record);

    int insertSelective(UserGithubEntity record);

    List<UserGithubEntity> selectByExample(UserGithubEntityExample example);

    UserGithubEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserGithubEntity record, @Param("example") UserGithubEntityExample example);

    int updateByExample(@Param("record") UserGithubEntity record, @Param("example") UserGithubEntityExample example);

    int updateByPrimaryKeySelective(UserGithubEntity record);

    int updateByPrimaryKey(UserGithubEntity record);
}