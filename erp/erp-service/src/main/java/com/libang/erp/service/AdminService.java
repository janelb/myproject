package com.libang.erp.service;


import com.libang.erp.entity.Admin;

/**
 * @author libang
 * @date 2018/7/25 12:50
 */
public interface AdminService {


    /**
     *
     * 管理员登录
     * @param adminName
     * @param password
     * @param loginIp
     * @return
     */
    Admin findByNameAndPassword(String adminName, String password, String loginIp);
}
