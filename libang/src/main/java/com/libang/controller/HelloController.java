package com.libang.controller;

import com.libang.entity.User;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * @author libang
 * @date 2018/7/22 14:39
 */
@Controller
@RequestMapping("/user")
public class HelloController {
  /*  @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})*/
   /* @GetMapping("/user/hello")*/
    @GetMapping("/hello")
    public String say(){
        System.out.println("你好");
        return "hello";
    }


    //进行请求转发跳转                      解决参数中文乱码问题
    @GetMapping(value = "/hello2/{name:.+}", produces = "text/plain;charset=UTF-8")
    public String hello(@PathVariable String name){
        System.out.println(name);
        return "hello2";
    }

    @PostMapping("/hello2")
    public String UserHello(String username,String address){
        System.out.println(username);
        System.out.println(address);
        return "redirect:/user/home";
    }

    @GetMapping("/home")
    public String UserHome(){

        return "hello";
    }


    @GetMapping("/add")
    @ResponseBody
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("username","tom");
        modelAndView.addObject("age",3);
        return modelAndView;
    }


    @GetMapping("/save")
    @ResponseBody
    public List<User> user(){

        List<User> userList = Arrays.asList(
                new User(1,"tom","USB"),
                new User(1,"jack","北京"),
                new User(1,"rose","js")
                );
        return  userList;

    }


}
