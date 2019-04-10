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
 * 菜单表
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_menu")
public class MenuEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("parent_id")
    private Long parentId;

    @TableField("parent_ids")
    private String parentIds;

    @TableField("menu_type")
    private String menuType;

    @TableField("menu_level")
    private Integer menuLevel;

    @TableField("menu_name")
    private String menuName;

    @TableField("menu_desc")
    private String menuDesc;

    @TableField("menu_sort")
    private Integer menuSort;

    @TableField("menu_href")
    private String menuHref;

    @TableField("menu_target")
    private String menuTarget;

    @TableField("menu_icon")
    private String menuIcon;

    @TableField("is_show")
    private Integer isShow;

    @TableField("remarks")
    private String remarks;


}
