package com.zimu.common.utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zimu.component.MenuComponent;
import com.zimu.domain.entity.MenuEntity;
import com.zimu.domain.info.MenuInfo;

import java.util.List;
import java.util.Map;

public class MenuUtils {

    public static final String MENU_LEFT = "leftMenus";

    public static final String MENU_MAP = "MENU_MAP";

    public static final String MENU_URI = "MENU_URI";

    public static final String CLICK_MENU = "clickMenu";

    public static final String CLICK_MENU_ID = "clickMenuId";

    public static final String CLICK_MENU_PARENT_IDS = "parentIds";

    public static final String CLICK_MENU_NAME = "clickMenuName";

    public static final String MENU_BREAD_CRUMBS = "breadcrumbs";

    private static MenuComponent getMenuComponent() {
        return SpringContextUtils.getBean(MenuComponent.class);
    }

    private static HttpSession getSession() {
        HttpServletRequest request = HttpServletManager.getRequest();
        return request.getSession();
    }

    public static void setMenuInfos(List<MenuInfo> menuInfos) {
        getSession().setAttribute(MENU_LEFT, menuInfos);
    }

    @SuppressWarnings("unchecked")
	public static List<MenuInfo> getMenuInfos() {
        return (List<MenuInfo>) getSession().getAttribute(MENU_LEFT);
    }

    public static void setMenuMap(Map<Long, MenuEntity> menuMap) {
        getSession().setAttribute(MENU_MAP, menuMap);
    }


    public static MenuInfo getMenuInfo(Long id) {
        return getMenuComponent().getMenuInfoById(id);
    }

    public static String getMenuName(Long id) {
        String menuName = "";
        MenuInfo menuInfo = getMenuInfo(id);
        if (menuInfo != null) {
            menuName = menuInfo.getMenuName();
        }
        return menuName;
    }

    public static MenuInfo getMenuInfo(String uri) {
        return getMenuComponent().getMenuInfoByUri(uri);
    }

    public static String getMenuName(String uri) {
        String menuName = "";
        MenuInfo menuInfo = getMenuInfo(uri);
        if (menuInfo != null) {
            menuName = menuInfo.getMenuName();
        }
        return menuName;
    }

}
