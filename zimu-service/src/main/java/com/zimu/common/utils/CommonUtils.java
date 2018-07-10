package com.zimu.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class CommonUtils {

    private static final String[] IP_HEADERS = {"X-Forwarded-For", "Proxy-Client-IP", "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR",};

    private static final String UNKNOWN = "unknown";
    /**
     * 手机号码正则表达式
     */
    private static final String MOBILE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

    private static final int MAX_IP_LENGTH = 15;

    private static final int MOBILE_LENGTH = 11;

    public static String getIpAddr() {
        HttpServletRequest request = HttpServletManager.getRequest();
        return getIpAddr(request);
    }

    public static String getIpAddr(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        // 遍历循环
        String ip = null;
        for (String ipHeader : IP_HEADERS) {
            ip = request.getHeader(ipHeader);
            if (StringUtils.isNotBlank(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
                break;
            }
        }

        // 获取信息
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else if (ip.length() > MAX_IP_LENGTH) {
            String[] ips = ip.split(",");
            for (String strIp : ips) {
                if (!(UNKNOWN.equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    public static boolean isMobile(String mobile) {
        if (StringUtils.isBlank(mobile) || !StringUtils.isNumeric(mobile) || mobile.trim().length() != MOBILE_LENGTH) {
            return false;
        }
        Pattern pattern = Pattern.compile(MOBILE_NUMBER_REG);
        Matcher m = pattern.matcher(mobile.trim());
        return m.matches();
    }

}
