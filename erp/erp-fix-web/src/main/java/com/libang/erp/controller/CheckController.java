package com.libang.erp.controller;

import com.github.pagehelper.PageInfo;
import com.libang.erp.dto.ResponseBean;
import com.libang.erp.entity.Employee;
import com.libang.erp.entity.FixOrder;
import com.libang.erp.service.FixOrderService;
import org.apache.shiro.subject.Subject;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author libang
 * @date 2018/8/10 9:05
 */
@Controller
@RequestMapping("/check")
public class CheckController {


    @Autowired
    private FixOrderService fixOrderService;



    /*质检*/
    @GetMapping("/list")

    public String checkList(@RequestParam(name="p",defaultValue = "1",required = false )Integer pageNo,Model model ){

        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("pageNo", pageNo);
        //1.查询所有订单
        PageInfo<FixOrder> page = fixOrderService.findPageByParam(orderMap);
        model.addAttribute("page", page);
        return "check/list";
    }



        /*任务领取*/
    @GetMapping("/{id:\\d+}/receive")
    @ResponseBody
    public ResponseBean checkTask(@PathVariable Integer id){
        //获取当前登录对象
        Subject subject = SecurityUtils.getSubject();
        Employee employee  = (Employee) subject.getPrincipal();

        //根据订单Id将检修人员姓名和ID保存到数据库
        try {
            fixOrderService.taskReceiveCheck(id,employee);
        } catch (Exception e) {
            return ResponseBean.error(e.getMessage());
        }

            return ResponseBean.success();

    }


    /*质检任务详情界面*/

    @GetMapping("/{id:\\d+}/service")
    public String checkTaskService(@PathVariable Integer id,Model model){
        //根据订单ID查询订单详情
        FixOrder fixOrder = fixOrderService.getFixOrderId(id);
        //获取当前灯登录人员
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        //将值传送到前端页面
        model.addAttribute("curr_employee_id",employee.getId());
        model.addAttribute("fixOrder",fixOrder);
        return "check/service";
    }

    /*完成质检任务*/

    @GetMapping("/{id:\\d+}/done")
    public String doneCheckTask(@PathVariable Integer id){
        //根据订单id修该订单状态
        fixOrderService.taskDoneCheck(id);

        return "redirect:/check/list";
    }




}
