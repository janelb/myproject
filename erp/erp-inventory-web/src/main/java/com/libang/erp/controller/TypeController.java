package com.libang.erp.controller;


import com.github.pagehelper.PageInfo;
import com.libang.erp.entity.Parts;
import com.libang.erp.entity.Type;
import com.libang.erp.service.PartService;
import com.libang.erp.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/7/25 15:05
 */
@Controller
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private PartService partService;

    @GetMapping
    public String list(@RequestParam(name = "p" ,defaultValue = "1",required = false) Integer pageNo,
                         Model model){
        Map<String ,Object> queryMap = new HashMap<>();
        PageInfo page = typeService.findPageAndByQueryMap(pageNo,queryMap);

        List<Type> typeList = typeService.findAll();
        model.addAttribute("page",page);
        model.addAttribute("typeList",typeList);

        return "type/list";
    }


    /*添加类型*/

    @GetMapping("/add")
    public String add(){

        return "type/list";
    }
    @PostMapping("/add")
    @ResponseBody
    public Integer  add(Type type, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message","保存成功");
      Integer res  =   typeService.save(type);

       return res;
    }


    /*检查类型名称*/

    @GetMapping("/check")
    @ResponseBody
    public boolean check(String typeName){

        Type type = typeService.findByName(typeName);

        if(type!=null){

             return false;
        }
        return true;
    }


    /*删除*/

    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public boolean del(@PathVariable Integer id){

        List<Parts> partsList = partService.findByTypeId(id);
        if(!partsList.isEmpty()){
            return false;
        }else{
            typeService.delById(id);
            return true;
        }
    }

    /*修改*/

    @PostMapping("/update")
    @ResponseBody
    public Integer update(Type type){
        Integer res = typeService.update(type);
        return res;
    }



}
