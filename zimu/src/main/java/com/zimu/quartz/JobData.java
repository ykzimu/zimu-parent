package com.zimu.quartz;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import java.util.Date;

@Data
@AllArgsConstructor
public class JobData {

    private String name;
    Trigger.TriggerState triggerState;
    private Trigger trigger;
    private JobDetail detail;
    private JobDataMap data;
    private Date startTime;
    private Date endTime;
    private String cron;
    private static final String[] voidParams = new String[0];

    public JobData(Trigger.TriggerState triggerState, Trigger trigger, JobDetail detail) {
        this.name = trigger.getKey().getName();
        this.trigger = trigger;
        this.triggerState = triggerState;
        this.detail = detail;
        this.data = detail.getJobDataMap();
        this.startTime=trigger.getStartTime();
        this.endTime=trigger.getEndTime();
    }
}
