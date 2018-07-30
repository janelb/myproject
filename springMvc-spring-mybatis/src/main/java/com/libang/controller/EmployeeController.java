package com.libang.controller;

import com.libang.entity.Employee;
import com.libang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


/**
 * @author libang
 * @date 2018/7/25 9:24
 */
@Controller
@RequestMapping("/employ")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //查询员工列表
    @GetMapping
    public String EmployList(Model model){
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employeeList",employeeList);
      return "employee/list";
    }





    //新增员工

    @GetMapping("/add")

    public String addEmploy(){


        return "employee/new";
    }

    @PostMapping("/add")
    public String addEmploy(Employee employee, RedirectAttributes redirectAttributes){

        employeeService.save(employee);
        redirectAttributes.addFlashAttribute("message","新增成功");
        return"redirect:/employ";
    }

    //修改员工信息

    @GetMapping("/{id:\\d+}/edit")
    public String edit(@PathVariable Integer id,Model model){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee",employee);
        return "employee/edit";
    }


    @PostMapping("/{id:\\d+}/edit")
    public String edit(Employee employee,RedirectAttributes redirectAttributes){

        employeeService.update(employee);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/employ";
    }

    /*删除*/

    @GetMapping("/{id:\\d+}/del")

    public String del(@PathVariable Integer id,RedirectAttributes redirectAttributes){

        employeeService.delById(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/employ";
    }




}
