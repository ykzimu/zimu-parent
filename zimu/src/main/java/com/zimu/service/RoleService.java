package com.zimu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zimu.entity.RoleEntity;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-04
 */
public interface RoleService extends IService<RoleEntity> {

    IPage<RoleEntity> getRoles();
}
