package com.zimu.component;

import com.zimu.domain.info.MenuInfo;
import com.zimu.entity.MenuEntity;
import com.zimu.entity.RoleEntity;

import java.util.List;

public interface MenuComponent {

    List<MenuInfo> listData();

    List<MenuInfo> getMenus(Long userId);

    List<MenuInfo> getMenus(Long userId, List<RoleEntity> roleEntities);

    List<MenuEntity> getSortMenus();

}
