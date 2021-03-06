package com.libang.controller;

import com.libang.entity.User;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;


/**
 * @author libang
 * @date 2018/7/19 19:12
 */
@Controller
@RequestMapping("/user")

//用于替代Controller, 默认在每个方法上都加上   @ResponseBody
/*@RestController*/
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

    // 利用正则，表达式来约束参数类型,可以进行多个参数的传递，produces设置mime类型
    @GetMapping(value = "/addUser/{id:\\d+}/{type:v-\\d+}",produces = "text/plain;charset=UTF-8")
    //用于设置将字符串作为响应内容显示
    @ResponseBody
    public String addUser(@PathVariable int id,@PathVariable String type,String name){
        System.out.println("id--->" +id);
        System.out.println("type--->" +type);
        System.out.println("name---->"+name);
        //返回String类型时：返回的是跳转路径
        /*return "user/home";*/
        return "成功了！！！！";
    }


    //返回一个json对象
    @GetMapping("/{id:\\d+}/json")
    @ResponseBody
    public User shouwUser(@PathVariable int id){
        User user = new User();
        user.setId(id);
        user.setUserName("jack");
        user.setAddress("beijiang");
        return user;
    }

    //返回一个json对象数组
    @GetMapping("/list.json")
   /* @ResponseBody*/
    public List<User> showAll(){
        List<User> userList = Arrays.asList(
                new User(1001,"jack","北京"),
                new User(1002,"tom","usa"),
                new User(1003,"rose","England")
                );
        return userList;
    }





    //进行请求转发跳转，和重定向跳转

    @GetMapping("/add")
    public String save(@CookieValue String userName, Model model,@RequestHeader(name = "User-Agent") String userAgent){
        model.addAttribute("userName",userName);
        //获取请求头信息
        System.out.println(userAgent);
        //默认是请求转发跳转
        return "user/add";
    }

  /*  @PostMapping("/add")
    public String save(String userName,String addr){

        System.out.println("userName:" +userName);
        System.out.println("addr:" +addr);
        //路劲前面添加 redirect: 是重定向
        return "redirect:/user/dh";
    }*/

  //使用原始方法获取前端的值
    @PostMapping("/add")
    public String save(User user,HttpServletRequest req, HttpServletResponse resp, HttpSession session){

        //设置cookie
        Cookie  cookie = new Cookie("userName",user.getUserName());
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*7);
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);

        System.out.println("userName"+user.getUserName());
        System.out.println("addr"+user.getAddress());



        //获取前端的值，获取session中的值
 /*       session.getAttribute("userName");
        String name = req.getParameter("userName");
        String addr = req.getParameter("addr");
        System.out.println(name);
        System.out.println(addr);*/


        return "redirect:/user/dh";
    }


    @GetMapping("/dh")
    public String hello(){
        return "hello";
    }





}
