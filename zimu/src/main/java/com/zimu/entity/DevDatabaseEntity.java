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
 * 
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dev_database")
public class DevDatabaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    /**
     * 删除标记：1：已删除，0：未删除
     */
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private LocalDateTime createDate;

    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("update_date")
    private LocalDateTime updateDate;

    /**
     * 版本号
     */
    @TableField("version")
    @Version
    private Integer version;


}
