package com.libang.erp.springQuartz;

import com.libang.erp.service.FixOrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author libang
 * @date 2018/8/19 13:57
 */
public class CheckOrderTimeOut implements Job {

        private Logger logger = LoggerFactory.getLogger(CheckOrderTimeOut.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        ApplicationContext applicationContext=null;
        try {
          applicationContext = (ApplicationContext) jobExecutionContext.getScheduler().getContext().get("applicationContext");

          FixOrderService fixOrderService =  applicationContext.getBean(FixOrderService.class);
          fixOrderService.addOrderTimeOut(jobName);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }
}
