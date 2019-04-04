package com.zimu.controller;

import com.zimu.common.MessageCode;
import com.zimu.common.exception.ValidationException;
import com.zimu.common.utils.LoginUserUtils;
import com.zimu.common.utils.ValidateCodeUtils;
import com.zimu.component.MenuComponent;
import com.zimu.domain.info.JsonView;
import com.zimu.domain.info.MenuInfo;
import com.zimu.domain.info.UserInfo;
import com.zimu.entity.UserEntity;
import com.zimu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 公用接口，未登陆下不进行拦截
 */

@Slf4j
@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MenuComponent menuComponent;

    /**
     * 数据校验
     *
     * @param username
     * @param password
     * @return JsonView
     */
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("/views/login");
        return mv;
    }

    /**
     * 数据校验
     *
     * @param username
     * @param password
     * @return JsonView
     */
    @PostMapping("/validateLogin")
    @ResponseBody
    public JsonView validateLogin(String username, String password) {
        JsonView jsonView = new JsonView();

        UserEntity userEntity = userService.getUserByUsername(username);
        if (userEntity == null) {
            throw new ValidationException(MessageCode.ERROR_E0003);
        }

        boolean flag = passwordEncoder.matches(password, userEntity.getPassword());
        if (!flag) {
            throw new ValidationException(MessageCode.ERROR_E0003);
        }

        return jsonView;
    }

    /**
     * @param username 用户名
     * @param email    邮件
     * @param mobile   手机号
     * @return JsonView
     */
    @GetMapping("/validateRegister")
    @ResponseBody
    public JsonView validateRegister(String username, String email, String mobile) {
        JsonView jsonView = new JsonView();

        String code = null;
        String name = "";
        if (StringUtils.isNotBlank(username)) {
            code = MessageCode.INFO_I0001;
            name = username;
        } else if (StringUtils.isNotBlank(email)) {
            code = MessageCode.INFO_I0002;
            name = email;
        } else if (StringUtils.isNotBlank(mobile)) {
            code = MessageCode.INFO_I0003;
            name = mobile;
        }

        UserEntity userEntity = userService.getUserByUsername(name);
        if (userEntity != null) {
            throw new ValidationException(code, name);
        }

        return jsonView;
    }

    /**
     * 验证码校验
     */
    @GetMapping("/validateCode")
    @ResponseBody
    public JsonView validateCode(String validateCode) {

        JsonView jsonView = new JsonView();

        // 验证码非空长度校验
        if (StringUtils.isBlank(validateCode) || validateCode.trim().length() != 4) {
            throw new ValidationException(MessageCode.INFO_I0004, validateCode);
        }

        boolean flag = ValidateCodeUtils.validate(validateCode);
        if (!flag) {
            throw new ValidationException(MessageCode.INFO_I0004, validateCode);
        }

        return jsonView;

    }

    /**
     * 生成验证码图片
     */
    @GetMapping("/getCodeImage")
    @ResponseBody
    public void getCodeImage() {
        try {
            ValidateCodeUtils.createImage();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    /**
     * 验证码校验
     */
    @GetMapping("/refesh")
    @ResponseBody
    public JsonView refesh(HttpSession httpSession) {

        JsonView jsonView = new JsonView();
        boolean isLogin = LoginUserUtils.isLogin();
        if (isLogin) {
            UserInfo userInfo = LoginUserUtils.getUserInfo();
            List<MenuInfo> menuInfos = menuComponent.getMenus(userInfo.getId());
            userInfo.setMenuInfos(menuInfos);
        }
        return jsonView;

    }

}
