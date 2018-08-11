package com.libang.erp.controller;

import com.github.pagehelper.PageInfo;
import com.libang.erp.entity.PartsIn;
import com.libang.erp.entity.PartsStream;
import com.libang.erp.service.PartsStreamService;
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
 * @date 2018/8/10 14:59
 */
@Controller
@RequestMapping("/inventory")
public class InventoryControy {

    @Autowired
    private PartsStreamService partsStreamService;

    /*配件出库查询*/

    @GetMapping("/out")
    public String partsOut(@RequestParam(name = "p",defaultValue = "1",required = false) Integer pageNo,
                           @RequestParam(required = false) Integer orderId,
                           @RequestParam(required = false) String partsNo,
                           Model model){

            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("pageNo",pageNo);
            queryMap.put("orderId",orderId);
            queryMap.put("partsNo",partsNo);
            PageInfo<PartsStream> page = partsStreamService.findPartsOutInventory(queryMap);
            model.addAttribute("page",page);
            return "parts/out";
    }

    /*配件入库查询*/

    @GetMapping("/in")
    public String partsIn(@RequestParam(name = "p",defaultValue = "1", required = false) Integer pageNo,
                            @RequestParam(required = false) String partsNo,
                            Model model){

        Map<String ,Object> inMap = new HashMap<>();
        inMap.put("pageNo",pageNo);
        inMap.put("partsNo",partsNo);
        PageInfo<PartsIn> page = partsStreamService.findPartsInInventory(inMap);
        model.addAttribute("page",page);
        return "parts/in";
    }

}
