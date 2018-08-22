package com.libang.timerTask;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;

/**
 * @author libang
 * @date 2018/8/16 10:53
 */

public class TimerTaskTestCase {

    @Test
    public void testTimerTask() throws IOException {

        Timer timer  = new Timer();

        //设置一段时间后开始执行
        // timer.schedule(new TimerTaskTest(),2000);

        //再具体时间点开始执行任务，以当前时间开始，3秒后开始执行任务
         // timer.schedule(new TimerTaskTest(),new Date(System.currentTimeMillis()+3000L));
        System.out.println("11111111111111");
        //首次执行等待3秒，以后每1秒执行一次
        // timer.schedule(new TimerTaskTest(),3000,1000)

        //在当前时间3秒后开始执行，每1秒执行一次
        timer.schedule(new TimerTaskTest(),new Date(System.currentTimeMillis()+3000L),1000);

        //fixedRate 是相对于上次任务开的时间的时间间隔，如果第二次任务开始执行，第一次任务还没有结束，等待第一次任务结束后再开始执行第二次任务
        //timer.scheduleAtFixedRate(new TimerTaskTest(),new Date(System.currentTimeMillis()+2000L),1500);

        System.in.read();

    }

}
