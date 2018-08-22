package com.libang.springQuartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author libang
 * @date 2018/8/16 20:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-quartzDB.xml")
public class SpringQuartzTestCaseDB {

    private int taskId = 1001;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Test
    public void testSpringQuartzDb() {

        //通过Scheduler来调度任务
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            //创建JobDataMap
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("message", "hello spring quartz!");

            //JobDetail
            JobDetail jobDetail = JobBuilder.newJob(SpringQuartzJobDb.class)
                    //设置任务扎起数据库表中的name和groupId 两者不能同时重复
                    .withIdentity("task:" + taskId, "springQuartzDb")
                    .setJobData(jobDataMap).build();


            //cron表达式
            String cronExpression = "0/10 * 20 16 8 ? 2018";
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();

            //保存到数据库
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
            System.in.read();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteSpringQuartz() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.deleteJob(new JobKey("task:"+taskId,"springQuartzDb"));


    }


}
