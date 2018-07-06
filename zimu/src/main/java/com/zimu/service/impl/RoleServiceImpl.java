package com.zimu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zimu.dao.RoleEntityMapper;
import com.zimu.domain.entity.RoleEntity;
import com.zimu.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleEntityMapper roleEntityMapper;

    public PageInfo<RoleEntity> getRoles() {
        List<RoleEntity> list = roleEntityMapper.selectByExample(null);
        PageInfo<RoleEntity> pageInfo = new PageInfo<RoleEntity>(list);
        return pageInfo;
    }
}
