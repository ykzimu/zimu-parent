package com.zimu.controller;

import com.zimu.component.QuartzComponent;
import com.zimu.domain.info.DataTablesInfo;
import com.zimu.domain.info.DataTablesView;
import com.zimu.domain.info.JsonView;
import com.zimu.quartz.JobData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/quartz")
public class QuartzController {

    @Autowired
    private QuartzComponent quartzComponent;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 数据字典
     *
     * @return ModelAndView
     */
    @PostMapping("/listData")
    public DataTablesView listData(DataTablesInfo dataTablesInfo) {
        try {
            List<JobData> jobDataList = quartzComponent.jobList();
            return new DataTablesView<>(jobDataList);
        } catch (Exception e) {
            return DataTablesView.error(e);
        }
    }

    @GetMapping("/bean/list")
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

    @GetMapping("/job/add")
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

    @GetMapping("/job/delete")
    public JsonView jobDelete() {
        JsonView jsonView = new JsonView();
        try {
            String[] beanNames = applicationContext.getBeanNamesForType(QuartzJobBean.class);
            boolean flag = quartzComponent.deleteJob(beanNames[0]);
            jsonView.setData(flag);
        } catch (Exception e) {
            log.error("", e);
        }

        return jsonView;
    }
}
