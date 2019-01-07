package com.zimu.service;

import com.zimu.domain.info.SelectInfo;
import com.zimu.quartz.JobData;
import org.quartz.SchedulerException;

import java.util.Date;
import java.util.List;

public interface QuartzService {

    List<SelectInfo> beansList() throws SchedulerException;

    List<JobData> jobList() throws SchedulerException;

    boolean addJob(String name, String beanName, String cron, Date startTime, Date endTime, String customProps, String description);
}
