package com.libang.erp.service.impl;


import com.libang.erp.entity.Admin;
import com.libang.erp.mapper.AdminMapper;
import com.libang.erp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author libang
 * @date 2018/7/25 12:51
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;


    /**
     * 管理员登录
     *
     * @param adminName
     * @param password
     * @param loginIp
     * @return
     */
    @Override
    public Admin findByNameAndPassword(String adminName, String password, String loginIp) {

        Admin admin  = adminMapper.findByAdminName(adminName);

        return admin;
    }
}
