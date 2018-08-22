package com.libang.springTask;

/**
 * @author libang
 * @date 2018/8/16 14:39
 */
public class SpringTask implements Runnable {

    @Override
    public void run() {
        //获取执行线程名称
        System.out.println(Thread.currentThread().getName()+"spring task begin");
        System.out.println("springTask running.......");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end------------");


    }
}
