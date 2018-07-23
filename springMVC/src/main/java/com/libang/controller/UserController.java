package com.libang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author libang
 * @date 2018/7/20 12:10
 */
@Controller
@RequestMapping("/user")
public class UserController {


    //往前端进行传值方法一

    //前端传    @GetMapping("/show/{id:\\d+}")
    public String showUser(@PathVariable int id, @RequestParam(defaultValue = "1") Integer p, Model model) {
        System.out.println("id-->" + id);
        System.out.println("pageNo--->" + p);
        model.addAttribute("id", id);
        model.addAttribute("p", p);

        //返回值String类型
        return "hello";
            }
        // 值方法二
        @GetMapping("/{id:\\d+}")
        public ModelAndView showUser ( @PathVariable int id, @RequestParam(defaultValue = "1") Integer p){
            System.out.println("id-->" + id);
            System.out.println("pageNo--->" + p);

            ModelAndView modelAndView = new ModelAndView("hello");

        /*ModelAndView modelAndView = new ModelAndView();

        //跳转路经
        modelAndView.setViewName("hello");*/
            modelAndView.addObject("id", id);
            modelAndView.addObject("p", p);

            //返回值ModelAndView类型
            return modelAndView;

        }


        //访问静态资源
        @GetMapping("/img")
        public String showImg () {

            return "hello";
        }


    }
