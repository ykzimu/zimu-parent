package com.zimu.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {


//    @Bean(name = "zimuJobDetail")
//    public JobDetailFactoryBean monthJobDetailFactoryBean() {
//        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
//        jobDetailFactoryBean.setJobClass(ZimuScheduledJob.class);
//        jobDetailFactoryBean.setDurability(true);
//        return jobDetailFactoryBean;
//    }
//
//
//    @Bean(name = "zimuCronTrigger")
//    public CronTriggerFactoryBean monthCronTriggerFactoryBean(@Autowired @Qualifier("zimuJobDetail") JobDetail jobDetail) {
//        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
//        cronTriggerFactoryBean.setJobDetail(jobDetail);
//
//        //测试用
//        cronTriggerFactoryBean.setCronExpression("*/10 * * * * ?");
//        return cronTriggerFactoryBean;
//    }

}
