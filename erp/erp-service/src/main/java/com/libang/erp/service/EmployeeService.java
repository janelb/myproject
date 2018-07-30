package com.libang.erp.service;


import com.libang.erp.entity.Employee;
import com.libang.erp.entity.EmployeeLoginLog;
import com.libang.erp.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/7/25 10:03
 */
public interface EmployeeService {






    /**
     * 根据Id进行查找
     * @param id
     * @return
     */
    Employee findById(Integer id);




    /**
     * 根据id进行删除
     * @param id
     */
    void delById(Integer id);


    /**
     * 登录，并记录登录日志
     * @param tell
     * @param password
     * @param loginIp
     * @return
     */
    Employee login(String tell, String password, String loginIp)throws ServiceException;


    /**
     * 查看员工状态
     * @param id
     * @param state
     * @return
     */
    Employee frozenById(Integer id, Integer state);

    /**
     * 查询所有员工的list集合
     * @param requestMap
     * @return
     */
    List<Employee> findAllAccountWithRolesByQueryParam(Map<String,Object> requestMap);

    /**
     * 新增员工
     * @param employee
     * @param roleIds
     */
    void save(Employee employee, Integer[] roleIds);

    /**
     * 修改员工信息
     * @param employee
     * @param roleIds
     */
    void update(Employee employee, Integer[] roleIds);

    /**
     * 员工个人设置
     * @param employee
     */
    void set(Employee employee);

    /**
     * 通过电话号码来获取该该对象
     * @param tell
     * @return
     */
    Employee findEmployTell(String tell);

    /**
     *
     * 登录成功记录日志
     * @param employeeLoginLog
     */
    void saveLog(EmployeeLoginLog employeeLoginLog);
}
