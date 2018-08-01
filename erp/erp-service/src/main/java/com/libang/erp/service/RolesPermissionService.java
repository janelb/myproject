package com.libang.erp.service;

import com.libang.erp.entity.Permission;
import com.libang.erp.entity.Role;
import com.libang.erp.entity.RolePermission;
import com.libang.erp.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/7/27 14:29
 */
public interface RolesPermissionService {
    /**
     * 新增权限
     * @param permission
     */
    void savePermission(Permission permission);

    /**
     * 查询所有权限列表
     * @return
     */
    List<Permission> findAll();

    /**
     * 根据菜单类型查询所有权限列表
     * @param permissionTypeMenu
     * @return
     */
    List<Permission> findPermissionListByType(String permissionTypeMenu);

    /**
     * 新增角色
     * 并新增关联关系表
     * @param role
     * @param permissionIds
     */
    void saveRole(Role role, Integer[] permissionIds);

    /**
     * 查询角色列表，和权限列表
     * @return
     */
    List<Role> findRoleWithPermission();

    /**
     * 根据权限id进行删除
     * @param id
     */
    void delPermission(Integer id)throws ServiceException;

    /**
     * 根据角色Id进删除
     * @param id
     */
    void delRoles(Integer id)throws ServiceException;


    /**
     * 根据perMissin的ID进行查找
     * @param id
     * @return
     */
    Permission findByPermissionId(Integer id);

    /**
     * 根据id修改permission
     * @param permission
     */
    void permissionEdit(Permission permission);

    /**
     * 根据角色Idj进行查找
     * @param id
     * @return
     */
    Role findByRoleId(Integer id);




    /**
     * 根据roleId查找RolePermission
     * @param id
     * @return
     */
    List<RolePermission> findListByPermissionId(Integer id);



    /**
     *
     * @param permissionList
     * @return
     */
    Map<Permission,Boolean> permissionBooleanMap(List<Permission> permissionList);

    /**
     * 修改角色
     * @param role
     * @param permissionIds
     */
    void roleEdit(Role role, Integer[] permissionIds);

    /**
     * 获取所有角色列表
     * @return
     */
    List<Role> findAllRoles();

    /**
     * 通过employeeId查找对应的role
     * @param id
     * @return
     */
    List<Role> findListByEmployeeId(Integer id);

    /**
     * 通过roleId查找对应的peimission
     * @param id
     * @return
     */
    List<Permission> findPermissionByRoleId(Integer id);
}
