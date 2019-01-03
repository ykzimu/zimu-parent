package com.zimu.quartz;

import com.zimu.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 月报
 */

@Slf4j
public class ZimuScheduledJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        try {
            String startDate = DateUtils.dateToStr(new Date(), DateUtils.FormatEnum.FORMAT_YMDHMS3);
            log.info("月报定时器开始：{}", startDate);


            String endDate = DateUtils.dateToStr(new Date(), DateUtils.FormatEnum.FORMAT_YMDHMS3);
            log.info("月报定时器结束：{}", endDate);
        } catch (Exception e) {
            log.error("月报统计任务执行异常", e);
        }
    }

}