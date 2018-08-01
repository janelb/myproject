package com.libang.erp.mapper;


import com.libang.erp.entity.Permission;
import com.libang.erp.entity.Role;
import com.libang.erp.entity.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);


    /**
     * @return
     * 查询角色列表，和权限列表
     */
    List<Role> findListWithPermission();

    List<Role> findListByEmployeeId(Integer id);

    Role findByIdWithPermission(Integer id);

    List<Permission> findListByRoleId(Integer id);
}