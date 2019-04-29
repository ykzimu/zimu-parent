package com.zimu.security;

import com.zimu.domain.info.UserInfo;
import com.zimu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl extends DefaultOAuth2UserService implements UserDetailsService, AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

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

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        DefaultOAuth2User oauth2User = (DefaultOAuth2User) super.loadUser(userRequest);
        return userService.getUserInfo(oauth2User);
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
