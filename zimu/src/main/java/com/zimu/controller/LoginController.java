package com.zimu.controller;

import com.zimu.common.utils.LoginUserUtils;
import com.zimu.view.LoginView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LoginController {

    /**
     * 登录页
     *
     * @return ModelAndView
     */
    @GetMapping(value = {"/login", "/auth/login"})
    public ModelAndView login() {
        // 视图
        ModelAndView mv = new ModelAndView();
        boolean islogin = LoginUserUtils.isLogin();
        if (islogin) {// 已登录，直接跳转至首页
            mv.setViewName("redirect:/");
        } else {// 未登陆，跳转到登录页
            return LoginView.builder().build().view();
        }
        return mv;
    }
}
