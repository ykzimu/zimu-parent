package com.zimu.security;

import com.zimu.component.MenuComponent;
import com.zimu.domain.entity.UserEntity;
import com.zimu.domain.info.MenuInfo;
import com.zimu.domain.info.UserInfo;
import com.zimu.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuComponent menuComponent;

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {

            // 查询用户信息
            UserEntity userEntity = userService.getUserByUsername(username);
            if (userEntity == null) {
                throw new UsernameNotFoundException("username not found.");
            }

            Long id = userEntity.getId();

            // 查询角色信息
            List<String> roles = userService.getRolesByUserId(id);
            //左侧菜单
            List<MenuInfo> menuInfos = menuComponent.getMenus(id);

            //角色
            Set<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());

            //构建用户信息
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(userEntity, userInfo);
            userInfo.setAuthorities(authorities);
            userInfo.setMenuInfos(menuInfos);
            return userInfo;
        } catch (Exception e) {
            throw new UsernameNotFoundException("username not found.");
        }
    }
}
