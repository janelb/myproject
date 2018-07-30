package com.libang.erp.shiro;

import com.libang.erp.entity.Employee;
import com.libang.erp.entity.EmployeeLoginLog;
import com.libang.erp.service.EmployeeService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


/**
 * @author libang
 * @date 2018/7/30 17:16
 */
public class MyRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(MyRealm.class);
    @Autowired
    private EmployeeService employeeService;
    /**
     * 判断角色 权限
     * @param principalCollection
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 判断登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String tell = usernamePasswordToken.getUsername();
        Employee employee =employeeService.findEmployTell(tell);

        if(employee==null){
            throw new UnknownAccountException("用户名或密码错误");

        }else{
            if(employee.getState().equals(Employee.EMPLOYEE_STATE_FROZEN)){

                throw new LockedAccountException("该账户被冻结");
            }else{
                //登录成功记录日志
                String loginIp  = usernamePasswordToken.getHost();
                EmployeeLoginLog employeeLoginLog = new EmployeeLoginLog();
                employeeLoginLog.setLoginIp(loginIp);
                employeeLoginLog.setEmployeeId(employee.getId());
                employeeLoginLog.setLoginTime(new Date());
                employeeService.saveLog(employeeLoginLog);


                logger.info("{}-{}在{}登录了系统",employee.getEmployeeName(),employee.getTell(),new Date());

                return new SimpleAuthenticationInfo(employee,employee.getPassword(),getName());
            }
        }

    }
}
