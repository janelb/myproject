package com.libang.erp.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.libang.erp.dto.ResponseBean;
import com.libang.erp.entity.*;
import com.libang.erp.exception.ServiceException;
import com.libang.erp.service.OrderService;
import com.libang.erp.service.PartService;
import com.libang.erp.vo.OrderInfoVo;
import com.libang.erp.vo.OrderVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jws.WebParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/8/2 14:07
 */
@RequestMapping("/order")
@Controller
public class OrderController {
   /* @Autowired
    private JmsTemplate jmsTemplate;*/

    @Autowired
    private OrderService orderService;

    @Autowired
    private PartService partService;

    /*未完成订单查询*/
    @GetMapping("/undone/list")

    public String undoneList(@RequestParam(name = "p", defaultValue = "1", required = false) Integer pageNo,
                             @RequestParam(required = false) String licenceNo,
                             @RequestParam(required = false) String tel,
                             @RequestParam(required = false) String startTime,
                             @RequestParam(required = false) String endTime,
                             Model model) {

        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("pageNo", pageNo);
        orderMap.put("licenceNo", licenceNo);
        orderMap.put("tel", tel);
        orderMap.put("startTime", startTime);
        orderMap.put("endTime", endTime);
        orderMap.put("exState", Order.ORDER_STATE_DONE);

        PageInfo<Order> page = orderService.findPageByParam(orderMap);

        /* List<Order> orderList = orderService.findAllOrder(orderMap);*/
        model.addAttribute("type", "");
        model.addAttribute("page", page);
        return "/order/list";
    }


    /*已完成订单*/
    @GetMapping("/done/list")

    public String doneList(@RequestParam(name = "p", defaultValue = "1", required = false) Integer pageNo,
                           @RequestParam(required = false) String licenceNo,
                           @RequestParam(required = false) String tel,
                           @RequestParam(required = false) String startTime,
                           @RequestParam(required = false) String endTime,
                           Model model) {

        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("pageNo", pageNo);
        orderMap.put("licenceNo", licenceNo);
        orderMap.put("tel", tel);
        orderMap.put("startTime", startTime);
        orderMap.put("endTime", endTime);
        orderMap.put("state", Order.ORDER_STATE_DONE);

        PageInfo<Order> page = orderService.findPageByParam(orderMap);

        /* List<Order> orderList = orderService.findAllOrder(orderMap);*/
        model.addAttribute("type", "done");
        model.addAttribute("page", page);
        return "/order/hasList";
    }





    /*新增订单*/

    @GetMapping("/new")
    public String orderAdd() {

        return "order/new";
    }

    @PostMapping("/new")
    @ResponseBody

    public ResponseBean orderAdd(String json) {



        Gson gson = new Gson();
        OrderVo orderVo = gson.fromJson(json, OrderVo.class);

        /*创建当前主题对象*/
        Subject subject = SecurityUtils.getSubject();
        /*根据当前主题对象获取当前登录对象*/
        Employee employee = (Employee) subject.getPrincipal();
        orderService.saveOrder(orderVo, employee);
        return ResponseBean.success();
    }






    /*查询serviceTypes类型的集合*/

    @GetMapping("/service/types")
    @ResponseBody
    public ResponseBean serviceTypes() {
        List<ServiceType> servicetypeList = orderService.findAllServiceType();
        return ResponseBean.success(servicetypeList);
    }


    @GetMapping("/parts/types")
    @ResponseBody
    public ResponseBean partsTypes() {
        List<Type> typeList = orderService.findAllPartsType();
        System.out.println(typeList);
        return ResponseBean.success(typeList);
    }


    @GetMapping("/{id:\\d+}/parts")
    @ResponseBody
    public ResponseBean partsByType(@PathVariable Integer id) {
        List<Parts> partsList = partService.findByTypeId(id);
        System.out.println(partsList);
        return ResponseBean.success(partsList);
    }


    /*订单详情*/
    @GetMapping("/{id:\\d+}/detail")

    public String detail(@PathVariable Integer id, Model model) {

        //根据订单id进行查询订单
        Order order = orderService.findOrderById(id);

        //根据订单id服务信息
        ServiceType serviceType = orderService.findServiceTypeByOrderId(order.getServiceTypeId());
        System.out.println(serviceType);

        //根据订单id查询配件列表
        List<Parts> partsList = partService.findPartsByOrderId(id);

        model.addAttribute("order", order);
        model.addAttribute("serviceType", serviceType);
        model.addAttribute("partsList", partsList);

        return "order/detail";
    }


    /*删除订单*/

    @GetMapping("/{id:\\d+}/del")

    public String delOrder(@PathVariable Integer id) {

        orderService.delOrderByOrderId(id);
        return "redirect:/order/undone/list";

    }


    /*修改订单*/

    @GetMapping("/{id:\\d+}/edit")
    public String editOrder(@PathVariable Integer id, Model model) {

        model.addAttribute("orderId", id);

        return "order/edit";
    }


    @GetMapping("/{id:\\d+}/info")
    @ResponseBody
    public ResponseBean editInfo(@PathVariable Integer id) {

        try{

        //根据订单id进行查询订单
        Order order = orderService.findOrderById(id);
        //根据订单id服务信息
        ServiceType serviceType = orderService.findServiceTypeByOrderId(order.getServiceTypeId());

        //根据订单id查询配件列表
        List<Parts> partsList = partService.findPartsByOrderId(id);

        OrderInfoVo orderInfoVo = new OrderInfoVo();
        orderInfoVo.setOrder(order);

        orderInfoVo.setServiceType(serviceType);
        orderInfoVo.setPartsList(partsList);

        return ResponseBean.success(orderInfoVo);
        }catch (ServiceException e){
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/{id:\\d+}/edit")
    @ResponseBody
    public ResponseBean orderEdit(String json) {
        // 将前端数据转化成对对象
        System.out.println("111");
        Gson gson = new Gson();
        OrderVo orderVo = gson.fromJson(json, OrderVo.class);
        System.out.println("222");
        try {
            orderService.editOrder(orderVo);
            System.out.println("333");
        } catch (ServiceException e) {
            return ResponseBean.error(e.getMessage());
        }
        return ResponseBean.success();
    }


    /*订单下发*/

    @GetMapping("/{id:\\d+}/trans")
    @ResponseBody
    public ResponseBean orderTrans(@PathVariable Integer id) {



        /*订单下发修改订单的状态*/
        try {

            orderService.transOrder(id);
        } catch (ServiceException e) {
            return ResponseBean.error(e.getMessage());
        }
        return ResponseBean.success();
    }


}
