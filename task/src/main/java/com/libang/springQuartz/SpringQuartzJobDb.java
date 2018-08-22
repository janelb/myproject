package com.libang.springQuartz;

import org.quartz.*;

/**
 * @author libang
 * @date 2018/8/16 20:02
 */
public class SpringQuartzJobDb implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap jobDataMap =  jobExecutionContext.getMergedJobDataMap();
        System.out.println("spring quartz running"+jobDataMap.get("message"));

    }
}
