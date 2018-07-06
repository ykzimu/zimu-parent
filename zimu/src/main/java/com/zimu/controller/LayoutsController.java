package com.zimu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("layouts")
public class LayoutsController {

	@GetMapping("decorator")
	public ModelAndView decorator() {
		ModelAndView mv = new ModelAndView("/layouts/decorator");
		return mv;
	}

	/**
	 * sitemesh模板引擎
	 *
	 * @return
	 */
	@GetMapping("cms")
	public ModelAndView cms() {
		ModelAndView mv = new ModelAndView("/layouts/cms");
		return mv;
	}
}
