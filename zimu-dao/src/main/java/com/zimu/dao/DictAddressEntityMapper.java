package com.zimu.dao;

import com.zimu.dao.entity.DictAddressEntity;
import com.zimu.dao.entity.DictAddressEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DictAddressEntityMapper {
    long countByExample(DictAddressEntityExample example);

    int deleteByExample(DictAddressEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DictAddressEntity record);

    int insertSelective(DictAddressEntity record);

    List<DictAddressEntity> selectByExample(DictAddressEntityExample example);

    DictAddressEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DictAddressEntity record, @Param("example") DictAddressEntityExample example);

    int updateByExample(@Param("record") DictAddressEntity record, @Param("example") DictAddressEntityExample example);

    int updateByPrimaryKeySelective(DictAddressEntity record);

    int updateByPrimaryKey(DictAddressEntity record);
}