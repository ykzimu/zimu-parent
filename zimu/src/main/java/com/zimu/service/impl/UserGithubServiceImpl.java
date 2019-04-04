package com.zimu.service.impl;

import com.zimu.entity.UserGithubEntity;
import com.zimu.mapper.UserGithubMapper;
import com.zimu.service.UserGithubService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * github用户 服务实现类
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-04
 */
@Service
public class UserGithubServiceImpl extends ServiceImpl<UserGithubMapper, UserGithubEntity> implements UserGithubService {

}
