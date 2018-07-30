package com.libang.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author libang
 * @date 2018/7/30 21:36
 */
public class MyShiro implements Realm {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String userName = usernamePasswordToken.getUsername();
        String password =new  String(usernamePasswordToken.getPassword());
        if(userName.equals("admin")){
            throw new UnknownAccountException("没有改账户");
        }
        if(password.equals("123")){
            throw new IncorrectCredentialsException("密码错误");
        }
        return new SimpleAuthenticationInfo(userName,password,getName());
    }
}
