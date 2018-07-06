package com.zimu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zimu.common.exception.BusinessException;
import com.zimu.common.exception.ValidationException;
import com.zimu.common.utils.LoginUserUtils;
import com.zimu.domain.entity.UserEntity;
import com.zimu.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

	@Autowired
	private UserService userService;

	/**
	 * 首页
	 *
	 * @return ModelAndView
	 */
	@GetMapping(value = { "/", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	/**
	 * 注册页
	 *
	 * @return ModelAndView
	 */
	@GetMapping("/register")
	public ModelAndView register() {
		// 视图
		ModelAndView mv = new ModelAndView();
		boolean islogin = LoginUserUtils.isLogin();
		if (islogin) {// 已登录，直接跳转至首页
			mv.setViewName("redirect:/");
		} else {// 未登陆，跳转到登录页
			mv.setViewName("/views/register");
		}
		return mv;
	}

	/**
	 * 注册页
	 *
	 * @return ModelAndView
	 */
	@PostMapping("/register")
	public ModelAndView register(UserEntity userEntity) {
		// 视图
		ModelAndView mv = new ModelAndView("/views/register");
		try {
			mv.addObject("user", userEntity);
			boolean flag = userService.registerUser(userEntity);
			if (flag) {
				mv.setViewName("redirect:/register/success");
			}
		} catch (ValidationException e) {
			mv.addObject("msg", e.getMessage());
			log.info(e.getMessage());
		} catch (BusinessException e) {
			mv.addObject("msg", e.getMessage());
			log.info(e.getMessage());
		} catch (Exception e) {
			mv.addObject("msg", "系统异常！");
			log.error(e.getMessage(), e);
		}
		return mv;
	}

	/**
	 * 注册页
	 *
	 * @return ModelAndView
	 */
	@GetMapping("/register/success")
	public ModelAndView registerSuccess() {
		// 视图
		ModelAndView mv = new ModelAndView("/views/register_success");
		return mv;
	}

	/**
	 * 登录页
	 *
	 * @return ModelAndView
	 */
	@GetMapping(value = { "/login", "/auth/login" })
	public ModelAndView login() {
		// 视图
		ModelAndView mv = new ModelAndView();
		boolean islogin = LoginUserUtils.isLogin();
		if (islogin) {// 已登录，直接跳转至首页
			mv.setViewName("redirect:/");
		} else {// 未登陆，跳转到登录页
			mv.setViewName("/views/login");
		}
		return mv;
	}

	/**
	 * 注册页
	 *
	 * @return ModelAndView
	 */
	@GetMapping("/home")
	public ModelAndView home() {
		// 视图
		ModelAndView mv = new ModelAndView("/views/home");
		return mv;
	}

	/**
	 * 注册页
	 *
	 * @return ModelAndView
	 */
	@GetMapping("/success")
	public ModelAndView success(String message) {
		// 视图
		ModelAndView mv = new ModelAndView("/views/success");
		mv.addObject("message", message);
		return mv;
	}
}
