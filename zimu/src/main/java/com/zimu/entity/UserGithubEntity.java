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
 * github用户
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_github")
public class UserGithubEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("github_user_id")
    private Long githubUserId;

    @TableField("login")
    private String login;

    @TableField("name")
    private String name;

    @TableField("avatar_url")
    private String avatarUrl;

    @TableField("gravatar_id")
    private String gravatarId;

    @TableField("url")
    private String url;

    @TableField("html_url")
    private String htmlUrl;

    @TableField("followers_url")
    private String followersUrl;

    @TableField("following_url")
    private String followingUrl;

    @TableField("gists_url")
    private String gistsUrl;

    @TableField("starred_url")
    private String starredUrl;

    @TableField("subscriptions_url")
    private String subscriptionsUrl;

    @TableField("organizations_url")
    private String organizationsUrl;

    @TableField("repos_url")
    private String reposUrl;

    @TableField("events_url")
    private String eventsUrl;

    @TableField("received_events_url")
    private String receivedEventsUrl;

    @TableField("type")
    private String type;

    @TableField("site_admin")
    private String siteAdmin;

    @TableField("company")
    private String company;

    @TableField("blog")
    private String blog;

    @TableField("location")
    private String location;

    @TableField("email")
    private String email;

    @TableField("hireable")
    private String hireable;

    @TableField("bio")
    private String bio;

    @TableField("public_repos")
    private Integer publicRepos;

    @TableField("public_gists")
    private Integer publicGists;

    @TableField("followers")
    private Integer followers;

    @TableField("following")
    private Integer following;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("private_gists")
    private Integer privateGists;

    @TableField("total_private_repos")
    private Integer totalPrivateRepos;

    @TableField("owned_private_repos")
    private Integer ownedPrivateRepos;

    @TableField("disk_usage")
    private String diskUsage;

    @TableField("collaborators")
    private Integer collaborators;

    @TableField("two_factor_authentication")
    private String twoFactorAuthentication;

    @TableField("plan_name")
    private String planName;

    @TableField("plan_space")
    private String planSpace;

    @TableField("plan_collaborators")
    private Integer planCollaborators;

    @TableField("plan_private_repos")
    private Integer planPrivateRepos;

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
