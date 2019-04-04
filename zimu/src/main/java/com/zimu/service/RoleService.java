package com.zimu.service;

import com.github.pagehelper.PageInfo;
import com.zimu.entity.RoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-04
 */
public interface RoleService extends IService<RoleEntity> {

    PageInfo<RoleEntity> getRoles();
}
