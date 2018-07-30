package com.libang.erp.controller;


import com.libang.erp.entity.Admin;
import com.libang.erp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author libang
 * @date 2018/7/25 12:52
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //登录

    @GetMapping("/home")
    public String home(){

        return "/home";
    }

    @GetMapping
    public String login(){

        return "login";
}

    @PostMapping
    public String login(String adminName,
                        String password,
                        String remeber,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes,
                        Model model){
        //获取登录Ip
        String loginIp = request.getRemoteAddr();

      Admin admin =  adminService.findByNameAndPassword(adminName,password,loginIp);

      if(admin != null && password.equals(admin.getPassword())){
          if(!remeber.isEmpty()){
              Cookie cookie = new Cookie("adminName",adminName);
              cookie.setDomain("localhost");
              cookie.setPath("/");
              cookie.setMaxAge(60*60*24*7);
              cookie.setHttpOnly(true);
              }

        return "home";

      }else{

          model.addAttribute("adminName",adminName);
          redirectAttributes.addFlashAttribute("message","用户名或密码错误");
          return "redirect:/admin";
      }

    }

    //修改密码

    @GetMapping("/edit")
    public String editPassword(){

        return "user/edit";
    }






}
