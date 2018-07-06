package com.zimu.component.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.zimu.common.CacheNames;
import com.zimu.component.MenuComponent;
import com.zimu.dao.MenuEntityMapper;
import com.zimu.dao.RoleMenuEntityMapper;
import com.zimu.dao.UserRoleEntityMapper;
import com.zimu.domain.entity.MenuEntity;
import com.zimu.domain.entity.MenuEntityExample;
import com.zimu.domain.entity.RoleMenuEntity;
import com.zimu.domain.entity.RoleMenuEntityExample;
import com.zimu.domain.entity.UserRoleEntity;
import com.zimu.domain.entity.UserRoleEntityExample;
import com.zimu.domain.info.MenuInfo;

@Component
@CacheConfig(cacheNames = CacheNames.CACHE_NAME_MUZI)
public class MenuComponentImpl implements MenuComponent {

    @Autowired
    private MenuEntityMapper menuEntityMapper;

    @Autowired
    private UserRoleEntityMapper userRoleEntityMapper;

    @Autowired
    private RoleMenuEntityMapper roleMenuEntityMapper;

    @Autowired
    private CacheManager cacheManager;


    public List<MenuInfo> getMenus(Long userId) {

        List<MenuInfo> menuInfos = new ArrayList<>();

        //获取角色信息
        UserRoleEntityExample userRoleEntityExample = new UserRoleEntityExample();
        userRoleEntityExample.createCriteria().andUserIdEqualTo(userId);
        List<UserRoleEntity> userRoleEntities = userRoleEntityMapper.selectByExample(userRoleEntityExample);
        if (userRoleEntities == null || userRoleEntities.isEmpty()) {
            return menuInfos;
        }
        List<Long> roleIds = new ArrayList<>();
        for (UserRoleEntity userRoleEntity : userRoleEntities) {
            roleIds.add(userRoleEntity.getRoleId());
        }

        //获取菜单
        RoleMenuEntityExample roleMenuEntityExample = new RoleMenuEntityExample();
        roleMenuEntityExample.createCriteria().andRoleIdIn(roleIds);
        List<RoleMenuEntity> roleMenuEntities = roleMenuEntityMapper.selectByExample(roleMenuEntityExample);
        if (roleMenuEntities == null || roleMenuEntities.isEmpty()) {
            return menuInfos;
        }
        List<Long> menuIds = new ArrayList<>();
        for (RoleMenuEntity roleMenuEntity : roleMenuEntities) {
            menuIds.add(roleMenuEntity.getMenuId());
        }

        //查询所有菜单
        MenuEntityExample example = new MenuEntityExample();
        example.createCriteria().andIdIn(menuIds).andMenuTypeEqualTo("LEFT_MENU").andIsShowEqualTo(1).andDelFlagEqualTo(0);
        example.setOrderByClause(" menu_level ASC , menu_sort ASC ");
        List<MenuEntity> list = menuEntityMapper.selectByExample(example);

        //用于快速判定是否有子元素
        Set<Long> sets = new HashSet<>();
        for (MenuEntity menuEntity : list) {
            sets.add(menuEntity.getParentId());
        }
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
                BeanUtils.copyProperties(menuInfo, menuEntity);
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

        }

        //数据返回
        return result;

    }

    /**
     * 缓存数据
     *
     * @return 所有菜单
     */
    @SuppressWarnings("unchecked")
	private List<MenuEntity> cacheMenus() {
        Cache cache = cacheManager.getCache(CacheNames.CACHE_NAME_MUZI);
        List<MenuEntity> cacheList = cache.get(CacheNames.CACHE_KEY_ALL_MENUS, List.class);
        if (cacheList != null && !cacheList.isEmpty()) {
            return cacheList;
        }

        MenuEntityExample example = new MenuEntityExample();
        example.createCriteria().andIsShowEqualTo(1).andDelFlagEqualTo(0);
        List<MenuEntity> list = menuEntityMapper.selectByExample(example);
        cache.put(CacheNames.CACHE_KEY_ALL_MENUS, list);
        return list;
    }

    @SuppressWarnings("unchecked")
	@Override
    public Map<String, MenuInfo> getMenuInfoMapId() {

        Cache cache = cacheManager.getCache(CacheNames.CACHE_NAME_MUZI);
        Map<String, MenuInfo> cacheMap = cache.get(CacheNames.CACHE_KEY_MENUINFO_MAP_ID, Map.class);
        if (cacheMap != null && !cacheMap.isEmpty()) {
            return cacheMap;
        }

        List<MenuEntity> list = cacheMenus();
        Map<String, MenuInfo> map = new HashMap<>();
        for (MenuEntity menuEntity : list) {
            MenuInfo menuInfo = new MenuInfo();
            try {
                BeanUtils.copyProperties(menuInfo, menuEntity);
            } catch (Exception e) {
            }
            map.put(menuEntity.getId().toString(), menuInfo);

        }
        cache.put(CacheNames.CACHE_KEY_MENUINFO_MAP_ID, map);
        return map;
    }

    @SuppressWarnings("unchecked")
	@Override
    public Map<String, MenuInfo> getMenuInfoMapUri() {

        Cache cache = cacheManager.getCache(CacheNames.CACHE_NAME_MUZI);
        Map<String, MenuInfo> cacheMap = cache.get(CacheNames.CACHE_KEY_MENUINFO_MAP_URI, Map.class);
        if (cacheMap != null && !cacheMap.isEmpty()) {
            return cacheMap;
        }
        List<MenuEntity> list = cacheMenus();
        Map<String, MenuInfo> map = new HashMap<>();
        for (MenuEntity menuEntity : list) {

            if (StringUtils.isBlank(menuEntity.getMenuHref()) || "javascript:void(0)".equals(menuEntity.getMenuHref().trim())) {
                continue;
            }
            MenuInfo menuInfo = new MenuInfo();
            try {
                BeanUtils.copyProperties(menuInfo, menuEntity);
                map.put(menuEntity.getMenuHref(), menuInfo);
            } catch (Exception e) {
            }


        }
        cache.put(CacheNames.CACHE_KEY_MENUINFO_MAP_URI, map);
        return map;
    }


    @Cacheable(key = "#id")
    @Override
    public MenuInfo getMenuInfoById(Long id) {
        return getMenuInfoMapId().get(id.toString());
    }

    @Cacheable(key = "#uri")
    @Override
    public MenuInfo getMenuInfoByUri(String uri) {
        return getMenuInfoMapUri().get(uri);
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
        List<MenuEntity> resultList = sortMenuList(list, sets, 1);
        return resultList;
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
