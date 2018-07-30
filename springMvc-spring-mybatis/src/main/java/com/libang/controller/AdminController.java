package com.libang.controller;

import com.libang.entity.Admin;
import com.libang.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping
    public String login(){

        return "login";
}

    @PostMapping
    public String login(String adminName, String password, RedirectAttributes redirectAttributes, Model model){

      Admin admin =  adminService.findByNameAndPassword(adminName);

      if(admin != null && password.equals(admin.getPassword())){

        return "redirect:/parts";

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
