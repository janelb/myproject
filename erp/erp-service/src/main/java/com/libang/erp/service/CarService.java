package com.libang.erp.service;

import com.libang.erp.entity.Car;
import com.libang.erp.entity.Customer;

/**
 * @author libang
 * @date 2018/8/2 12:43
 */
public interface CarService {
    /**
     * 新增车辆信息和车主信息
     * @param car
     * @param customer
     */
    void addCarIndo(Car car, Customer customer);


    /**
     * 根据车牌号查找对应的车辆信息和车主信息
     * @param licenceNo
     * @return
     */
    Car findCarInfoWithCustomer(String licenceNo);
}
