package com.libang.service;

import com.libang.entity.Admin;

/**
 * @author libang
 * @date 2018/7/25 12:50
 */
public interface AdminService {


    /**
     * 通过用户名和密码查找用户
     * @param adminName

     * @return
     */
    Admin findByNameAndPassword(String adminName);
}
