package com.libang.service;

import com.libang.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;

/**
 * @author libang
 * @date 2018/7/16 19:10
 */


@Service("userService")
/*@Scope("prototype")
@Lazy*/
public class UserService {

    /*如果是setter方法注入，setter方法可以省略，在属性上添加注解*/
 /*   @Autowired*/
    private UserDao userDao;

    /*构造方法注入*/
    @Autowired
     public  UserService(UserDao userDao){
         this.userDao =userDao;
    }

    public  void save(){
          /*  int n =10/0;*/
        userDao.save();
    }






    /*setter方法注入*/
  /* @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/
}
