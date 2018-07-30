package com.libang.service;

import com.libang.entity.Employee;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/25 10:03
 */
public interface EmployeeService {

    /**
     * 新增员工
     * @param employee
     */
     void save(Employee employee) ;


    /**
     * 查询所有员工
     * @return
     */
    List<Employee> findAll();


    /**
     * 根据Id进行查找
     * @param id
     * @return
     */
    Employee findById(Integer id);

    /**
     *
     * 跟新
     * @param employee
     */
    void update(Employee employee);


    /**
     * 根据id进行删除
     * @param id
     */
    void delById(Integer id);
}
