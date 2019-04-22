package com.zimu.common.utils;

import com.zimu.domain.info.UserInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author yk
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginUserUtils {

    // 获取是否登录
    public static boolean isLogin() {
        UserInfo userInfo = getUserInfo();
        return userInfo != null && userInfo.getId() != null && userInfo.getId() != 0L;
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
