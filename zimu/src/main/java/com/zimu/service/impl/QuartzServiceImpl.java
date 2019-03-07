package com.zimu.service.impl;

import com.zimu.common.utils.SpringContextUtils;
import com.zimu.domain.info.SelectInfo;
import com.zimu.quartz.JobData;
import com.zimu.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class QuartzServiceImpl implements QuartzService {

    private static final String GROUP_NAME = "zimu";

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public List<JobData> jobList() throws SchedulerException {

        List<JobData> jobDataList = new ArrayList<>();
        Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());
        for (TriggerKey triggerKey : triggerKeys) {
            Trigger trigger = scheduler.getTrigger(triggerKey);
            Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
            JobKey jobKey = trigger.getJobKey();
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            JobData jobData = new JobData(triggerState, trigger, jobDetail);
            if (trigger instanceof CronTrigger) {
                jobData.setCron(((CronTrigger) trigger).getCronExpression());
            }
            jobDataList.add(jobData);
        }
        return jobDataList;
    }

    @Override
    public boolean addJob(String name, String beanName, String cron, Date startTime, Date endTime, String customProps, String description) {
        try {

            JobKey jobKey = new JobKey(beanName + "JobDetail", GROUP_NAME);
            TriggerKey triggerKey = new TriggerKey(beanName + "Trigger", GROUP_NAME);
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if (trigger != null) {
                return false;
            }

            JobDetail jd = scheduler.getJobDetail(jobKey);
            if (jd != null) {
                return false;
            }

            QuartzJobBean beanClazz = SpringContextUtils.getBean(beanName, QuartzJobBean.class);
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("name", name);


            JobDetailImpl jdi = new JobDetailImpl();
            jdi.setKey(jobKey);
            jdi.setJobClass(beanClazz.getClass());
            jdi.setJobDataMap(jobDataMap);
            jdi.setDurability(true);
            jdi.setDescription(description);


            CronTriggerImpl cti = new CronTriggerImpl();
            cti.setJobKey(jdi.getKey());
            cti.setKey(triggerKey);
            cti.setJobDataMap(jobDataMap);
            cti.setStartTime(startTime);
            cti.setEndTime(endTime);
            cti.setCronExpression(cron);
            cti.setDescription(description);


            scheduler.scheduleJob(jdi, cti);
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
        return true;
    }
}
