package com.zimu.service;

import com.github.pagehelper.PageInfo;
import com.zimu.domain.entity.MenuEntity;

public interface MenuService {

    PageInfo<MenuEntity> getMenus();

    MenuEntity getMenuById(Long id);

    boolean sortMenu(String value);

    boolean changeMenuStatusById(Long id, Boolean delFlag, Boolean isShow);

    boolean saveMenu(MenuEntity menuEntity);

    boolean updateMenu(MenuEntity menuEntity);
}
