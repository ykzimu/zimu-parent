package com.zimu.service;

import com.github.pagehelper.PageInfo;
import com.zimu.domain.entity.UserEntity;
import com.zimu.domain.info.DataTablesInfo;
import com.zimu.domain.info.SearchInfo;
import com.zimu.domain.info.UserInfo;

import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.List;

public interface UserService {

    UserEntity getUserById(Long id);

    UserEntity getUserByUsername(String username);

    List<String> getRolesByUserId(Long userId);

    List<String> getRoles(Long userId);

    void test();

    UserInfo getUserInfo(DefaultOAuth2User oauth2User);

    Boolean registerUser(UserEntity userEntity) throws Exception;

    PageInfo<UserEntity> getUsers(SearchInfo searchInfo);

    PageInfo<UserEntity> getUsers(DataTablesInfo dataTablesInfo);

    int deleteUserByIds(List<Long> userIds);

    boolean updPwd(String password);

}
