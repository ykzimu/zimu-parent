package com.zimu.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.sitemesh.webapp.contentfilter.io.HttpContentType;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zimu.common.utils.LoginUserUtils;
import com.zimu.common.utils.MenuUtils;
import com.zimu.common.utils.SpringContextUtils;
import com.zimu.component.MenuComponent;
import com.zimu.domain.info.MenuInfo;

public class MenuInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {

        // 如果是模板，则不进行处理
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/layouts/")) {
            return;
        }

        // 如果是ajax请求，也不进行处理
        String type = new HttpContentType(response.getContentType()).getType();
        if ("application/json".equals(type)) {
            return;
        }

        // 如果已经有数据，也不进行处理
        if (request.getAttribute(MenuUtils.CLICK_MENU_ID) != null) {
            return;
        }

        // 从session获取当前用户菜单
        List<MenuInfo> menuInfos = MenuUtils.getMenuInfos();
        // 如果菜单不存在，session
        if ((menuInfos == null || menuInfos.isEmpty()) && LoginUserUtils.isLogin()) {
            // 获取当前登录用户信息
            Long userId = LoginUserUtils.getUserInfo().getId();
            MenuComponent menuComponent = SpringContextUtils.getBean(MenuComponent.class);
            menuInfos = menuComponent.getMenus(userId);
            MenuUtils.setMenuInfos(menuInfos);
        }

        // 获取当前请求URI对应的菜单信息
        MenuInfo menuInfo = MenuUtils.getMenuInfo(requestURI);
        if (menuInfo == null) {
            return;
        }

        // 构造面包屑导航
        List<MenuInfo> breadcrumbs = new ArrayList<>();
        if (StringUtils.isNotBlank(menuInfo.getParentIds())) {
            String strs[] = menuInfo.getParentIds().split(",");
            if (strs.length > 0) {
                for (String str : strs) {
                    if (StringUtils.isNotBlank(str) && !"0".equals(str) && StringUtils.isNumeric(str)) {
                        MenuInfo info = MenuUtils.getMenuInfo(Long.parseLong(str));
                        if (info != null) {
                            breadcrumbs.add(info);
                        }
                    }
                }
            }
        }
        breadcrumbs.add(menuInfo);// 当期页在导航

        // 菜单赋值
        request.setAttribute(MenuUtils.CLICK_MENU_ID, menuInfo.getId().toString());
        request.setAttribute(MenuUtils.CLICK_MENU_NAME, menuInfo.getMenuName());
        request.setAttribute(MenuUtils.CLICK_MENU_PARENT_IDS, menuInfo.getParentIds());
        request.setAttribute(MenuUtils.MENU_BREAD_CRUMBS, breadcrumbs);
    }
}
