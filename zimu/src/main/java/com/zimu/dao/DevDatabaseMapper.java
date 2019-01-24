package com.zimu.dao;

import com.zimu.domain.entity.DevDatabase;
import com.zimu.domain.entity.DevDatabaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DevDatabaseMapper {
    long countByExample(DevDatabaseExample example);

    int deleteByExample(DevDatabaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DevDatabase record);

    int insertSelective(DevDatabase record);

    List<DevDatabase> selectByExampleWithBLOBs(DevDatabaseExample example);

    List<DevDatabase> selectByExample(DevDatabaseExample example);

    DevDatabase selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DevDatabase record, @Param("example") DevDatabaseExample example);

    int updateByExampleWithBLOBs(@Param("record") DevDatabase record, @Param("example") DevDatabaseExample example);

    int updateByExample(@Param("record") DevDatabase record, @Param("example") DevDatabaseExample example);

    int updateByPrimaryKeySelective(DevDatabase record);

    int updateByPrimaryKeyWithBLOBs(DevDatabase record);

    int updateByPrimaryKey(DevDatabase record);
}