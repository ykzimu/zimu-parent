package com.zimu.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zimu.common.exception.ValidationException;
import com.zimu.common.utils.LoginUserUtils;
import com.zimu.domain.info.DataTablesInfo;
import com.zimu.domain.info.DataTablesView;
import com.zimu.domain.info.JsonView;
import com.zimu.domain.info.UserInfo;
import com.zimu.entity.UserEntity;
import com.zimu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户列表
     */
    @PostMapping("/listData")
    public DataTablesView listData(DataTablesInfo dataTablesInfo) {
        IPage<UserEntity> page = userService.getUsers(dataTablesInfo);
        UserInfo userInfo = LoginUserUtils.getUserInfo();
        DataTablesView dataTablesView = new DataTablesView<>(page);
        JSONObject extendData = new JSONObject();
        extendData.put("userId", userInfo.getId());
        dataTablesView.setExtendData(extendData);
        return dataTablesView;
    }

    /**
     * 更新用户锁定状态
     */
    @PostMapping(value = "updateLockStatus")
    public JsonView updateLockStatus(Long userId, Boolean state) {
        UserInfo userInfo = LoginUserUtils.getUserInfo();
        JsonView jsonView = new JsonView();

        Integer isLocked = state ? 0 : 1;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setIsLocked(isLocked);
        userEntity.setUpdateBy(userInfo.getUsername());
        userEntity.setUpdateDate(LocalDateTime.now());

        // 更新密码
        userService.updateById(userEntity);

        return jsonView;
    }


    /**
     * 密码验证
     *
     * @param password 密码
     * @return JsonView
     */
    @PostMapping(value = "validatePwd")
    public JsonView validatePwd(String password) {
        JsonView jsonView = new JsonView();

        boolean flag = passwordEncoder.matches(password, LoginUserUtils.getUserInfo().getPassword());
        if (!flag) {
            throw new ValidationException("原密码错误!");
        }

        return jsonView;
    }

    /**
     * 更新密码
     *
     * @param password    原密码
     * @param newPassword 新密码
     * @return JsonView
     */
    @PostMapping(value = "updPwd")
    public JsonView updPwd(String password, String newPassword) {
        JsonView jsonView = new JsonView();

        // 数据校验
        validateUpdPwd(password, newPassword);

        // 更新密码
        userService.updPwd(newPassword);

        return jsonView;
    }

    /**
     * 数据校验
     *
     * @param password    原密码
     * @param newPassword 新密码
     */
    private void validateUpdPwd(String password, String newPassword) {
        if (StringUtils.isBlank(password)) {
            throw new ValidationException("请输入原密码！");
        }

        if (password.trim().length() < 6 || password.trim().length() > 16) {
            throw new ValidationException("原密码6~16位！");
        }

        if (StringUtils.isBlank(newPassword)) {
            throw new ValidationException("请输入新密码！");
        }

        if (newPassword.trim().length() < 6 || newPassword.trim().length() > 16) {
            throw new ValidationException("新密码6~16位！");
        }

        if (password.trim().equals(newPassword.trim())) {
            throw new ValidationException("新密码与原密码不能相同！");
        }

        boolean flag = passwordEncoder.matches(password, LoginUserUtils.getUserInfo().getPassword());
        if (!flag) {
            throw new ValidationException("原密码错误!");
        }
    }

}
