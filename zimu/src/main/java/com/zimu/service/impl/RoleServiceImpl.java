package com.zimu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zimu.entity.RoleEntity;
import com.zimu.mapper.RoleMapper;
import com.zimu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {


    @Autowired
    private RoleMapper roleMapper;

    @Override
    public IPage<RoleEntity> getRoles() {
        List<RoleEntity> list = roleMapper.selectList(Wrappers.emptyWrapper());
        Page<RoleEntity> page = new Page<>();
        page.setRecords(list);
        return page;
    }
}
