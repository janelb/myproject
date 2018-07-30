package com.libang.erp.service.impl;


import com.libang.erp.entity.*;
import com.libang.erp.exception.ServiceException;
import com.libang.erp.mapper.EmployeeLoginLogMapper;
import com.libang.erp.mapper.EmployeeMapper;
import com.libang.erp.mapper.EmployeeRoleMapper;
import com.libang.erp.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;


import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author libang
 * @date 2018/7/25 10:04
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeLoginLogMapper employeeLoginLogMapper;
    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;


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
     * 根据id进行删除
     *
     * @param id
     */
    @Override
    public void delById(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
        employeeRoleMapper.deleteByPrimaryKey(id);
    }

    /*==========================================*/

    /**
     * 登录，并记录登录日志
     *
     * @param tell
     * @param password
     * @param loginIp
     * @return
     */
    @Override
    public Employee login(String tell, String password, String loginIp)throws ServiceException {

        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andTellEqualTo(tell);
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);


        Employee employee = null;
        if (employeeList != null && employeeList.size() > 0) {

            employee = employeeList.get(0);
            if (employee.getPassword().equals(password)) {

                if (employee.getState().equals(Employee.EMPLOYEE_STATE_NORMAL)) {

                    EmployeeLoginLog employeeLoginLog = new EmployeeLoginLog();
                    employeeLoginLog.setLoginIp(loginIp);
                    employeeLoginLog.setEmployeeId(employee.getId());
                    employeeLoginLog.setLoginTime(new Date());

                    employeeLoginLogMapper.insertSelective(employeeLoginLog);

                    logger.info("{}-{}在{}登录了系统",employee.getEmployeeName(),employee.getTell(),new Date());
                    List<Role> roleList = employeeMapper.findByRoleList(employee.getId());
                    employee.setRoleList(roleList);
                    return employee;
                } else {

                    throw new ServiceException("当前账户状态异常，请联系管理员");
                }


            } else {

                throw new ServiceException("用户名或密码错误");
            }


        } else {

            throw new ServiceException("用户名或密码错误");
        }


    }



    /**
     * 查看员工状态
     * @param id
     * @param state
     * @return
     */
    @Override
    public Employee frozenById(Integer id, Integer state) {

        Employee employee = employeeMapper.selectByPrimaryKey(id);
              Integer sta =    employee.getState();
              if(sta.equals(2)){

                  employee.setState(1);
              }else{

                  employee.setState(2);

              }
           employeeMapper.updateByPrimaryKeySelective(employee);

        return employee;
    }



    /**
     * 查询所有员工的list集合
     * @param requestMap
     * @return
     */
    @Override
    public List<Employee> findAllAccountWithRolesByQueryParam(Map<String, Object> requestMap) {
        return employeeMapper.findAllAccountWithRolesByQueryParam(requestMap);
    }




    /*=============================新增员工==============================*/
    /**
     * 新增员工
     *
     * @param employee
     * @param roleIds
     */
    @Override
    public void save(Employee employee, Integer[] roleIds) {
            //对密码进行加密

        /*String codePassword = DigestUtils.md5Hex(employee.getPassword());
        employee.setPassword(codePassword);*/

        employeeMapper.insertSelective(employee);

        for(Integer roleId : roleIds){
            EmployeeRole employeeRole = new EmployeeRole();
            employeeRole.setRoleId(roleId);
            employeeRole.setEmployeeId(employee.getId());

            employeeRoleMapper.insertSelective(employeeRole);

        }


    }

    /**
     * 修改员工信息
     *
     * @param employee
     * @param roleIds
     */
    @Override
    public void update(Employee employee, Integer[] roleIds) {

        /*1.删除对应的关联关系表*/
        EmployeeRoleExample employeeRoleExample = new EmployeeRoleExample();
        employeeRoleExample.createCriteria().andEmployeeIdEqualTo(employee.getId());
        employeeRoleMapper.deleteByExample(employeeRoleExample);
        /*2.添加跟新关联关系表*/
        for(Integer roleId : roleIds){
            EmployeeRole employeeRole = new EmployeeRole();
            employeeRole.setRoleId(roleId);
            employeeRole.setEmployeeId(employee.getId());
            employeeRoleMapper.insert(employeeRole);
        }
        /*3.跟新对象*/
        employeeMapper.updateByPrimaryKeySelective(employee);
    }


    /**
     * 员工个人设置
     *
     * @param employee
     */
    @Override
    public void set(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }


    /**
     * 通过电话号码来获取该该对象
     *
     * @param tell
     * @return
     */
    @Override
    public Employee findEmployTell(String tell) {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andTellEqualTo(tell);
       List<Employee> employeeList =  employeeMapper.selectByExample(employeeExample);
       if(employeeList!=null && employeeList.size()>0){

           return employeeList.get(0);

       }
        return null;
    }

    /**
     * 登录成功记录日志
     *
     * @param employeeLoginLog
     */
    @Override
    public void saveLog(EmployeeLoginLog employeeLoginLog) {

        employeeLoginLogMapper.insertSelective(employeeLoginLog);

    }


}
