package com.zimu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.zimu.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 省市区字典表
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_dict_address")
public class DictAddressEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * code码
     */
    @TableField("code")
    private String code;

    /**
     * 父code码
     */
    @TableField("parent_code")
    private String parentCode;

    /**
     * 等级
     */
    @TableField("level")
    private Integer level;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 排序号
     */
    @TableField("sort_no")
    private Integer sortNo;


}
