package com.libang.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author libang
 * @date 2018/8/11 12:29
 */
public class App {
    public static void main(String[] args) throws IOException {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-dubbo-consumer.xml");
    ProductService productService = (ProductService) context.getBean("productService");
    String name = productService.findById(1001);
        System.out.println(name);
        System.in.read();

    }


}
