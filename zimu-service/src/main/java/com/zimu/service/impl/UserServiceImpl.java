package com.zimu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zimu.common.MessageCode;
import com.zimu.common.enums.RoleEnum;
import com.zimu.common.exception.BusinessException;
import com.zimu.common.utils.CommonUtils;
import com.zimu.common.utils.LoginUserUtils;
import com.zimu.component.CommonComponent;
import com.zimu.dao.RoleEntityMapper;
import com.zimu.dao.UserEntityMapper;
import com.zimu.dao.UserGithubEntityMapper;
import com.zimu.dao.UserRoleEntityMapper;
import com.zimu.domain.entity.RoleEntity;
import com.zimu.domain.entity.RoleEntityExample;
import com.zimu.domain.entity.UserEntity;
import com.zimu.domain.entity.UserEntityExample;
import com.zimu.domain.entity.UserGithubEntity;
import com.zimu.domain.entity.UserGithubEntityExample;
import com.zimu.domain.entity.UserRoleEntity;
import com.zimu.domain.entity.UserRoleEntityExample;
import com.zimu.domain.info.SearchInfo;
import com.zimu.domain.info.UserInfo;
import com.zimu.service.UserService;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_USER = "1";

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private UserRoleEntityMapper userRoleEntityMapper;

    @Autowired
    private RoleEntityMapper roleEntityMapper;

    @Autowired
    private UserGithubEntityMapper userGithubEntityMapper;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity getUserById(Long id) {
        UserEntity userEntity = userEntityMapper.selectByPrimaryKey(id);
        return userEntity;
    }

    @Override
    public UserEntity getUserByUsername(String username) {

        if (StringUtils.isBlank(username)) {
            return null;
        } else {
            username = username.trim();
        }

        UserEntityExample example = new UserEntityExample();
        example.or().andUsernameEqualTo(username).andDelFlagNotEqualTo(1);
        example.or().andMobileEqualTo(username).andDelFlagNotEqualTo(1);
        example.or().andEmailEqualTo(username).andDelFlagNotEqualTo(1);
        List<UserEntity> list = userEntityMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<String> getRolesByUserId(Long userId) {

        // 角色名
        List<String> roleCodes = new ArrayList<>();

        // 查询用户角色关联表
        UserRoleEntityExample userRoleEntityExample = new UserRoleEntityExample();
        userRoleEntityExample.createCriteria().andUserIdEqualTo(userId);
        List<UserRoleEntity> userRoleEntities = userRoleEntityMapper.selectByExample(userRoleEntityExample);
        if (userRoleEntities == null || userRoleEntities.isEmpty()) {
            return roleCodes;
        }

        // 构造角色
        List<Long> roleIds = new ArrayList<>();
        for (UserRoleEntity userRoleEntity : userRoleEntities) {
            roleIds.add(userRoleEntity.getRoleId());
        }

        // 查询角色表
        RoleEntityExample roleEntityExample = new RoleEntityExample();
        roleEntityExample.createCriteria().andIdIn(roleIds);
        List<RoleEntity> roleEntities = roleEntityMapper.selectByExample(roleEntityExample);
        if (roleEntities == null || roleEntities.isEmpty()) {
            return roleCodes;
        }
        for (RoleEntity roleEntity : roleEntities) {
            roleCodes.add(roleEntity.getRoleCode());
        }
        return roleCodes;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public UserInfo getUserInfo(DefaultOAuth2User oauth2User) {

        // ip地址
        String ip = CommonUtils.getIpAddr();
        Date date = new Date();

        // 构造数据用于更新
        UserGithubEntity userGithubEntity = this.mapToUserGithubEntity(oauth2User.getAttributes());
        UserEntity userEntity = null;
        // 默认为id
        Long githubUserId = Long.parseLong(oauth2User.getName());
        UserGithubEntityExample example = new UserGithubEntityExample();
        example.createCriteria().andGithubUserIdEqualTo(githubUserId);
        List<UserGithubEntity> list = userGithubEntityMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {

            // 插入用户基本信息
            userEntity = new UserEntity();
            String username = userGithubEntity.getLogin() + userGithubEntity.getGithubUserId();
            userEntity.setUsername(username);
            String password = passwordEncoder.encode(username);
            userEntity.setPassword(password);
            userEntity.setNickname(userGithubEntity.getName());
            userEntity.setRealname(userGithubEntity.getName());
            userEntity.setCreateBy(DEFAULT_USER);
            userEntity.setCreateDate(date);
            userEntity.setRegisterIp(ip);
            userEntity.setRegisterDate(date);
            userEntity.setLoginDate(date);
            userEntity.setLoginIp(ip);
            userEntity.setIsLocked(0);
            userEntity.setIsExpired(0);
            userEntity.setIsCredentialsExpired(0);
            userEntity.setIsEnabled(1);
            userEntity.setDelFlag(0);
            userEntity.setVersion(1);
            userEntityMapper.insert(userEntity);

            // 插入git用户信息
            userGithubEntity.setUserId(userEntity.getId());
            userGithubEntity.setCreateBy("1");
            userGithubEntity.setCreateDate(date);
            userGithubEntity.setDelFlag(0);
            userGithubEntity.setVersion(1);
            userGithubEntityMapper.insertSelective(userGithubEntity);

            // 查询用户权限信息
            RoleEntity roleEntity = commonComponent.getRoleByRoleCode(RoleEnum.ROLE_USER.getRoleCode());

            // 给新用户赋权限
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(userEntity.getId());
            userRoleEntity.setRoleId(roleEntity.getId());
            userRoleEntity.setCreateBy(DEFAULT_USER);
            userRoleEntity.setCreateDate(date);
            userRoleEntity.setDelFlag(0);
            userRoleEntity.setVersion(1);
            userRoleEntityMapper.insert(userRoleEntity);
        } else {

            // 更新用户基本信息
            userEntity = userEntityMapper.selectByPrimaryKey(list.get(0).getUserId());
            userEntity.setNickname(userGithubEntity.getName());
            userEntity.setRealname(userGithubEntity.getName());
            userEntity.setLoginDate(date);
            userEntity.setLoginIp(ip);
            userEntity.setUpdateBy(list.get(0).getUserId().toString());
            userEntity.setUpdateDate(date);
            userEntity.setVersion(list.get(0).getVersion() + 1);
            userEntityMapper.updateByPrimaryKey(userEntity);

            // 更新git用户信息
            userGithubEntity.setId(list.get(0).getId());
            userGithubEntity.setUpdateBy(list.get(0).getUserId().toString());
            userGithubEntity.setUpdateDate(date);
            userGithubEntity.setVersion(list.get(0).getVersion() + 1);
            userGithubEntityMapper.updateByPrimaryKeySelective(userGithubEntity);

        }

        // 查询角色信息
        List<String> roles = this.getRolesByUserId(userEntity.getId());
        Set<GrantedAuthority> authorities = new HashSet<>();
        SimpleGrantedAuthority grantedAuthority = null;
        for (String role : roles) {
            grantedAuthority = new SimpleGrantedAuthority(role);
            authorities.add(grantedAuthority);
        }
        authorities.addAll(oauth2User.getAuthorities());
        UserInfo userInfo = new UserInfo();
        try {
            BeanUtils.copyProperties(userEntity, userInfo);
        } catch (Exception e) {

        }
        userInfo.setAuthorities(authorities);
        userInfo.setAttributes(oauth2User.getAttributes());
        return userInfo;
    }

    private UserGithubEntity mapToUserGithubEntity(Map<String, Object> map) {
        UserGithubEntity entity = new UserGithubEntity();
        if (map.get("login") != null) {
            entity.setLogin(map.get("login").toString());
        }
        if (map.get("id") != null) {
            entity.setGithubUserId(Long.parseLong(map.get("id").toString()));
        }
        if (map.get("avatar_url") != null) {
            entity.setAvatarUrl(map.get("avatar_url").toString());
        }
        if (map.get("gravatar_id") != null) {
            entity.setGravatarId(map.get("gravatar_id").toString());
        }
        if (map.get("url") != null) {
            entity.setUrl(map.get("url").toString());
        }
        if (map.get("html_url") != null) {
            entity.setHtmlUrl(map.get("html_url").toString());
        }
        if (map.get("followers_url") != null) {
            entity.setFollowersUrl(map.get("followers_url").toString());
        }
        if (map.get("following_url") != null) {
            entity.setFollowingUrl(map.get("following_url").toString());
        }
        if (map.get("gists_url") != null) {
            entity.setGistsUrl(map.get("gists_url").toString());
        }
        if (map.get("starred_url") != null) {
            entity.setStarredUrl(map.get("starred_url").toString());
        }
        if (map.get("subscriptions_url") != null) {
            entity.setSubscriptionsUrl(map.get("subscriptions_url").toString());
        }
        if (map.get("organizations_url") != null) {
            entity.setOrganizationsUrl(map.get("organizations_url").toString());
        }
        if (map.get("repos_url") != null) {
            entity.setReposUrl(map.get("repos_url").toString());
        }
        if (map.get("events_url") != null) {
            entity.setEventsUrl(map.get("events_url").toString());
        }
        if (map.get("received_events_url") != null) {
            entity.setReceivedEventsUrl(map.get("received_events_url").toString());
        }
        if (map.get("type") != null) {
            entity.setType(map.get("type").toString());
        }
        if (map.get("site_admin") != null) {
            entity.setSiteAdmin(map.get("site_admin").toString());
        }
        if (map.get("name") != null) {
            entity.setName(map.get("name").toString());
        }
        if (map.get("company") != null) {
            entity.setCompany(map.get("company").toString());
        }
        if (map.get("blog") != null) {
            entity.setBlog(map.get("blog").toString());
        }
        if (map.get("location") != null) {
            entity.setLocation(map.get("location").toString());
        }
        if (map.get("email") != null) {
            entity.setEmail(map.get("email").toString());
        }
        if (map.get("hireable") != null) {
            entity.setHireable(map.get("hireable").toString());
        }
        if (map.get("bio") != null) {
            entity.setBio(map.get("bio").toString());
        }
        if (map.get("public_repos") != null) {
            entity.setPublicRepos(Integer.parseInt(map.get("public_repos").toString()));
        }
        if (map.get("public_gists") != null) {
            entity.setPublicGists(Integer.parseInt(map.get("public_gists").toString()));
        }
        if (map.get("followers") != null) {
            entity.setFollowers(Integer.parseInt(map.get("followers").toString()));
        }
        if (map.get("following") != null) {
            entity.setFollowing(Integer.parseInt(map.get("following").toString()));
        }

        return entity;
    }

    /**
     * 注册用户
     *
     * @param userEntity 用户
     * @return Boolean
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean registerUser(UserEntity userEntity) throws Exception {

        // 用户已经存在
        if (getUserByUsername(userEntity.getUsername()) != null) {
            throw new BusinessException(MessageCode.INFO_I0001, userEntity.getUsername());
        }

        // 邮箱已经存在
        if (getUserByUsername(userEntity.getEmail()) != null) {
            throw new BusinessException(MessageCode.INFO_I0002, userEntity.getEmail());
        }

        // 手机号码已经存在
        if (getUserByUsername(userEntity.getMobile()) != null) {
            throw new BusinessException(MessageCode.INFO_I0003, userEntity.getMobile());
        }

        // 新建用户
        Date date = new Date();
        String pwd = userEntity.getPassword();
        String password = passwordEncoder.encode(pwd);
        userEntity.setPassword(password);
        String ip = CommonUtils.getIpAddr();
        userEntity.setCreateBy(DEFAULT_USER);
        userEntity.setCreateDate(date);
        userEntity.setRegisterIp(ip);
        userEntity.setRegisterDate(date);
        userEntity.setLoginDate(date);
        userEntity.setLoginIp(ip);
        userEntity.setIsLocked(0);
        userEntity.setIsEnabled(1);
        userEntity.setIsExpired(0);
        userEntity.setIsCredentialsExpired(0);
        userEntity.setDelFlag(0);
        userEntity.setVersion(1);
        userEntityMapper.insert(userEntity);

        // 查询用户权限信息
        RoleEntity roleEntity = commonComponent.getRoleByRoleCode(RoleEnum.ROLE_USER.getRoleCode());

        // 给新用户赋权限
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userEntity.getId());
        userRoleEntity.setRoleId(roleEntity.getId());
        userRoleEntity.setCreateBy(DEFAULT_USER);
        userRoleEntity.setCreateDate(date);
        userRoleEntity.setDelFlag(0);
        userRoleEntity.setVersion(1);
        userRoleEntityMapper.insert(userRoleEntity);

        // 构建为登录状态
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userEntity, userInfo);
        Set<GrantedAuthority> authorities = new HashSet<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(RoleEnum.ROLE_USER.getRoleCode());
        authorities.add(authority);
        userInfo.setAuthorities(authorities);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userInfo, pwd,
                authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }

    @Override
    public void test() {
        PageHelper.startPage(1, 10);
        userEntityMapper.selectByExample(null);
    }

    @Override
    public PageInfo<UserEntity> getUsers(SearchInfo searchInfo) {

        String keyword = searchInfo.getKeyword();
        UserEntityExample example = new UserEntityExample();
        if (StringUtils.isNotBlank(keyword)) {
            keyword = "%" + keyword + "%";
            example.or().andUsernameLike(keyword).andDelFlagEqualTo(0);
            example.or().andNicknameLike(keyword).andDelFlagEqualTo(0);
            example.or().andRealnameLike(keyword).andDelFlagEqualTo(0);
            example.or().andEmailLike(keyword).andDelFlagEqualTo(0);
            example.or().andMobileLike(keyword).andDelFlagEqualTo(0);
        } else {
            example.createCriteria().andDelFlagEqualTo(0);
        }
        if (StringUtils.isNotBlank(searchInfo.getFieldName()) && StringUtils.isNotBlank(searchInfo.getSortType())) {
            String orderByClause = " " + searchInfo.getFieldName().trim();
            if ("DESC".equals(searchInfo.getSortType().trim())) {
                orderByClause = orderByClause + " DESC";
            }
            example.setOrderByClause(orderByClause);
        }

        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize());
        List<UserEntity> list = userEntityMapper.selectByExample(example);
        PageInfo<UserEntity> page = new PageInfo<UserEntity>(list);
        return page;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int deleteUserByIds(List<Long> userIds) {

        // 查询ROLE_USER角色
        RoleEntity roleEntity = commonComponent.getRoleByRoleCode(RoleEnum.ROLE_USER.getRoleCode());

        // 先删除角色（保留用户角色）
        UserRoleEntityExample userRoleEntityExample = new UserRoleEntityExample();
        userRoleEntityExample.or().andUserIdIn(userIds).andRoleIdNotEqualTo(roleEntity.getId());
        userRoleEntityMapper.deleteByExample(userRoleEntityExample);

        // 删除用户（逻辑删除）
        UserEntityExample example = new UserEntityExample();
        example.or().andIdIn(userIds);
        UserEntity userEntity = new UserEntity();
        userEntity.setDelFlag(1);
        userEntity.setUpdateBy(LoginUserUtils.getUserInfo().getId().toString());
        userEntity.setUpdateDate(new Date());
        int cnt = userEntityMapper.updateByExampleSelective(userEntity, example);
        return cnt;
    }

    /**
     * 修改密码
     *
     * @param password
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean updPwd(String password) {

        // 获取用户信息
        UserInfo userInfo = LoginUserUtils.getUserInfo();
        String pwd = passwordEncoder.encode(password);

        // 更新数据库密码
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userInfo.getId());
        userEntity.setPassword(pwd);
        userEntityMapper.updateByPrimaryKeySelective(userEntity);

        // 更新session密码
        userInfo.setPassword(pwd);

        return true;
    }

}
