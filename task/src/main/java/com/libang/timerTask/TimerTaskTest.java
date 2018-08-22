package com.libang.timerTask;

import java.util.TimerTask;

/**
 * @author libang
 * @date 2018/8/16 10:49
 */
public class TimerTaskTest extends TimerTask {

    /*继承TimerTask类，在TimerTask实现了Runnable接口*/

    @Override
    public void run() {
        System.out.println("TimerTask begin.......");
        System.out.println("timerTask is running....");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
