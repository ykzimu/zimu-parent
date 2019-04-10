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
 * 数据字典表
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_dict")
public class DictEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 数据值
     */
    @TableField("value")
    private String value;

    /**
     * 标签名
     */
    @TableField("label")
    private String label;

    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 排序（升序）
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 父级编号
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 备注信息
     */
    @TableField("remarks")
    private String remarks;


}
