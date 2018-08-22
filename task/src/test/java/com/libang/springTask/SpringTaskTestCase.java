package com.libang.springTask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author libang
 * @date 2018/8/16 14:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-task.xml")
public class SpringTaskTestCase {

    //注入线程池
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Test
    public void springTaskTest() throws IOException {
        //立即执行
        //taskScheduler.execute(new SpringTask());

        //在指定时间执行任务
        // taskScheduler.schedule(new SpringTask() ,new Date(System.currentTimeMillis() + 3000L));

        //延迟循环执行任务  fixedDelay从上一次任务结束开始时间开始的时间间隔
        // taskScheduler.scheduleWithFixedDelay(new SpringTask(),1500);

        // fixedRate 从上一次任务开始的时间间隔
        // taskScheduler.scheduleAtFixedRate(new SpringTask(),1500);

        //指定开始时间，循环执行任务
        // taskScheduler.scheduleAtFixedRate(new SpringTask(),new Date(System.currentTimeMillis()+3000L),1500);
        //基cron表达式执行任务
       // taskScheduler.schedule(new SpringTask(),new  CronTrigger("0/3 * * * * ?"));

        //使用PeriodicTrigger执行任务
        //每2秒钟执行一次，首次执行延迟两秒钟
        PeriodicTrigger periodicTrigger  =  new PeriodicTrigger(2,TimeUnit.SECONDS);
        periodicTrigger.setInitialDelay(2);
        taskScheduler.schedule(new SpringTask(),periodicTrigger);

         System.in.read();







    }
}
