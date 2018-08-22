package com.libang.springTask.annotation;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author libang
 * @date 2018/8/16 15:22
 */
@Component
public class SpringAnnotationTask {

    @Scheduled(fixedDelay = 1000)
    @Async
    public void rateJob(){

        System.out.println(Thread.currentThread().getName()+"spring annotation begin......");
        try {
        System.out.println("this is a job");
            Thread.sleep(2000);

            if(1 == 1){
                throw new RuntimeException("出异常了");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end----------------------");

    }


}
