package com.libang.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author libang
 * @date 2018/7/19 19:12
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String say(){
        System.out.println("你好！");

        //返回的路径：回到hello.jsp
        return "hello";

    }

}
