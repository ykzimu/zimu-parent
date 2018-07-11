package com.zimu.security;

import com.zimu.domain.entity.UserEntity;
import com.zimu.domain.info.UserInfo;
import com.zimu.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {

            // 查询用户信息
            UserEntity userEntity = userService.getUserByUsername(username);
            if (userEntity == null) {
                throw new UsernameNotFoundException("username not found.");
            }

            // 查询角色信息
            List<String> roles = userService.getRolesByUserId(userEntity.getId());
            Set<GrantedAuthority> authorities = new HashSet<>();
            SimpleGrantedAuthority grantedAuthority = null;
            for (String role : roles) {
                grantedAuthority = new SimpleGrantedAuthority(role);
                authorities.add(grantedAuthority);
            }
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(userEntity, userInfo);
            userInfo.setAuthorities(authorities);
            return userInfo;
        } catch (Exception e) {
            throw new UsernameNotFoundException("username not found.");
        }
    }
}
