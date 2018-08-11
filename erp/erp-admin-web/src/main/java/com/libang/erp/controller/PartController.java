package com.libang.erp.controller;


import com.github.pagehelper.PageInfo;
import com.libang.erp.entity.Parts;
import com.libang.erp.entity.Type;
import com.libang.erp.service.PartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/7/23 21:01
 */
@Controller
@RequestMapping("/parts")
public class PartController {

    @Autowired
    private PartService partService;



    /**
     * 查询所有
     * @param pageNo
     * @param model
     * @return
     */
    @GetMapping
    public String list(@RequestParam(name = "p" ,defaultValue = "1",required = false) Integer pageNo,
                                        @RequestParam(required = false) Integer partsType,
                                        @RequestParam(required = false) String partsName,
                                        Model model){

        Map<String ,Object> queryMap = new HashMap<>();
        queryMap.put("partsName",partsName);
        queryMap.put("partsType",partsType);

        PageInfo<Parts> page = partService.findPageAndByQueryMap(pageNo,queryMap);
        List<Type> typeList = partService.findByTypeList();
        model.addAttribute("page",page);
        model.addAttribute("typeList",typeList);
        return "parts/list";

    }




    @GetMapping("/{id:\\d+}")
    public String findById(@PathVariable Integer id, Model model) throws IOException {

        Parts parts = partService.findById(id);

        if(parts==null){

            throw new IOException();

        }else{
            model.addAttribute("parts",parts);
        }
        return "parts/detail";
    }





    //根据id进行删除
    @GetMapping("/{id:\\d+}/del")
    public String del(@PathVariable Integer id , RedirectAttributes redirectAttributes)  {

        partService.delById(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/parts";
    }

    /*新增*/

    @GetMapping("/new")
    public String add(Model model){

        List<Type> typeList = partService.findByTypeList();

        model.addAttribute("typeList",typeList);

        return "/parts/new";
    }

    @PostMapping("/new")
    public String add(Parts parts,RedirectAttributes redirectAttributes){
        partService.save(parts);
        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/parts";
    }

    /*修改*/

    @GetMapping("/{id:\\d+}/edit")
    public String edit(@PathVariable Integer id ,Model model){
        Parts parts = partService.findById(id);
        List<Type> typeList = partService.findByTypeList();
        model.addAttribute("parts",parts);
        model.addAttribute("typeList",typeList);
        return "/parts/edit";
    }
    @PostMapping("/{id:\\d+}/edit")
    public String edit(Parts parts,RedirectAttributes redirectAttributes){

        partService.edit(parts);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/parts";
    }


    //根据数量

    @GetMapping("/no")
    public String list(@RequestParam(name = "p" ,defaultValue = "1",required = false) Integer pageNo,
                       @RequestParam(required = false) Integer partsType,
                       @RequestParam(required = false) String partsName,
                       @RequestParam(required = false) Integer inventory,
                       Model model){

        Map<String ,Object> queryMap = new HashMap<>();
        queryMap.put("partsName",partsName);
        queryMap.put("partsType",partsType);
        queryMap.put("inventory",inventory);


        PageInfo<Parts> page = partService.findPageAndByQueryMapAndNumber(pageNo,queryMap);
        List<Type> typeList = partService.findByTypeList();
        model.addAttribute("page",page);
        model.addAttribute("typeList",typeList);
        return "parts/list";

    }



    //检查编码

    @GetMapping("/check")
    @ResponseBody
    public boolean check(String partsNo){

       Parts parts =  partService.findByPartsNo(partsNo);
       if(parts!=null){
           return false;
       }else{
           return  true;
       }

    }



}
