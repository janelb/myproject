package com.libang.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


/**
 * @author libang
 * @date 2018/7/30 11:02
 */
public class QuickStart {
        private static  Logger logger = LoggerFactory.getLogger(QuickStart.class);

    public static void main(String[] args) {

        //1.通过IniSecurityManagerFactory来获取，SecurityManager对象
        Factory<SecurityManager> factory  = new IniSecurityManagerFactory("classpath:apache-shiro.ini");
        SecurityManager securityManager = factory.getInstance();

        //2.设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        //3.通过SecurityUtils的getSubject（），来获取subject对象（主体账号）
        Subject subject = SecurityUtils.getSubject();

            //判断当前账号是否登录是否被认证（返回的是一个boolean类型）
            System.out.println(subject.isAuthenticated());


        //4.判断当前有没有登录
        if(!subject.isAuthenticated()){
            //如果没有的登录根据账号密码登录
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin","123");

            try {
                subject.login(usernamePasswordToken );
                System.out.println("isAuthenticated:"+subject.isAuthenticated());


            //获取当前登录账号的用户名
                subject.getPrincipal();
                logger.info("{} login success",subject.getPrincipal());
                //通过hasRole("角色名称")，判断当前主题是否具有某个角色
                logger.info("hashRole {}:{}","admin",subject.hasRole("superAdmin"));
                logger.info("hashRole {}:{}","admin",subject.hasRole("tom"));

              /*  //通过checkRole(),判断当前主体是否具有该权限，如果不具有该权限抛出异常
                subject.checkRole("admin");*/

                //通过hasRoles（）判断当期主体，对多个角色的拥有权
              boolean[] results = subject.hasRoles(Arrays.asList("admin","user","superAdmin"));
              for(Boolean result : results){
                  logger.info("result:{}",result);
              }
                //判断当前登录对象是否拥有所有角色
                logger.info("result All:{} ",subject.hasAllRoles(Arrays.asList("admin")));

              //通过isPermitted()方法判断当前对象是否具有对应的权限
              logger.info("customer: add --> {}",subject.isPermitted("customer:delete"));
              logger.info("customer : all -->{}",subject.isPermitted("customer:add","customer:query"));

              //判断是否具有该权限，没有抛出异常
              subject.checkPermission("customer:add");

              //获取session,大多数情况下可以替代HttpSession
              Session session = subject.getSession();
              //存值
                session.setAttribute("userName",subject.getPrincipal());
               //取值
               session.getAttribute("userName");


                //安全退出
                subject.logout();
                
            } catch (UnknownAccountException ex){
                    ex.printStackTrace();
                System.out.println("未找到该账号");
            }catch (IncorrectCredentialsException ex){
                System.out.println("密码异常");

            }catch (LockedAccountException ex){
                System.out.println("账号冻结异常");

            }catch (AuthenticationException ex) {

                System.out.println("认证异常");
            }

        }
    }



}
