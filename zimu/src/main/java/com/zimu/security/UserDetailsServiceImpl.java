package com.zimu.security;

import com.zimu.domain.info.UserInfo;
import com.zimu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService, AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return this.loadUserInfo(username);
    }

    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) {
        return this.loadUserInfo(token.getName());
    }

    private UserInfo loadUserInfo(String username) {
        // 查询用户信息
        UserInfo userInfo = userService.getUserInfoByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("username not found.");
        }
        return userInfo;
    }
}
