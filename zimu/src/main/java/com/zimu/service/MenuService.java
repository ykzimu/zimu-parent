package com.zimu.service;

import com.github.pagehelper.PageInfo;
import com.zimu.domain.info.MenuInfo;
import com.zimu.entity.MenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-04
 */
public interface MenuService extends IService<MenuEntity> {

    PageInfo<MenuInfo> listData();

    PageInfo<MenuEntity> getMenus();

    MenuEntity getMenuById(Long id);

    boolean sortMenu(String value);

    boolean changeMenuStatusById(Long id, Boolean delFlag, Boolean isShow);

    boolean saveMenu(MenuEntity menuEntity);

    boolean updateMenu(MenuEntity menuEntity);
}
