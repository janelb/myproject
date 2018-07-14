package com.libang.service;

import com.libang.dao.UserDao;

/**
 * @author libang
 * @date 2018/7/14 10:46
 */
public class UserService {

    private UserDao userDao;

    public void save(){
        userDao.save();
    }


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


}

