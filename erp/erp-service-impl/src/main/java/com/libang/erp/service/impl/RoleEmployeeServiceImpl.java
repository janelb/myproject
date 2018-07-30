package com.libang.erp.service.impl;

import com.libang.erp.entity.Role;
import com.libang.erp.mapper.EmployeeRoleMapper;
import com.libang.erp.mapper.RoleMapper;
import com.libang.erp.service.RoleEmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/28 15:11
 */
@Service
public class RoleEmployeeServiceImpl implements RoleEmployeeService {

    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;
    @Autowired
    private RoleMapper roleMapper;


    /**
     * 根据员工Id进行查找对应角色
     * @param id
     * @return
     */
    @Override
    public List<Role> findRoleWithEmployeeById(Integer id) {

        List<Role> roleList = roleMapper.findListByEmployeeId(id);


        return roleList;
    }
}
