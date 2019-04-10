package com.zimu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.time.LocalDateTime;
import com.zimu.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 真实姓名
     */
    @TableField("realname")
    private String realname;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机号码
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 头像
     */
    @TableField("photo")
    private String photo;

    /**
     * 注册IP
     */
    @TableField("register_ip")
    private String registerIp;

    /**
     * 注册时间
     */
    @TableField("register_date")
    private LocalDateTime registerDate;

    /**
     * 最近登录IP
     */
    @TableField("login_ip")
    private String loginIp;

    /**
     * 最近登录时间
     */
    @TableField("login_date")
    private LocalDateTime loginDate;

    /**
     * 登录标记
     */
    @TableField("login_flag")
    private String loginFlag;

    /**
     * 是否过期 1：已过期，0：未过期
     */
    @TableField("is_expired")
    private Integer isExpired;

    /**
     * 凭证是否过期 1：已过期，0：未过期
     */
    @TableField("is_credentials_expired")
    private Integer isCredentialsExpired;

    /**
     * 是否被锁 1：锁住，0：解锁
     */
    @TableField("is_locked")
    private Integer isLocked;

    /**
     * 是否激活，1：已激活，0：未激活
     */
    @TableField("is_enabled")
    private Integer isEnabled;


}
