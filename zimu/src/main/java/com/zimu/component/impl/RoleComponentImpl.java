package com.zimu.component.impl;

import com.zimu.common.Constants;
import com.zimu.component.RoleComponent;
import com.zimu.dao.*;
import com.zimu.domain.entity.*;
import com.zimu.domain.info.RoleMenuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RoleComponentImpl implements RoleComponent {

    @Autowired
    private RoleEntityMapper roleEntityMapper;

    @Autowired
    private UserRoleEntityMapper userRoleEntityMapper;

    @Autowired
    private GroupEntityMapper groupEntityMapper;

    @Autowired
    private GroupRoleEntityMapper groupRoleEntityMapper;

    @Autowired
    private UserGroupEntityMapper userGroupEntityMapper;

    // 查询表结构，构造表结构权限
    @Override
    public Map<String, String[]> getRoleMenus() {

        // 查询参数
        Map<String, Object> params = new HashMap<>();
        params.put("delFlag", 1);
        List<RoleMenuInfo> list = roleEntityMapper.selectRoleMenus(params);
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }

        String rolePrefix = "ROLE_";
        int len = rolePrefix.length();

        // 中间量
        Map<String, List<String>> map = new HashMap<>();
        // 遍历数据
        for (RoleMenuInfo roleMenuInfo : list) {
            String code = roleMenuInfo.getRoleCode().substring(len);
            List<String> roleCodes = map.computeIfAbsent(roleMenuInfo.getMenuHref(), x -> new ArrayList<>());
            roleCodes.add(code);
        }

        Map<String, String[]> result = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> hrefs = entry.getValue();
            String[] array = new String[hrefs.size()];
            String[] value = hrefs.toArray(array);
            result.put(entry.getKey(), value);
        }

        // 获取迭代器
        return result;
    }


    @Override
    public List<RoleEntity> getRolesByUserId(Long userId) {
        return getRolesByUserIdA(userId);
    }

    /**
     * 方法1：使用sql直接查询
     *
     * @param userId
     * @return
     */
    private List<RoleEntity> getRolesByUserIdA(Long userId) {
        return roleEntityMapper.selectByUserId(userId);
    }

    /**
     * 方法2：使用sql查询，并使用java处理
     *
     * @param userId
     * @return
     */
    private List<RoleEntity> getRolesByUserIdB(Long userId) {
        Set<Long> roleIds = new HashSet<>();

        // 查询用户角色关联表
        UserRoleEntityExample userRoleEntityExample = new UserRoleEntityExample();
        userRoleEntityExample.createCriteria().andUserIdEqualTo(userId).andDelFlagNotEqualTo(Constants.DEL_FLAG_OK);
        List<UserRoleEntity> userRoleEntities = userRoleEntityMapper.selectByExample(userRoleEntityExample);
        if (userRoleEntities != null && !userRoleEntities.isEmpty()) {
            Set<Long> rIds = userRoleEntities.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toSet());
            roleIds.addAll(rIds);
        }

        //查询用户组信息
        UserGroupEntityExample userGroupEntityExample = new UserGroupEntityExample();
        userGroupEntityExample.createCriteria().andUserIdEqualTo(userId).andDelFlagNotEqualTo(Constants.DEL_FLAG_OK);
        List<UserGroupEntity> userGroupEntities = userGroupEntityMapper.selectByExample(userGroupEntityExample);
        if (userGroupEntities != null && !userGroupEntities.isEmpty()) {

            //查询组信息
            List<Long> groupIds = userGroupEntities.stream().map(UserGroupEntity::getGroupId).collect(Collectors.toList());
            GroupEntityExample groupEntityExample = new GroupEntityExample();
            groupEntityExample.createCriteria().andIdIn(groupIds).andDelFlagNotEqualTo(Constants.DEL_FLAG_OK);
            List<GroupEntity> groupEntities = groupEntityMapper.selectByExample(groupEntityExample);
            if (groupEntities != null && !groupEntities.isEmpty()) {

                //查询组角色信息
                List<Long> gIds = groupEntities.stream().map(GroupEntity::getId).collect(Collectors.toList());
                GroupRoleEntityExample groupRoleEntityExample = new GroupRoleEntityExample();
                groupRoleEntityExample.createCriteria().andGroupIdIn(gIds).andDelFlagNotEqualTo(Constants.DEL_FLAG_OK);
                List<GroupRoleEntity> groupRoleEntities = groupRoleEntityMapper.selectByExample(groupRoleEntityExample);

                if (groupRoleEntities != null && !groupRoleEntities.isEmpty()) {
                    Set<Long> rIds = groupRoleEntities.stream().map(GroupRoleEntity::getRoleId).collect(Collectors.toSet());
                    roleIds.addAll(rIds);
                }
            }
        }

        // 构造角色
        if (roleIds.isEmpty()) {
            return Collections.emptyList();
        }
        // 查询角色表
        RoleEntityExample roleEntityExample = new RoleEntityExample();
        roleEntityExample.createCriteria().andIdIn(new ArrayList<>(roleIds)).andDelFlagNotEqualTo(Constants.DEL_FLAG_OK);
        List<RoleEntity> roleEntities = roleEntityMapper.selectByExample(roleEntityExample);
        if (roleEntities == null || roleEntities.isEmpty()) {
            return Collections.emptyList();
        }
        return roleEntities;
    }

}
