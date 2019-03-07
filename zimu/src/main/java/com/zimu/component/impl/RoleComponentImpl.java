package com.zimu.component.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zimu.component.RoleComponent;
import com.zimu.dao.RoleEntityMapper;
import com.zimu.domain.info.RoleMenuInfo;

@Component
public class RoleComponentImpl implements RoleComponent {

    @Autowired
    private RoleEntityMapper roleEntityMapper;

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
}
