package com.zimu.controller;

import com.zimu.view.IndexView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class IndexController {

    /**
     * 首页
     *
     * @return ModelAndView
     */
    @GetMapping(value = {"/", "/index"})
    public ModelAndView index() {
        return IndexView.builder().build().view();
    }

}
