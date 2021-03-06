package com.libang.erp.controller;

import com.github.pagehelper.PageInfo;
import com.libang.erp.dto.ResponseBean;
import com.libang.erp.entity.Employee;
import com.libang.erp.entity.FixOrder;
import com.libang.erp.entity.Order;
import com.libang.erp.service.FixOrderService;
import com.libang.erp.service.OrderService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private FixOrderService fixOrderService;

    @GetMapping("/list")
    public String fixList(@RequestParam(name = "p", defaultValue = "1", required = false) Integer pageNo,
                          Model model) {
        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("pageNo", pageNo);
        //1.查询所有订单
        PageInfo<FixOrder> page = fixOrderService.findPageByParam(orderMap);
        model.addAttribute("page", page);
        return "fix/list";
    }


    /*领取任务*/

    @GetMapping("/{id:\\d+}/receive")
    @ResponseBody
    public ResponseBean receiveTast(@PathVariable Integer id) {
        //获取当前登录对象
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();

        try {
            fixOrderService.taskReceive(id, employee);
        } catch (Exception e) {
            return ResponseBean.error(e.getMessage());
        }
        return ResponseBean.success();
    }



    /*领取任务后进行跳转到任务详情页面*/

    @GetMapping("/{id:\\d+}/detail")
    public String serviceTask(@PathVariable Integer id, Model model) {
        //根据订单Id查询订单
        FixOrder fixOrder = fixOrderService.getFixOrderId(id);

        //获取当前登录对象
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        model.addAttribute("curr_employee_id", employee.getId());
        model.addAttribute("fixOrder", fixOrder);

        return "fix/detail";
    }

    /*完成任务*/

    @GetMapping("/{id:\\d+}/done")
    public String taskDone(@PathVariable Integer id){
        fixOrderService.taskDone(id);
        return "redirect:/fix/list";
    }


    /*=====================质检=========================*/

    



}
