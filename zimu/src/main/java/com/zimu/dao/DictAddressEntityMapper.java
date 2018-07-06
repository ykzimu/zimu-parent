package com.zimu.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zimu.domain.entity.DictAddressEntity;
import com.zimu.domain.entity.DictAddressEntityExample;

public interface DictAddressEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dict_address
     *
     * @mbg.generated
     */
    long countByExample(DictAddressEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dict_address
     *
     * @mbg.generated
     */
    int deleteByExample(DictAddressEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dict_address
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dict_address
     *
     * @mbg.generated
     */
    int insert(DictAddressEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dict_address
     *
     * @mbg.generated
     */
    int insertSelective(DictAddressEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dict_address
     *
     * @mbg.generated
     */
    List<DictAddressEntity> selectByExample(DictAddressEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dict_address
     *
     * @mbg.generated
     */
    DictAddressEntity selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dict_address
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DictAddressEntity record, @Param("example") DictAddressEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dict_address
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DictAddressEntity record, @Param("example") DictAddressEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dict_address
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DictAddressEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_dict_address
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DictAddressEntity record);
}