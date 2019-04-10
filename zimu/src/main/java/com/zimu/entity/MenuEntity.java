package com.zimu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_menu")
public class MenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    @TableField("del_flag")
    private Integer delFlag;

    @TableField("create_by")
    private String createBy;

    @TableField("create_date")
    private LocalDateTime createDate;

    @TableField("update_by")
    private String updateBy;

    @TableField("update_date")
    private LocalDateTime updateDate;

    @TableField("version")
    @Version
    private Integer version;


}
