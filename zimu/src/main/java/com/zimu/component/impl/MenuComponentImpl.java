package com.zimu.component.impl;

import com.zimu.common.Constants;
import com.zimu.component.MenuComponent;
import com.zimu.component.RoleComponent;
import com.zimu.dao.MenuEntityMapper;
import com.zimu.dao.RoleMenuEntityMapper;
import com.zimu.domain.entity.*;
import com.zimu.domain.info.MenuInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MenuComponentImpl implements MenuComponent {

    @Autowired
    private MenuEntityMapper menuEntityMapper;

    @Autowired
    private RoleComponent roleComponent;

    @Autowired
    private RoleMenuEntityMapper roleMenuEntityMapper;

    @Override
    public List<MenuInfo> listData() {
        //查询所有菜单
        MenuEntityExample example = new MenuEntityExample();
        example.setOrderByClause(" menu_level ASC , menu_sort ASC ");
        List<MenuEntity> list = menuEntityMapper.selectByExample(example);

        //用于快速判定是否有子元素
        Set<Long> sets = list.stream().map(MenuEntity::getParentId).collect(Collectors.toSet());
        return menuInfos(list, sets, 1);
    }

    @Override
    public List<MenuInfo> getMenus(Long userId) {
        //获取角色信息
        List<RoleEntity> roleEntities = roleComponent.getRolesByUserId(userId);
        return getMenus(userId, roleEntities);
    }

    @Override
    public List<MenuInfo> getMenus(Long userId, List<RoleEntity> roleEntities) {
        List<MenuInfo> menuInfos = new ArrayList<>();
        //获取角色信息
        if (roleEntities == null || roleEntities.isEmpty()) {
            return menuInfos;
        }

        //获取roleIds
        List<Long> roleIds = roleEntities.stream().map(RoleEntity::getId).collect(Collectors.toList());

        //获取菜单
        RoleMenuEntityExample roleMenuEntityExample = new RoleMenuEntityExample();
        roleMenuEntityExample.createCriteria().andRoleIdIn(roleIds).andDelFlagNotEqualTo(Constants.DEL_FLAG_OK);
        List<RoleMenuEntity> roleMenuEntities = roleMenuEntityMapper.selectByExample(roleMenuEntityExample);
        if (roleMenuEntities == null || roleMenuEntities.isEmpty()) {
            return menuInfos;
        }

        //获取menuIds
        List<Long> menuIds = roleMenuEntities.stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList());

        //查询所有菜单
        MenuEntityExample example = new MenuEntityExample();
        example.createCriteria().andIdIn(menuIds).andMenuTypeEqualTo("LEFT_MENU").andIsShowEqualTo(1).andDelFlagNotEqualTo(Constants.DEL_FLAG_OK);
        example.setOrderByClause(" menu_level ASC , menu_sort ASC ");
        List<MenuEntity> list = menuEntityMapper.selectByExample(example);

        //用于快速判定是否有子元素
        Set<Long> sets = list.stream().map(MenuEntity::getParentId).collect(Collectors.toSet());

        menuInfos = menuInfos(list, sets, 1);
        return menuInfos;
    }

    /**
     * 递归处理菜单，可以无限分层
     *
     * @param list      将要排序的菜单
     * @param sets      中间量，用于快速判断是否有子元素
     * @param menuLevel 菜单级别
     * @return 分层左菜单
     */
    private List<MenuInfo> menuInfos(List<MenuEntity> list, Set<Long> sets, int menuLevel) {

        //新建数据列表
        List<MenuInfo> result = new ArrayList<>();
        //封装数据
        for (MenuEntity menuEntity : list) {

            //从根目录算起，逐层推算，发现层次不同，则当前层已经算完
            if (menuLevel != menuEntity.getMenuLevel()) {
                return result;
            }

            //数据封装
            MenuInfo menuInfo = new MenuInfo();
            try {
                BeanUtils.copyProperties(menuEntity, menuInfo);
            } catch (Exception e) {
                //正常情况下，基本不会报异常
            }
            result.add(menuInfo);

            //判断当前节点是否有子元素
            boolean flag = sets.contains(menuEntity.getId());
            //没有子元素，则进入下一次循环
            if (!flag) {
                continue;
            }

            //有子元素，查询所有子元素
            List<MenuEntity> childAll = getChildListById(menuEntity.getId(), list);
            //再进行递归处理
            List<MenuInfo> childList = menuInfos(childAll, sets, menuLevel + 1);
            //递归算出所有子元素，并设置子元素
            menuInfo.setChildList(childList);
            menuInfo.setChildren(childList);

        }

        //数据返回
        return result;

    }

    /**
     * 排序所有左菜单
     *
     * @return 排序后的菜单
     */
    @Override
    public List<MenuEntity> getSortMenus() {

        //查询所有菜单
        MenuEntityExample example = new MenuEntityExample();
        example.setOrderByClause(" menu_level ASC , menu_sort ASC ");
        List<MenuEntity> list = menuEntityMapper.selectByExample(example);

        //用于快速判定是否有子元素
        Set<Long> sets = new HashSet<>();
        for (MenuEntity menuEntity : list) {
            sets.add(menuEntity.getParentId());
        }

        //递归排序
        return sortMenuList(list, sets, 1);
    }


    /**
     * 递归排序菜单
     *
     * @param list      将要排序的菜单
     * @param sets      中间量，用于快速判断是否有子元素
     * @param menuLevel 菜单级别
     * @return 排序后的菜单
     */
    private List<MenuEntity> sortMenuList(List<MenuEntity> list, Set<Long> sets, int menuLevel) {

        //新建列表，用于封装数据
        List<MenuEntity> result = new ArrayList<>();
        //封装数据
        for (MenuEntity menuEntity : list) {
            if (menuLevel != menuEntity.getMenuLevel()) {
                return result;
            }
            boolean flag = sets.contains(menuEntity.getId());
            result.add(menuEntity);
            menuEntity.setRemarks("0");
            if (flag) {
                List<MenuEntity> childList = getChildListById(menuEntity.getId(), list);
                menuEntity.setRemarks(childList.size() + "");
                List<MenuEntity> childAll = sortMenuList(childList, sets, menuLevel + 1);
                result.addAll(childAll);
            }
        }
        return result;
    }


    /**
     * 查询所有的子菜单
     *
     * @param id   父菜单ID
     * @param list 所以菜单
     * @return 当前ID下的所有子菜单
     */
    private List<MenuEntity> getChildListById(Long id, List<MenuEntity> list) {
        List<MenuEntity> childList = new ArrayList<>();
        for (MenuEntity menuEntity : list) {

            //空处理
            if (StringUtils.isBlank(menuEntity.getParentIds())) {
                continue;
            }

            //切位处理
            String[] ids = menuEntity.getParentIds().split(",");
            if (ids.length == 0) {
                continue;
            }

            //循环判断是否是子元素
            for (String p : ids) {
                if (id.toString().equals(p)) {
                    childList.add(menuEntity);
                    break;
                }
            }
        }
        return childList;
    }
}
