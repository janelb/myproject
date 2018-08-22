package com.libang.quartz;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

/**
 * @author libang
 * @date 2018/8/16 12:21
 */
public class QuartzJobTestCase {

    @Test
    public void quartzTest() throws SchedulerException, IOException {
        //创建jobDetail指定执行任务
        JobDetail jobDetail  = JobBuilder.newJob(MyQuartzJob.class).build();
        //定义trigger何时触发任务
        SimpleScheduleBuilder sch = SimpleScheduleBuilder.simpleSchedule();
        //设置间隔时间
        sch.withIntervalInSeconds(4);
        //设置持续执行
        sch.repeatForever();
        Trigger simpleTrigger = TriggerBuilder.newTrigger().withSchedule(sch).build();

        //通过schedule调度任务
        Scheduler scheduler =new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,simpleTrigger);
        scheduler.start();
        System.in.read();
    }


    /*使用cron来进行任务调度,重点*/
    @Test
    public void cronRun() throws SchedulerException, IOException {
        //创建jobDetail执行任务
        JobDetail jobDetail  = JobBuilder.newJob(MyQuartzJob.class).build();

        //根据cron表达式来确定任务触发的时间点
        ScheduleBuilder scheduleBuilder  = CronScheduleBuilder.cronSchedule("0/3 * * * * ? *");
        Trigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();


        //通过scheduler调度任务
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();
        System.in.read();


    }

}
