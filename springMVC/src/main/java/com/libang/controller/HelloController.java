package com.libang.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author libang
 * @date 2018/7/19 19:12
 */
@Controller
@RequestMapping("/user")
public class HelloController {
    //spring 3.x时 @RequestMapping即支持get请求有支持post请求,@RequestMapping(value="/hemo" ,method={RequestMethod.POST,RequestMethod.GET})
    /*@RequestMapping("/hello")*/

    //spring 4.x 版本
    @GetMapping("/hello")
    @PostMapping("/hello")
    public String say(){
        System.out.println("你好！");

        //返回的路径：回到hello.jsp
        return "hello";
    }

    // 利用正则，表达式来约束参数类型,可以进行多个参数的传递
    @GetMapping("/addUser/{id:\\d+}/{type:v-\\d+}")
    public String addUser(@PathVariable int id,@PathVariable String type){
        System.out.println("id--->" +id);
        System.out.println("type--->" +type);
        //返回String类型时：返回的是跳转路径
        return "user/home";
    }







    //进行请求转发跳转，和重定向跳转

    @GetMapping("/add")
    public String save(){

        //默认是请求转发跳转
        return "user/add";
    }

    @PostMapping("/add")
    public String save(String username,String addr){

        System.out.println("username:" +username);
        System.out.println("addr:" +addr);
        //路劲前面添加 redirect: 是重定向
        return "redirect:/user/dh";
    }
    @GetMapping("/dh")
    public String hello(){
        return "hello";
    }





}
