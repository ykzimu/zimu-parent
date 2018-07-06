package com.zimu.component;

import java.util.List;
import java.util.Map;

import com.zimu.domain.entity.MenuEntity;
import com.zimu.domain.info.MenuInfo;

public interface MenuComponent {

    List<MenuInfo> getMenus(Long userId);

    Map<String, MenuInfo> getMenuInfoMapId();

    MenuInfo getMenuInfoById(Long id);

    Map<String, MenuInfo> getMenuInfoMapUri();

    MenuInfo getMenuInfoByUri(String uri);

    List<MenuEntity> getSortMenus();

}
