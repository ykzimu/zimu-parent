package com.zimu.dao;

import com.zimu.dao.entity.DictEntity;
import com.zimu.dao.entity.DictEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DictEntityMapper {
    long countByExample(DictEntityExample example);

    int deleteByExample(DictEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DictEntity record);

    int insertSelective(DictEntity record);

    List<DictEntity> selectByExample(DictEntityExample example);

    DictEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DictEntity record, @Param("example") DictEntityExample example);

    int updateByExample(@Param("record") DictEntity record, @Param("example") DictEntityExample example);

    int updateByPrimaryKeySelective(DictEntity record);

    int updateByPrimaryKey(DictEntity record);
}