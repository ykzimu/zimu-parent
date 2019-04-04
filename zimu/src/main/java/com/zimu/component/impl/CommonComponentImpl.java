package com.zimu.component.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zimu.component.CommonComponent;
import com.zimu.domain.info.SelectInfo;
import com.zimu.entity.RoleEntity;
import com.zimu.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommonComponentImpl implements CommonComponent {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleEntity getRoleByRoleCode(String roleCode) {

        //查询用户权限信息
        LambdaQueryWrapper<RoleEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(RoleEntity::getRoleCode, roleCode);
        List<RoleEntity> list = roleMapper.selectList(queryWrapper);
        if (list == null || list.size() != 1) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SelectInfo> getPageSizeList() {
        List<SelectInfo> list = new ArrayList<>();

        SelectInfo selectInfo = new SelectInfo();
        selectInfo.setId("10");
        selectInfo.setText("10");
        list.add(selectInfo);

        selectInfo = new SelectInfo();
        selectInfo.setId("20");
        selectInfo.setText("20");
        list.add(selectInfo);

        selectInfo = new SelectInfo();
        selectInfo.setId("50");
        selectInfo.setText("50");
        list.add(selectInfo);

        selectInfo = new SelectInfo();
        selectInfo.setId("100");
        selectInfo.setText("100");
        list.add(selectInfo);
        return list;
    }
}
