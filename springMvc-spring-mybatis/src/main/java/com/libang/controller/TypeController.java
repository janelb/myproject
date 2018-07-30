package com.libang.controller;

import com.libang.entity.Parts;
import com.libang.entity.Type;
import com.libang.service.PartService;
import com.libang.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Part;
import java.util.List;

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
    public String list(Model model){

        List<Type> typeList = typeService.findAll();
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


}
