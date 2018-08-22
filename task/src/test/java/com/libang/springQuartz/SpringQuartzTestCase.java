package com.libang.springQuartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author libang
 * @date 2018/8/16 19:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-quartz.xml")
public class SpringQuartzTestCase {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    private JobDetail jobDetail;
    @Autowired
    private Trigger trigger;

    @Test
    public void testSpringQuartz() throws IOException {

        System.out.println("容器启动");
        System.in.read();


    }

}
