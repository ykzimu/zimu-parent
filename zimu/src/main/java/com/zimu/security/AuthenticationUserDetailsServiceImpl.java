package com.zimu.security;

import com.zimu.component.MenuComponent;
import com.zimu.component.RoleComponent;
import com.zimu.domain.info.MenuInfo;
import com.zimu.domain.info.UserInfo;
import com.zimu.entity.RoleEntity;
import com.zimu.entity.UserEntity;
import com.zimu.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthenticationUserDetailsServiceImpl implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuComponent menuComponent;

    @Autowired
    private RoleComponent roleComponent;

    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) {
        try {

            //TODO 实现获取用户信息
            Map<String, Object> userAttributess = token.getAssertion().getPrincipal().getAttributes();

            // 查询用户信息
            UserEntity userEntity = userService.getUserByUsername(token.getName());
            if (userEntity == null) {
                throw new UsernameNotFoundException("username not found.");
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
            userInfo.setAttributes(userAttributess);
            userInfo.setMenuInfos(menuInfos);

            return userInfo;
        } catch (Exception e) {
            throw new UsernameNotFoundException("username not found.");
        }
    }

}
