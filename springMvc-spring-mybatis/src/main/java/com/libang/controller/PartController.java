package com.libang.controller;

import com.libang.entity.Parts;
import com.libang.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author libang
 * @date 2018/7/23 21:01
 */
@Controller
@RequestMapping("/parts")
public class PartController {

    @Autowired
    private PartService partService;

    @GetMapping("/{id:\\d+}")
    public String findById(@PathVariable Integer id, Model model){

        Parts parts = partService.findById(id);

        if(parts==null){

            //抛出异常

        }else{
            model.addAttribute("parts",parts);
        }
        return "parts/detail";


    }

}
