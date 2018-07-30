package com.libang.service.impl;

import com.libang.entity.Employee;
import com.libang.entity.EmployeeExample;
import com.libang.mapper.EmployeeMapper;
import com.libang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/25 10:04
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 新增员工
     *
     * @param employee
     */
    @Override
    public void save(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 查询所有员工
     */
    @Override
    public List<Employee> findAll() {
        EmployeeExample employeeExample = new EmployeeExample();
      List<Employee> employeeList =  employeeMapper.selectByExample(employeeExample);
        return employeeList;
    }

    /**
     * 根据Id进行查找
     *
     * @param id
     * @return
     */
    @Override
    public Employee findById(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);

        return employee;
    }

    /**
     * 跟新
     *
     * @param employee
     */
    @Override
    public void update(Employee employee) {
       employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 根据id进行删除
     *
     * @param id
     */
    @Override
    public void delById(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);

    }
}
