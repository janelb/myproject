package com.libang.erp.springQuartz;

import com.libang.erp.service.OrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author libang
 * @date 2018/8/17 10:00
 */
public class CountDetil implements Job {




    private Logger logger  = LoggerFactory.getLogger(CountDetil.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

             ApplicationContext applicationContext = null;
        try {

           applicationContext = (ApplicationContext) jobExecutionContext
                                                .getScheduler().getContext().get("applicationContext");

            OrderService orderService = (OrderService) applicationContext.getBean(OrderService.class);
            orderService.countDetilOrder();


        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }
}
