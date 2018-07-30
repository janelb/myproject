package com.libang.service.impl;

import com.libang.entity.Admin;
import com.libang.mapper.AdminMapper;
import com.libang.service.AdminService;
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
     * 通过用户名和密码查找用户
     *
     * @param adminName

     * @return
     */
    @Override
    public Admin findByNameAndPassword(String adminName) {

        Admin admin =adminMapper.findByNameAndPassword(adminName);

        return admin;
    }
}
