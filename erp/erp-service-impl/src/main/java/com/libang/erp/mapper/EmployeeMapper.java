package com.libang.erp.mapper;


import com.libang.erp.entity.Employee;
import com.libang.erp.entity.EmployeeExample;
import com.libang.erp.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    /**
     * 带有条件的查询
     * @param requestMap
     * @return
     */
    List<Employee> findAllAccountWithRolesByQueryParam(Map<String,Object> requestMap);

    /**
     *根据员工Idc查找员工对应角色
     * @param id
     * @return
     */
    List<Role> findByRoleList(Integer id);

    /* List<Employee> findAllWithRole();*/
}