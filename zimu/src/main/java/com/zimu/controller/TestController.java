package com.zimu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zimu.common.utils.LoginUserUtils;
import com.zimu.domain.info.JsonView;
import com.zimu.domain.info.UserInfo;

@Controller
@RequestMapping(value = "test")
public class TestController {

	@GetMapping(value = "myTest")
	@ResponseBody
	public JsonView myTest() {
		JsonView jsonView = new JsonView();
		UserInfo userInfo = LoginUserUtils.getUserInfo();
		jsonView.setData(userInfo);
		return jsonView;
	}

}
