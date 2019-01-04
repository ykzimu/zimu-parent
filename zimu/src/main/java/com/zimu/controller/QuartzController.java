package com.zimu.controller;

import com.zimu.common.utils.SpringContextUtils;
import com.zimu.domain.info.JsonView;
import com.zimu.domain.info.SelectInfo;
import com.zimu.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("/views/quartz/list");
        return mv;
    }

    @GetMapping("/job/list")
    @ResponseBody
    public JsonView jobList() {
        JsonView jsonView = new JsonView();
        List<SelectInfo> selectInfoList = quartzService.beansList();
        jsonView.setData(selectInfoList);
        return jsonView;
    }
}
