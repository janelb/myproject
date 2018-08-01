package com.libang.erp.shiro;

import com.libang.erp.entity.Employee;
import com.libang.erp.entity.EmployeeLoginLog;
import com.libang.erp.entity.Permission;
import com.libang.erp.entity.Role;
import com.libang.erp.service.EmployeeService;
import com.libang.erp.service.RolesPermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * @author libang
 * @date 2018/7/30 17:16
 */
public class MyRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(MyRealm.class);
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RolesPermissionService rolesPermissionService;
    /**
     * 判断角色 权限
     * @param principalCollection
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前对象
        Employee employee = (Employee) principalCollection.getPrimaryPrincipal();
        //获取当前对象所有的角色
        List<Role> roleList = rolesPermissionService.findListByEmployeeId(employee.getId());
        //获取当前登录对象拥有的权限
        List<Permission> permissionList = new ArrayList<>();
        for(Role role : roleList){
            List<Permission> permissions = rolesPermissionService.findPermissionByRoleId(role.getId());
            permissionList.addAll(permissions);
        }
        //封装roleCode的Set集合
        Set<String> roleCodeSet = new HashSet<>();
        for(Role role : roleList){
            roleCodeSet.add(role.getRoleCode());
        }
        //封装permissionCode的set 集合
        Set<String> permissionCodeSet = new HashSet<>();
        for(Permission per : permissionList){
            permissionCodeSet.add(per.getPermissionCode());
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo  = new SimpleAuthorizationInfo();
        //当前用户拥有的角色（code）
        simpleAuthorizationInfo.setRoles(roleCodeSet);
        //当前用户拥有得我权限
        simpleAuthorizationInfo.setStringPermissions(permissionCodeSet);
        return simpleAuthorizationInfo;
    }






    /**
     * 判断登录，认证
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
