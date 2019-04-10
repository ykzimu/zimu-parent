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
 * 
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_dev_database")
public class DevDatabaseEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库名称
     */
    @TableField("database_name")
    private String databaseName;

    /**
     * 数据库描述
     */
    @TableField("database_desc")
    private String databaseDesc;

    /**
     * 数据库url
     */
    @TableField("database_url")
    private String databaseUrl;

    /**
     * 登录数据库用户名
     */
    @TableField("database_username")
    private String databaseUsername;

    /**
     * 登录数据库密码
     */
    @TableField("database_password")
    private String databasePassword;

    /**
     * 驱动
     */
    @TableField("database_driver_class_name")
    private String databaseDriverClassName;


}
