package com.zimu.common.utils;

import com.zimu.domain.info.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginUserUtils {

    // 获取是否登录
    public static boolean isLogin() {
        UserInfo userInfo = getUserInfo();
        boolean isLogin = false;
        if (userInfo != null && userInfo.getId() != null && userInfo.getId() != 0L) {
            isLogin = true;
        }
        return isLogin;
    }

    // 获取登录用户信息
    public static UserInfo getUserInfo() {
        try {
            UserInfo userInfo = null;
            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (obj instanceof UserInfo) {
                userInfo = (UserInfo) obj;
            }
            return userInfo;
        } catch (Exception e) {
            return null;
        }
    }
}
