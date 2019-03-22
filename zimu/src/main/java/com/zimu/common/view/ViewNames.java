package com.zimu.common.view;

/**
 * 视图viewName
 */
public enum ViewNames {

    //看板首页
    DASHBOARD_INDEX("/views/dashboard/index"),

    //添加菜单页
    MENU_ADD("/views/menu/add"),

    //菜单页列表
    MENU_LIST("/views/menu/add");

    private String viewName;

    ViewNames(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
