package com.zimu.dao;

import com.zimu.dao.entity.PersistentLoginsEntity;
import com.zimu.dao.entity.PersistentLoginsEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersistentLoginsEntityMapper {
    long countByExample(PersistentLoginsEntityExample example);

    int deleteByExample(PersistentLoginsEntityExample example);

    int deleteByPrimaryKey(String series);

    int insert(PersistentLoginsEntity record);

    int insertSelective(PersistentLoginsEntity record);

    List<PersistentLoginsEntity> selectByExample(PersistentLoginsEntityExample example);

    PersistentLoginsEntity selectByPrimaryKey(String series);

    int updateByExampleSelective(@Param("record") PersistentLoginsEntity record, @Param("example") PersistentLoginsEntityExample example);

    int updateByExample(@Param("record") PersistentLoginsEntity record, @Param("example") PersistentLoginsEntityExample example);

    int updateByPrimaryKeySelective(PersistentLoginsEntity record);

    int updateByPrimaryKey(PersistentLoginsEntity record);
}