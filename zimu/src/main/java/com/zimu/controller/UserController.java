package com.zimu.controller;

import com.zimu.common.exception.ValidationException;
import com.zimu.common.utils.LoginUserUtils;
import com.zimu.domain.info.JsonView;
import com.zimu.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("index")
	public ModelAndView index() {
		// 视图
		ModelAndView mv = new ModelAndView("/views/user/info");
		return mv;
	}

	@GetMapping("info")
	public ModelAndView info() {
		// 视图
		ModelAndView mv = new ModelAndView("/views/user/info");
		return mv;
	}

	@GetMapping("pwd")
	public ModelAndView pwd() {
		// 视图
		ModelAndView mv = new ModelAndView("/views/user/pwd");
		return mv;
	}

	/**
	 * 密码验证
	 *
	 * @param password
	 *            密码
	 * @return JsonView
	 */
	@RequestMapping(value = "validatePwd", method = RequestMethod.POST)
	@ResponseBody
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
	 * @param password
	 *            原密码
	 * @param newPassword
	 *            新密码
	 * @return JsonView
	 */
	@RequestMapping(value = "updPwd", method = RequestMethod.POST)
	@ResponseBody
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
	 * @param password
	 *            原密码
	 * @param newPassword
	 *            新密码
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
