package com.zimu.controller;

import com.zimu.component.QuartzComponent;
import com.zimu.domain.info.JsonView;
import com.zimu.quartz.JobData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private QuartzComponent quartzComponent;

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("/views/quartz/list");
        return mv;
    }

    @GetMapping("/bean/list")
    @ResponseBody
    public JsonView beanList() {
        JsonView jsonView = new JsonView();
        try {

            //List<SelectInfo> selectInfoList = quartzService.beansList();
            //jsonView.setData(selectInfoList);
        } catch (Exception e) {
            log.error("", e);
        }

        return jsonView;
    }

    @GetMapping("/job/list")
    @ResponseBody
    public JsonView jobList() {
        JsonView jsonView = new JsonView();
        try {
            List<JobData> jobDataList = quartzComponent.jobList();
            jsonView.setData(jobDataList);
        } catch (Exception e) {
            log.error("", e);
        }

        return jsonView;
    }

    @GetMapping("/job/add")
    @ResponseBody
    public JsonView jobAdd() {
        JsonView jsonView = new JsonView();
        try {
            String[] beanNames = applicationContext.getBeanNamesForType(QuartzJobBean.class);
            boolean flag = quartzComponent.addJob("定时统计", beanNames[0], "*/10 * * * * ?", new Date(), null, null, "我就是测试");
            jsonView.setData(flag);
        } catch (Exception e) {
            log.error("", e);
        }

        return jsonView;
    }
}
