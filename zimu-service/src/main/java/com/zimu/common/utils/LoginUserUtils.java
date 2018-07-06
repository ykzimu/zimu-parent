package com.zimu.common.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.zimu.domain.info.UserInfo;

public class LoginUserUtils {

    // 获取是否登录
    public static boolean isLogin() {
        UserInfo userInfo = getUserInfoFromSecurity();
        boolean isLogin = false;
        if (userInfo != null && userInfo.getId() != null && userInfo.getId() != 0L) {
            isLogin = true;
        }
        return isLogin;
    }

    // 获取登录用户信息
    public static UserInfo getUserInfo() {
        return getUserInfoFromSecurity();
    }

    // 获取当前登录用户信息(通过spring security)
    private static UserInfo getUserInfoFromSecurity() {
        UserInfo userInfo = null;
        try {
            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (obj instanceof UserInfo) {
                userInfo = (UserInfo) obj;
            }
        } catch (Exception e) {
            return null;
        }
        return userInfo;
    }
}
