package com.zimu.service;

import com.github.pagehelper.PageInfo;
import com.zimu.domain.entity.RoleEntity;

public interface RoleService {

    PageInfo<RoleEntity> getRoles();
}
