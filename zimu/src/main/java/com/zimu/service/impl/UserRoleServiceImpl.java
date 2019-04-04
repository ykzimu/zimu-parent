package com.zimu.service.impl;

import com.zimu.entity.UserRoleEntity;
import com.zimu.mapper.UserRoleMapper;
import com.zimu.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色表 服务实现类
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-04
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {

}
