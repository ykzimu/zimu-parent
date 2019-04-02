package com.zimu.component;

import com.zimu.domain.entity.MenuEntity;
import com.zimu.domain.entity.RoleEntity;
import com.zimu.domain.info.MenuInfo;

import java.util.List;

public interface MenuComponent {

    List<MenuInfo> getMenus(Long userId);

    List<MenuInfo> getMenus(Long userId ,List<RoleEntity> roleEntities);

    List<MenuEntity> getSortMenus();

}
