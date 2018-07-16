package com.libang.dao;

import org.springframework.stereotype.Repository;

/**
 * @author libang
 * @date 2018/7/16 17:47
 */

@Repository("userDao")
public class UserDao {

    public void save(){
        System.out.println("已保存");
    }


}
