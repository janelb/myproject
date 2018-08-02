package com.libang.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author libang
 * @date 2018/8/2 14:07
 */
@RequestMapping("/order")
@Controller
public class OrderController {

    /*订单查询*/

    @GetMapping("/list")
    public String list(){

        return "/order/list";
    }

    /*新增订单*/

    @GetMapping("/new")
    public String orderAdd(){

        return "order/new";
    }
}
