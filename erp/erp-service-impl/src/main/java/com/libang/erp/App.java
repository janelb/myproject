package com.libang.erp;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author libang
 * @date 2018/8/17 12:47
 */
public class App {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");

        context.start();

        System.out.println("容器启动成功...");

        System.in.read();
    }

}
