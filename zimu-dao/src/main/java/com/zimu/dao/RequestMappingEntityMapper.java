package com.zimu.dao;

import com.zimu.dao.entity.RequestMappingEntity;
import com.zimu.dao.entity.RequestMappingEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RequestMappingEntityMapper {
    long countByExample(RequestMappingEntityExample example);

    int deleteByExample(RequestMappingEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RequestMappingEntity record);

    int insertSelective(RequestMappingEntity record);

    List<RequestMappingEntity> selectByExample(RequestMappingEntityExample example);

    RequestMappingEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RequestMappingEntity record, @Param("example") RequestMappingEntityExample example);

    int updateByExample(@Param("record") RequestMappingEntity record, @Param("example") RequestMappingEntityExample example);

    int updateByPrimaryKeySelective(RequestMappingEntity record);

    int updateByPrimaryKey(RequestMappingEntity record);
}