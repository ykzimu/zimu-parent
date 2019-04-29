package com.zimu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zimu.domain.info.MenuInfo;
import com.zimu.entity.MenuEntity;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-04
 */
public interface MenuService extends IService<MenuEntity> {

    IPage<MenuInfo> listData();

    IPage<MenuEntity> getMenus();

    MenuEntity getMenuById(Long id);

    boolean sortMenu(String value);

    boolean changeMenuStatusById(Long id, Boolean delFlag, Boolean isShow);

    boolean saveMenu(MenuEntity menuEntity);

}
