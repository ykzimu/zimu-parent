package com.zimu.dao;

import com.zimu.dao.entity.MenuEntity;
import com.zimu.dao.entity.MenuEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuEntityMapper {
    long countByExample(MenuEntityExample example);

    int deleteByExample(MenuEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MenuEntity record);

    int insertSelective(MenuEntity record);

    List<MenuEntity> selectByExample(MenuEntityExample example);

    MenuEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MenuEntity record, @Param("example") MenuEntityExample example);

    int updateByExample(@Param("record") MenuEntity record, @Param("example") MenuEntityExample example);

    int updateByPrimaryKeySelective(MenuEntity record);

    int updateByPrimaryKey(MenuEntity record);
}