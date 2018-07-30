package com.libang.erp.service;

import com.libang.erp.entity.Role;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/28 15:12
 */
public interface RoleEmployeeService {
    /**
     * 根据员工Id进行查找对应角色
     * @param id
     * @return
     */
    List<Role> findRoleWithEmployeeById(Integer id);
}
