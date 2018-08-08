package com.libang.erp.controller;

import com.github.pagehelper.PageInfo;
import com.libang.erp.entity.Order;
import com.libang.erp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/8/7 8:55
 */
@RequestMapping("/fix")
@Controller
public class FixController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public String fixList(@RequestParam(name = "p", defaultValue = "1", required = false) Integer pageNo,
                          Model model){

        Map<String,Object> orderMap = new HashMap<>();
        orderMap.put("pageNo",pageNo);
        //1.查询所有订单
        PageInfo<Order> page = orderService.findPageByParam(orderMap);
        //2.根据订单信息查询所有车辆信息，顾客信息
        //3.根据订单信息查询对应的配件信息
        model.addAttribute("page",page);
        return "fix/list";
    }


}
