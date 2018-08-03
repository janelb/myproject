package com.libang.erp.service.impl;

import com.libang.erp.entity.Car;

import com.libang.erp.entity.Customer;
import com.libang.erp.entity.CustomerExample;
import com.libang.erp.exception.ServiceException;
import com.libang.erp.mapper.CarMapper;
import com.libang.erp.mapper.CustomerMapper;
import com.libang.erp.service.CarService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author libang
 * @date 2018/8/2 12:44
 */
@Service
public class CarServiceImpl implements CarService {
    private Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private CustomerMapper customerMapper;
    /**
     * 新增车辆信息和车主信息
     *
     * @param car
     * @param customer
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addCarIndo(Car car, Customer customer) {
        //添加车主信息，根据车主身份证号查找车主
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andIdCardEqualTo(customer.getIdCard());
       List<Customer> customerList =  customerMapper.selectByExample(customerExample);
        //如果该车主已存在，则不用重复添加，如果不存在则进行添加
        Integer customerId = null;
       if(customerList == null || customerList.size() == 0){
           customerMapper.insertSelective(customer);
           //获取已添加车主的id,
           customerId = customer.getId();
       }else{
           customerId = customerList.get(0).getId();
       }
       //进行车辆信息添加
        car.setCustomerId(customerId);
       carMapper.insertSelective(car);
       logger.info("添加车辆信息:{}",car);
    }

    /**
     * 根据车牌号查找对应的车辆信息和车主信息
     * @param licenceNo
     * @return
     */
    @Override
    public Car findCarInfoWithCustomer(String licenceNo) {


            Car car  = carMapper.findCarInfoWithCutomer(licenceNo);
            return car;

    }





}
