package com.zimu.controller;

import com.zimu.view.DashboardIndexView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 仪表盘
 *
 * @author yk
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index() {
        return DashboardIndexView.builder().build().view();
    }
}
