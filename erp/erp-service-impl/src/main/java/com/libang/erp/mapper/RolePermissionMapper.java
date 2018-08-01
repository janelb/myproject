package com.libang.erp.mapper;


import com.libang.erp.entity.EmployeeRole;
import com.libang.erp.entity.RolePermission;
import com.libang.erp.entity.RolePermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {
    long countByExample(RolePermissionExample example);

    int deleteByExample(RolePermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    List<RolePermission> selectByExample(RolePermissionExample example);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    int updateByExample(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);


    RolePermission findPermissionByRoleId(Integer roleId);

    void deleteByRoleId(Integer roleId);

    List<RolePermission> findPermissionListByRoleId(Integer roleId);
}