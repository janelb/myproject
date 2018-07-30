package com.libang.erp.service.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.libang.erp.entity.*;
import com.libang.erp.exception.ServiceException;
import com.libang.erp.mapper.PermissionMapper;
import com.libang.erp.mapper.RoleMapper;
import com.libang.erp.mapper.RolePermissionMapper;
import com.libang.erp.service.RolesPermissionService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author libang
 * @date 2018/7/27 14:29
 */
@Service
public class RolesPermissionServiceImpl implements RolesPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;




    /**
     * 新增权限
     *
     * @param permission
     */
    @Override
    public void savePermission(Permission permission) {
            permissionMapper.insertSelective(permission);
    }

    /**
     * 查询所有权限列表
     *
     * @return
     */
    @Override
    public List<Permission> findAll() {
        PermissionExample permissionExample = new PermissionExample();
       List<Permission> permissionList =  permissionMapper.selectByExample(permissionExample);

        List<Permission> endList =new ArrayList<>();
        treeList(permissionList,endList,0);
        return endList;
    }

    /**
     *将查询数据库的角色列表转换为树形集合
     * @param souerceList 数据库查询的集合
     * @param endList 转换结束的结果集合
     * @param parentId 父Id
     */

    private void treeList(List<Permission> souerceList, List<Permission> endList, int parentId) {
        /*使用递归方式进行删除*/
        /*lamda表达式*/
        List<Permission> tempList  = Lists.newArrayList(Collections2.filter(souerceList, permission -> permission.getPid().equals(parentId)));
        for(Permission permission : tempList){
            endList.add(permission);
            treeList(souerceList,endList,permission.getId());

        }

    }



    /**
     * 根据菜单类型查询所有权限列表
     *
     * @param permissionTypeMenu
     * @return
     */
    @Override
    public List<Permission> findPermissionListByType(String permissionTypeMenu) {

        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andPermissionTypeEqualTo(permissionTypeMenu);
        return   permissionMapper.selectByExample(permissionExample);

    }

    /**
     * 新增角色
     * 并新增关联关系表
     *
     * @param role
     * @param permissionIds
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveRole(Role role, Integer[] permissionIds) {
        roleMapper.insertSelective(role);
        for(Integer permissionId : permissionIds){
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(permissionId);

            rolePermissionMapper.insertSelective(rolePermission);

        }

    }



    /**
     * 查询角色列表，和权限列表
     *
     * @return
     */
    @Override
    public List<Role> findRoleWithPermission() {
        List<Role> roleList = roleMapper.findListWithPermission();
        return roleList;
    }

    /*=======================删除权限===========================*/
    /**
     * 根据角色id进行删除
     * 根据id删除权限
     *
     * @param id
     */
    @Override
    public void delPermission(Integer id)throws ServiceException {
        /*1.如果是父权限，如果有子权限则不能删除*/
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andPidEqualTo(id);
        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        if(permissionList !=null && permissionList.size()>0){

            throw new ServiceException("该权限下有子权限不能被删除");
        }
        /*2，如果权限被暂用不能删除*/
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.createCriteria().andPermissionIdEqualTo(id);
        List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(rolePermissionExample);
            if(rolePermissionList!=null && rolePermissionList.size()>0){

                throw new ServiceException("该权限正在被使用，不能被删除");

            }

          permissionMapper.deleteByPrimaryKey(id);
    }


    /*============================修改权限===========================*/
    /**
     * 根据perMissin的ID进行查找
     *
     * @param id
     * @return
     */
    @Override
    public Permission findByPermissionId(Integer id) {

        return permissionMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据id修改permission
     *
     * @param permission
     */
    @Override
    public void permissionEdit(Permission permission) {
        permission.setUpdateTime(new Date());
        permissionMapper.updateByPrimaryKeySelective(permission);
    }





    /*=======================删除角色==============================*/

    /**
     * 根据角色Id进删除
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delRoles(Integer id) throws ServiceException {
        /*1.该角色是否被引用，如果被引用不能删除*/


        /*1.该角色有有权限则不能删除*/
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.createCriteria().andRoleIdEqualTo(id);
      rolePermissionMapper.selectByExample(rolePermissionExample);
      Role role = roleMapper.selectByPrimaryKey(id);
      /*删除角色*/
       if(role!=null) {
           roleMapper.deleteByPrimaryKey(id);
       }else{

           throw new ServiceException("该角色不存在");
       }

    }





/*=============================================*/




        /*TODO*/

    /**
     * 根据id查找角色附带角色的权限列表
     *
     * @param id
     * @return
     */
    @Override
    public Role findByRoleId(Integer id) {

        return roleMapper.findByIdWithPermission(id);
    }






    /**
     * 根据roleId查找RolePermission
     *
     * @param id
     * @return
     */
    @Override
    public List<RolePermission> findListByPermissionId(Integer id) {
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.createCriteria().andRoleIdEqualTo(id);
        return rolePermissionMapper.selectByExample(rolePermissionExample);
    }



    /*====================修改角色============================*/

    /**
     * 在编辑页面判断当前权限的复选框是否被选中
     * @param permissionList 当前角色拥有的权限
     * @return 有顺序的map集合，如果被选中返回true
     */
    @Override
    public Map<Permission, Boolean> permissionBooleanMap(List<Permission> permissionList) {
        /*获取所有权限列表*/
        List<Permission> permissionLists = findAll();
        /*获取有序的map集合*/
        Map<Permission,Boolean> resultMap  = Maps.newLinkedHashMap();

        for(Permission permission : permissionLists){
            System.out.println(permission.getId());
            boolean flag = false;
            for(Permission rolePermission : permissionList){

                System.out.println(rolePermission.getId());

                if(permission.getId().equals(rolePermission.getId())){
                    flag=true;

                    break;
                }
            }
            resultMap.put(permission,flag);
        }

        return resultMap;
    }

    /**
     * 修改角色
     * @param role
     * @param permissionIds
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void roleEdit(Role role, Integer[] permissionIds) {
        /*1.删除原有的权限的对应关系*/
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.createCriteria().andRoleIdEqualTo(role.getId());
        rolePermissionMapper.deleteByExample(rolePermissionExample);

        /*2.添加现有的权限对应关系*/

        for(Integer permissionId : permissionIds){
            RolePermission rolePermission  =new RolePermission();
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(permissionId);
            rolePermissionMapper.insert(rolePermission);
        }
        role.setUpdateTime(new Date());
        /*3.修改角色对象*/
        roleMapper.updateByPrimaryKeySelective(role);

    }



    /**
     * 获取所有角色列表
     *
     * @return
     */
    @Override
    public List<Role> findAllRoles() {
        RoleExample roleExample = new RoleExample();
        return  roleMapper.selectByExample(roleExample);
    }





}