package com.zimu.component.impl;

import com.zimu.component.QuartzComponent;
import com.zimu.quartz.JobData;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class QuartzComponentImpl implements QuartzComponent {

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


            Class clazz = jobDetail.getJobClass();
            String[] beanNames = applicationContext.getBeanNamesForType(clazz);
            JobData jobData = new JobData(triggerState, trigger);
            if (trigger instanceof CronTrigger) {
                jobData.setCron(((CronTrigger) trigger).getCronExpression());
            }

            jobData.setBeanName(beanNames[0]);
            jobData.setBeanClass(clazz.getName());
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

            QuartzJobBean quartzJobBean = applicationContext.getBean(beanName, QuartzJobBean.class);
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("name", name);

            JobDetail jobDetail = JobBuilder.newJob(quartzJobBean.getClass())//
                    .withIdentity(jobKey)//
                    .usingJobData(jobDataMap)//
                    .storeDurably(true)//
                    .withDescription(description)//
                    .build();//

            CronTrigger cronTrigger = TriggerBuilder.newTrigger()//
                    .forJob(jobKey)//
                    .usingJobData(jobDataMap)//
                    .withIdentity(triggerKey)//
                    .startAt(startTime)//
                    .endAt(endTime)//
                    .withDescription(description)//
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))//
                    .build();

            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
        return true;
    }

    public boolean deleteJob(String beanName) {
        try {
            JobKey jobKey = new JobKey(beanName + "JobDetail", GROUP_NAME);
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
        return true;
    }
}
