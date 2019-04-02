package com.zimu.service.impl;

import com.github.pagehelper.PageInfo;
import com.zimu.common.enums.RoleEnum;
import com.zimu.common.utils.LoginUserUtils;
import com.zimu.component.CommonComponent;
import com.zimu.component.MenuComponent;
import com.zimu.dao.MenuEntityMapper;
import com.zimu.dao.RequestMappingEntityMapper;
import com.zimu.dao.RoleMenuEntityMapper;
import com.zimu.domain.entity.MenuEntity;
import com.zimu.domain.entity.RequestMappingEntity;
import com.zimu.domain.entity.RoleEntity;
import com.zimu.domain.entity.RoleMenuEntity;
import com.zimu.domain.info.UserInfo;
import com.zimu.service.MenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuComponent menuComponent;

    @Autowired
    private MenuEntityMapper menuEntityMapper;

    @Autowired
    private RequestMappingEntityMapper requestMappingEntityMapper;

    @Autowired
    private RoleMenuEntityMapper roleMenuEntityMapper;

    @Autowired
    private CommonComponent commonComponent;

    @Override
    public PageInfo<MenuEntity> getMenus() {

        List<MenuEntity> list = menuComponent.getSortMenus();
        PageInfo<MenuEntity> pageInfo = new PageInfo<MenuEntity>(list);

        return pageInfo;
    }

    @Override
    public MenuEntity getMenuById(Long id) {
        return menuEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean sortMenu(String value) {

        String[] items = value.split(",");
        if (items != null && items.length != 0) {
            for (String item : items) {
                if (StringUtils.isBlank(item)) {
                    continue;
                }
                String[] ids = item.split(":");
                if (ids == null || ids.length != 2 || !StringUtils.isNumeric(ids[0]) || !StringUtils.isNumeric(ids[1])) {
                    continue;
                }
                MenuEntity menuEntity = new MenuEntity();
                menuEntity.setId(Long.parseLong(ids[0]));
                menuEntity.setUpdateBy(LoginUserUtils.getUserInfo().getId().toString());
                menuEntity.setUpdateDate(new Date());
                menuEntity.setMenuSort(Integer.parseInt(ids[1]));
                menuEntityMapper.updateByPrimaryKeySelective(menuEntity);
            }
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean changeMenuStatusById(Long id, Boolean delFlag, Boolean isShow) {
        MenuEntity entity = menuEntityMapper.selectByPrimaryKey(id);
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setId(id);
        if (delFlag != null && delFlag) {
            menuEntity.setDelFlag(1 - entity.getDelFlag());
        }
        if (isShow != null && isShow) {
            menuEntity.setIsShow(1 - entity.getIsShow());
        }
        menuEntityMapper.updateByPrimaryKeySelective(menuEntity);
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean saveMenu(MenuEntity menuEntity) {

        Date date = new Date();

        UserInfo userInfo = LoginUserUtils.getUserInfo();
        Integer menuLevel = 1;
        String parentIds = "0";
        if (menuEntity.getParentId() != null) {
            MenuEntity parentEntity = menuEntityMapper.selectByPrimaryKey(menuEntity.getParentId());
            menuLevel = parentEntity.getMenuLevel() + 1;
            parentIds = parentEntity.getParentIds() + "," + menuEntity.getParentId();
        } else {
            menuEntity.setParentId(0L);
        }
        menuEntity.setIsShow(1);
        menuEntity.setDelFlag(0);
        menuEntity.setCreateBy(userInfo.getId().toString());
        menuEntity.setCreateDate(date);
        menuEntity.setMenuLevel(menuLevel);
        menuEntity.setParentIds(parentIds);
        menuEntity.setVersion(1);

        if (StringUtils.isNumeric(menuEntity.getMenuHref())) {
            RequestMappingEntity requestMappingEntity = requestMappingEntityMapper.selectByPrimaryKey(Long.parseLong(menuEntity.getMenuHref()));
            if (requestMappingEntity != null) {
                menuEntity.setMenuHref(requestMappingEntity.getPatterns());
            }
        }

        menuEntityMapper.insert(menuEntity);

        //获取超级管理员角色（默认新加菜单只有超级管理员有权限）
        RoleEntity roleEntity = commonComponent.getRoleByRoleCode(RoleEnum.ROLE_SUPER_ADMIN.getRoleCode());
        RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
        roleMenuEntity.setRoleId(roleEntity.getId());
        roleMenuEntity.setMenuId(menuEntity.getId());
        roleMenuEntity.setCreateBy(userInfo.getId().toString());
        roleMenuEntity.setCreateDate(date);
        roleMenuEntity.setDelFlag(0);
        roleMenuEntity.setVersion(1);
        roleMenuEntityMapper.insert(roleMenuEntity);

        return true;
    }


    @Override
    public boolean updateMenu(MenuEntity menuEntity) {
        Date date = new Date();
        UserInfo userInfo = LoginUserUtils.getUserInfo();
        menuEntity.setUpdateBy(userInfo.getId().toString());
        menuEntity.setUpdateDate(date);
        if (StringUtils.isNumeric(menuEntity.getMenuHref())) {
            RequestMappingEntity requestMappingEntity = requestMappingEntityMapper.selectByPrimaryKey(Long.parseLong(menuEntity.getMenuHref()));
            if (requestMappingEntity != null) {
                menuEntity.setMenuHref(requestMappingEntity.getPatterns());
            }
        }
        menuEntityMapper.updateByPrimaryKeySelective(menuEntity);
        return true;
    }


}
