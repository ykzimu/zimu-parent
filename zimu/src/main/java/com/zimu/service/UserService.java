package com.zimu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zimu.domain.info.DataTablesInfo;
import com.zimu.domain.info.SearchInfo;
import com.zimu.domain.info.UserInfo;
import com.zimu.entity.UserEntity;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 杨坤
 * @since 2019-04-04
 */
public interface UserService extends IService<UserEntity> {

    UserEntity getUserByUsername(String username);

    UserInfo getUserInfoByUsername(String username);

    List<String> getRoleCodesByUserId(Long userId);

    void test();

    UserInfo getUserInfo(DefaultOAuth2User oauth2User);

    Boolean registerUser(UserEntity userEntity) throws Exception;

    IPage<UserEntity> getUsers(SearchInfo searchInfo);

    IPage<UserEntity> getUsers(DataTablesInfo dataTablesInfo);

    int deleteUserByIds(List<Long> userIds);

    boolean updPwd(String password);

}
