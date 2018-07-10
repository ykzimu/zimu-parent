package com.zimu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.zimu.domain.info.UserInfo;
import com.zimu.service.UserService;

@Component("userInfoOauth2UserService")
public class UserInfoOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2User oauth2User = (DefaultOAuth2User) super.loadUser(userRequest);
        UserInfo userInfo = userService.getUserInfo(oauth2User);
        return userInfo;
    }

}
