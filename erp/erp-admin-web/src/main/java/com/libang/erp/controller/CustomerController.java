package com.libang.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author libang
 * @date 2018/7/25 8:57
 */

@Controller
@RequestMapping("/user")
public class CustomerController {

        @GetMapping("/login")
        public String login(){

           return "user/login";
        }
}
