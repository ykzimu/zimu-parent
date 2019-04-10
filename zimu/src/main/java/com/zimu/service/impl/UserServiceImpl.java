package com.zimu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zimu.common.Constants;
import com.zimu.common.MessageCode;
import com.zimu.common.enums.RoleEnum;
import com.zimu.common.exception.BusinessException;
import com.zimu.common.mybatisplus.enums.DelFlagEnum;
import com.zimu.common.utils.CommonUtils;
import com.zimu.common.utils.LoginUserUtils;
import com.zimu.component.CommonComponent;
import com.zimu.component.MenuComponent;
import com.zimu.component.RoleComponent;
import com.zimu.domain.info.DataTablesInfo;
import com.zimu.domain.info.MenuInfo;
import com.zimu.domain.info.SearchInfo;
import com.zimu.domain.info.UserInfo;
import com.zimu.entity.RoleEntity;
import com.zimu.entity.UserEntity;
import com.zimu.entity.UserGithubEntity;
import com.zimu.entity.UserRoleEntity;
import com.zimu.mapper.UserGithubMapper;
import com.zimu.mapper.UserMapper;
import com.zimu.mapper.UserRoleMapper;
import com.zimu.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    private static final String DEFAULT_USER = "1";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserGithubMapper userGithubMapper;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private RoleComponent roleComponent;

    @Autowired
    private MenuComponent menuComponent;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity getUserByUsername(String username) {

        if (StringUtils.isBlank(username)) {
            return null;
        }
        username = username.trim();
        LambdaQueryWrapper<UserEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.or().eq(UserEntity::getUsername, username).ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
        queryWrapper.or().eq(UserEntity::getMobile, username).ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
        queryWrapper.or().eq(UserEntity::getEmail, username).ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
        return this.getOne(queryWrapper, false);
    }

    @Override
    public UserInfo getUserInfoByUsername(String username) {
        // 查询用户信息
        UserEntity userEntity = this.getUserByUsername(username);
        if (userEntity == null) {
            return null;
        }

        Long userId = userEntity.getId();
        //查询角色信息
        List<RoleEntity> roleEntities = roleComponent.getRolesByUserId(userId);
        //左侧菜单
        List<MenuInfo> menuInfos = menuComponent.getMenus(userId, roleEntities);
        //角色
        Set<GrantedAuthority> authorities = roleEntities.stream().map(RoleEntity::getRoleCode).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());

        //构建用户信息
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userEntity, userInfo);
        userInfo.setAuthorities(authorities);
        userInfo.setMenuInfos(menuInfos);
        return userInfo;
    }

    @Override
    public List<String> getRoleCodesByUserId(Long userId) {
        List<RoleEntity> roleEntities = roleComponent.getRolesByUserId(userId);
        if (roleEntities == null || roleEntities.isEmpty()) {
            return Collections.emptyList();
        }
        return roleEntities.stream().map(RoleEntity::getRoleCode).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public UserInfo getUserInfo(DefaultOAuth2User oauth2User) {

        // ip地址
        String ip = CommonUtils.getIpAddr();
        LocalDateTime date = LocalDateTime.now();

        // 构造数据用于更新
        UserGithubEntity userGithubEntity = this.mapToUserGithubEntity(oauth2User.getAttributes());
        UserEntity userEntity = null;
        // 默认为id
        Long githubUserId = Long.parseLong(oauth2User.getName());
        LambdaQueryWrapper<UserGithubEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(UserGithubEntity::getGithubUserId, githubUserId);
        List<UserGithubEntity> list = userGithubMapper.selectList(queryWrapper);
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
            userEntity.setDelFlag(DelFlagEnum.NO);
            userEntity.setVersion(1);
            userMapper.insert(userEntity);

            // 插入git用户信息
            userGithubEntity.setUserId(userEntity.getId());
            userGithubEntity.setCreateBy("1");
            userGithubEntity.setCreateDate(date);
            userGithubEntity.setDelFlag(DelFlagEnum.NO);
            userGithubEntity.setVersion(1);
            userGithubMapper.insert(userGithubEntity);

            // 查询用户权限信息
            RoleEntity roleEntity = commonComponent.getRoleByRoleCode(RoleEnum.ROLE_USER.getRoleCode());

            // 给新用户赋权限
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(userEntity.getId());
            userRoleEntity.setRoleId(roleEntity.getId());
            userRoleEntity.setCreateBy(DEFAULT_USER);
            userRoleEntity.setCreateDate(date);
            userRoleEntity.setDelFlag(DelFlagEnum.NO);
            userRoleEntity.setVersion(1);
            userRoleMapper.insert(userRoleEntity);
        } else {

            // 更新用户基本信息
            userEntity = userMapper.selectById(list.get(0).getUserId());
            userEntity.setNickname(userGithubEntity.getName());
            userEntity.setRealname(userGithubEntity.getName());
            userEntity.setLoginDate(date);
            userEntity.setLoginIp(ip);
            userEntity.setUpdateBy(list.get(0).getUserId().toString());
            userEntity.setUpdateDate(date);
            userEntity.setVersion(list.get(0).getVersion() + 1);
            userMapper.updateById(userEntity);

            // 更新git用户信息
            userGithubEntity.setId(list.get(0).getId());
            userGithubEntity.setUpdateBy(list.get(0).getUserId().toString());
            userGithubEntity.setUpdateDate(date);
            userGithubEntity.setVersion(list.get(0).getVersion() + 1);
            userGithubMapper.updateById(userGithubEntity);

        }

        // 查询角色信息
        List<String> roles = this.getRoleCodesByUserId(userEntity.getId());
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
        LocalDateTime date = LocalDateTime.now();
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
        userEntity.setDelFlag(DelFlagEnum.NO);
        userEntity.setVersion(1);
        userMapper.insert(userEntity);

        // 查询用户权限信息
        RoleEntity roleEntity = commonComponent.getRoleByRoleCode(RoleEnum.ROLE_USER.getRoleCode());

        // 给新用户赋权限
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userEntity.getId());
        userRoleEntity.setRoleId(roleEntity.getId());
        userRoleEntity.setCreateBy(DEFAULT_USER);
        userRoleEntity.setCreateDate(date);
        userRoleEntity.setDelFlag(DelFlagEnum.NO);
        userRoleEntity.setVersion(1);
        userRoleMapper.insert(userRoleEntity);

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
        userMapper.selectList(null);
    }

    @Override
    public IPage<UserEntity> getUsers(SearchInfo searchInfo) {

        String keyword = searchInfo.getKeyword();
        LambdaQueryWrapper<UserEntity> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(keyword)) {
            queryWrapper.or().like(UserEntity::getUsername, keyword).ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
            queryWrapper.or().like(UserEntity::getNickname, keyword).ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
            queryWrapper.or().like(UserEntity::getRealname, keyword).ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
            queryWrapper.or().like(UserEntity::getEmail, keyword).ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
            queryWrapper.or().like(UserEntity::getMobile, keyword).ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
        } else {
            queryWrapper.ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
        }
        if (StringUtils.isNotBlank(searchInfo.getFieldName()) && StringUtils.isNotBlank(searchInfo.getSortType())) {
            String orderByClause = " " + searchInfo.getFieldName().trim();
            if ("DESC".equals(searchInfo.getSortType().trim())) {
                orderByClause = orderByClause + " DESC";
            }

        }
        IPage<UserEntity> page = new Page<>(searchInfo.getPageNum(), searchInfo.getPageSize());
        return userMapper.selectPage(page, queryWrapper);
    }

    @Override
    public IPage<UserEntity> getUsers(DataTablesInfo dataTablesInfo) {
        String keyword = dataTablesInfo.getSearch().getValue();
        LambdaQueryWrapper<UserEntity> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(keyword)) {
            queryWrapper.or().like(UserEntity::getUsername, keyword).ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
            queryWrapper.or().like(UserEntity::getNickname, keyword).ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
            queryWrapper.or().like(UserEntity::getRealname, keyword).ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
            queryWrapper.or().like(UserEntity::getEmail, keyword).ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
            queryWrapper.or().like(UserEntity::getMobile, keyword).ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
        } else {
            queryWrapper.ne(UserEntity::getDelFlag, Constants.DEL_FLAG_OK);
        }

        List<DataTablesInfo.Order> orderList = dataTablesInfo.getOrder();
        List<DataTablesInfo.Column> columnList = dataTablesInfo.getColumns();
        if (CollectionUtils.isNotEmpty(orderList)) {
            StringBuffer buffer = new StringBuffer();
            orderList.forEach(order -> {
                String name = columnList.get(order.getColumn()).getName();
                String dir = order.getDir();
                buffer.append(", " + name + " " + dir);
            });
            String orderBy = buffer.substring(1);
            // example.setOrderByClause(orderBy);
        }
        IPage<UserEntity> page = new Page<>(dataTablesInfo.getPageNum(), dataTablesInfo.getPageSize());
        return userMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int deleteUserByIds(List<Long> userIds) {

        // 查询ROLE_USER角色
        RoleEntity roleEntity = commonComponent.getRoleByRoleCode(RoleEnum.ROLE_USER.getRoleCode());

        // 先删除角色（保留用户角色）
        LambdaUpdateWrapper<UserRoleEntity> wrapper = Wrappers.lambdaUpdate();
        wrapper.in(UserRoleEntity::getUserId, userIds).ne(UserRoleEntity::getRoleId, roleEntity.getId());
        userRoleMapper.delete(wrapper);

        // 删除用户（逻辑删除）
        LambdaUpdateWrapper<UserEntity> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.in(UserEntity::getId, userIds);
        UserEntity userEntity = new UserEntity();
        userEntity.setDelFlag(DelFlagEnum.YES);
        userEntity.setUpdateBy(LoginUserUtils.getUserInfo().getId().toString());
        userEntity.setUpdateDate(LocalDateTime.now());
        int cnt = userMapper.update(userEntity, updateWrapper);
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
        userMapper.updateById(userEntity);

        // 更新session密码
        userInfo.setPassword(pwd);

        return true;
    }

}
