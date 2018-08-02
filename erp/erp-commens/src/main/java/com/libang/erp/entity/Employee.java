package com.libang.erp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 
 */
public class Employee implements Serializable {

    /**
     * 账户状态 1：冻结 2：正常
     */
    public static Integer EMPLOYEE_STATE_FROZEN = 1;
    public static String EMPLOYEE_FROZEN = "冻结";

    public static Integer EMPLOYEE_STATE_NORMAL = 2;
    public static String EMPLOYEE_NORMAL = "正常";





    /**
     * 员工ID
     */
    private Integer id;

    /**
     * 员工姓名
     */
    private String employeeName;

    /**
     * 角色ID
     */
    private Integer roleId;

    private String tell;

    /**
     * 密码
     */
    private String password;

    /**
     * 权限ID
     */
    private Integer permissionId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态：1.禁用 2启用
     */
    private Integer state;

    private List<Role> roleList;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", roleId=" + roleId +
                ", tell='" + tell + '\'' +
                ", password='" + password + '\'' +
                ", permissionId=" + permissionId +
                ", createTime=" + createTime +
                ", state=" + state +
                ", roleList=" + roleList +
                '}';
    }
}