package com.libang.erp.quartz;

import com.libang.erp.springQuartz.CountDetil;
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
 * @date 2018/8/17 12:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class CountDetilTestCase {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    private JobDetail jobDetail;
    @Autowired
    private Trigger trigger;


        private int taskId = 1002;
    @Test
    public void testCountDetil() throws IOException {


        //通过Scheduler来调度任务
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            //创建JobDataMap
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("message", "hello spring quartz!");

            //JobDetail
            JobDetail jobDetail = JobBuilder.newJob(CountDetil.class)
                    //设置任务扎起数据库表中的name和groupId 两者不能同时重复
                    .withIdentity("task:" + taskId, "CountDetil")
                    .setJobData(jobDataMap).build();


            //cron表达式
            String cronExpression = "0/10 * 20 17 8 ? 2018";
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


}
