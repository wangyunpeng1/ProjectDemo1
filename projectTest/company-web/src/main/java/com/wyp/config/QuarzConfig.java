package com.wyp.config;

import com.wyp.jobFactory.StatisticsFactory;
import com.wyp.service.job.StatisticsJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author wyp
 * @date 2020/4/3
 * @description 定时配置
 */
@Configuration
public class QuarzConfig{

    /**
     * 创建Job
     * @return
     */
    @Bean
    public JobDetailFactoryBean job()
    {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        //关联自己的job
        factory.setJobClass(StatisticsJob.class);
        return factory;
    }

    /**
     * 创建Trigger
     * @param job
     * @return
     */
    @Bean
    public CronTriggerFactoryBean cronTrigger1(JobDetailFactoryBean job)
    {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(job.getObject());
        factory.setCronExpression("0 0 0 * * ?");
        return factory;
    }

    /**
     * 创建Scheduler对象
     * @param cronTrigger
     * @param jobFactory
     * @return
     */
    @Bean
    public SchedulerFactoryBean scheduler1(CronTriggerFactoryBean cronTrigger, StatisticsFactory jobFactory)
    {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //关联trigger
        factory.setTriggers(cronTrigger.getObject());
        factory.setJobFactory(jobFactory);
        return factory;
    }
}

