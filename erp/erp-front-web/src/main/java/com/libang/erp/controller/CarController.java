package com.libang.erp.controller;

import com.libang.erp.dto.ResponseBean;
import com.libang.erp.entity.Car;
import com.libang.erp.entity.Customer;
import com.libang.erp.exception.ServiceException;
import com.libang.erp.service.CarService;
import com.libang.erp.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author libang
 * @date 2018/8/2 12:35
 */
@RequestMapping("/car")
@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerService customerService;


    /*新增车辆信息*/

    @PostMapping("/add")
    public String addCar(Car car , Customer customer, Model model){
            carService.addCarIndo(car,customer);
            model.addAttribute("car",car);
            model.addAttribute("customer",customer);
             return "/order/new";
    }

    //检查车辆信息是否存在

    @GetMapping("/check")
    @ResponseBody
    public ResponseBean checkCarInfo(String licenceNo,Model model){

        Car car = carService.findCarInfoWithCustomer(licenceNo);

        if(car!=null){
                return  ResponseBean.success(car);
            }else{
                return ResponseBean.error("该车辆不存在");
            }



    }








}
