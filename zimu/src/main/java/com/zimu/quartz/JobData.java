package com.zimu.quartz;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.quartz.JobDataMap;
import org.quartz.Trigger;

import java.util.Date;

@Data
@AllArgsConstructor
public class JobData {


    //  与application.yml里的spring.jackson.date-format保持一致
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private String name;
    private String description;
    private Trigger.TriggerState triggerState;
    private String triggerStateDesc;
    private JobDataMap data;
    private Date startTime;
    private Date endTime;
    private Date nextFireTime;
    private Date previousFireTime;
    private String cron;
    private String beanName;
    private String beanClass;

    public JobData(Trigger.TriggerState triggerState, Trigger trigger) {
        this.name = trigger.getKey().getName();
        this.triggerState = triggerState;
        this.data = trigger.getJobDataMap();
        this.startTime = trigger.getStartTime();
        this.endTime = trigger.getEndTime();
        this.nextFireTime = trigger.getNextFireTime();
        this.previousFireTime = trigger.getPreviousFireTime();
        this.description = trigger.getDescription();


        switch (triggerState) {
            case NONE:
                triggerStateDesc = "无";
                break;
            case NORMAL:
                triggerStateDesc = "正常";
                break;
            case PAUSED:
                triggerStateDesc = "暂停";
                break;
            case COMPLETE:
                triggerStateDesc = "已完成";
                break;
            case ERROR:
                triggerStateDesc = "异常";
                break;
            case BLOCKED:
                triggerStateDesc = "阻塞";
                break;
            default:
                break;
        }

    }
}
