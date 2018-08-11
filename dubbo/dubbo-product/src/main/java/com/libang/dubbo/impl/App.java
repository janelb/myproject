package com.libang.dubbo.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author libang
 * @date 2018/8/11 12:21
 */
public class App {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-dubbo-product.xml");
        context.start();
        System.out.println("服务器启动");
        System.in.read();
    }
}
