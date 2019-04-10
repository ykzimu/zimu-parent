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
 * 组-角色表
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_group_role")
public class GroupRoleEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("group_id")
    private Long groupId;

    @TableField("role_id")
    private Long roleId;

    @TableField("remarks")
    private String remarks;


}
