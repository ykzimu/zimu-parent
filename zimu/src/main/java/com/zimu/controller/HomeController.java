package com.zimu.controller;

import com.zimu.view.HomeView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class HomeController {

    /**
     * HOME页
     *
     * @return ModelAndView
     */
    @GetMapping("/home")
    public ModelAndView home() {
        // 视图
        return HomeView.builder().build().view();
    }
}
